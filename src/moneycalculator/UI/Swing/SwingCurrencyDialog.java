package moneycalculator.UI.Swing;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import moneycalculator.Model.Currency;
import moneycalculator.Model.CurrencySet;
import moneycalculator.UI.CurrencyDialog;

public class SwingCurrencyDialog extends JPanel implements CurrencyDialog {

    private Currency currency;

    public SwingCurrencyDialog() {
    }

    @Override
    public void dialog(String label) {
        this.add(new JLabel(label));
        this.add(createCurrencyField());
    }

    @Override
    public Currency getCurrency() {
        return currency;
    }

    private JTextField createCurrencyField() {
        String fillText = "EUR";
        final JTextField text = new JTextField(10);
        text.setText(fillText);
        text.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
            }

            @Override
            public void keyPressed(KeyEvent ke) {
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                currency = CurrencySet.getInstance().searchCurrency(text.getText());
            }
        });
        return text;
    }
}
