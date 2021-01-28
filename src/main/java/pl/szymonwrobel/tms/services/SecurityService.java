package pl.szymonwrobel.tms.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.szymonwrobel.tms.dtos.StudentUserDTO;
import pl.szymonwrobel.tms.dtos.TrainerUserDTO;

import java.security.SecureRandom;

@Service
public class SecurityService {

    public String encodeUserPassword(String plainPassword) {
        int strength = 10; // work factor of bcrypt
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(strength,
                new SecureRandom());
        String encodedPassword = bCryptPasswordEncoder.encode(plainPassword);
        return encodedPassword;
    }

    public String encodeUserPassword(TrainerUserDTO trainerUserDTO) {
        int strength = 10;
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(strength,
                new SecureRandom());
        String encodedPassword = bCryptPasswordEncoder.encode(trainerUserDTO.getPassword());
        return encodedPassword;
    }

    public String encodeUserPassword(StudentUserDTO studentUserDTO) {
        int strength = 10;
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(strength,
                new SecureRandom());
        String encodedPassword = bCryptPasswordEncoder.encode(studentUserDTO.getPassword());
        return encodedPassword;
    }
}
