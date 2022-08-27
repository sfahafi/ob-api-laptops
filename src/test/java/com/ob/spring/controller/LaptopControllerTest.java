package com.ob.spring.controller;

import com.google.gson.Gson;
import com.ob.spring.entities.Laptop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {

    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @DisplayName("Comprobar busqueda de todas las laptops")
    @Test
    void buscarTodas() {
        ResponseEntity<Laptop[]> response  =
                testRestTemplate.getForEntity("/equipos/api/laptops", Laptop[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());

        List<Laptop> books = Arrays.asList(response.getBody());
        System.out.println(books.size());
    }

    @DisplayName("Comprobar busqueda por un unico id de las laptops")
    @Test
    void buscarPorId() {
        ResponseEntity<Laptop> response  =
                testRestTemplate.getForEntity("/equipos/api/laptops/3", Laptop.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @DisplayName("Comprobar guardar una nueva laptop")
    @Test
    void guardar() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        Gson gson = new Gson();
        Laptop laptop = new Laptop("Dell", "Inspiron","64GB");

        String json = gson.toJson(laptop);

        HttpEntity<String> request = new HttpEntity<>(json,headers);

        ResponseEntity<Laptop> response = testRestTemplate.exchange("/equipos/api/laptops", HttpMethod.POST, request, Laptop.class);

        Laptop result = response.getBody();

        assertEquals(1, result.getId());
        assertEquals("Inspiron", result.getModelo());
    }

    @DisplayName("Comprobar actualizar una laptop")
    @Test
    void actulizar() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        Gson gson = new Gson();
        Laptop laptop = new Laptop("Dell", "Inspiron","64GB");

        String json = gson.toJson(laptop);

        HttpEntity<String> request = new HttpEntity<>(json,headers);

        ResponseEntity<Laptop> response = testRestTemplate.exchange("/equipos/api/laptops", HttpMethod.POST, request, Laptop.class);

        Laptop result = response.getBody();

        assertEquals(1, result.getId());
        assertEquals("Inspiron", result.getModelo());
    }

    @DisplayName("Comprobar eliminar una laptop")
    @Test
    void eliminar() {
        ResponseEntity<Laptop> response  =
                testRestTemplate.getForEntity("/equipos/api/laptops/2", Laptop.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @DisplayName("Comprobar eliminar todas las laptops")
    @Test
    void eliminarTodas() {
        ResponseEntity<Laptop[]> response  =
                testRestTemplate.getForEntity("/equipos/api/laptops", Laptop[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());
    }
}