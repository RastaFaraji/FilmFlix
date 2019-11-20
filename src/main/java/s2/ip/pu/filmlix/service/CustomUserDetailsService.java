package s2.ip.pu.filmlix.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import s2.ip.pu.filmlix.model.Role;
import s2.ip.pu.filmlix.model.Uzytkownicy;
import s2.ip.pu.filmlix.repository.RoleRepository;
import s2.ip.pu.filmlix.repository.UserRepository;

import java.util.*;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    public Uzytkownicy findUserByEmail(String mail) {
        return userRepository.findByLogin(mail);
    }

    public void saveUser(Uzytkownicy user) {
        user.setHaslo(bCryptPasswordEncoder.encode(user.getHaslo()));
        Role userRole = roleRepository.findByRole("USER");
        user.setRoles(new HashSet<>(Collections.singletonList(userRole)));
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        Uzytkownicy user = userRepository.findByLogin(login);
        if (user != null) {
            List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
            return buildUserForAuthentication(user, authorities);
        } else {
            throw new UsernameNotFoundException("username not found");
        }
    }

    private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
        Set<GrantedAuthority> roles = new HashSet<>();
        userRoles.forEach((role) -> roles.add(new SimpleGrantedAuthority(role.getRole())));

        return new ArrayList<>(roles);
    }

    private UserDetails buildUserForAuthentication(Uzytkownicy user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getHaslo(), authorities);
    }
}
