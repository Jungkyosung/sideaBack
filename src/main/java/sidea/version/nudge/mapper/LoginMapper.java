package sidea.version.nudge.mapper;

import org.apache.ibatis.annotations.Mapper;
import sidea.version.nudge.dto.UserDto;

@Mapper
public interface LoginMapper {

    public UserDto login(UserDto userDto) throws Exception;

    public UserDto selectUserByUserEmail(String userEmail);

    public int registerUser(UserDto userDto) throws Exception;


}
