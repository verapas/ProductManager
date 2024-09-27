package ch.csbe.productmanager.resources.user;

import ch.csbe.productmanager.exception.ResourceNotFoundException;
import ch.csbe.productmanager.resources.user.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserShowDto> findAll() {
        List<User> users = this.userRepository.findAll();
        return users.stream()
                .map(userMapper::toShowDto)
                .collect(Collectors.toList());
    }

    public UserDetailDto findById(Long id) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Benutzer mit der ID: " + id + " konnte nicht gefunden werden!"));
        return userMapper.toDetailDto(user);
    }

    public UserDetailDto create(UserCreateDto dto) {
        User user = userMapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = this.userRepository.save(user);
        return userMapper.toDetailDto(savedUser);
    }

    public UserDetailDto update(Long id, UserUpdateDto dto) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Benutzer mit der ID: " + id + " konnte nicht gefunden werden!"));
        userMapper.updateEntity(dto, user);
        User updatedUser = userRepository.save(user);
        return userMapper.toDetailDto(updatedUser);
    }

    public void delete(Long id) {
        this.userRepository.deleteById(id);
    }

    public User findUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new ResourceNotFoundException("Benutzer mit dieser E-Mail: " + email + " konnte nicht gefunden werden!");
        }
        return user;
    }

    public User getUserWithCredentials(LoginRequestDto loginRequestDto) {
        User user = this.findUserByEmail(loginRequestDto.getEmail());
        if (user != null && passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
            return user;
        }
        return null;
    }

}