package apiAuthentication.example.ApiAuth.Entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Rol")
public class Rol {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="role_name")
    @Enumerated(EnumType.STRING)
    private RoleEnum roleEnum;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="role_permissions", joinColumns = @JoinColumn(name="role_id") ,  inverseJoinColumns = @JoinColumn(name="permission_id") )

    private Set<Permission> permissionsList= new HashSet<>();

}
