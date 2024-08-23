package com.revature.byteshare.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** MODEL CLASS DOCUMENTATION
 * The model class defines how are data is constructed and what it should contain.
 * Spring Data Annotations will allow us to implement an entity and table in our database for other objects and layers to access.
 */
// Lombok Annotations
@Data
@NoArgsConstructor
@AllArgsConstructor

// Jakarta Persistence Annotations
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Column(unique = true, nullable = false)
    @Email(message = "An email address must be in the form of _@_._; please try again.")
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String first_name;
    @Column(nullable = false)
    private String last_name;
    //private String auth_token; may not need after all
    @Column(name = "user_type", columnDefinition = "varchar(10) default 'AUTHOR'")
    @Enumerated(EnumType.STRING)
    private UserType userType;

    public enum UserType {
        ADMIN, AUTHOR, USER
    }
    public User(String email, String password, String firstName, String lastName, String username, UserType userType) {
        this.email = email;
        this.password = password;
        this.first_name = firstName;
        this.last_name = lastName;
        this.username = username;
        this.userType = userType;
    }

}
