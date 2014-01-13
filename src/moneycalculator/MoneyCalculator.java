package moneycalculator;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import moneycalculator.Command.ActionListenerFactory;
import moneycalculator.Command.Command;
import moneycalculator.Command.CommandMap;
import moneycalculator.Model.Currency;
import moneycalculator.Persistence.FileExchangeRateLoader;
import moneycalculator.Model.ExchangeRate;
import moneycalculator.Model.Money;
import moneycalculator.Model.MoneyExchanger;
import moneycalculator.Persistence.FileCurrencySetLoader;
import moneycalculator.Persistence.CurrencySetLoader;
import moneycalculator.Persistence.ExchangeRateLoader;
import moneycalculator.UI.Console.ConsoleCurrencyDialog;
import moneycalculator.UI.Console.ConsoleMoneyDialog;
import moneycalculator.UI.Console.ConsoleMoneyViewer;
import moneycalculator.UI.CurrencyDialog;
import moneycalculator.UI.MoneyDialog;
import moneycalculator.UI.MoneyViewer;

public class MoneyCalculator {

    private static void switchConsoleSwing(String[] args) {
        if (args[0].equals("0")) {
            new MoneyCalculator().runConsole(args[1], args[2]);
        } else {
            new MoneyCalculator().runSwing(args[1], args[2]);
        }

    }
    private CurrencySetLoader currencyLoader;
    private ExchangeRateLoader exchangeLoader;
    private MoneyExchanger exchanger;
    private ApplicationFrame frame;

    public static void main(String[] args) {
        switchConsoleSwing(args);
    }

    private void runConsole(String fileCurrencies, String fileRates) {
        initialize(fileCurrencies, fileRates);
        MoneyDialog moneyDialog = new ConsoleMoneyDialog();
        CurrencyDialog currencyDialog = new ConsoleCurrencyDialog();
        ExchangeRate rate;
        moneyDialog.dialog();
        System.out.println("to: ");
        currencyDialog.dialog("");
        rate = exchangeLoader.load(moneyDialog.getMoney().getCurrency(), currencyDialog.getCurrency());
        Money result = exchanger.exchange(moneyDialog.getMoney().getAmount(), rate);
        MoneyViewer moneyViewer = new ConsoleMoneyViewer(result);
        moneyViewer.showMoney();
    }

    private void runSwing(String fileCurrencies, String fileRates) {
        initialize(fileCurrencies, fileRates);
        ActionListenerFactory factory = new ActionListenerFactory(createCommands());
        frame = new ApplicationFrame(factory);
    }

    private CommandMap createCommands() {

        CommandMap commands = new CommandMap();
        commands.add("exit", new Command() {
            @Override
            public void execute() {
                frame.dispose();
            }
        });
        commands.add("calc", new Command() {
            @Override
            public void execute() {
                Money money = frame.getMoneyDialog().getMoney();
                ExchangeRate rate = exchangeLoader.load(money.getCurrency(), new Currency("USD", "Dólar Americano", "$"));//frame.getCurrencyDialog().getCurrency();
                Money result = exchanger.exchange(money.getAmount(), rate);
                String param = result.toString();
                frame.add(createTextField(param),BorderLayout.SOUTH);
                MoneyViewer viewer = frame.getMoneyViewer();
                viewer.setMoney(result);
                viewer.showMoney();
            }
        });
        return commands;
    }
    
    private JLabel createTextField(String param){
        JLabel text = new JLabel(" ");
        text.setText(param);
        return text;
    }

    private void initialize(String fileCurrencies, String fileRates) {
        currencyLoader = new FileCurrencySetLoader(fileCurrencies);//o lo que sea que vaya a cargar
        currencyLoader.load();
        exchangeLoader = new FileExchangeRateLoader(fileRates);//o lo que sea que vaya a cargar
        exchanger = new MoneyExchanger();
    }
}
