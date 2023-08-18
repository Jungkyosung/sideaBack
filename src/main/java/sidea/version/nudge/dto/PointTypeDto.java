package sidea.version.nudge.dto;

import lombok.Data;

@Data
public class PointTypeDto {

    private long pointTypeIdx;
    private String pointTypeName;
    public enum PointType{
        기부, 획득, 충전
    }
}
