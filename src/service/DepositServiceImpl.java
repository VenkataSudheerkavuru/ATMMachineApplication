package service;

import constants.NoteEnum;
import display.DisplayService;
import model.AtmMachine;
import validation.DepositValidation;

import java.util.Scanner;

/**
 * Performs all activities of deposit functionality
 */
public class DepositServiceImpl implements DepositService {

    private final AtmMachine atmMachine;
    private final DepositValidation depositValidation;
    private final DisplayService displayService;
    private final Scanner scanner;

    public DepositServiceImpl(AtmMachine atmMachine, DisplayService displayService, Scanner scanner) {
        this.atmMachine = atmMachine;
        this.displayService = displayService;
        this.scanner = scanner;
        this.depositValidation = new DepositValidation();
    }

    /**
     * Calculate the amount based on given Notes and noteCount and deposit to ATM
     */
    @Override
    public void deposite() {
        Double balance = atmMachine.getBalance();
        NoteEnum[] noteEnums = getNoteEnums();

        balance = getBalance(noteEnums, balance);
        atmMachine.setBalance(balance);
        displayService.display("After Depositing Atm machine contains : " + atmMachine);
    }

    private Double getBalance(NoteEnum[] noteEnums, Double balance) {
        for (NoteEnum noteEnum : noteEnums) {
            int note = noteEnum.getValue();
            int noteCount = getNoteCount(note);
            if (depositValidation.validateNoteCount(noteCount)) {
                displayService.display("Enter positive value only.");
            } else {
                atmMachine.addNotesToMap(note, noteCount);
                balance = balance + noteCount * note;
            }
        }
        return balance;
    }

    /**
     * @return note count for the given note
     */
    private int getNoteCount(int note) {
        displayService.display("Enter how many " + note + " notes will be deposited :");
        return scanner.nextInt();
    }

    /**
     * @return noteEnum array
     */
    private NoteEnum[] getNoteEnums() {
        return NoteEnum.values();
    }
}
