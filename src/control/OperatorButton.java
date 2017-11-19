package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by kayla on 2017-08-12.
 */
public class OperatorButton implements ActionListener {

    private CalculatorControl control = CalculatorControl.getInstance();
    private Operation operation;

    public OperatorButton(Operation o) {
        this.operation = o;
    }

    /* Called when the button is pressed */
    @Override
    public void actionPerformed(ActionEvent e) {
        control.pressedOperatorButton(this.operation);
    }
}
