package moneycalculator.UI.Console;

import java.util.Scanner;
import moneycalculator.Model.Currency;
import moneycalculator.Model.CurrencySet;
import moneycalculator.UI.CurrencyDialog;

public class ConsoleCurrencyDialog implements CurrencyDialog {

    private Currency currency;

    @Override
    public void dialog(String label) {
        Currency searchResult;
        while (true) {
            System.out.println("Enter the currency: ");
            Scanner scanner = new Scanner(System.in);
            CurrencySet currencies = CurrencySet.getInstance();
            searchResult = currencies.searchCurrency(scanner.nextLine());
            if (searchResult == null) {
                System.out.println("Not found any currency. Try again");
            } else {
                currency = searchResult;
                break;
            }
        }
    }

    @Override
    public Currency getCurrency() {
        return currency;
    }
}
