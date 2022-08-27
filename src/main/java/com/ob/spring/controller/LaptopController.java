package com.ob.spring.controller;

import com.ob.spring.entities.Laptop;
import com.ob.spring.repository.LaptopRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/equipos")
public class LaptopController {

    private LaptopRepository laptopRepository;

    public LaptopController(LaptopRepository laptopRepository){
        this.laptopRepository = laptopRepository;
    }

    @Value("${app.mensajetest}")
    String saludo2;

    @GetMapping("/hola")
    public String saludar(){
        return saludo2;
    }

    @GetMapping("/api/laptops")
    public List<Laptop> buscarTodas(){
        return laptopRepository.findAll();
    }

    @GetMapping("/api/laptops/{id}")
    public ResponseEntity<Laptop> buscarPorId(@PathVariable Integer id){
        Optional<Laptop> optional = laptopRepository.findById(id);

        if (optional.isPresent()){
            return ResponseEntity.ok(optional.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/laptops")
    public ResponseEntity<Laptop> guardar(@RequestBody Laptop laptop){
        if (laptop.getId() != null){
            return ResponseEntity.badRequest().build();
        }

        Laptop result = laptopRepository.save(laptop);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/api/laptops")
    public ResponseEntity<Laptop> actulizar(@RequestBody Laptop laptop){
        if(laptop.getId() == null ){
            return ResponseEntity.badRequest().build();
        }
        if(!laptopRepository.existsById(laptop.getId())){
            return ResponseEntity.notFound().build();
        }

        Laptop result = laptopRepository.save(laptop);
        return ResponseEntity.ok(result);
    }


    @DeleteMapping("/api/laptops/{id}")
    public ResponseEntity<Laptop> eliminar(@PathVariable Integer id){

        if(!laptopRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }

        laptopRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/api/laptops")
    public ResponseEntity<Laptop> eliminarTodas(){
        laptopRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }


}
