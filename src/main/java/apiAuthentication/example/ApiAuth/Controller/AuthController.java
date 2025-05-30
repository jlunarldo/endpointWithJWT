package apiAuthentication.example.ApiAuth.Controller;


import apiAuthentication.example.ApiAuth.Model.Request.LoginRequest;
import apiAuthentication.example.ApiAuth.Model.Request.RegisterRequest;
import apiAuthentication.example.ApiAuth.Model.Response.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

/*
    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request)
    {

        return ResponseEntity.ok(authService.login(request));*//*
        }


    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request)
    {
        return ResponseEntity.ok(authService.register(request));
    }
*/

}
