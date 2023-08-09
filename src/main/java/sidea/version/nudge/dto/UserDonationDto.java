package sidea.version.nudge.dto;

import lombok.Data;

@Data
public class UserDonationDto {

    private long userDonationIdx;
    private long donationIdx;
    private long userIdx;
    private double userDonationPoint;

}
