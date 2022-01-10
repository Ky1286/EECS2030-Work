package javmos.components.functions;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.HashSet;
import javmos.JavmosGUI;
import javmos.enums.RootType;
import javmos.components.JavmosComponent;
import javmos.enums.FunctionType;

public abstract class Function extends JavmosComponent {        
    
    protected Function(JavmosGUI gui) {
        super(gui);
    }
                                            //getRoots requires(gui, function, minDomain, maxDomain)
    public HashSet getCriticalPoints() {    //gui, "this" calls it's constructor for getRoots which requires a function,
        return RootType.CRITICAL_POINT.getRoots(gui, this, gui.getMinDomain(), gui.getMaxDomain());
    }
    
    public HashSet getXIntercepts() {          //minDomain, maxDomain idk the values for
        return RootType.X_INTERCEPT.getRoots(gui, this, gui.getMinDomain(), gui.getMaxDomain());
    }

    public HashSet getInflectionPoints() {          //minDomain, maxDomain idk the values for
        return RootType.INFLECTION_POINT.getRoots(gui, this, gui.getMinDomain(), gui.getMaxDomain());
    }
    
    @Override
    public void draw(Graphics2D graphics) {
        String function = gui.getEquationField();
        double a = 0, k = 0, x1, x2, y1, y2;
        graphics.setStroke(new BasicStroke(3));
        graphics.setColor(Color.RED);
        for (double i = gui.getMinDomain(); i < gui.getMaxDomain(); i += 0.01) {
            if (getValueAt(i, FunctionType.ORIGINAL) <= gui.getMaxRange() && getValueAt(i, FunctionType.ORIGINAL) >= gui.getMinRange()) {
                x1 = i * gui.getZoom() / gui.getDomainStep();
                y1 = getValueAt(i, FunctionType.ORIGINAL) * gui.getZoom() / gui.getRangeStep();
                x2 = (i + 0.01) * gui.getZoom() / gui.getDomainStep();
                y2 = getValueAt(i + 0.01, FunctionType.ORIGINAL) * gui.getZoom() / gui.getRangeStep();
                Line2D.Double line = new Line2D.Double(400 + x1, 400 - y1, 400 + x2, 400 - y2);
               /* if (function.contains("tan")) {
                    if (function.substring(0, 1).equals(Character.toString("tan".charAt(0)))) {
                        a = 1;
                    } else if (function.substring(0, 2).equals("-" + "tan".charAt(0))) {
                        a = -1;
                    } else {
                        a = Double.parseDouble(function.substring(0, function.indexOf(Character.toString("tan".charAt(0)))));
                    }

                    if (function.contains("(x")) {
                        k = 1;
                    } else if (function.contains("(-x")) {
                        k = -1;
                    } else {
                        k = Double.parseDouble(function.substring(function.indexOf("(") + 1, function.indexOf("x")));
                    }

                    if (a < 0 && k < 0) {
                        a = 1;
                        k = 1;
                    }
                }
                if (!(function.contains("tan")) || (line.getY1() > line.getY2() && k > 0 && a > 0) || (line.getY2() > line.getY1() && (k < 0 || a < 0))) {
                    graphics.draw(line);
                }*/
            }
        }
    }
    
    public abstract String getFirstDerivative();
    
    public abstract String getSecondDerivative();
    
    public abstract double getValueAt(double x, FunctionType functionType);

}
