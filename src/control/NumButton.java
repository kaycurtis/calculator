package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by kayla on 2017-08-12.
 */
public class NumButton implements ActionListener{
    private String num;
    public CalculatorControl control;

    public NumButton(String s, CalculatorControl control) {
        this.num = s;
        this.control = control;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        control.pressedNumButton(num);
    }
}
