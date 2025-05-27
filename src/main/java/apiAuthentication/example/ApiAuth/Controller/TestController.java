package apiAuthentication.example.ApiAuth.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("test")
@RestController
@PreAuthorize("denyAll()")
public class TestController {

    @GetMapping("/hi")
    @PreAuthorize("hasAuthority('VIEW_DASHBOARD')")
    public String hello(){
        return "hasAuthority(VIEW_DASHBOARD)";
    }

    @GetMapping("/secured")
    @PreAuthorize("hasAuthority('READ_PRIVILEGES')")
    public String helloSecured(){
        return "chain Secuted ";
    }

    @GetMapping("/holanda")
    @PreAuthorize("permitAll()")
    public String hello1(){
        return "hello";
    }

    @GetMapping("/manager")
    @PreAuthorize("hasAuthority('MANAGE_USERS')")
    public String helloManager(){
        return "this is a Manage";
    }


}
