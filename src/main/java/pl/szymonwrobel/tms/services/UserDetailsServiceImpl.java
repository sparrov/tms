package pl.szymonwrobel.tms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.szymonwrobel.tms.MyUserDetails;
import pl.szymonwrobel.tms.entities.UserEntity;
import pl.szymonwrobel.tms.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    final private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByLogin(login);
        if (userEntity == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
        return new MyUserDetails(userEntity);
    }
}
