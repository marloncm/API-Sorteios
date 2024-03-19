package com.dell.desafio.desafiosorteio.services;

import com.dell.desafio.desafiosorteio.entities.Bet;
import com.dell.desafio.desafiosorteio.repositories.BetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BetService {

    @Autowired
    private BetRepository repo;

    public List<Bet> findAll() {
        return repo.findAll();
    }

    public Bet findById(long id) {
        return repo.findById(id).get();
    }

    public Bet save(Bet bet) {
        return repo.save(bet);
    }

    public void delete(long id) {
        repo.deleteById(id);
    }

    public Bet update(long id, Bet bet) {
        Bet obj = findById(id);
        obj.updateData(bet);
        return repo.save(obj);
    }





}
