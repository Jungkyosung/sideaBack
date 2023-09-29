package sidea.version.nudge.controller.web;

import org.springframework.context.ApplicationEvent;

public class PointUpdateEvent extends ApplicationEvent {

    private long userIdx;
    private double userPointBalance;

    //포인트 기부, 적립, 충전에 대한 테이블 생성해서 업데이트 때마다

    public PointUpdateEvent(Object source, long userIdx, double userPointBalance){
        super(source);
        this.userIdx = userIdx;
        this.userPointBalance = userPointBalance;
    }

    public Long getUserIdx(){
        return userIdx;
    }

    public Double getUserPointBalance(){
        return userPointBalance;
    }

}
