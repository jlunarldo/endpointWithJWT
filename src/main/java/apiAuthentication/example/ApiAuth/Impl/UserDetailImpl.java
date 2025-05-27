package apiAuthentication.example.ApiAuth.Impl;

import apiAuthentication.example.ApiAuth.Entities.UserGeneral;
import apiAuthentication.example.ApiAuth.Repository.UserGeneralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailImpl implements UserDetailsService {

    @Autowired
    private UserGeneralRepository userGeneralRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserGeneral userGeneral= userGeneralRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User not exist"));

        List<SimpleGrantedAuthority> authorityList= new ArrayList<>();

        userGeneral.getRoles()
                .forEach(role->authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name()))));


        userGeneral.getRoles()
                .stream()
                .flatMap(role-> role.getPermissionsList().stream())
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getName())));
        return new User(
                userGeneral.getUsername(),
                userGeneral.getPassword(),
                userGeneral.isEnabled(),
                userGeneral.isAccountNoExpired(),
                userGeneral.isCredentialNoExpired(),
                userGeneral.isAccountNoLocked(),
                authorityList
        );
    }
}
