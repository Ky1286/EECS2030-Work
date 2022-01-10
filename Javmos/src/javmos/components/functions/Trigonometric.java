package javmos.components.functions;

import javmos.JavmosGUI;

public abstract class Trigonometric extends Function {

    protected double a;
    protected double k;

    public Trigonometric(JavmosGUI gui, String function, String name) {
        super(gui);
        String x = Character.toString(name.charAt(0));
        if (function.substring(0, 1).equals(x)) {  //if the function contains "sin"/"cos"/"tan", assume first preceding char after number = s/c/t
           a = 1;
        } else if (function.substring(0, 2).equals("-" + name.charAt(0))) { //if no trig return 1
            a = -1;
        } else {
            a = Double.parseDouble(function.substring(0, function.indexOf(x)));
        }
        
        if (function.contains("(x)")) {    //assuming following char is "x", index start at "(", index end at "x"
            k = 1;
        } else if (function.contains("(-x)")) {//if no k, return 1
            k = -1;
        } else {
            k = Double.parseDouble(function.substring(function.indexOf("(") + 1, function.indexOf("x")));
        }
    }
}
