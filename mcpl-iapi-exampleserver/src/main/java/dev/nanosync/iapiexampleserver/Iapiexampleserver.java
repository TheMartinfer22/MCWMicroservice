package dev.nanosync.iapiexampleserver;

import okhttp3.*;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class Iapiexampleserver extends JavaPlugin {

    @Override
    public void onEnable() {

        Bukkit.getServer().getPluginManager().registerEvents(new PlayerActions(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private static class PlayerActions implements Listener {

        @EventHandler
        public void onPlayerJoin(org.bukkit.event.player.PlayerJoinEvent event) {


            RequestBody formBody = new FormBody.Builder()
                    .add("username", event.getPlayer().getDisplayName())
                    .build();

            Request request = new Request.Builder()
                    .url("localhost:8080/api/v1/player")
                    .post(formBody)
                    .build();


            OkHttpClient httpClient = new OkHttpClient();

            try (Response response = httpClient.newCall(request).execute()) {

                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                // Get response body
                System.out.println(response.body().string());

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
