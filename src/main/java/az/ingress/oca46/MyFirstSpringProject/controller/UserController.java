package az.ingress.oca46.MyFirstSpringProject.controller;

import az.ingress.oca46.MyFirstSpringProject.dto.request.UserRequestDTO;
import az.ingress.oca46.MyFirstSpringProject.servis.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
public class UserController {
    private final UserService userService;
    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody UserRequestDTO userRequestDTO){
        userService.register(userRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("/check")
    public ResponseEntity<String> check() {
        return ResponseEntity.ok("OK");
    }
}
