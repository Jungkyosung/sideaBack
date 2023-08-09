package sidea.version.nudge.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class DonationDto {

    //SQL의 DateTime으로 Timestamp를 사용했는데 String과 비교해서 변경가능

    private long donationIdx;
    private long donorIdx;
    private double donationAmount;       //기부액
    private double donationTargetAmount; //목표기부액
    private Timestamp donationDate;      //기부날짜
    private Timestamp donationDuration;  //기부마감
    private String donationDelete;       //삭제여부

}
