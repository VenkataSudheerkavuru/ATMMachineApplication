package model;

import java.util.HashMap;
import java.util.Map;

public class AtmMachine {
    private Double balance;
    private Map<Integer,Integer> noteMap;
    private final static AtmMachine instance = new AtmMachine();
    private AtmMachine(){
        noteMap = new HashMap<>();
        balance = 0.0;
    }
    public static AtmMachine getInstance(){
        return instance;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Map<Integer, Integer> getNoteMap() {
        return noteMap;
    }

    public void setNoteMap(Map<Integer, Integer> noteMap) {
        this.noteMap = noteMap;
    }

    public void addNotesToMap(int note,int count){
        noteMap.put(note,noteMap.getOrDefault(note,0)+count);
    }

    @Override
    public String toString() {
        return "balance=" + balance + ", noteMap=" + noteMap ;
    }
}
