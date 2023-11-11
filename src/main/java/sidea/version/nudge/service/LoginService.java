package sidea.version.nudge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import sidea.version.nudge.dto.UserDto;
import sidea.version.nudge.mapper.LoginMapper;

import java.util.ArrayList;

@Service
public class LoginService implements UserDetailsService {

    @Autowired
    private LoginMapper loginMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public UserDto login(UserDto userDto) throws Exception{
        return loginMapper.login(userDto);
    }


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto userDto = loginMapper.selectUserByUserEmail(username);
        //가져온 userDto내용이 없다면, 에러발생
        if ( userDto == null ) {
            throw new UsernameNotFoundException(username);
        }
        //있다면, 유저의 Id와 Pw를 기준으로 User객체 생성
        //User객체는 UserDetails 인터페이스를 상속.
        return new User(userDto.getUserEmail(), userDto.getUserPw(),
                true, true, true, true, new ArrayList<>());
    }

    public UserDto selectUserByUserId(String userId) throws Exception {
        return loginMapper.selectUserByUserEmail(userId);
    }

    public int registerUser(UserDto userDto) throws Exception {
        userDto.setUserImage("profile" + ((int)(Math.random()*3)+1) + ".png");
        userDto.setUserPw(passwordEncoder.encode(userDto.getUserPw()));
        return loginMapper.registerUser(userDto);
    }


}
