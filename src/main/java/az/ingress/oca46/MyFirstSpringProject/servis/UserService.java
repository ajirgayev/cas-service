package az.ingress.oca46.MyFirstSpringProject.servis;

import az.ingress.oca46.MyFirstSpringProject.dto.request.UserRequestDTO;
import az.ingress.oca46.MyFirstSpringProject.entity.Users;
import az.ingress.oca46.MyFirstSpringProject.exception.AlreadyExistException;
import az.ingress.oca46.MyFirstSpringProject.exception.myexceptions.AlreadyUserExistException;
import az.ingress.oca46.MyFirstSpringProject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public void register(UserRequestDTO userRequestDTO){
        userRepository.findByUsername(userRequestDTO.getUsername())
                .ifPresent(user -> {
                    throw new AlreadyUserExistException(userRequestDTO.getUsername());
                });
        Users user = Users.builder().username(userRequestDTO.getUsername())
                        .password(passwordEncoder.encode(userRequestDTO.getPassword()))
                                .role("USER").build();
        userRepository.save(user);
    }
}
