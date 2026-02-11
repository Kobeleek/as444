package com.example.Footballl.service.impl;

import com.example.Footballl.model.dto.PlayerDTO;

import java.util.List;

public interface PlayerService {
    PlayerDTO create(PlayerDTO dto);
    PlayerDTO getByID(Long id);
    List<PlayerDTO> getAll();
    PlayerDTO update(Long id, PlayerDTO dto);
    void delete(Long id);

}
