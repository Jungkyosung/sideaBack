package sidea.version.nudge.dto;

import lombok.Data;

@Data
public class UserDto {

    private long userIdx;
    private String userEmail;
    private String userPw;
    private String userNickname;
    private String userImage;
    private double userPointBalance;
    private double userDonationBalance;
    private String userLeave;

}
