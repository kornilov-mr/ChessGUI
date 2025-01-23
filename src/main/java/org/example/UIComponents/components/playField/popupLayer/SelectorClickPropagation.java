package org.example.UIComponents.components.playField.popupLayer;

import org.example.UIComponents.components.playField.MainPlayField;
import org.example.UIComponents.components.playField.popupLayer.selector.PromotionSelectorPopup;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class SelectorClickPropagation implements MouseListener {
    private final MainPlayField mainPlayField;

    public SelectorClickPropagation(MainPlayField mainPlayField) {
        this.mainPlayField = mainPlayField;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        PromotionSelectorPopup currPopup = mainPlayField.getPopupLayer().getPromotionSelectorPopup();
        if(currPopup == null) return;
        ((MouseListener) (PromotionSelectorPopup) currPopup).mousePressed(e);
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
