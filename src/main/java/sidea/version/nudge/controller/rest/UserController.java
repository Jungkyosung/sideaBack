package sidea.version.nudge.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sidea.version.nudge.dto.UserDto;
import sidea.version.nudge.service.LoginService;
import sidea.version.nudge.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    //회원정보 변경
    @PutMapping("/user")
    public ResponseEntity<Object> userLeave(@RequestBody UserDto userDto) throws Exception{

        int leftCnt = userService.modifyUser(userDto);

        if( leftCnt > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body(leftCnt);
        } else {
            return ResponseEntity.status(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE).body(leftCnt);
        }
    }

    //회원탈퇴
    @PutMapping("/user/delete")
    public ResponseEntity<Object> deleteUser(@RequestBody UserDto userDto) throws Exception{

        int deletedCnt = userService.deleteUser(userDto);

        if( deletedCnt > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body(deletedCnt);
        } else {
            return ResponseEntity.status(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE).body(deletedCnt);
        }
    }

}
