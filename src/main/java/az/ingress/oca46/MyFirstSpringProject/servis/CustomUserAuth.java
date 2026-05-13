package az.ingress.oca46.MyFirstSpringProject.servis;

import az.ingress.oca46.MyFirstSpringProject.entity.Users;
import az.ingress.oca46.MyFirstSpringProject.exception.myexceptions.UserNotFoundException;
import az.ingress.oca46.MyFirstSpringProject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserAuth implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        System.out.println("USERNAME = " + username);

        Users user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UserNotFoundException(username));

        System.out.println("DB PASSWORD = " + user.getPassword());

        return User
                .builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }
}
