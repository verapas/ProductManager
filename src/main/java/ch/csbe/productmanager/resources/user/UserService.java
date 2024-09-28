package ch.csbe.productmanager.resources.user;

import ch.csbe.productmanager.exception.ResourceNotFoundException;
import ch.csbe.productmanager.resources.user.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Geschäftslogik zur verwaltung von Benutzern.
 * Umfasst Methoden zum Erstellen, Abrufen, Aktualisieren und Löschen von Benutzern
 * sowie zur Authentifizierung und Passwortverschlüsselung.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Findet alle Benutzer und gibt sie als Liste von UserShowDto zurück.
     */
    public List<UserShowDto> findAll() {
        List<User> users = this.userRepository.findAll();
        return users.stream()
                .map(userMapper::toShowDto)
                .collect(Collectors.toList());
    }

    /**
     * Findet einen Benutzer anhand seiner ID und gibt ihn als UserDetailDto zurück.
     */
    public UserDetailDto findById(Long id) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Benutzer mit der ID: " + id + " konnte nicht gefunden werden!"));
        return userMapper.toDetailDto(user);
    }

    /**
     * Erstellt einen neuen Benutzer, verschlüsselt dessen Passwort und speichert ihn in der Datenbank.
     */
    public UserDetailDto create(UserCreateDto dto) {
        User user = userMapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = this.userRepository.save(user);
        return userMapper.toDetailDto(savedUser);
    }

    /**
     * Aktualisiert einen vorhandenen Benutzer und speichert die Änderungen in der Datenbank..
     */
    public UserDetailDto update(Long id, UserUpdateDto dto) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Benutzer mit der ID: " + id + " konnte nicht gefunden werden!"));
        userMapper.updateEntity(dto, user);
        User updatedUser = userRepository.save(user);
        return userMapper.toDetailDto(updatedUser);
    }

    /**
     * Löscht einen Benutzer anhand seiner ID aus der Datenbank.
     */
    public void delete(Long id) {
        this.userRepository.deleteById(id);
    }

    /**
     * Findet einen Benutzer anhand seiner E-Mail-Adresse.
     */
    public User findUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new ResourceNotFoundException("Benutzer mit dieser E-Mail: " + email + " konnte nicht gefunden werden!");
        }
        return user;
    }

    /**
     * Überprüft die Anmeldedaten eines Benutzers und gibt den Benutzer zurück, wenn die Anmeldedaten korrekt sind.
     */
    public User getUserWithCredentials(LoginRequestDto loginRequestDto) {
        User user = this.findUserByEmail(loginRequestDto.getEmail());
        if (user != null && passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
            return user;
        }
        return null;
    }

}