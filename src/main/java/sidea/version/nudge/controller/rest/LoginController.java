package sidea.version.nudge.controller.rest;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sidea.version.nudge.dto.UserDto;
import sidea.version.nudge.service.LoginService;

import java.util.Random;

@RestController
@RequestMapping("/api")
public class LoginController {


    @Autowired
    private LoginService loginService;

    @PostMapping("/regist")
    public ResponseEntity<Object> regist(@Validated  @RequestBody UserDto userDto) throws Exception{

        if(userDto.getUserLeave() == null) {
            userDto.setUserLeave("N");
        }

        int registedCnt = loginService.registUser(userDto);

        if( registedCnt > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body(registedCnt);
        } else {
            return ResponseEntity.status(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE).body(registedCnt);
        }
    }

}
