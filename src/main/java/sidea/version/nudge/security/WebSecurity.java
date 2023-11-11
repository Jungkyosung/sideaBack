package sidea.version.nudge.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import sidea.version.nudge.mapper.LoginMapper;
import sidea.version.nudge.service.LoginService;

@Configuration
@EnableWebSecurity
public class WebSecurity {

    private LoginService loginService;
    private LoginMapper loginMapper;
    private BCryptPasswordEncoder passwordEncoder;
    private Environment env;
    private JwtTokenUtil jwtTokenUtil;
    private JwtRequestFilter jwtRequestFilter;
    private final ObjectPostProcessor<Object> objectPostProcessor;

    public WebSecurity(LoginService loginService, LoginMapper loginMapper,
                       BCryptPasswordEncoder passwordEncoder, Environment env,
                       JwtTokenUtil jwtTokenUtil, JwtRequestFilter jwtRequestFilter,
                       ObjectPostProcessor<Object> objectPostProcessor) {
        this.loginService = loginService;
        this.loginMapper = loginMapper;
        this.passwordEncoder = passwordEncoder;
        this.env = env;
        this.jwtTokenUtil = jwtTokenUtil;
        this.jwtRequestFilter = jwtRequestFilter;
        this.objectPostProcessor = objectPostProcessor;
    }

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer(){
//        return (web)-> web.ignoring().requestMatchers("/login");
//    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationManagerBuilder auth)
    throws Exception{
        auth.userDetailsService(loginService).passwordEncoder(passwordEncoder);
        return auth.build();
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.cors().and().csrf().disable();
        http.authorizeRequests()
                .requestMatchers("/**", "/login", "/register", "/hello", "/api/email/code", "/api/email/password").permitAll() //로그인 없이 허용할 주소 설정.
                .anyRequest().authenticated()					//허용 외의 다른 요청들은 인증실행한다.
                .and().addFilter(getAuthenticationFilter())		//필터를 추가한다.(콜백으로 인증필터 실행)
                .addFilterBefore(jwtRequestFilter, AuthenticationFilter.class);	//필터수행전에.

        return http.build();
    }

    private AuthenticationFilter getAuthenticationFilter() throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(loginMapper, env, jwtTokenUtil);
        AuthenticationManagerBuilder builder = new AuthenticationManagerBuilder(objectPostProcessor);
        authenticationFilter.setAuthenticationManager(authenticationManager(builder));
        return authenticationFilter;
    }

}
