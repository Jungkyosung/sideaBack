package sidea.version.nudge.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sidea.version.nudge.dto.UserDto;
import sidea.version.nudge.mapper.UserMapper;

@Slf4j
@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    //회원정보조회
    public UserDto getUser(String userEmail) throws Exception{
        return userMapper.getUser(userEmail);
    }


    //정보수정
    public int modifyUser(UserDto userDto) throws Exception{
        userDto.setUserPw(passwordEncoder.encode(userDto.getUserPw()));
        return userMapper.modifyUser(userDto);
    }

    //회원탈퇴
    public int deleteUser(UserDto userDto) throws Exception{
        return userMapper.deleteUser(userDto);
    }

    //닉네임 중복 확인
    public UserDto nicknameDuplCheck(String userNickname) throws Exception{
        return userMapper.nicknameDuplCheck(userNickname);
    }

    //이메일 중복 확인
    public UserDto emailDuplCheck(String userEmail) throws Exception{
        return userMapper.emailDuplCheck(userEmail);
    }

    //비밀번호 확인
    public boolean selectUserPw(String userEmail, String userPw) throws Exception{

        UserDto selectedUser = userMapper.getUser(userEmail);
        String selectedUserPw = selectedUser.getUserPw();

        return passwordEncoder.matches(userPw, selectedUserPw);
    }

    public int setTempPassword(String userEmail, String userPw) throws Exception{
        userPw = passwordEncoder.encode(userPw);
        return userMapper.setTempPassword(userEmail, userPw);
    }
}
