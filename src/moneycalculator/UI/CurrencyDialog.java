package moneycalculator.UI;

import moneycalculator.Model.Currency;

public interface CurrencyDialog {

    public void dialog(String label);

    public Currency getCurrency();
}