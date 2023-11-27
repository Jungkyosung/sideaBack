package sidea.version.nudge.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class PointAlarmController {
//어떻게 해야 이벤트가 발생했을때 알림을 전달할 수 있나?

    @MessageMapping("/msg")
    @SendTo("/topic/alarm")
    public String registerStompEndpoint(@Payload String msg){
        System.out.println("msg");
        return msg; //포인트 내역이 업데이트 될 때 마다 해당 내용을 정리해서 보내줘야 함.
    }
}
