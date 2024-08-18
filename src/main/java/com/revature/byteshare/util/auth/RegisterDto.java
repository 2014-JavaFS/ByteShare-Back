package com.revature.byteshare.util.auth;

import com.revature.byteshare.user.User;
import lombok.Data;

@Data
public class RegisterDto {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String username;
    private User.UserType userType;
}
