package sidea.version.nudge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sidea.version.nudge.dto.UserDto;
import sidea.version.nudge.mapper.UserMapper;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public int modifyUser(UserDto userDto) throws Exception{
        return userMapper.modifyUser(userDto);
    }

    public int deleteUser(UserDto userDto) throws Exception{
        return userMapper.deleteUser(userDto);
    }
}
