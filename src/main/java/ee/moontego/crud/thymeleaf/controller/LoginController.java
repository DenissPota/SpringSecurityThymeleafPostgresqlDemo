package ee.moontego.crud.thymeleaf.controller;


import ee.moontego.crud.thymeleaf.entity.auth.authority.Authority;
import ee.moontego.crud.thymeleaf.entity.auth.user.User;
import ee.moontego.crud.thymeleaf.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showMyLoginPage() {
        return "plain-login";
    }

    @GetMapping("/access-denied")
    public String showAccessDeniedPage() {
        return "access-denied";
    }

}
