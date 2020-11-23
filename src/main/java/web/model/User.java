package web.model;

import web.validation.PasswordMatches;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Calendar;

@Entity
@Table(name = "user")
@PasswordMatches
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

    public Calendar getCreated() {
        return this.created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", passwordConfirmation='" + passwordConfirmation + '\'' +
                ", created=" + created +
                '}';
    }
}
