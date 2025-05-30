package apiAuthentication.example.ApiAuth.Repository;

import apiAuthentication.example.ApiAuth.Entities.Rol;
import apiAuthentication.example.ApiAuth.Entities.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Long> {

    Optional<Rol> findByRoleEnum(RoleEnum roleEnum);
}