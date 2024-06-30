import java.util.HashMap;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, Double> rates = new HashMap<>();
        rates.put("USD", 1.00);
        rates.put("EUR", 0.90);
        rates.put("JPY", 120.50);
        rates.put("CAD", 1.25);
        rates.put("GBP", 0.80);
        rates.put("AUD", 1.40);
        rates.put("CHF", 0.95);

        boolean repeat = true;
        do {
            System.out.print("Enter the amount to convert: ");
            double amount = getAmount(scanner);

            String fromCurrency = getCurrency(scanner, "Enter the currency you want to convert from (e.g., USD, EUR): ");
            String toCurrency = getCurrency(scanner, "Enter the currency you want to convert to (e.g., JPY, CAD): ");

            double convertedAmount = convert(amount, fromCurrency, toCurrency, rates);
            System.out.println(amount + " " + fromCurrency + " is equivalent to " + convertedAmount + " " + toCurrency);

            System.out.print("Do you want to convert another amount? (y/n): ");
            String response = scanner.nextLine().toLowerCase();
            repeat = response.startsWith("y");
        } while (repeat);

        scanner.close();
    }

    public static double getAmount(Scanner scanner) {
        while (true) {
            try {
                double amount = scanner.nextDouble();
                scanner.nextLine();
                if (amount > 0) {
                    return amount;
                } else {
                    System.out.println("Please enter a positive amount.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid amount.");
                scanner.nextLine();
            }
        }
    }

    public static String getCurrency(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String currency = scanner.nextLine().toUpperCase();
            if (currency.length() == 3 && currency.matches("[A-Z]+")) {
                return currency;
            } else {
                System.out.println("Invalid currency code. Please enter a valid 3-letter code (e.g., USD, EUR).");
            }
        }
    }

    public static double convert(double amount, String fromCurrency, String toCurrency, HashMap<String, Double> rates) {
        double fromRate = rates.get(fromCurrency);
        double toRate = rates.get(toCurrency);
        if (fromRate == 0 || toRate == 0) {
            System.out.println("Invalid currency code. Please try again.");
            return 0;
        }
        return amount * (toRate / fromRate);
    }
}
