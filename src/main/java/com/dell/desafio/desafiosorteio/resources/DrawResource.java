package com.dell.desafio.desafiosorteio.resources;

import com.dell.desafio.desafiosorteio.entities.Bet;
import com.dell.desafio.desafiosorteio.entities.Draw;
import com.dell.desafio.desafiosorteio.services.BetService;
import com.dell.desafio.desafiosorteio.services.DrawService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

//@Validated
@RestController
@RequestMapping(value = "/draws")
public class DrawResource {
    @Autowired
    private DrawService service;

    @GetMapping
    public ResponseEntity<List<Draw>> findAll() {
        List<Draw> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Draw> findById(@PathVariable long id) {
        Draw obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping(value = "/new")
    public ResponseEntity<Draw> insert() {
        Draw draw = new Draw();
        service.save(draw);
        return ResponseEntity.ok().body(draw);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Draw> update(@PathVariable long id, @Valid @RequestBody Draw obj) {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }

    @PutMapping(value = "/sortear")
    public void sortear(){
        Draw lastDraw = service.findLastDraw();
        lastDraw.sorteio();
        service.update(lastDraw.getIdDraw(), lastDraw);
    }

    @GetMapping(value = "/vencedores")
    public ResponseEntity<?> vencedores(){
        Draw lastDraw = service.findLastDraw();
            if(lastDraw.getWinners() != null){
                List<Bet> winners = lastDraw.getWinners();
                winners.sort(Comparator.comparing(Bet::getBetterName));
                return ResponseEntity.ok().body(winners);
            }else{
                return ResponseEntity.badRequest().body("Sorteio sem vencedores.");
            }

    }
}
