package javmos.components.functions;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javmos.JavmosGUI;
import javmos.enums.FunctionType;
import javmos.exceptions.PolynomialException;

public class Polynomial extends Function{

    private double[] coefficients;
    private int[] degrees;

    public Polynomial(JavmosGUI gui, String function) throws PolynomialException {
        super(gui);
        try {
            function = function.contains("=") ? function.substring(function.indexOf("=") + 1, function.length()) : function;
            String[] terms = function.charAt(0) == '-' ? function.substring(1, function.length()).split("\\+|\\-") : function.split("\\+|\\-");
            coefficients = new double[terms.length];
            degrees = new int[terms.length];
            int termsStart = 0;

            for (int i = 0; i < terms.length; i++) {
                if (terms[i].contains("x^")) {
                    if (terms[i].substring(0, 2).equals("x^")) {
                        coefficients[i] = 1;
                    } else {
                        coefficients[i] = Double.parseDouble(terms[i].substring(0, terms[i].indexOf("x")));
                    }
                    degrees[i] = Integer.parseInt(terms[i].substring(terms[i].indexOf("^") + 1, terms[i].length()));
                } else if (terms[i].contains("x") && !terms[i].contains("^")) {
                    coefficients[i] = terms[i].length() == 1 ? 1 : Double.parseDouble(terms[i].substring(0, terms[i].indexOf("x")));
                    degrees[i] = 1;
                } else {
                    coefficients[i] = Double.parseDouble(terms[i]);
                    degrees[i] = 0;
                }

                coefficients[i] *= (function.contains("-") && function.substring(termsStart, termsStart + 1).equals("-")) ? -1 : 1;
                termsStart += i == 0 && !(function.charAt(0) == '-') ? terms[i].length() : terms[i].length() + 1;
            }
        } catch (Exception exception) {
            throw new PolynomialException(function + " is not a valid polynomial!");
        }
    }
        
    @Override
    public String getFirstDerivative() {
        String eqn = "";
        String plusOrMin = "";
        String firstDeriv;
        for (int i = 0; i < degrees.length; i++) {
            if (coefficients[i] >= 0 && i > 0) {
                plusOrMin = "+";
            }    
            if (degrees[i] > 2) {
                eqn = eqn + plusOrMin + coefficients[i] * degrees[i] + "x^" + (degrees[i] - 1);
            } else if (degrees[i] == 2) {
                eqn = eqn + plusOrMin + coefficients[i] * degrees[i] + "x";
            } else if (degrees[i] == 1) {
                if (coefficients[i] != 0) {
                    eqn = eqn + plusOrMin + coefficients[i];
                }                        
            } else {
                eqn = eqn + "";
            }
            plusOrMin = "";
        }
        if (eqn.equals("")) {
            firstDeriv = "No First Derivative";
        } else {
            firstDeriv = "f'(x) = " + eqn;            
        }
        return firstDeriv;
    }

    @Override
    public String getSecondDerivative() {
        String eqn = "";
        String plusOrMin = "";
        String secondDeriv;
        for (int i = 0; i < degrees.length; i++) {
            if (coefficients[i] >= 0 && i > 0) {
                plusOrMin = "+";
            }    
            if (degrees[i] > 3) {
                eqn += plusOrMin + coefficients[i] * degrees[i] * (degrees[i] - 1) + "x^" + (degrees[i] - 2);
            } else if (degrees[i] == 3) {
                eqn += plusOrMin + coefficients[i] * degrees[i] * (degrees[i] - 1) + "x";
            } else if (degrees[i] == 2) {
                if (coefficients[i] != 0) {
                    eqn += plusOrMin + coefficients[i] * degrees[i];
                }                        
            } else {
                eqn += "";
            }
            plusOrMin = "";
        }
        if (eqn.equals("")) {
            secondDeriv = "No Second Derivative";
        } else {
            secondDeriv = "f''(x) = " + eqn;            
        }
        return secondDeriv;
    }
    
    @Override
    public double getValueAt(double x, FunctionType functionType) {
        try {
            Polynomial p = null;
            if (functionType != null) switch (functionType) {
                case FIRST_DERIVATIVE:
                    p = new Polynomial(gui, getFirstDerivative());
                    break;
                case SECOND_DERIVATIVE:
                    p = new Polynomial(gui, getSecondDerivative());
                    break;
                case THIRD_DERIVATIVE:
                    Polynomial j = new Polynomial(gui, getFirstDerivative());
                    p = new Polynomial(gui, j.getSecondDerivative());
                    break;
                default:
                    p = this;
                    break;
            }
            double[] coefficient = p.coefficients;
            int[] degree = p.degrees;
            double value = 0;
            for (int i = 0; i < coefficient.length; i++) {
                if (degree[i] > 0) {
                    value += 0;
                } else if (degree[i] == 0) {
                    value += coefficient[i];
                } else {
                    value += coefficient[i] * Math.pow(x, degree[i]);
                }
            }
            return value;
        } catch (PolynomialException ex) {
            Logger.getLogger(Polynomial.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}

