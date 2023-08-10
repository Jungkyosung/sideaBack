package sidea.version.nudge.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import sidea.version.nudge.service.LoginService;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    

}
