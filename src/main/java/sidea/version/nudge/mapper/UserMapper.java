package sidea.version.nudge.mapper;

import org.apache.ibatis.annotations.Mapper;
import sidea.version.nudge.dto.UserDto;

@Mapper
public interface UserMapper {

    //정보수정
    public int modifyUser(UserDto userDto) throws Exception;

    //회원탈퇴
    public int deleteUser(UserDto userDto) throws Exception;

    //닉네임 중복 확인
    public UserDto nicknameDuplCheck(String userNickname) throws Exception;

    //비밀번호 확인
    public UserDto selectUserPw(UserDto userDto) throws Exception;

}
