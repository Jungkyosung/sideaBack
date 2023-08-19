package sidea.version.nudge.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class AskDto {

    //SQL의 DateTime으로 Timestamp를 사용했는데 String과 비교해서 변경가능

    private long askIdx;
    private long userIdx;
    private String askTitle;
    private String askContents;
    private Timestamp askDate;
    private String askAnswer;
    private Timestamp askAnswerDate;

    //문의 삭제 컬럼 추가필요

}
