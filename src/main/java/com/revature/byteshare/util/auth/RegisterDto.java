package com.revature.byteshare.util.auth;

import com.revature.byteshare.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterDto {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String username;
    private User.UserType userType;
}
