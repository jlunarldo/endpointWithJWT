/*
package apiAuthentication.example.ApiAuth.Service;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsService {


    public InMemoryUserDetailsManager  userDetailsService(){

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
    }
}
*/
