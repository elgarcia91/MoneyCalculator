package moneycalculator.UI.Console;

import java.util.Scanner;
import moneycalculator.Model.Currency;
import moneycalculator.Model.Number;
import moneycalculator.Model.Money;
import moneycalculator.UI.CurrencyDialog;
import moneycalculator.UI.MoneyDialog;

public class ConsoleMoneyDialog implements MoneyDialog {

    private Money money;
    private Currency currency;

    @Override
    public void dialog() {
        System.out.println("Enter the amount: ");
        Scanner scanner = new Scanner(System.in);
        Number amount = new Number(scanner.nextDouble()) {
        };
        getCurrency();
        money = new Money(amount, currency);
    }

    @Override
    public Money getMoney() {
        return money;
    }

    private void getCurrency() {
        CurrencyDialog consoleCurrencyDialog = new ConsoleCurrencyDialog();
        consoleCurrencyDialog.dialog("");
        currency = consoleCurrencyDialog.getCurrency();
    }
}
