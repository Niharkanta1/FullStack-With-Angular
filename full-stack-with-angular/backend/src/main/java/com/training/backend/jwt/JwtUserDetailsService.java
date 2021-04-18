package com.training.backend.jwt;

import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toSet;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.training.backend.entity.User;
import com.training.backend.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
    private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.debug("Loading user by username from database {}", username);
		User user = userRepository.findByUsername(username);
        
        if (isNull(user)) {
            throw new UsernameNotFoundException("Could not find user");
        }
        Set<String> roles = user.getRoles().stream().map(role-> role.getRoleName()).collect(toSet());
        log.debug("User with Roles {}", roles);
		UserDetails userDetails = new JwtUserDetails(user.getUserId(), user.getUsername(), user.getPassword(), roles);
		return userDetails;
	}

}
