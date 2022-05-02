package com.uoons.users.serviceImpl;

import com.uoons.users.enitity.UserEntity;
import com.uoons.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user =userRepository.findByEmail(email);

        if(user==null){
            throw new UsernameNotFoundException("Bad user");
        }
        return new CustomUserDetails(user);
    }


}
