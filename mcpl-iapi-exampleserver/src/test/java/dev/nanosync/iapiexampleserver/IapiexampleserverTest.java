package dev.nanosync.iapiexampleserver;


import okhttp3.*;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class IapiexampleserverTest {


    @Test
    void deveRetornarUmOkEOnomeDoJogadorQueFoiInserido(){
        RequestBody formBody = new FormBody.Builder()
                .add("username", "Martin")
                .build();

        Request request = new Request.Builder()
                .url("http://localhost:8080/api/v1/player")
                .post(formBody)
                .build();


        OkHttpClient httpClient = new OkHttpClient();

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            Assertions.assertEquals("Martin", response.body().string());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
