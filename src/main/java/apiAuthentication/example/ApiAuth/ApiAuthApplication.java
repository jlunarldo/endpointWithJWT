package apiAuthentication.example.ApiAuth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiAuthApplication.class, args);
	}


	/*@Bean
	CommandLineRunner init(UserGeneralRepository userGeneralService){
		return args->{
			Permission createPermission= Permission.builder()
					.name("Created")
					.build();
			Rol createRol=Rol.builder()
					.roleEnum(RoleEnum.ADMIN)
					.permissionsList(Set.of(createPermission))
					.build();
		};
	}*/
}
