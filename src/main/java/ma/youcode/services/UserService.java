package ma.youcode.services;

import ma.youcode.repositories.UserRepository;
import ma.youcode.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public void registerUser(User user) throws Exception {
        addUser(user);
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public Optional<User> getUser(String key) {
        return repository.findByPersonalInfoPhoneNumberOrPersonalInfoEmail(key, key);
    }

    public void registerAdmin(User user) throws Exception {
        addUser(user);
    }

    private void addUser(User user) throws Exception {
        var matches = thisNaturalIdExists(user);
        if (!matches.isEmpty())
            throw new Exception(String.valueOf(matches));

        User person = null;

        try {
            person = repository.save(user);
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "", e);
        }

        assert person != null;
        if (person.getUserId() == null)
            throw new Exception("Could not create this user");
    }

    private Map<String, String> thisNaturalIdExists(User user) {
        Map<String, String> matches = new HashMap<>();

        try {
            var phoneNumberMatch = repository.findByPersonalInfoPhoneNumber(user.getPersonalInfo().getPhoneNumber());
            if (phoneNumberMatch.isPresent())
                matches.put("phoneNumber", "User with this phone number already exists");
        } catch (Exception ignored) {
        }

        try {
            var emailMatch = repository.findByPersonalInfoEmail(user.getPersonalInfo().getEmail());
            if (emailMatch.isPresent())
                matches.put("email", "User with this email already exists");
        } catch (Exception ignored) {
        }

        return matches;
    }

    public User getById(UUID id) throws Exception {
        return repository.findById(id)
                .orElseThrow(() -> new Exception("User not found"));
    }
}