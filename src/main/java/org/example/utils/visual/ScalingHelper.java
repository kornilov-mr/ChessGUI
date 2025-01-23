package org.example.utils.visual;

import javax.swing.*;
import java.awt.*;

public class ScalingHelper {

    private static double originalWidth = ((Toolkit.getDefaultToolkit()).getScreenSize()).getWidth();
    private static double originalHeight = ((Toolkit.getDefaultToolkit()).getScreenSize()).getHeight();


    public static void scaleComponent(Dimension newSize, JPanel panel) {
        int frameWidth = newSize.width;
        int frameHeight = newSize.height;

        double widthScale = frameWidth / originalWidth;
        double heightScale = frameHeight / originalHeight;
        double scale = Math.min(widthScale, heightScale);

        for (Component component : panel.getComponents()) {
            if (component != null) {
                if (component instanceof AbstractButton || component instanceof JLabel) {
                    Font originalFont = component.getFont();
                    float newFontSize = (float) (originalFont.getSize() * scale);
                    component.setFont(originalFont.deriveFont(newFontSize));
                }

                if (component instanceof JButton button) {
                    Dimension preferredSize = button.getPreferredSize();
                    button.setPreferredSize(new Dimension(
                            (int) (preferredSize.width * scale),
                            (int) (preferredSize.height * scale)
                    ));
                }

                if (component instanceof JLabel label) {
                    Dimension preferredSize = label.getPreferredSize();
                    label.setPreferredSize(new Dimension(
                            (int) (preferredSize.width * scale),
                            (int) (preferredSize.height * scale)
                    ));
                }
            }
        }

        panel.revalidate();
        panel.repaint();
    }
}
