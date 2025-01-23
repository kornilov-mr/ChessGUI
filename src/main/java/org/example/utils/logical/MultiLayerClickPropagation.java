package org.example.utils.logical;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Propagates Click from the highest Layer in JLayeredPane
 * to lower layers
 */
public class MultiLayerClickPropagation implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        Component c = (Component) e.getSource();
        Container container = c.getParent();
        if(container instanceof JLayeredPane panel) {
            Component[] components = panel.getComponents();
            for (int i = components.length - 1; i >= 0; i--) {
                Component component = components[i];
                if(component.equals(c)) continue;
                if (component.getBounds().contains(e.getPoint())) {
                    for(MouseListener mouseListener: component.getMouseListeners()){
                        mouseListener.mouseClicked(e);
                    }
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Component c = (Component) e.getSource();
        Container container = c.getParent();
        if(container instanceof JLayeredPane panel) {
            Component[] components = panel.getComponents();
            for (int i = components.length - 1; i >= 0; i--) {
                Component component = components[i];
                if(component.equals(c)) continue;
                if (component.getBounds().contains(e.getPoint())) {
                    for(MouseListener mouseListener: component.getMouseListeners()){
                        mouseListener.mousePressed(e);
                    }
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Component c = (Component) e.getSource();
        Container container = c.getParent();
        if(container instanceof JLayeredPane panel) {
            Component[] components = panel.getComponents();
            for (int i = components.length - 1; i >= 0; i--) {
                Component component = components[i];
                if(component.equals(c)) continue;
                if (component.getBounds().contains(e.getPoint())) {
                    for(MouseListener mouseListener: component.getMouseListeners()){
                        mouseListener.mouseReleased(e);
                    }
                }
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Component c = (Component) e.getSource();
        Container container = c.getParent();
        if(container instanceof JLayeredPane panel) {
            Component[] components = panel.getComponents();
            for (int i = components.length - 1; i >= 0; i--) {
                Component component = components[i];
                if(component.equals(c)) continue;
                if (component.getBounds().contains(e.getPoint())) {
                    for(MouseListener mouseListener: component.getMouseListeners()){
                        mouseListener.mouseEntered(e);
                    }
                }
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Component c = (Component) e.getSource();
        if(c instanceof JPanel panel) {
            Component[] components = panel.getComponents();
            for (int i = components.length - 1; i >= 0; i--) {
                Component component = components[i];
                if (component.getBounds().contains(e.getPoint())) {
                    for(MouseListener mouseListener: component.getMouseListeners()){
                        mouseListener.mouseExited(e);
                    }
                }
            }
        }
    }
}
