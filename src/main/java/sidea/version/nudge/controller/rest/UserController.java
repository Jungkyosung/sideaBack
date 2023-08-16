package sidea.version.nudge.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sidea.version.nudge.dto.UserDto;
import sidea.version.nudge.service.UserService;

@Slf4j
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

    //닉네임 중복 확인
    @GetMapping("/usernickname/duplicate")
    public ResponseEntity<String> nicknameDuplCheck(@RequestParam(value = "usernickname") String userNickname) throws Exception{

        UserDto selectedUser = userService.nicknameDuplCheck(userNickname);

        if( selectedUser == null){
            return ResponseEntity.status(HttpStatus.OK).body("중복 닉네임 없음");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("중복 닉네임 있음");
        }
    }

    //비밀번호 확인
    @GetMapping("/userpw")
    public ResponseEntity<String> selectUserPw(@RequestBody UserDto userDto) throws Exception{

        boolean selectedUser = userService.selectUserPw(userDto);

        if( selectedUser){
            return ResponseEntity.status(HttpStatus.OK).body("비밀번호 일치");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("비밀번호 불일치");
        }
    }
}
