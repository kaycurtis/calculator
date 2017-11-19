package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by kayla on 2017-08-12.
 */
public enum SpecialButtons implements ActionListener {
    CANCEL("C"), PLUS_MINUS("\u00B1"), EQUALS("=");

    private CalculatorControl control = CalculatorControl.getInstance();
    private String symbol;

    SpecialButtons(String s) {
        this.symbol = s;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (this) {
            case CANCEL:
                control.pressedCancelButton();
                break;
            case EQUALS:
                control.pressedEqualsButton();
                break;
            case PLUS_MINUS:
                control.pressedPlusMinus();
                break;
        }
    }
}
