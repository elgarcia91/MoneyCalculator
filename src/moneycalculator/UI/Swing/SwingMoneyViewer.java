package moneycalculator.UI.Swing;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import moneycalculator.Model.Currency;
import moneycalculator.Model.Number;
import moneycalculator.Model.Money;
import moneycalculator.UI.MoneyViewer;

public class SwingMoneyViewer extends JPanel implements MoneyViewer {

    private Money money= new Money(new Number(20), new Currency("MPR", "Moneda de prueba", "&"));
    private JLabel label;

    public SwingMoneyViewer() {
        super(new FlowLayout(FlowLayout.CENTER));
        label = new JLabel(" ");
    }

    @Override
    public void setMoney(Money money) {
        this.money = money;
    }

    @Override
    public void showMoney() {
        label.setText(money.toString());
    }
}
