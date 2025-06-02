package org.lessons.java.spring_pokemon.security;

import java.util.Optional;

import org.lessons.java.spring_pokemon.model.User;
import org.lessons.java.spring_pokemon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DatabaseUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userAttempt= userRepository.findByUsername(username);

        if (userAttempt.isEmpty()) {
            
            throw new UsernameNotFoundException("User not found");
        }
        
        return new DatabaseUserDetails(userAttempt.get());

    }

}
