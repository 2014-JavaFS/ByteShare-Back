package com.revature.byteshare.user;

import com.revature.byteshare.util.exceptions.DataNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByUserIdNumber(int userId) throws DataNotFoundException {
        return userRepository.findById(userId).orElseThrow(() -> new DataNotFoundException("No User found with userIdNumber " + userId));
    }

    public int lookupUserIdByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow().getUserId();
    }

    public User findById(int userId){
        return userRepository.findById(userId).orElseThrow();
    }

    public User createUser(User newUser) {
        return userRepository.save(newUser);
    }

    @Transactional
    public boolean updateUser(User updatedUser) {
        userRepository.save(updatedUser);
        return true;
    }

    public boolean deleteUser(int userId) {
        userRepository.deleteById(userId);
        return true;
    }

    protected User updateUserAccessLevel(User user, User.UserType userType){
        user.setUserType(userType);
        return userRepository.save(user);
    }
}
