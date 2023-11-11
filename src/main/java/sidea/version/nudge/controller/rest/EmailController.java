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

import java.net.URLDecoder;
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

    @PostMapping("/code")
    public ResponseEntity<Object> sendRegisterCodeMail(@RequestBody String email) throws Exception{
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

    @PostMapping("/password")
    public ResponseEntity<Object> sendPasswordMail(@RequestBody String email) throws Exception{
        EmailMessage emailMessage = EmailMessage.builder()
                .to(email)
                .subject("임시비번발급")
                .build();

        //로직이 조금 문제가 있는 듯. 실제론 메일에 이 URL을 보내야 할 듯. 그래야 해당 이메일 보유자만 비번을 바꿀 수 있으니까.
        //만약 이메일 주소만 입력하고 이 요청을 할 수 있다면, 다른 사람 이메일도 내가 비번을 임시비번으로 바꿀 수 있으니까 문제가 됨.
        //일단 임시 비번으로 바뀌는지 먼저 테스트 해봐야겠음.
        String code = emailService.sendMail(emailMessage, "password");

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
