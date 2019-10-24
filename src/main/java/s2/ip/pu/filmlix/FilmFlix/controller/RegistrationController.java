package s2.ip.pu.filmlix.FilmFlix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import s2.ip.pu.filmlix.FilmFlix.model.User;
import s2.ip.pu.filmlix.FilmFlix.repository.ClientRepository;

@RestController
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    ClientRepository clientRepository;

    @PostMapping("")
    public String registerNewClient(User user) {
        if (createNewClient(user)) {
            return "Success";
        }
        return "Login already taken";
    }

    private boolean createNewClient(User user) {
        if (clientRepository.findByLogin(user.getLogin()) == null) {
            clientRepository.save(user);
            return true;
        }
        return false;
    }
}
