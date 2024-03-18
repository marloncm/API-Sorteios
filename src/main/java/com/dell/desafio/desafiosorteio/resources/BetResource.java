package com.dell.desafio.desafiosorteio.resources;

import com.dell.desafio.desafiosorteio.entities.Bet;
import com.dell.desafio.desafiosorteio.services.BetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//@Validated
@RestController
@RequestMapping(value = "/bets")
public class BetResource {
    @Autowired
    private BetService service;

    @GetMapping
    public ResponseEntity<List<Bet>> findAll() {
        List<Bet> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Bet> findById(@PathVariable long id) {
        Bet obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Bet> insert(@Valid @RequestBody Bet obj) {
        obj = service.save(obj);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Bet> update(@PathVariable long id, @Valid @RequestBody Bet obj) {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }

}
