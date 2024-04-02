package com.ortega.tshomboapi.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IUserDetailsService {
    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;
}
