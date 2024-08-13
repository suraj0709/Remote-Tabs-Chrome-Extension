package com.kishorita.remoteTabs.controller;

import com.kishorita.remoteTabs.entity.User;
import com.kishorita.remoteTabs.entity.UserKey;
import com.kishorita.remoteTabs.service.UserDetailServiceImpl;
import com.kishorita.remoteTabs.service.UserServices;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServices userServices;
    @Autowired
    private UserDetailServiceImpl userDetailService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<User> allUser = userServices.getAllUser();
        if(allUser != null && !allUser.isEmpty())
            return new ResponseEntity<>(allUser, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    @GetMapping("/fetchAllURL")
//    public ResponseEntity<List<String> >fetchAllURL(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String userName = authentication.getName();
//        User user = userServices.findByUserName(userName);
//        List<String> allUrl = userServices.fetchAllUrl(user.getId());
//        return new ResponseEntity<>(allUrl, HttpStatus.OK);
//    }

    @PostMapping("/fetchAllURL")
    public ResponseEntity<?> fetchAll(@RequestBody UserKey user) {
        User existingUser = userServices.findByUserNameAndKey(user.getUserName(), user.getKey());
        if (existingUser != null) {
            List<String> allUrl = userServices.fetchAllUrl(existingUser.getId());
            return new ResponseEntity<>(allUrl, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No User Found", HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/pushURL")
    public ResponseEntity<?> pushUrlList(@RequestBody List<String> urlList){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userServices.findByUserName(userName);
        return new ResponseEntity<>(userServices.pushUrlList(urlList, user.getId()), HttpStatus.ACCEPTED);
    }

}
