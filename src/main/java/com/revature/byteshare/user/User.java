package com.revature.byteshare.user;

import jakarta.persistence.*;
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
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String first_name;
    @Column(nullable = false)
    private String last_name;
    private String auth_token;
    @Column(name = "user_type", columnDefinition = "varchar(10) default 'AUTHOR'")
    @Enumerated(EnumType.STRING)
    private userType user_type;

    public enum userType {
        ADMIN, AUTHOR, USER
    }

}
