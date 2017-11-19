package control;

/**
 * Created by kayla on 2017-08-12.
 */
public enum Operation {
    MULTIPLY("\u00D7"), DIVIDE("\u00F7"), MODULO("\u0025"), SUBTRACT("-"), ADD("+");

    private String symbol;

    Operation(String s) {
        this.symbol = s;
    }

    public String getSymbol() {
        return symbol;
    }

    public double performOperation(double x, double y) throws CalculationFailureException {
        try {
            switch (this) {
                case ADD:
                    return x + y;
                case DIVIDE:
                    if (y == 0) throw new CalculationFailureException();
                    return x / y;
                case MODULO:
                    return x % y;
                case MULTIPLY:
                    return x * y;
                case SUBTRACT:
                    return x - y;
                default:
                    throw new AssertionError("Calculator encountered an unrecognized operation");
            }
        }
        catch(ArithmeticException e) {
            throw new CalculationFailureException();
        }
    }

}
