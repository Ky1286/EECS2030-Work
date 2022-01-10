package javmos.enums;

import java.awt.Color;
import java.math.*;
import static java.math.RoundingMode.HALF_DOWN;
import java.text.DecimalFormat;
import java.util.HashSet;
import javmos.JavmosGUI;
import javmos.components.functions.Function;
import javmos.components.Point;

public enum RootType {

    X_INTERCEPT(Color.GRAY, "X-intercept", FunctionType.ORIGINAL, FunctionType.FIRST_DERIVATIVE),
    CRITICAL_POINT(Color.RED, "Critical Point", FunctionType.FIRST_DERIVATIVE, FunctionType.SECOND_DERIVATIVE),
    INFLECTION_POINT(Color.ORANGE, "Inflection Point", FunctionType.SECOND_DERIVATIVE, FunctionType.THIRD_DERIVATIVE);

    public int ATTEMPTS = 15;
    public FunctionType functionOne;
    public FunctionType functionTwo;
    public final Color rootColor;
    public final String rootName;

    RootType(Color color, String name, FunctionType functionOne, FunctionType functionTwo) {
        this.rootColor = color;
        this.rootName = name;
        this.functionOne = functionOne;
        this.functionTwo = functionTwo;
    }

    public String getRootName() {
        return rootName;
    }

    public Color getRootColor() {
        return rootColor;
    }

    public java.util.HashSet<Point> getRoots(JavmosGUI gui, Function function, double minDomain, double maxDomain) {
        DecimalFormat formatter = new DecimalFormat("#.###");
        formatter.setRoundingMode(HALF_DOWN);
        HashSet<Point> set = new HashSet<>();
        double min = gui.getMinDomain();
        double max = gui.getMaxDomain();
        if (newtonsMethod(function, min, ATTEMPTS) == null) {
            return null;
        } else {
            for (double i = min; i < max; i += 0.01) {
                if (newtonsMethod(function, i, ATTEMPTS) != null) {
                    if (rootName.equals("X-intercept")) {
                        set.add(new Point(gui, this, this.newtonsMethod(function, i, ATTEMPTS), 0.0));
                    } else {
                        set.add(new Point(gui, this, this.newtonsMethod(function, i, ATTEMPTS), function.getValueAt(this.newtonsMethod(function, i, ATTEMPTS), FunctionType.ORIGINAL)));
                    }
                }
            }
        }
        return set;
    }

    protected Double newtonsMethod(Function function, double guess, int attempts) {
        if (attempts == 0) {
            return null;
        }
        double n = function.getValueAt(guess, functionOne);
        double d = function.getValueAt(guess, functionTwo);
        if (d == 0) {
            return null;
        } else if ((Math.abs(guess - (n / d)) - Math.abs(guess)) <= 0.001) {
            return (guess - (n / d));
        } else {
            return newtonsMethod(function, guess - (n / d), attempts - 1);
        }
    }
}
