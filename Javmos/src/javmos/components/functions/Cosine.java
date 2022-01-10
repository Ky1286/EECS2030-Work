package javmos.components.functions;

import javmos.JavmosGUI;
import javmos.enums.FunctionType;

public class Cosine extends Trigonometric {

    public Cosine(JavmosGUI gui, String function) {
        super(gui, function, "cos");
    }

    @Override
    public String getFirstDerivative() {
        return "f'(x) = " + -a * k + " sin(" + k + "x)";
    }

    @Override
    public String getSecondDerivative() {
        return "f\"(x) = " + -a * k * k + " cos(" + k + "x)";
    }

    @Override
    public double getValueAt(double x, FunctionType functionType) {
        switch(functionType) {
            case FIRST_DERIVATIVE:
                return -a * k * Math.sin(k * x);
            case SECOND_DERIVATIVE:
                return -a * Math.pow(k,2) * Math.cos(k * x);
            case THIRD_DERIVATIVE:
                return a * Math.pow(k,3) * Math.sin(k * x);
            default:    //"ORIGINAL" case
                return a * Math.cos(k * x);
        }
    }
}
