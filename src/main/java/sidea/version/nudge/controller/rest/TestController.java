package sidea.version.nudge.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sidea.version.nudge.service.TodoPointBatchService;

@Slf4j
@RestController
@RequestMapping("/notification/test")
public class TestController {

    @Autowired
    private TodoPointBatchService service;

    @GetMapping
    public void msgTest(){
        log.info("알림 테스트");
        service.notificationTest();
    }

}
