package model;

import jdk.jshell.Snippet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import service.BankingService;



import java.util.InputMismatchException;
import java.util.Scanner;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Account implements BankingService {
    private String id;
    private String uuid;
    private String email;
    private String password;

    private double cash = 1000000;
    private String accountName;
    public Account(String accountName){
        this.accountName = accountName;
    }
    public boolean login(Scanner input) {
        return false;
    }
    public Account(){}
    @Override
    public void withdraw(Scanner input) {
        double cash = 0.0;
        while (true) {
            System.out.print("[+] Enter the cash you want to withdraw: ");
            try {
                cash = input.nextDouble();
                break;
            } catch (InputMismatchException e) {
                System.out.println("[!] Invalid, Input cash in number.");
                input.nextLine();
                continue;
            }
        }
        if(cash>this.cash){
            System.out.println("[!] Cannot withdraw, you do not have enough balance");
            return;
        }
        else if (cash <= 0) {
            System.out.println("[!] Negative number or '0' are not allowed.");
            return;
        }
        this.cash-=cash;// this.cash = this.cash-cash;
        System.out.println("[+] Your transaction in " + this.accountName + " account" + " withdrawn successfully");
    }

    @Override
    public void deposit(Scanner input) {
        double cash = 0.0;
        while (true) {
            System.out.println("╔════════════════════════════════════════════════╗");
            System.out.print("║ [+] Enter the cash you want to deposit:        ║\n╚> ");
            try {
                cash = input.nextDouble();
                break;
            } catch (InputMismatchException e) {
                System.out.println("╔════════════════════════════════════════════════╗");
                System.out.println("║ [!] Invalid input. Please enter a valid number.║");
                System.out.println("╚════════════════════════════════════════════════╝");
                input.nextLine(); // Clear invalid input
            }
        }

        if (cash <= 0.0) {
            System.out.println("╔════════════════════════════════════════════════╗");
            System.out.println("║ [!] Cannot deposit amounts less than or equal  ║");
            System.out.println("║     to 0. Please try again.                    ║");
            System.out.println("╚════════════════════════════════════════════════╝");
            return;
        }

        this.cash += cash;
        System.out.println("╔════════════════════════════════════════════════╗");
        System.out.println("║ [+] Your transaction in \"" + this.accountName + "\" account    ║");
        System.out.println("║     has been deposited successfully.          ║");
        System.out.println("╚════════════════════════════════════════════════╝");
    }

    @Override
    public void showBalance() {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                          Your Balance                        ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.println("║ [+] Your balance: " + this.cash + " $                        ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }

    @Override
    public void getKHCurrency() {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                   Your Balance in KHR                        ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.println("║ KH: " + (this.cash * 4080) + " KHR                           ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }

    @Override
    public void getUSDCurrency() {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                   Your Balance in USD                        ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.println("║ USD: " + this.cash + " $                                     ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }


//    public String getId() {
//        return this.id;
//    }
//    public String getUuid() {
//        return this.uuid;
//    }
//    public String getEmail() {
//        return this.email;
//    }
//    public String getPassword() {
//        return this.password;
//    }
//    public String getAccountName() {
//        return this.accountName;
//    }
}