package com.dell.desafio.desafiosorteio.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.UniqueElements;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "aposta")
public class Bet implements Serializable {

   //id must start at 1000
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bet_sequence")
    @SequenceGenerator(name = "bet_sequence", sequenceName = "bet_sequence", allocationSize = 1, initialValue = 1000)
    private long idBet;

    @NotNull
    @Column(name = "nome_apostador")
    private String betterName;

    @NotNull
    @Column(name = "cpf_apostador")
    private String betterCPF;

    @Column(name = "numeros_escolhidos")
    private int[] chosenNumbers = new int[5];

    @ManyToOne
    private Draw draw;

    private boolean winner;

    public Bet() {
    }

    public Bet(String betterName, String betterCPF) {
        this.betterName = betterName;
        this.betterCPF = betterCPF;
    }

    public Bet(String betterName, String betterCPF, int[] chosenNumbers) {
        this.betterName = betterName;
        this.betterCPF = betterCPF;
        this.chosenNumbers = chosenNumbers;
    }

    public Bet(long idBet, String betterName, String betterCPF, int[] chosenNumbers) {
        this.idBet = idBet;
        this.betterName = betterName;
        this.betterCPF = betterCPF;
        this.chosenNumbers = chosenNumbers;
    }

    public Bet(long idBet, String betterName, String betterCPF, int[] chosenNumbers, boolean winner) {
        this.idBet = idBet;
        this.betterName = betterName;
        this.betterCPF = betterCPF;
        this.chosenNumbers = chosenNumbers;
        this.winner = winner;
    }

    public long getIdBet() {
        return idBet;
    }

    public void setIdBet(long idBet) {
        this.idBet = idBet;
    }

    public String getBetterName() {
        return betterName;
    }

    public void setBetterName(String betterName) {
        this.betterName = betterName;
    }

    public String getBetterCPF() {
        return betterCPF;
    }

    public void setBetterCPF(String betterCPF) {
        this.betterCPF = betterCPF;
    }

    public int[] getChosenNumbers() {
        return chosenNumbers;
    }

    public void setChosenNumbers(int[] chosenNumbers) {
        this.chosenNumbers = chosenNumbers;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bet bet = (Bet) o;
        return idBet == bet.idBet;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBet);
    }

    public int[] surprise() {
        int[] nums = new int[5];
        for (int i = 0; i < 5; i++) {
            nums[i] = (int) (Math.random() * 50) + 1;
            System.out.println(nums[i]);
        }
        return nums;
    }

    public boolean checkNumbers(){
        for(int i = 0; i < 5; i++){
            if(chosenNumbers[i] > 50 || chosenNumbers[i] < 1){
                return false;
            }
        }
        return true;
    }

    public void updateData(Bet bet) {
        this.setBetterName(bet.getBetterName());
        this.setBetterCPF(bet.getBetterCPF());
        this.setChosenNumbers(bet.getChosenNumbers());
        this.setWinner(bet.isWinner());
    }
}
