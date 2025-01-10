package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CreditAccount extends Account{
    private String cardNumber;
    private LocalDateTime expiredDate;
    private int cvv;
    public CreditAccount(String accountName){
        super(accountName);
    }
    //
    public boolean login(Scanner input) {
        String inputCardNumber;
        int inputCVV;
        while (true) {
            System.out.println("╔══════════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                                  Log In                                  ║");
            System.out.println("╠══════════════════════════════════════════════════════════════════════════╣");
            System.out.print(" [+] Enter your card number: ");
            inputCardNumber = input.nextLine();
            System.out.print(" [+] Enter your CVV number: ");

            try {
                inputCVV = input.nextInt();
                System.out.println("╚══════════════════════════════════════════════════════════════════════════╝");
                break;
            } catch (InputMismatchException e) {
                System.out.println("╠══════════════════════════════════════════════════════════════════════════╣");
                System.out.println("║ [!] CVV should be a numeric value. Please try again.                     ║");
                System.out.println("╚══════════════════════════════════════════════════════════════════════════╝");
                input.nextLine(); // Clear invalid input
            }
        }

        return this.cardNumber.equals(inputCardNumber) && this.cvv == inputCVV;
    }
}