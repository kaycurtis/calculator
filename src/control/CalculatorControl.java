package control;


import display.CalculatorDisplay;

public class CalculatorControl {

    /**
     * Only one Calculator control can exist at any given moment
     */
    private static CalculatorControl instance = new CalculatorControl();

    /**
     * Maintain a reference to the display
     */
    public CalculatorDisplay display;


    /**
     * The constructor for the CalculatorControl class. Made private to enforce the
     * class invariant that only one CalculatorControl can exist at a time.
     */
    private CalculatorControl() {
    }

    /**
     * Indicates whether the user is currently entering the first or second
     * argument to the calculator. True before the user presses an operator button,
     * and false thereafter.
     */
    private boolean first = true;

    /**
     * Keep track of the user input to the calculator.
     */
    private String firstArg = "";
    private String secondArg = "";
    private Operation function;


    /**
     * Returns a reference to the CalculatorControl object stored in the
     * instance field. Guaranteed to return the same instance each time it
     * is called during the lifetime of the program.
     * @return CalculatorControl returns the only instance of this class
     */
    public static CalculatorControl getInstance() {
        return instance;
    }

    /**
     * Called when the user presses a button on the calculator associated with
     * a numerical value. Updates the class variables keeping track of such
     * input and updates the calculator display accordingly.
     * @param s the value of the button pressed by the user
     */
    public void pressedNumButton(String s) {
        if (first)  {
            firstArg += s;
            display.updateLabel(firstArg);
        }
        else {
            secondArg += s;
            display.updateLabel(secondArg);
        }
    }

    /**
     * Called when the user presses the equals button on the calculator. Attempts to
     * perform the last operation selected by the user with the numerical input values
     * they provided before and after pressing that button. Displays an error message if the
     * operation cannot be performed, otherwise performs the operation and displays
     * the result on the calculator.
     */
    public void pressedEqualsButton() {
        if (secondArg.equals("") || firstArg.equals("")) {
            display.updateLabel("Error: Missing arguments");
        }
        try {
            Double result = function.performOperation(Double.parseDouble(firstArg), Double.parseDouble(secondArg));
            display.updateLabel(String.valueOf(result));
        }
        catch (CalculationFailureException e) {
            display.updateLabel("Error: Math error");
        }

        // restart (calculator currently has no memory)
        firstArg = "";
        secondArg = "";
        first = true;

    }

    public void pressedOperatorButton(Operation o) {
        function = o;
        if (first) {
            first = false;
        }
        else {
            pressedEqualsButton();
        }
    }

    public void pressedCancelButton() {
        firstArg = "";
        secondArg = "";
        display.updateLabel("0");
        first = true;
    }

    public void pressedPlusMinus() {
        if (first)  {
            firstArg = "-" + firstArg;
            display.updateLabel(firstArg);
        }
        else {
            secondArg = "-" + secondArg;
            display.updateLabel(secondArg);
        }
    }

}
