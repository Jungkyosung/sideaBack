package sidea.version.nudge.mapper;

import org.apache.ibatis.annotations.Mapper;
import sidea.version.nudge.dto.UserDto;

@Mapper
public interface UserMapper {


    public int modifyUser(UserDto userDto) throws Exception;

    public int deleteUser(UserDto userDto) throws Exception;

}
