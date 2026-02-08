package service.impl;

import model.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO create(UserDTO dto);

    UserDTO getByID(Long id);

    List<UserDTO> getAll();
}
