package com.example.Footballl.controller;

import com.example.Footballl.model.dto.PlayerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.Footballl.service.impl.PlayerService;

@RestController
@RequestMapping("/players")

public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping
    public ResponseEntity<PlayerDTO>  create(@RequestBody PlayerDTO dto){
        return ResponseEntity.ok(playerService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerDTO> getByID(@PathVariable Long id) {
        return ResponseEntity.ok(playerService.getByID(id));
    }

    @GetMapping
    public ResponseEntity<java.util.List<PlayerDTO>> getAll() {
        return ResponseEntity.ok(playerService.getAll());
    }
}



