package sidea.version.nudge.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class DonationInfoDto {

    //기부정보
    private long donationIdx;
    private long donorIdx;
    private double donationAmount;       //기부액
    private double donationTargetAmount; //목표기부액
    private Timestamp donationDate;      //기부날짜
    private Timestamp donationDuration;  //기부마감
    private String donationDelete;       //삭제여부
    private String donationName;
    private String donationImage;        //이미지
    //기부자 정보
    private String donorName;
    private String donorImage;
}
