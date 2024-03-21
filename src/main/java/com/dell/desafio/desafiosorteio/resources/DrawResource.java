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

    @PostMapping(value = "/startNewDraw")
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
    public ResponseEntity<Draw> update(@PathVariable long id, @Valid @RequestBody Draw draw) {
        draw = service.update(id, draw);
        return ResponseEntity.ok().body(draw);
    }

    @PutMapping(value = "/makeDraw")
    public ResponseEntity<?> makeDraw(){
        Draw lastDraw = service.findLastDraw();
        //lastDraw.checkWinner();
        service.update(lastDraw.getIdDraw(), lastDraw.checkWinner());
        return ResponseEntity.ok().body(lastDraw);
    }

    @GetMapping(value = "/winners")
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

    @GetMapping(value = "/getTotalWinners")
    public ResponseEntity<?> totalWinners(){
        Draw lastDraw = service.findLastDraw();
        if(lastDraw.getWinners() != null){
            return ResponseEntity.ok().body(lastDraw.getWinners().size() + "");
        }else{
            return ResponseEntity.badRequest().body("Sorteio sem vencedores.");
        }

    }

    @GetMapping(value = "/getDrawnNumbers")
    public ResponseEntity<?> drawnNumbers(){
        Draw lastDraw = service.findLastDraw();
        return ResponseEntity.ok().body(lastDraw.getChosenNumbers().toArray());
    }

    @GetMapping(value = "/getAllBets")
    public ResponseEntity<?> allBets(){
        Draw lastDraw = service.findLastDraw();
        return ResponseEntity.ok().body(lastDraw.getBets());
    }
}
