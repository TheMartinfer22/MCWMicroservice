package dev.nanosync.mgexampleserver.controller;

import dev.nanosync.mgexampleserver.entity.Player;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class PlayerController {

    @PostMapping("/player")
    public String sendPlayerData(Player player) {
        return player.getUsername();
    }

}
