package sidea.version.nudge.mapper;

import org.apache.ibatis.annotations.Mapper;
import sidea.version.nudge.dto.PointDto;
import sidea.version.nudge.dto.UserDto;

import java.util.List;

@Mapper
public interface PointMapper {

    //포인트 내역 조회
    public List<PointDto> getPointList(long userIdx) throws Exception;

    //포인트 충전
    public int chargePoint(PointDto pointDto) throws Exception;

    //포인트 기부
    public int donatePoint(PointDto pointDto) throws Exception;

    //포인트 획득
    public int earnPoint(PointDto pointDto) throws Exception;


    //회원 현재 포인트 조회
    public int getPointBalance(long userIdx) throws Exception;

    //회원 포인트 잔액 수정
    public int updatePointBalance(UserDto userDto) throws Exception;

}
