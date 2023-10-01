package sidea.version.nudge.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import sidea.version.nudge.email.EmailMessage;

import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final UserService userService;
    public String sendMail(EmailMessage emailMessage, String type) throws Exception{
        String authNum = createCode();

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        if (type.equals("password")) userService.setTempPassword(emailMessage.getTo(), authNum);

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            mimeMessageHelper.setTo(emailMessage.getTo()); //메일 수신자
            mimeMessageHelper.setSubject(emailMessage.getSubject()); //메일 제목
            mimeMessageHelper.setText(authNum, false);  //메일 내용, html 여부
            javaMailSender.send(mimeMessage);   //메일 전송
            log.info("success");
            return authNum;
        } catch (MessagingException e) {
            log.info("fail");
            throw new RuntimeException(e);
        }

    }

    public String createCode(){
        Random random = new Random();
        StringBuffer key = new StringBuffer();

        for (int i = 0; i < 8; i++) {
            int index  = random.nextInt(4);

            switch (index) {
                case 0: key.append((char) ((int) random.nextInt(26) + 97)); break;
                case 1: key.append((char) ((int) random.nextInt(26) + 65)); break;
                default: key.append(random.nextInt(9));
            }
        }
        return key.toString();
    }
}
