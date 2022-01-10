package javmos.components.functions;

import java.util.HashSet;
import javmos.JavmosGUI;
import javmos.components.Point;
import javmos.enums.FunctionType;

public class Logarithmic extends Function {

    public double a;
    public double base;
    public double k;

    public Logarithmic(JavmosGUI gui, String function) {
        super(gui);

        if (function.contains("log(")) {
            base = 10;
        } else if (function.contains("ln")) {
            base = Math.E;
        } else {
            base = Double.parseDouble(function.substring(function.indexOf("g") + 1, function.indexOf("(")));
            System.out.print(function.indexOf("g"));
        }

        if (function.substring(0, 1).equals("l")) {
            a = 1;
        } else {
            a = Double.parseDouble(function.substring(0, function.indexOf("l")));
        }

        if (function.contains("x")) {
            k = Double.parseDouble(function.substring(function.indexOf("(") + 1, function.indexOf("x)")));
        }
    }

    public String getFirstDerivative() {
        String bass = ("f'(x)= " + a + " / " + a + " ln " + base);
        return bass;
    }

    public String getSecondDerivative() {
        String bass2 = ("f''(x)= " + -a + " / " + " x^2ln " + base);
        return bass2;
    }

    public double getValueAt(double x, FunctionType functionType) {
        if (null == functionType) {
            return a * (Math.log10(k * x) / Math.log10(base));
        } else switch (functionType) {
            case FIRST_DERIVATIVE:
                return a / (x * Math.log(base));
            case SECOND_DERIVATIVE:
                return -a / (Math.pow(x, 2) * Math.log(base));
            default:
                return a * (Math.log10(k * x) / Math.log10(base));
        }
    }

    public HashSet<Point> getCriticalPoints() {
        return new HashSet<>();
    }

    public HashSet<Point> getInflectionPoints() {
        return new HashSet<>();
    }
}
