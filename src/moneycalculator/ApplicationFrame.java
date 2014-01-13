package moneycalculator;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import moneycalculator.Command.ActionListenerFactory;
import moneycalculator.Model.Currency;
import moneycalculator.Model.Money;
import moneycalculator.UI.CurrencyDialog;
import moneycalculator.UI.MoneyDialog;
import moneycalculator.UI.MoneyViewer;
import moneycalculator.UI.Swing.SwingCurrencyDialog;
import moneycalculator.UI.Swing.SwingMoneyDialog;
import moneycalculator.UI.Swing.SwingMoneyViewer;

public class ApplicationFrame extends JFrame {

    private ActionListenerFactory factory;
    private MoneyDialog money;
    private CurrencyDialog currencyTo;
    private MoneyViewer moneyViewer;

    ApplicationFrame(ActionListenerFactory factory) {
        super("Money Calculator");
        this.factory = factory;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(600,200);
        this.setLocationRelativeTo(null);
        this.createComponents();
        this.setVisible(true);
    }

    private void createComponents() {
        this.add(createInputPanel(), BorderLayout.NORTH);
        this.add(createToolbar(), BorderLayout.CENTER);
        this.add(createOutputPanel(), BorderLayout.SOUTH);
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(new JLabel("Convert: "));
        panel.add(createSwingMoneyDialog());
        panel.add(new JLabel("To: "));
        panel.add(createSwingCurrencyDialog());
        return panel;
    }

    private JPanel createToolbar() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(createCalculateButton());
        panel.add(createExitButton());
        return panel;

    }

    private JPanel createOutputPanel() {
        SwingMoneyViewer panel = new SwingMoneyViewer();
        panel.showMoney();
        moneyViewer = panel;
        return panel;
    }

    private JPanel createSwingMoneyDialog() {
        SwingMoneyDialog panel = new SwingMoneyDialog();
        panel.dialog();
        money = panel;
        return panel;
    }

    private JPanel createSwingCurrencyDialog() {
        SwingCurrencyDialog panel = new SwingCurrencyDialog();
        panel.dialog("");
        currencyTo = panel;
        return panel;
    }

    private JButton createCalculateButton() {
        JButton calculate = new JButton("CALCULATE");
        calculate.addActionListener(factory.addAction("calc"));
        return calculate;
    }

    private JButton createExitButton() {
        JButton exit = new JButton("EXIT");
        exit.addActionListener(factory.addAction("exit"));
        return exit;
    }

    public MoneyDialog getMoneyDialog() {
        return money;
    }

    public CurrencyDialog getCurrencyDialog() {
        return currencyTo;
    }

    public MoneyViewer getMoneyViewer() {
        return moneyViewer;
    }
}
