package service;
import java.util.Scanner;
public interface BankingService {
    void withdraw(Scanner input);
    void deposit(Scanner input);
    void showBalance();
    void getKHCurrency();
    void getUSDCurrency();
}