package pl.szymonwrobel.tms.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.szymonwrobel.tms.dtos.UserDTO;
import pl.szymonwrobel.tms.repositories.UserRepository;

import java.security.SecureRandom;

@Service
public class SecurityService {

    private final UserRepository userRepository;

    public SecurityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String encodeUserPassword(UserDTO userDTO){
        int strength = 10; // work factor of bcrypt
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(strength, new SecureRandom());
        String encodedPassword = bCryptPasswordEncoder.encode(userDTO.getPassword());
        return encodedPassword;
    }
}
