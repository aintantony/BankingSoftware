import model.Account;
import model.CreditAccount;
import model.SavingAccount;

import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    static void ui() {
        System.out.println("╔══════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                              BANKING SERVICE                             ║");
        System.out.println("╠══════════════════════════════════════════════════════════════════════════╣");
        System.out.println("║  1.   Show Balance                                                       ║");
        System.out.println("║  2.   Withdraw                                                           ║");
        System.out.println("║  3.   Deposit                                                            ║");
        System.out.println("║  4.   Get USD Currency                                                   ║");
        System.out.println("║  5.   Get KHR Currency                                                   ║");
        System.out.println("║  6.   Show User Information                                              ║");
        System.out.println("╠══════════════════════════════════════════════════════════════════════════╣");
        System.out.println("║  Enter '0' -> Exit                                                       ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════════════╝");
        System.out.print("[+] Enter your option: ");
    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Account savingAccount = new SavingAccount("Antony");
        Account creditCardAccount = new CreditAccount("111-222-333-444-5555",
                LocalDateTime.of(2026, 1, 1, 1, 1),
                333);
        Account user;
        do {
            if (creditCardAccount.login(input)) {
                while (true) {
                    ui();
                    short option;
                    try {
                        option = input.nextShort();
                    } catch (InputMismatchException e) {
                        System.out.println("[!] Invalid, Enter number only.");
                        input.nextLine();
                        continue;
                    }
                    switch (option) {
                        case 1 -> savingAccount.showBalance();
                        case 2 -> savingAccount.withdraw(input);
                        case 3 -> savingAccount.deposit(input);
                        case 4 -> savingAccount.getUSDCurrency();
                        case 5 -> savingAccount.getKHCurrency();
                        case 6 -> {
                            user = Account.builder()
                                    .accountName("Antony")
                                    .id("00001")
                                    .uuid(UUID.randomUUID().toString())
                                    .email("antony@gmail.com")
                                    .password("123456789")
                                    .build();

                            // Create table with proper spacing and borders
                            Table table = new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);

                            // Define column names with proper array syntax and consistent naming
                            String[] columnNames = {
                                    "ID",
                                    "UUID",
                                    "User Name",
                                    "Email",
                                    "Password"
                            };

                            // Set column headers with consistent styling
                            for (int i = 0; i < columnNames.length; i++) {
                                CellStyle headerStyle = new CellStyle(CellStyle.HorizontalAlign.center);
                                table.addCell(columnNames[i], headerStyle);
                                // Set reasonable column widths based on content
                                int minWidth = 15;
                                int maxWidth = columnNames[i].equals("UUID") ? 40 : 25;
                                table.setColumnWidth(i, minWidth, maxWidth);
                            }

                            // Add user data with consistent styling
                            CellStyle dataStyle = new CellStyle(CellStyle.HorizontalAlign.center);
                            table.addCell(user.getId(), dataStyle);
                            table.addCell(user.getUuid(), dataStyle);
                            table.addCell(user.getAccountName(), dataStyle);
                            table.addCell(user.getEmail(), dataStyle);
                            table.addCell(user.getPassword(), dataStyle);

                            System.out.println(table.render());
                        }
                        case 0 -> {
                            return;
                        }
                        default -> System.out.println("[!] Invalid input");
                    }
                }
            } else {
                System.out.println("[!] Invalid Card Number or Secret Card.");
                input.nextLine();
            }
        } while (!creditCardAccount.login(input));
    }
}