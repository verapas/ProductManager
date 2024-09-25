package ch.csbe.productmanager.resources.user;
import ch.csbe.productmanager.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User findById(Long id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with the id " + id + " could not be found!"));
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public User create(User userToSave) {
        return this.userRepository.save(userToSave);
    }

    public User update(Long id, User userToUpdate) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with the id " + id + " could not be found!"));
        user.setUsername(userToUpdate.getUsername());
        user.setPassword(userToUpdate.getPassword());
        user.setRole(userToUpdate.getRole());
        return userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
