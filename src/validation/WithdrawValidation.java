package validation;

import display.DisplayService;
import model.AtmMachine;

/**
 * validations for withdraw service
 */
public class WithdrawValidation {
    AtmMachine atmMachine = AtmMachine.getInstance();
    DisplayService displayService = new DisplayService();

    /**
     *
     * @param amount entered by customer
     * @return true if Machine don't have expected amount
     */
    public boolean validateWithdrawAmount(double amount) {
        if (amount < 0 || amount > atmMachine.getBalance()) {
            displayService.display("Insufficient balance");
            return true;
        }
        return false;
    }

    /**
     * @param remainingAmount After completing all available notes still
     *                        requested amount is not adjusted then remaining amount is not 0
     */
    public boolean validateRemainingAmount(double remainingAmount) {
        if (remainingAmount != 0) {
            displayService.display("Withdraw unsuccessful");
            return true;
        }
        return false;
    }
}
