package com.example.demo.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

@RunWith(SpringRunner.class)
@WebFluxTest(Controller.class)
public class ControllerIntTest {


    @Autowired
    protected WebTestClient webTestClient;


    /**
     * Custom {@link com.example.demo.config.SecurityConfiguration} disables csrf + permits any exchange, so this test should work.
     */
    @Test
    public void shouldActuallyWork() {
        callTestEndpointAndAssertResponse();
    }

    /**
     * Instead WebFluxSecurityConfiguration is in use, which has csrf enabled + requires authenticated user.
     */
    @Test
    @WithMockUser
    public void working() {
        //the standard WebFluxSecurityConfiguration has csrf enabled...
        webTestClient = webTestClient.mutateWith(csrf());

        callTestEndpointAndAssertResponse();
    }

    private void callTestEndpointAndAssertResponse() {
        UUID randomId = UUID.randomUUID();

        webTestClient.post().uri("/v1/test/" + randomId.toString())
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .consumeWith(response -> {
                    assertThat(response.getResponseBody()).isNotNull();
                    assertThat(new String(response.getResponseBody(), StandardCharsets.UTF_8)).isEqualTo(randomId.toString());
                });
    }

}