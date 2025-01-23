package org.example.utils.logical;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
/**
 * Propagates Click from the highest Layer in JLayeredPane
 * to lower layers
 */
public class MultiLayerMotionPropagation implements MouseMotionListener {

    @Override
    public void mouseDragged(MouseEvent e) {
        Component c = (Component) e.getSource();
        Container container = c.getParent();
        if(container instanceof JLayeredPane panel) {
            Component[] components = panel.getComponents();
            for (int i = components.length - 1; i >= 0; i--) {
                Component component = components[i];
                if(component.equals(c)) continue;
                if (component.getBounds().contains(e.getPoint())) {
                    for(MouseMotionListener mouseMotionListener: component.getMouseMotionListeners()){
                        mouseMotionListener.mouseDragged(e);
                    }
                }
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Component c = (Component) e.getSource();
        Container container = c.getParent();
        if(container instanceof JLayeredPane panel) {
            Component[] components = panel.getComponents();
            for (int i = components.length - 1; i >= 0; i--) {
                Component component = components[i];
                if(component.equals(c)) continue;
                if (component.getBounds().contains(e.getPoint())) {
                    for(MouseMotionListener mouseMotionListener: component.getMouseMotionListeners()){
                        mouseMotionListener.mouseMoved(e);
                    }
                }
            }
        }
    }
}
