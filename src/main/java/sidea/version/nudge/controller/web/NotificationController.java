package sidea.version.nudge.controller.web;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.management.Notification;

@RestController
public class NotificationController {

    @MessageMapping("/sendNotification")
    @SendTo("/topic/notifications")
    public String sendNotification(@RequestParam(value = "name") String name){
        //알림 메시지 받아 처리한 후, 클라이언트에게 다시 전송
        return "hello" + name;
    }

}
