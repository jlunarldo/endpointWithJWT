package apiAuthentication.example.ApiAuth.Repository;

import apiAuthentication.example.ApiAuth.Entities.UserGeneral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserGeneralRepository extends JpaRepository<UserGeneral, Long> {


    Optional<UserGeneral> findByUsername(String username);
}
