package apiAuthentication.example.ApiAuth.Model.Request;


import apiAuthentication.example.ApiAuth.Entities.RoleEnum;
import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    String username;
    String password;
    RoleEnum roleEnum;
}
