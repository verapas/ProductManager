package ch.csbe.productmanager.resources.user;

import ch.csbe.productmanager.exception.ResourceNotFoundException;
import ch.csbe.productmanager.resources.user.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public List<UserShowDto> findAll() {
        List<User> users = this.userRepository.findAll();
        return users.stream()
                .map(userMapper::toShowDto)
                .collect(Collectors.toList());
    }

    public UserDetailDto findById(Long id) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with the id " + id + " could not be found!"));
        return userMapper.toDetailDto(user);
    }

    public UserDetailDto create(UserCreateDto dto) {
        User user = userMapper.toEntity(dto);
        User savedUser = this.userRepository.save(user);
        return userMapper.toDetailDto(savedUser);
    }

    public UserDetailDto update(Long id, UserUpdateDto dto) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with the id " + id + " could not be found!"));
        userMapper.updateEntity(dto, user);
        User updatedUser = userRepository.save(user);
        return userMapper.toDetailDto(updatedUser);
    }

    public void delete(Long id) {
        this.userRepository.deleteById(id);
    }
}