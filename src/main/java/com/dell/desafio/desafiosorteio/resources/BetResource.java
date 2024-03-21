package com.dell.desafio.desafiosorteio.resources;

import com.dell.desafio.desafiosorteio.entities.Bet;
import com.dell.desafio.desafiosorteio.entities.Draw;
import com.dell.desafio.desafiosorteio.services.BetService;
import com.dell.desafio.desafiosorteio.services.DrawService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//@Validated
@RestController
@RequestMapping(value = "/bets")
public class BetResource {
    @Autowired
    private BetService service;

    @Autowired
    DrawService drawService;


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
    public ResponseEntity<?> insert(@Valid @RequestBody Bet bet) {
        if(bet.checkNumbers()){
            Draw lastDraw = drawService.findLastDraw();
            if(!lastDraw.isFinished()){
                lastDraw.addBet(bet);
                drawService.update(lastDraw.getIdDraw(), lastDraw);
                return ResponseEntity.ok().body(bet);
            }else{
                return ResponseEntity.badRequest().body("O sorteio já foi finalizado. Comece um novo sorteio");
            }
        }
        return ResponseEntity.badRequest().body("Informe cinco numeros de 1 a 50.");
    }

    @PostMapping(value = "/surprise")
    public ResponseEntity<?> insertSurprise(@Valid @RequestBody Bet bet) {
        bet.setChosenNumbers(bet.surprise());
        Draw lastDraw = drawService.findLastDraw();
        if(!lastDraw.isFinished()) {
            lastDraw.addBet(bet);
            drawService.update(lastDraw.getIdDraw(), lastDraw);
            return ResponseEntity.ok().body(bet);
        }else{
            return ResponseEntity.badRequest().body("O sorteio já foi finalizado. Comece um novo sorteio");
        }
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
