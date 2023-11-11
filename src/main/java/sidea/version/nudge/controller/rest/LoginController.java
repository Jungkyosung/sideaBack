package sidea.version.nudge.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sidea.version.nudge.dto.UserDto;
import sidea.version.nudge.service.LoginService;

import java.time.Duration;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/api")
public class LoginController {


    @Autowired
    private LoginService loginService;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@Validated  @RequestBody UserDto userDto) throws Exception{

        EmailController.EmailVerifyCode codeInfo = EmailController.keyStorage.get(userDto.getUserEmail());
        LocalDateTime now = LocalDateTime.now();
        Duration difTime = Duration.between(now, codeInfo.getTime());
        log.info("시간차이={}",difTime.toMinutes());
        if (userDto.getRegisterCode().equals(codeInfo.getCode()) && difTime.toMinutes() < 30){
            if(userDto.getUserLeave() == null) {
                userDto.setUserLeave("N");
            }

            int registeredCnt = loginService.registerUser(userDto);

            if( registeredCnt > 0) {
                return ResponseEntity.status(HttpStatus.CREATED).body(registeredCnt);
            } else {
                return ResponseEntity.status(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE).body(registeredCnt);
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("인증코드를 다시 확인해주세요.");
        }


    }

}
