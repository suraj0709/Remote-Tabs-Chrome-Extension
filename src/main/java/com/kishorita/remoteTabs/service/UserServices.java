package com.kishorita.remoteTabs.service;

import com.kishorita.remoteTabs.entity.User;
import com.kishorita.remoteTabs.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserServices {

    @Autowired
    UserRepository userRepository;

    public static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void saveNewUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public List<String> fetchAllUrl(ObjectId id){
        User userInDB = userRepository.findById(id).orElseThrow();
        return userInDB.getUrl();
    }

    public ResponseEntity<?> pushUrlList(List<String> url, ObjectId id){
        User userInDB = userRepository.findById(id).orElseThrow();
        userInDB.setUrl(url);
        userRepository.save(userInDB);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public Optional<User> findById(ObjectId id){
        return userRepository.findById(id);
    }
    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }
    public User findByUserNameAndKey(String username, String key){
        return userRepository.findByUserNameAndKey(username, key);
    }
}
