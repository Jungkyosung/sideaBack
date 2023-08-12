package sidea.version.nudge.dto;

import lombok.Data;

@Data
public class UserDto {

    //검증 처리 필요 NotNull인 것들 확인 필요.

    private long userIdx;
    private String userEmail;
    private String userPw;
    private String userNickname;
    private String userImage;
    private double userPointBalance;
    private double userDonationBalance;
    private String userLeave;

}
