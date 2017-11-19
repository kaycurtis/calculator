package display;

import control.*;

import javax.swing.*;
import java.awt.*;

/**
 * Created by kayla on 2017-08-12.
 */
public class CalculatorDisplay extends JFrame {

    private static final int CALCULATOR_WIDTH = 350;
    private static final int CALCULATOR_HEIGHT = 500;
    private static final int DISPLAY_HEIGHT = CALCULATOR_HEIGHT/8;
    private static final int BUTTON_AREA_HEIGHT = 7*DISPLAY_HEIGHT;

    private Font buttonFont = new Font("serif", Font.BOLD, fontSize);

    private CalculatorControl control;

    private static int fontSize = 30;

    private JPanel container = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JLabel displayField = new JLabel("0");
    private JPanel buttons = new JPanel(new GridLayout(0,1));

    private CalculatorDisplay() {
        super("calculator");
        setMinimumSize(new Dimension(CALCULATOR_WIDTH, CALCULATOR_HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // terminate when user closes
        setLocationRelativeTo(null); // put in the middle of the screen

        control = CalculatorControl.getInstance();
        control.display = this;

        setUpDisplay();
        initiateButtons();
        
        container.add(displayField);
        container.add(buttons);

        this.setContentPane(container);
        setVisible(true);

    }

    private void setUpDisplay() {
        displayField.setPreferredSize(new Dimension(CALCULATOR_WIDTH, DISPLAY_HEIGHT));
        displayField.setMinimumSize(new Dimension(CALCULATOR_WIDTH, DISPLAY_HEIGHT));
        displayField.setMaximumSize(new Dimension(CALCULATOR_WIDTH, DISPLAY_HEIGHT));
        displayField.setBackground(new Color(0x46, 0x82, 0xb4));
        displayField.setOpaque(true);
        displayField.setFont(buttonFont);
        displayField.setHorizontalTextPosition(SwingConstants.CENTER);
    }

    public void updateLabel(String s) {
        displayField.setText(s);
    }

    private void addOperationButton(Operation o, JPanel panel) {
        JButton button = new JButton(o.getSymbol());
        button.setFont(buttonFont);
        panel.add(button);
        OperatorButton listener = new OperatorButton(o);
        button.addActionListener(listener);

    }

    private void addSpecialButton(SpecialButtons sb, JPanel panel) {
        JButton button = new JButton(sb.getSymbol());
        button.setFont(new Font("serif", Font.BOLD, fontSize));
        panel.add(button);
        button.addActionListener(sb);

    }

    private void initiateButtons() {

        JPanel row1 = new JPanel(new GridLayout(1,4));
        addSpecialButton(SpecialButtons.CANCEL,row1);
        addSpecialButton(SpecialButtons.PLUS_MINUS,row1);
        addOperationButton(Operation.MODULO,row1);
        addOperationButton(Operation.DIVIDE,row1);
        buttons.add(row1);

        JPanel row2 = new JPanel(new GridLayout(1,4));
        initiateThreeButtons(7,9,row2);
        addOperationButton(Operation.MULTIPLY,row2);
        buttons.add(row2);

        JPanel row3 = new JPanel(new GridLayout(1,4));
        initiateThreeButtons(4,6, row3);
        addOperationButton(Operation.SUBTRACT,row3);
        buttons.add(row3);

        JPanel row4 = new JPanel(new GridLayout(1,4));
        initiateThreeButtons(1,3, row4);
        addOperationButton(Operation.ADD, row4);
        buttons.add(row4);

        JPanel row5 = new JPanel(new GridLayout(1,2));
        initiateNumButton("0",row5);

        JPanel subUnit = new JPanel(new GridLayout(1,2));
        addDot(subUnit);
        addSpecialButton(SpecialButtons.EQUALS, subUnit);
        row5.add(subUnit);
        buttons.add(row5);

        buttons.setMinimumSize(new Dimension(CALCULATOR_WIDTH,BUTTON_AREA_HEIGHT));
        buttons.setMaximumSize(new Dimension(CALCULATOR_WIDTH,BUTTON_AREA_HEIGHT));
        buttons.setPreferredSize(new Dimension(CALCULATOR_WIDTH,BUTTON_AREA_HEIGHT));

    }

    private void addDot(JPanel panel) {
        JButton button = new JButton(".");
        button.setFont(buttonFont);
        NumButton n = new NumButton(".",control);
        button.addActionListener(n);
        panel.add(button);
    }

    private void initiateThreeButtons(int start, int end, JPanel panel) {
        for (int i = start; i <= end; i++) {
            initiateNumButton(String.valueOf(i), panel);
        }
    }

    private void initiateNumButton(String s, JPanel panel) {
        JButton button = new JButton(s);
        button.setFont(buttonFont);
        NumButton n = new NumButton(s,control);
        button.addActionListener(n);
        panel.add(button);
    }

    public static void main(String[] args) {
        new CalculatorDisplay();
    }
}
