package org.example.UIComponents.components.playField.fidgets;

import org.example.utils.visual.IconsEnum;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Panel with a Button, which fires ChangeOrientationAction
 */
public class OrientationSwitcher extends JPanel implements MouseListener {
    private final ChangeOrientationAction changeOrientationAction;

    public OrientationSwitcher(int size, ChangeOrientationAction changeOrientationAction) {
        this.changeOrientationAction = changeOrientationAction;
        addMouseListener(this);
        add(IconsEnum.SWITCH_ICON.getPieceLabel(size));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        changeOrientationAction.run();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
