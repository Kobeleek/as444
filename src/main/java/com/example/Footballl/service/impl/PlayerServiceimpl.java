package com.example.Footballl.service.impl;

import com.example.Footballl.model.dto.PlayerDTO;
import com.example.Footballl.model.entity.Player;
import com.example.Footballl.repository.PlayerRepository;
import com.example.Footballl.service.impl.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlayerServiceimpl implements PlayerService {

    private final PlayerRepository playerRepository;

    @Override
    public PlayerDTO create(PlayerDTO dto) {
        if (playerRepository.existsByName(dto.getName())) {
            throw new IllegalArgumentException("Player with name '" + dto.getName() + "' already exists");
        }

        if (dto.getAge() < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }

        if (dto.getAge() > 150) {
            throw new IllegalArgumentException("Age cannot be more than 150");
        }

        if (dto.getTransferValue() < 0) {
            throw new IllegalArgumentException("Transfer value cannot be negative");
        }

        Player player = Player.builder()
                .name(dto.getName())
                .age(dto.getAge())
                .position(dto.getPosition())
                .club(dto.getClub())
                .transferValue(dto.getTransferValue())
                .build();

        Player savedPlayer = playerRepository.save(player);
        return mapToDTO(savedPlayer);
    }

    @Override
    public PlayerDTO getByID(Long id) {
        return mapToDTO(playerRepository.findById(id).orElseThrow(() -> new RuntimeException("Player not found")));
    }

    @Override
    public List<PlayerDTO> getAll() {
        return playerRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PlayerDTO update(Long id, PlayerDTO dto) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Player not found with id: " + id));

        if (!player.getName().equals(dto.getName()) &&
                playerRepository.existsByName(dto.getName())) {
            throw new IllegalArgumentException("Player with name '" + dto.getName() + "' already exists");
        }

        if (dto.getAge() < 0) throw new IllegalArgumentException("Age cannot be negative");
        if (dto.getAge() > 150) throw new IllegalArgumentException("Age cannot be more than 150");
        if (dto.getTransferValue() < 0) throw new IllegalArgumentException("Transfer value cannot be negative");

        player.setName(dto.getName());
        player.setAge(dto.getAge());
        player.setPosition(dto.getPosition());
        player.setClub(dto.getClub());
        player.setTransferValue(dto.getTransferValue());

        Player updatedPlayer = playerRepository.save(player);
        return mapToDTO(updatedPlayer);
    }

    @Override
    public void delete(Long id) {
        if (!playerRepository.existsById(id)) {
            throw new RuntimeException("Player not found with id: " + id);
        }
        playerRepository.deleteById(id);
    }

    private PlayerDTO mapToDTO(Player player) {
        return PlayerDTO.builder()
                .id(player.getId())
                .name(player.getName())
                .age(player.getAge())
                .position(player.getPosition())
                .club(player.getClub())
                .transferValue(player.getTransferValue())
                .build();
    }
}