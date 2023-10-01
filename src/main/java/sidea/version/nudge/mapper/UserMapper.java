package sidea.version.nudge.mapper;

import org.apache.ibatis.annotations.Mapper;
import sidea.version.nudge.dto.UserDto;

import java.util.List;

@Mapper
public interface UserMapper {

    //회원정보 조회
    public UserDto getUser(String userEmail) throws Exception;

    //정보수정
    public int modifyUser(UserDto userDto) throws Exception;

    //회원탈퇴
    public int deleteUser(UserDto userDto) throws Exception;

    //닉네임 중복 확인
    public UserDto nicknameDuplCheck(String userNickname) throws Exception;

    //비밀번호 확인
    public UserDto selectUserPw(UserDto userDto) throws Exception;

    //탈퇴하지 않은 회원 전체 조회
    public List<UserDto> findNotLeaveUser() throws Exception;

    //임시 비번 부여
    public int setTempPassword(String userEmail, String userPw) throws Exception;


}
