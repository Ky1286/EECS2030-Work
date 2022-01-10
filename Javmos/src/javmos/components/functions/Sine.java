package javmos.components.functions;

import javmos.JavmosGUI;
import javmos.enums.FunctionType;

public class Sine extends Trigonometric {

    public Sine(JavmosGUI gui, String function) {
        super(gui, function, "sin");
    }

    @Override
    public String getFirstDerivative() {
        return "f\'(x) = " + a * k + "cos(" + k + "x)";
    }

    @Override
    public String getSecondDerivative() {
        return "f\"(x) = " + -a * k * k + "sin(" + k + "x)";
    }

    @Override
    public double getValueAt(double x, FunctionType functionType) {
        switch(functionType) {
            case FIRST_DERIVATIVE:
                return a * k * Math.cos(k * x);
            case SECOND_DERIVATIVE:
                return -a * Math.pow(k,2) * Math.sin(k * x);
            case THIRD_DERIVATIVE:
                return -a * Math.pow(k,3)* Math.cos(k * x);
            default:    //"ORIGINAL" case
                return a * Math.sin(k * x);
        }
    }
    
}
