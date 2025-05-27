package apiAuthentication.example.ApiAuth.Config;


import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class PasswordEncoderConfig {

    public PasswordEncoder passwordEncoder(){

        // return  new BCryptPasswordEncoder(); //esto retorna contraseñas encriptadas q no usaremos por ahora
        return NoOpPasswordEncoder.getInstance(); //no retorna contraseñas encriptadas pero lo usaremos por ahora para aprender
    }

}
