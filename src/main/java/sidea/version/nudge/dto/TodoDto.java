package sidea.version.nudge.dto;

import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
public class TodoDto {

    //SQL의 DateTime으로 Timestamp를 사용했는데 String과 비교해서 변경가능
    //SQL의 Date로 java.sql.Date를 사용했는데 String과 비교해서 변경가능

    private long todoIdx;
    private long userIdx;
    private String todoContents;
    private Date todoDate;
    private int todoDone;               //완료여부
    private String todoDelete;          //삭제여부
    private Timestamp todoStartDate;
    private Timestamp todoEndDate;
    private Timestamp todoDoneDate;
    private int todoAlarm;              //알람여부
    private String userEmail;

    private int todoPoint;              //투두별 point 지정

}
