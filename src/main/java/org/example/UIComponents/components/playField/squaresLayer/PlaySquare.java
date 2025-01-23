package org.example.UIComponents.components.playField.squaresLayer;

import javax.swing.*;
import java.awt.*;
import org.example.utils.visual.ColorsEnum;

/**
 * JPanel with single square
 * allows to set different highlighting
 */
public class PlaySquare extends JPanel {
    private Color color;
    public PlaySquare(Color color, int size) {
        this.color = color;
        setBackground(color);
        setBounds(0,0,size,size);
        setMaximumSize(new Dimension(size,size));
        setPreferredSize(new Dimension(size,size));
        setMinimumSize(new Dimension(size,size));
    }
    protected void setColor(Color color){
        this.color=color;
        setBackground(color);
    }
    public void setBackToNormalColor(){
        setBackground(color);
    }
    public void setNormalHighlight(){
        setBackground(ColorsEnum.PLAY_FIELD_HIGHLIGHT_COLOR.getColor());
    }
    public void changeHighLight(){
        if(getBackground().equals(color)){
            setBackground(ColorsEnum.PLAY_FIELD_HIGHLIGHT_COLOR.getColor());
        }else{
            setBackground(color);
        }
    }
    public void setShahColor(){
        setBackground(ColorsEnum.PLAY_FIELD_SHAH_COLOR.getColor());
    }
    public void changeShahHighlight(){
        if(getBackground().equals(ColorsEnum.PLAY_FIELD_SHAH_COLOR.getColor())){
            setBackground(color);
        }
    }

    public Color getColor() {
        return color;
    }
}
