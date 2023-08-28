package sidea.version.nudge.controller.web;

import org.springframework.context.ApplicationEvent;

public class PointUpdateEvent extends ApplicationEvent {

    private long userIdx;
    private double userPointBalance;

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
