package com.example.cafemangement.JWT;

import com.example.cafemangement.dao.UserDao;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Service
public class CustomerUserDetailService implements UserDetailsService {
    public  UserDao userDao;

    public CustomerUserDetailService(UserDao userDao) {
        this.userDao = userDao;
    }

    private com.example.cafemangement.POJO.User userDetail;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        userDetail=userDao.findByEmail(username);
        if(!Objects.isNull(userDetail))
        {
            return  new User(userDetail.getEmail(),userDetail.getPassword(),new ArrayList<>());
        }
        else
            throw new UsernameNotFoundException("user not found.");
    }
    public  com.example.cafemangement.POJO.User getUserDetail(){
        return  userDetail;
    }
}
