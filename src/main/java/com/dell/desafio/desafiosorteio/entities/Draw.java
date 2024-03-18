package com.dell.desafio.desafiosorteio.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sorteio")
public class Draw implements Serializable {
    private final long SerialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_draw;

    //lista de todos os apostadores desse sorteio
    @OneToMany(cascade = CascadeType.ALL)
    private List<Bet> bets = new ArrayList<>();

    @ElementCollection
    private int[] chosenNumbers = new int[25];

    //lista de apostadores que acertaram os números sorteados
    @OneToMany(cascade = CascadeType.ALL)
    private List<Bet> winners = new ArrayList<>();

    public Draw() {
    }

    public Draw(long id_draw, List<Bet> bets, int[] chosenNumbers, List<Bet> winners) {
        this.id_draw = id_draw;
        this.bets = bets;
        this.chosenNumbers = chosenNumbers;
        this.winners = winners;
    }


    public long getId_draw() {
        return id_draw;
    }

    public void setId_draw(long id_draw) {
        this.id_draw = id_draw;
    }

    public List<Bet> getBets() {
        return bets;
    }

    public void setBets(List<Bet> bets) {
        this.bets = bets;
    }

    public int[] getChosenNumbers() {
        return chosenNumbers;
    }

    public void setChosenNumbers(int[] chosenNumbers) {
        this.chosenNumbers = chosenNumbers;
    }

    public List<Bet> getWinners() {
        return winners;
    }

    public void setWinners(List<Bet> winners) {
        this.winners = winners;
    }


    public void addBet(Bet bet){
        bets.add(bet);
    }

    public void addWinner(Bet bet){
        winners.add(bet);
    }





}
