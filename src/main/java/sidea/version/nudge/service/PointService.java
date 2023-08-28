package sidea.version.nudge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import sidea.version.nudge.controller.web.PointUpdateEvent;
import sidea.version.nudge.dto.PointDto;
import sidea.version.nudge.dto.UserDto;
import sidea.version.nudge.mapper.PointMapper;

import java.util.List;

@Service
public class PointService {

    @Autowired
    private PointMapper pointMapper;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

//    @Autowired
//    public PointService(ApplicationEventPublisher eventPublisher){
//        this.eventPublisher = eventPublisher;
//    }


    //포인트 내역 조회
    public List<PointDto> getPointList(long userIdx) throws Exception{
        return pointMapper.getPointList(userIdx);
    }

    //포인트 충전
    public int chargePoint(PointDto pointDto) throws Exception{
        return pointMapper.chargePoint(pointDto);
    }

    //포인트 기부
    public int donatePoint(PointDto pointDto) throws Exception{
        return pointMapper.donatePoint(pointDto);
    }

    //포인트 획득
    public int earnPoint(PointDto pointDto) throws Exception{
        return pointMapper.earnPoint(pointDto);
    }

    //회원 현재 포인트잔액 조회
    public double getPointBalance(long userIdx) throws Exception{
        return pointMapper.getPointBalance(userIdx);
    }

    //회원 현재 포인트 잔액 수정
    public int updatePointBalance(UserDto userDto) throws Exception{
        eventPublisher.publishEvent(new PointUpdateEvent(this, userDto.getUserIdx(), userDto.getUserPointBalance()));
        return pointMapper.updatePointBalance(userDto);
    }


}
