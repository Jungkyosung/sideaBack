package sidea.version.nudge.mapper;

import org.apache.ibatis.annotations.Mapper;
import sidea.version.nudge.dto.PointDto;

import java.util.List;

@Mapper
public interface PointMapper {

    //포인트 내역 조회
    public List<PointDto> getPointList(long userIdx) throws Exception;

    //포인트 충전
    public int chargePoint(PointDto pointDto) throws Exception;

}
