package com.dell.desafio.desafiosorteio.services;

import com.dell.desafio.desafiosorteio.entities.Draw;
import com.dell.desafio.desafiosorteio.repositories.DrawRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrawService {
    @Autowired
    private DrawRepository repo;

    public List<Draw> findAll() {
        return repo.findAll();
    }

    public Draw findById(long id) {
        return repo.findById(id).get();
    }

    public Draw save(Draw draw) {
        return repo.save(draw);
    }

    public void delete(long id) {
        repo.deleteById(id);
    }

    public Draw update(long id, Draw draw) {
        Draw obj = findById(id);
        updateData(obj, draw);
        return repo.save(obj);
    }

    private void updateData(Draw obj, Draw draw) {
     obj.setBets(draw.getBets());
     obj.setChosenNumbers(draw.getChosenNumbers());
     obj.setWinners(draw.getWinners());
    }

    public Draw findLastDraw() {
        List<Draw> list = repo.findAll();
        return list.get(list.size() - 1);
    }
}
