package sidea.version.nudge.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.sql.Date;
import java.sql.Timestamp;

@Data
public class TodoDoneDto {

    private long todoIdx;
    private long userIdx;
    @NotBlank(message = "내용에 빈 문자열은 입력할 수 없습니다.")
    private String todoContents;
    private String todoDate;
    private int todoDone;               //완료여부
    private String todoDelete;          //삭제여부

    private Date todoStartDate;

    private Date todoEndDate;
    private Timestamp todoDoneDate;
    private int todoAlarm;              //알람여부
    private String todoAlarmTime;
    private String userEmail;

    @Range(min = 0, max = 500, message = "0~500포인트만 지정할 수 있습니다.")
    private int todoPoint;              //투두별 point 지정

    //요일 컬럼
    @Range(min = 0, max = 1, message = "0과 1만 입력할 수 있습니다.")
    private int todoMon;
    @Range(min = 0, max = 1, message = "0과 1만 입력할 수 있습니다.")
    private int todoTue;
    @Range(min = 0, max = 1, message = "0과 1만 입력할 수 있습니다.")
    private int todoWed;
    @Range(min = 0, max = 1, message = "0과 1만 입력할 수 있습니다.")
    private int todoThu;
    @Range(min = 0, max = 1, message = "0과 1만 입력할 수 있습니다.")
    private int todoFri;
    @Range(min = 0, max = 1, message = "0과 1만 입력할 수 있습니다.")
    private int todoSat;
    @Range(min = 0, max = 1, message = "0과 1만 입력할 수 있습니다.")
    private int todoSun;

    //todo Done Table

    private int todoDoneIdx;
    @Range(min = 0, max = 1, message = "0과 1만 입력할 수 있습니다.")
    private int todoDoneCheck;
    private Timestamp todoDoneTime;
}
