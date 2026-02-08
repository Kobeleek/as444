package service.impl;

import model.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import java.util.List;

@Service
public class UserServiceimpl implements service.impl.UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceimpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO create(UserDTO dto) {
        User user = User.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .age(dto.getAge())
                .build();

        user = userRepository.save(user);
        return mapToDTO(user);
    }

    @Override

    public UserDTO getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return mapToDTO(user);
    }

    @Override
    public List<UserDTO> getAll() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.tolist())
    }

    private UserDTO mapToDTO(User user) {
        return UserDTO.builder()
                .id(user.getID())
                .firsName(user.getgirstName())
                .lastName(user.getlastName())
                .age(user.getAge())
                .build();
    }
}

