package sidea.version.nudge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sidea.version.nudge.dto.PointDto;
import sidea.version.nudge.dto.UserDto;
import sidea.version.nudge.mapper.PointMapper;

import java.util.List;

@Service
public class PointService {

    @Autowired
    private PointMapper pointMapper;

    //포인트 내역 조회
    public List<PointDto> getPointList(long userIdx) throws Exception{
        return pointMapper.getPointList(userIdx);
    }

    //포인트 충전
    public int chargePoint(PointDto pointDto) throws Exception{
        return pointMapper.chargePoint(pointDto);
    }

    //포인트 기부


    //포인트 획득

    //회원 현재 포인트잔액 조회
    public int getPointBalance(long userIdx) throws Exception{
        return pointMapper.getPointBalance(userIdx);
    }

    //회원 현재 포인트 잔액 수정
    public int updatePointBalance(UserDto userDto) throws Exception{
        return pointMapper.updatePointBalance(userDto);
    }


}