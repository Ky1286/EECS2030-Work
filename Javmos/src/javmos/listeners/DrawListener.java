package javmos.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javmos.JavmosGUI;
import javmos.components.JavmosPanel;
import javmos.components.functions.Cosine;
import javmos.components.functions.Function;
import javmos.components.functions.Logarithmic;
import javmos.components.functions.Polynomial;
import javmos.components.functions.Sine;
import javmos.components.functions.Tangent;
import javmos.exceptions.PolynomialException;

public class DrawListener implements ActionListener {

    private final JavmosGUI gui;
    private final JavmosPanel panel;

    public DrawListener(JavmosGUI gui, JavmosPanel panel) {
        this.gui = gui;
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        try { 
            if (gui.getEquationField().contains("sin")) {
                panel.setFunction(new Sine(gui, gui.getEquationField()));
            } else if (gui.getEquationField().contains("cos")) {
                panel.setFunction(new Cosine(gui, gui.getEquationField()));
            } else if (gui.getEquationField().contains("tan")) {
                panel.setFunction(new Tangent(gui, gui.getEquationField()));
            } else if (gui.getEquationField().contains("log")) {
                panel.setFunction(new Logarithmic(gui, gui.getEquationField()));
            } else if (gui.getEquationField().contains("ln")) {
                panel.setFunction(new Logarithmic(gui, gui.getEquationField()));
            } else {
                panel.setFunction(new Polynomial(gui, gui.getEquationField()));
            }
            gui.setFirstDerivativeLabel(panel.getFunction().getFirstDerivative());
            gui.setSecondDerivativeLabel(panel.getFunction().getSecondDerivative());
            panel.repaint();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "The function you entered is not valid", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (PolynomialException ex) {
            Logger.getLogger(DrawListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
