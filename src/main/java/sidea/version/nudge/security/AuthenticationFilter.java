package sidea.version.nudge.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import sidea.version.nudge.dto.UserDto;
import sidea.version.nudge.mapper.LoginMapper;

import java.io.IOException;
import java.util.ArrayList;

@Slf4j
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private LoginMapper loginMapper;
    private Environment env;
    private JwtTokenUtil jwtTokenUtil;

    public AuthenticationFilter(LoginMapper loginMapper, Environment env, JwtTokenUtil jwtTokenUtil) {
        this.loginMapper = loginMapper;
        this.env = env;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        try {
            UserDto creds = new ObjectMapper().readValue(request.getInputStream(), UserDto.class);
            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUserEmail(),
                            creds.getUserPw(),
                            new ArrayList<>()
                    )
            );
        } catch ( Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult)
            throws IOException, ServletException {

        String username = ((User)authResult.getPrincipal()).getUsername();
        UserDto userDto = loginMapper.selectUserByUserEmail(username);

        String jwtToken = jwtTokenUtil.generateToken(userDto);
        response.setHeader("token", jwtToken);
        response.getWriter().write(jwtToken);           //헤더에 설정했는데 바디에도 추가하는 건가?
    }
}
