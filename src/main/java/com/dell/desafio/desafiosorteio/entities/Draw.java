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
    private long id_draw;
    private boolean finished = false;

    //lista de todos os apostadores desse sorteio
    @OneToMany(cascade = CascadeType.ALL)
    private List<Bet> bets = new ArrayList<>();


    private int[] chosenNumbers = new int[25];

    //lista de apostadores que acertaram os números sorteados
    @OneToMany(cascade = CascadeType.ALL)
    private List<Bet> winners = new ArrayList<>();


    //inicia um novo sorteio zerado
    public Draw(){
        this.chosenNumbers = drawNumbers();
    }


    public Draw(List<Bet> bets, int[] chosenNumbers, List<Bet> winners) {
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
        this.chosenNumbers = drawNumbers();
    }

    public int[] drawNumbers() {
        int[] nums = new int[5];
        for (int i = 0; i < 5; i++) {
            nums[i] = (int) (Math.random() * 50) + 1;
            System.out.println(nums[i]);
        }
        return nums;
    }

    public void addNumber(int index){
        this.chosenNumbers[index] = (int) (Math.random() * 50);
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

    public void printWinners(){
        winners.sort(Comparator.comparing(Bet::getBetterName));
        System.out.println("Mostrando os vencedores do sorteio: ");
        for (Bet bet : winners) {
            System.out.println(bet.getBetterName());
        }
    }

    public void sorteio(){
        boolean hasWinner = false;
        for(Bet bet : bets){
            int acertos = 0;
            for(int i=0; i< 5; i++){
                for(int j=0; j<chosenNumbers.length; j++){
                    if(bet.getChosenNumbers()[i] == chosenNumbers[j]){
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
            for (int i = 5; i < 25; i++){
                addNumber(i);
                sorteio();
            }
            if(!hasWinner){
                System.out.println("Este sorteio não teve vencedores.");
            }
        }else{
            printWinners();
        }


    }


    public boolean isFinished() {
        return finished;
    }

}
