package sidea.version.nudge.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class PointDto {

    //SQL의 DateTime으로 Timestamp를 사용했는데 String과 비교해서 변경가능

    private long pointIdx;
    private long userIdx;
    private long pointTypeIdx;          //포인트 타입
    private double pointScore;          //사용포인트
    private Timestamp pointDate;        //포인트사용시간
}
