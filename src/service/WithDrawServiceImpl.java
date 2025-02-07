package service;

import constants.NoteEnum;
import display.DisplayService;
import model.AtmMachine;
import validation.WithdrawValidation;

import java.util.*;

/**
 * Performs all activities of withdraw functionality
 */
public class WithDrawServiceImpl implements WithDrawService {

    private final AtmMachine atmMachine;
    private final DisplayService displayService;
    private final WithdrawValidation withdrawValidation;
    private final Scanner scanner;


    public WithDrawServiceImpl(AtmMachine atmMachine, DisplayService displayService, Scanner scanner) {
        this.atmMachine = atmMachine;
        this.displayService = displayService;
        this.scanner = scanner;
        this.withdrawValidation = new WithdrawValidation();
    }

    /**
     * Withdraw the amount from ATM if available balance and sufficient required notes available
     */
    @Override
    public void withDraw() {

        //creating new hashmap because if requested amount not adjusted with available notes
        // our original map should get preserved.
        Map<Integer, Integer> noteMap = new HashMap<>(atmMachine.getNoteMap());
        NoteEnum[] sortedKeys = NoteEnum.values();
        Map<Integer, Integer> withdrawMap = new HashMap<>();
        double amount = getWithdrawAmount();
        if (withdrawValidation.validateWithdrawAmount(amount)) {
            return;
        }
        double remainingAmount = amount;
        for (NoteEnum key : sortedKeys) {
            int enumValue = key.getValue();
            if (remainingAmount >= enumValue) {
                int requiredNotes = (int) (remainingAmount / enumValue);
                int availableNotes = noteMap.get(enumValue);
                if (availableNotes < requiredNotes) {
                    remainingAmount -= availableNotes * enumValue;
                    withdrawMap.put(enumValue, availableNotes);
                    noteMap.put(enumValue, 0);
                } else {
                    remainingAmount -= requiredNotes * enumValue;
                    withdrawMap.put(enumValue, requiredNotes);
                    noteMap.put(enumValue, availableNotes - requiredNotes);
                }
            }
        }
        // After completing all available notes still requested amount is not available then stop withdraw
        if (withdrawValidation.validateRemainingAmount(remainingAmount)) {
            return;
        }
        setAtmMachineValues(noteMap, amount);
        displayService.display("Amount withdrawn with notes: " + withdrawMap);
    }

    /**
     * setting the values to atmMachine
     */
    private void setAtmMachineValues(Map<Integer, Integer> noteMap, double amount) {
        atmMachine.setNoteMap(noteMap);
        atmMachine.setBalance(atmMachine.getBalance() - amount);
    }

    /**
     * return amount to be withdrawn
     */
    private Double getWithdrawAmount() {
        displayService.display("Enter the amount to withdraw : ");
        return scanner.nextDouble();
    }
}
