package com.dell.desafio.desafiosorteio.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.UniqueElements;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Entity
public class Bet implements Serializable {

   //id must start at 1000
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bet_sequence")
    @SequenceGenerator(name = "bet_sequence", sequenceName = "bet_sequence", allocationSize = 1, initialValue = 1000)
    private long idBet;

    @NotNull
    private String betterName;

    @NotNull
    private String betterCPF;

    //private int[] chosenNumbers = new int[5];
    @ElementCollection
    private List<Integer> chosenNumbers = new ArrayList<>();

    @ManyToOne
    private Draw draw;



    public Bet() {
    }

    public Bet(String betterName, String betterCPF) {
        this.betterName = betterName;
        this.betterCPF = betterCPF;
    }

    public Bet(String betterName, String betterCPF, List<Integer> chosenNumbers) {
        this.betterName = betterName;
        this.betterCPF = betterCPF;
        this.chosenNumbers = chosenNumbers;
    }

    public Bet(String betterName, String betterCPF, int[] numbers) {
        this.betterName = betterName;
        this.betterCPF = betterCPF;
        for (int i = 0; i < 5; i++) {
            this.chosenNumbers.add(numbers[i]);
        }
    }

    public Bet(long idBet, String betterName, String betterCPF, List<Integer> chosenNumbers) {
        this.idBet = idBet;
        this.betterName = betterName;
        this.betterCPF = betterCPF;
        this.chosenNumbers = chosenNumbers;
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

    public List<Integer> getChosenNumbers() {
        return chosenNumbers;
    }

    public void setChosenNumbers(List<Integer> chosenNumbers) {
        this.chosenNumbers = chosenNumbers;
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

    public List<Integer> surprise() {
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            nums.add((int) (Math.random() * 50) + 1);
        }
        return nums;
    }

    public boolean checkNumbers(){
        for(Integer i : chosenNumbers){
            if(i > 50 || i < 1){
                return false;
            }
        }
        return true;
    }

    public void updateData(Bet bet) {
        this.setBetterName(bet.getBetterName());
        this.setBetterCPF(bet.getBetterCPF());
        this.setChosenNumbers(bet.getChosenNumbers());
    }
}
