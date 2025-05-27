package apiAuthentication.example.ApiAuth.Config;


import apiAuthentication.example.ApiAuth.Impl.UserDetailImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity //Permite trabajar con anotaciones de PreAuthorize los authorizeHttpRequests
public class SecurityConfig {


    /*//Configuración del filtro
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity //TODOS ESTOS son las cadenas de filtros
                .csrf(crsf->crsf.disable())
                .httpBasic(Customizer.withDefaults()) // para que podamos consumir la API, solo nos va a pedir usuario y contraseña,
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // stateless signifca que no vamos a guardar el estado de la sesión en memoria, sino que la duración del usuario logueado va a depender de la duración del token de autorización
                .authorizeHttpRequests(http->{

                    //Configurar enpoints públicos
                    http.requestMatchers(HttpMethod.GET,"/test/hi").permitAll(); //permite el acceso a todos los que quieran consumir ese endpoint

                    //Configurar endpoint privados
                    http.requestMatchers(HttpMethod.GET,"/test/secured").hasAuthority("READ"); //Todos los que quieran acceder al endpoint, deben tener autorización de READ

                    //Resto de endpoint -NO ESPECIFICADOS
                    http.anyRequest().denyAll(); // Los endpoints que no estén especificado en el bloque código de arriba, serán rechazado
                    //http.anyRequest().authenticated();//Deja entrar a los que estén autenticados (ósea que tenga un usuario y contraseña básica), permite q cualquiera entre al endpoint mientras tenga usuario y password


                })
                .build();
    }*/
    //Esta función le quito los authorizeHttpRequests y los trabajo con PreAuthorize, los establezco en los endpoints
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity //TODOS ESTOS son las cadenas de filtros
                .csrf(crsf -> crsf.disable())
                .httpBasic(Customizer.withDefaults()) // para que podamos consumir la API, solo nos va a pedir usuario y contraseña,
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // stateless signifca que no vamos a guardar el estado de la sesión en memoria, sino que la duración del usuario logueado va a depender de la duración del token de autorización

                .build();

    }
    //Establecemos el Authencation Manager
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    //Establcermos el provider, el cuál para este desafío va a  ser un provider de base de datos
    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailImpl userDetail){
        DaoAuthenticationProvider provider= new DaoAuthenticationProvider();

        //Esto es para el password Encoder
        provider.setPasswordEncoder( new PasswordEncoderConfig().passwordEncoder());

        //Para el user Details, ósea detalles para conectarse a la base de datos
        provider.setUserDetailsService(userDetail);
         return provider;
    }


    //definimos unos usuarios para pruebas

    /*@Bean
    public  UserDetailsService userDetailsService(){
        List<UserDetails> userDetailsList= new ArrayList<>();

        userDetailsList.add(
                User.withUsername("Santiago")
                        .password("{noop}1234")
                        .roles("ADMIN")
                        .authorities("READ", "CREATE")
                        .build()
        );

        userDetailsList.add(
                User.withUsername("Daniel")
                        .password("{noop}dan")
                        .roles("ADMIN")
                        .authorities("READ", "CREATE")
                        .build()
        );

        return new InMemoryUserDetailsManager(userDetailsList);
    }*/
}
