package sidea.version.nudge.controller.rest;


import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sidea.version.nudge.email.EmailMessage;
import sidea.version.nudge.service.EmailService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/email")
public class EmailController {

    public static Map<String, EmailVerifyCode> keyStorage = new HashMap<>();

    @Autowired
    private EmailService emailService;

    @PostMapping("/regist/code")
    public ResponseEntity<Object> sendPasswordMail(@RequestBody String email) throws Exception{
        EmailMessage emailMessage = EmailMessage.builder()
                .to(email)
                .subject("코드발급")
                .build();

        String code = emailService.sendMail(emailMessage, "code");

        EmailVerifyCode codeInfo = new EmailVerifyCode();
        codeInfo.setCode(code);
        codeInfo.setTime(LocalDateTime.now());

        keyStorage.put(email, codeInfo);    //이메일별로 인증코드 비교함. 코드정보의 시간이랑 인증하는 시간 비교해서 늦으면 인증 불가

        EmailVerifyCode confirmCodeInfo = keyStorage.get(email);
        log.info("keyStorageSize={}",keyStorage.size());
        String confirmCode = confirmCodeInfo.getCode();
        LocalDateTime confirmTime = confirmCodeInfo.getTime();

        return ResponseEntity.status(HttpStatus.OK).body("메일발송" + confirmCode + confirmTime);
    }

    @Getter
    @Setter
    public static class EmailVerifyCode {

        private String code;
        private LocalDateTime time;

    }
}
