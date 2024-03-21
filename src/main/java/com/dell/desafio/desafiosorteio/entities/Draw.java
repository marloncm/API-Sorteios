package com.dell.desafio.desafiosorteio.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Draw implements Serializable {
    private final long SerialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idDraw;
    private boolean finished = false;

    //lista de todos os apostadores desse sorteio
    @OneToMany(cascade = CascadeType.ALL)
    private List<Bet> bets = new ArrayList<>();

    @ElementCollection
    private List<Integer> chosenNumbers = new ArrayList<>();

    //lista de apostadores que acertaram os números sorteados
    @OneToMany(cascade = CascadeType.ALL)
    private List<Bet> winners = new ArrayList<>();


    //Construtores

    //inicia um novo sorteio zerado e já com os 5 primeiros números sorteados
    public Draw(){
        this.chosenNumbers = drawNumbers();
    }


    //Getters e Setters
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

    //adiciona um novo número para a lista de números sorteados
    public void addNumber(){
        chosenNumbers.add((int) (Math.random() * 50));
    }

    public List<Bet> getWinners() {
        return winners;
    }

    public void setWinners(List<Bet> winners) {
        this.winners = winners;
    }

    public List<Integer> getChosenNumbers() {
        return chosenNumbers;
    }

    public void setChosenNumbers(List<Integer> chosenNumbers) {
        this.chosenNumbers = chosenNumbers;
    }

    public void addBet(Bet bet){
        bets.add(bet);
    }

    public boolean isFinished() {
        return finished;
    }

    public void addWinner(Bet bet){
        winners.add(bet);
    }


    //sorteia 5 números aleatórios
    public List<Integer> drawNumbers() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            numbers.add((int) (Math.random() * 50) + 1) ;
        }
        return numbers;
    }

    //lógica para apuração dos vencedores
    public Draw checkWinner(){
        boolean hasWinner = false;
        for(Bet bet : bets){ //para cada aposta...
            int hits = 0;
            for(Integer betNumber: bet.getChosenNumbers()){ // para cada numero apostado...
                for(Integer drawNumber: chosenNumbers){ //para cada numero sorteado...
                    if(betNumber.intValue() == drawNumber.intValue()){
                        hits++; //se encontrar um número sorteado igual a um número apostado, incrementa o contador de acertos

                    }
                    if(hits == 5){ //se o contador de acertos chegar a 5, a aposta é vencedora

                        hasWinner = true; //não precisa sortear um número adicional
                        winners.add(bet);
                    }
                }
            }
        }

        if(!hasWinner){ //se não houver vencedores, sorteia mais 20 números
            for (int i = 0; i < 20; i++){
                addNumber(); //adiciona um número sorteado
                checkWinner(); //chama o método novamente para verificar se há vencedores
            }
            if(!hasWinner){ //se mesmo após 25 números sorteados não houver vencedores, o sorteio é finalizado
                System.out.println("Este sorteio não teve vencedores.");
            }
        }
        finished = true;

        return this;
    }




    public String printNumerosSorteados(){
        String numeros = "";
        for (Integer numero: chosenNumbers) {
            numeros += numero + " ";
        }
        return numeros;
    }

    public String printWinners(){
        String winners = "Total de vencedores: " + this.winners.size() + "\n";
        for (Bet bet: this.winners) {
            winners += bet.getBetterName() + "\n";
        }
        return winners;
    }

    public void updateData(Draw draw) {
        this.chosenNumbers = draw.getChosenNumbers();
        this.winners = draw.getWinners();
        this.bets = draw.getBets();
        this.finished = draw.isFinished();
    }
}
