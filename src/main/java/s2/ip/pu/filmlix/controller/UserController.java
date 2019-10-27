package s2.ip.pu.filmlix.controller;

import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import s2.ip.pu.filmlix.config.JwtTokenProvider;
import s2.ip.pu.filmlix.model.User;
import s2.ip.pu.filmlix.repository.UserRepository;
import s2.ip.pu.filmlix.service.CustomUserDetailsService;

import java.util.HashMap;
import java.util.Map;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/auth/")
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor(access = PACKAGE)
final class UserController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private CustomUserDetailsService userService;

    @PostMapping("/login")
    public ResponseEntity<Map<Object, Object>> login(@RequestBody User user) {
        try {
            String username = user.getLogin();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, user.getPassword()));
            String token = jwtTokenProvider.createToken(username, this.userRepository.findByLogin(username).getRoles());
            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("token", token);
            return ok(model);
        } catch (AuthenticationException e) {
            Map<Object, Object> model = new HashMap<>();
            model.put("error", e.getMessage());
            model.put("message", "Wrong username or password");
            return new ResponseEntity<>(model, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Map<Object, Object>> register(@RequestBody User user) {
        User userExists = userService.findUserByEmail(user.getLogin());
        if (userExists != null) {
            Map<Object, Object> model = new HashMap<>();
            model.put("message", "User with username: " + user.getLogin() + " already exists");
            return new ResponseEntity<>(model, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        userService.saveUser(user);
        Map<Object, Object> model = new HashMap<>();
        model.put("message", "User registered successfully");
        return ok(model);
    }
}
