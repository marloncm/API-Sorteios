package com.dell.desafio.desafiosorteio.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Entity
@Table(name = "sorteio")
public class Draw implements Serializable {
    private final long SerialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idDraw;
    private boolean finished = false;

    //lista de todos os apostadores desse sorteio
    @OneToMany(cascade = CascadeType.ALL)
    private List<Bet> bets = new ArrayList<>();


   // private int[] chosenNumbers = new int[25];

    @ElementCollection
    private List<Integer> numerosSorteados = new ArrayList<>();

    //lista de apostadores que acertaram os números sorteados
    @OneToMany(cascade = CascadeType.ALL)
    private List<Bet> winners = new ArrayList<>();


    //inicia um novo sorteio zerado
    public Draw(){
        this.numerosSorteados = drawNumbers();
    }


  /*  public Draw(List<Bet> bets, int[] chosenNumbers, List<Bet> winners) {
        this.bets = bets;
        this.chosenNumbers = chosenNumbers;
        this.winners = winners;
    }*/


    public long getIdDraw() {
        return idDraw;
    }

    public void setIdDraw(long idDraw) {
        this.idDraw = idDraw;
    }

    public List<Bet> getBets() {
        return bets;
    }

    public void setBets(List<Bet> bets) {
        this.bets = bets;
    }

  /*  public int[] getChosenNumbers() {
        return chosenNumbers;
    }

    public void setChosenNumbers(int[] chosenNumbers) {
        this.chosenNumbers = chosenNumbers;
    }*/

    public List<Integer> drawNumbers() {
        List<Integer> numeros = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            numeros.add((int) (Math.random() * 50) + 1) ;
        }
        return numeros;
    }

    public void addNumber(){
        numerosSorteados.add((int) (Math.random() * 50));
    }

    public List<Bet> getWinners() {
        return winners;
    }

    public void setWinners(List<Bet> winners) {
        this.winners = winners;
    }

    public List<Integer> getChosenNumbers() {
        return numerosSorteados;
    }

    public void setChosenNumbers(List<Integer> chosenNumbers) {
        this.numerosSorteados = chosenNumbers;
    }


    public void addBet(Bet bet){
        bets.add(bet);
    }

    public void addWinner(Bet bet){
        winners.add(bet);
    }

/*
    public void printWinners(){
        winners.sort(Comparator.comparing(Bet::getBetterName));
        System.out.println("Mostrando os vencedores do sorteio: ");
        for (Bet bet : winners) {
            System.out.println(bet.getBetterName());
        }
    }
*/


    public void sorteio(){
        boolean hasWinner = false;
        for(Bet bet : bets){ //pra cada aposta...
            int acertos = 0;
            for(int i=0; i< 5; i++){ // pra cada numero apostado...

                for(Integer numero: numerosSorteados){
                    if(bet.getChosenNumbers()[i] == numero){
                        acertos++;

                    }
                    if(acertos == 5){
                        bet.setWinner(true);
                        hasWinner = true;
                        winners.add(bet);
                    }
                }
            }
        }

        if(!hasWinner){
            for (int i = 0; i < 20; i++){
                addNumber();
                sorteio();
            }
            if(!hasWinner){
                System.out.println("Este sorteio não teve vencedores.");
            }
        }
        finished = true;

    }


    public boolean isFinished() {
        return finished;
    }


    public void updateData(Draw draw) {
        this.setBets(draw.getBets());
        this.setChosenNumbers(draw.getChosenNumbers());
        this.setWinners(draw.getWinners());
    }
}
