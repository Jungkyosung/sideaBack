package sidea.version.nudge.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserDto {

    //검증 처리 필요 NotNull인 것들 확인 필요.

    private long userIdx;
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String userEmail;
    @Length(min = 10, message = "비밀번호는 최소 10글자를 입력해주세요.")
    private String userPw;
    @NotBlank(message = "빈 문자열은 입력할 수 없습니다.")
    private String userNickname;
    private String userImage;
    private double userPointBalance;
    private double userDonationBalance;
    private String userLeave;
    private int userAuth;
    private String registerCode;    //회원가입시 코드열

}
