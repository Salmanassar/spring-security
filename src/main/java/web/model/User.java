package web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import web.validation.PasswordMatches;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Calendar;

@Data
@Entity
@Table(name = "user")
@PasswordMatches
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Fist Name is required.")
    @Column(name = "firstName")
    private String firstName;

    @NotEmpty(message = "LastName is required.")
    @Column(name = "lastName")
    private String lastName;

    @NotEmpty(message = "Email is required.")
    @Column(name = "email")
    private String email;

    @NotEmpty(message = "Password is required.")
    @Column(name ="password")
    private String password;

    @Transient
    @NotEmpty(message = "Password is required.")
    private String passwordConfirmation;

    @Column(name = "calendar")
    private Calendar created = Calendar.getInstance();

    //    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "roles_access", joinColumns = @JoinColumn(name = "id_user"),
//            inverseJoinColumns = @JoinColumn(name = "role_id"))
//    private Set<Role> roles;
//
//    public Set<Role> getRoles(){
//        return roles;
//    }
    @Enumerated(value = EnumType.STRING)
    @Column(name = "roles")
    private Roles roles;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "permission")
    private Permission permission;
}
