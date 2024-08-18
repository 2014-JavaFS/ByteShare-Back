package com.revature.byteshare.security;

import com.revature.byteshare.user.User;
import com.revature.byteshare.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <h5>Creates in memory Users to test authentication and authorization</h5>
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    private ArrayList<String> roles = new ArrayList<>();

    /**
     * <h5>LoadUserByUsername takes an email and tests to see if it is in the InMemory database</h5>
     * @param username String - takes users email
     * @return UserDetails which is the in memory User
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Email not found"));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(roles));
    }

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
        roles.add("AUTHOR");
        roles.add("ADMIN");
        roles.add("USER");
    }


    /**
     *  <h5>Creates a collection of GrantedAuthority by iterating through roles</h5>
     *  <p></p>
     *
     * @param ArrayList - roles is the MemberTypes that are possible in Users
     * @return Collection of GrantedAuthority
     */

    private Collection<GrantedAuthority> mapRolesToAuthorities(List<String> roles) {
        return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
}
