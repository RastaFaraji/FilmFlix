package s2.ip.pu.filmlix.FilmFlix.controller;

import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import s2.ip.pu.filmlix.FilmFlix.config.JwtTokenProvider;
import s2.ip.pu.filmlix.FilmFlix.model.Role;
import s2.ip.pu.filmlix.FilmFlix.model.User;
import s2.ip.pu.filmlix.FilmFlix.repository.UserRepository;
import s2.ip.pu.filmlix.FilmFlix.service.CustomUserDetailsService;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

    @SuppressWarnings("rawtypes")
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody User user) {
        try {
            String username = user.getLogin();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, user.getPassword()));
            String token = jwtTokenProvider.createToken(username, this.userRepository.findByLogin(username).getRoles());
            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("token", token);
            return ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid email/password supplied");
        }
    }

    @SuppressWarnings("rawtypes")
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody User user) {
        User userExists = userService.findUserByEmail(user.getLogin());
        if (userExists != null) {
            throw new BadCredentialsException("User with username: " + user.getLogin() + " already exists");
        }
        userService.saveUser(user);
        Map<Object, Object> model = new HashMap<>();
        model.put("message", "User registered successfully");
        return ok(model);
    }

    @GetMapping("/roles")
    public Set<Role> getRoles(@RequestBody User user) {
        return userRepository.findByLogin(user.getLogin()).getRoles();
    }
}
