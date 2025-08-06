package org.example.moviehub.service;

import org.springframework.stereotype.Service;

@Service
public class PasswordChecker {

    public boolean isValid(String password) {
        //later for complex logic like do not use the username in the password. Not necessary right now
        return true;
    }
}
