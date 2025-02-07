package utility;

import display.DisplayService;
import model.AtmMachine;
import service.DepositService;
import service.DepositServiceImpl;
import service.WithDrawService;
import service.WithDrawServiceImpl;
import java.util.Scanner;

/**
 * Class which acts as controller for application
 */
public class AtmUtility {

    /**
     * Displays the different options to select
     * and route to respective service based on selection
     */
    public void run() {
        AtmMachine atmMachine = AtmMachine.getInstance();
        Scanner scanner = new Scanner(System.in);
        DisplayService displayService = new DisplayService();
        DepositService depositService = new DepositServiceImpl(atmMachine,displayService,scanner);
        WithDrawService withDrawService = new WithDrawServiceImpl(atmMachine,displayService,scanner);
        displayService.display("Welcome to ATM Machine..");
        while(true){
            displayService.display("\n 1. Deposit \n 2. Withdraw \n 3. Display \n 4. Exit");
            displayService.display("Enter Your Choice ");
            int selection = scanner.nextInt();
            switch (selection){
                case 1:
                    depositService.deposite();
                    break;
                case 2:
                    withDrawService.withDraw();
                    break;
                case 3: displayService.display(atmMachine.toString());
                    break;
                case 4:
                    displayService.display("Thank you visit again..");
                    return;
                default: displayService.display("Invalid selection");
            }
        }
    }
}
