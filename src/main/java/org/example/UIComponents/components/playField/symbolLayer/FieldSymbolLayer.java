package org.example.UIComponents.components.playField.symbolLayer;

import logic.chessPieces.PieceColor;
import org.example.UIComponents.components.playField.MainPlayField;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Layer for MainPlayField, which holds
 * all arrows and files and rows symbols
 */
public class FieldSymbolLayer extends JPanel {
    private final JPanel[][] panels = new JPanel[8][8];
    private final MainPlayField mainPlayField;
    private final Set<Arrow> displayedArrows = new HashSet<>();
    private final int squareSize;

    public FieldSymbolLayer(int squareSize, MainPlayField mainPlayField) {
        this.mainPlayField = mainPlayField;
        this.squareSize = squareSize;
        GridLayout layout = new GridLayout(8, 8);
        layout.setHgap(0);
        layout.setVgap(0);
        setBounds(0, 0, squareSize*8, squareSize*8);
        setLayout(layout);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JPanel panel = new JPanel();
                panel.setOpaque(false);
                panel.setLayout(new BorderLayout());
                panels[i][j] = panel;
                add(panel);
            }
        }
        fillLeftPanels(mainPlayField.getColorOrientation());
        fillBottomPanels();
        fillLeftBottomPanel(mainPlayField.getColorOrientation());
        setOpaque(false);
        addMouseListener(new ArrowMouseListener(mainPlayField));

    }
    private void deleteLeftPanels(){
        for (int i = 0; i < 8; i++) {
            panels[i][0].removeAll();
        }
    }
    private void fillBottomPanels(){
        for (int i = 0; i < 7; i++) {
            JLabel label = new JLabel(String.valueOf((char) (98 + i)));
            label.setFont(new Font("Arial", Font.BOLD, 18));
            label.setHorizontalAlignment(SwingConstants.RIGHT);
            label.setVerticalAlignment(SwingConstants.BOTTOM);
            label.setBorder(new EmptyBorder(0, 4, 4, 4));
            panels[7][i+1].add((label), BorderLayout.SOUTH);
        }
    }
    private void fillLeftBottomPanel(PieceColor colorOrientation){
        int labelValue;
        if(colorOrientation.equals(PieceColor.Black)){
            labelValue = 1;
        }else{
            labelValue = 8;
        }
        JLabel label1 = new JLabel(String.valueOf(labelValue));
        label1.setFont(new Font("Arial", Font.BOLD, 18));
        label1.setHorizontalAlignment(SwingConstants.LEFT);
        label1.setVerticalAlignment(SwingConstants.TOP);
        label1.setBorder(new EmptyBorder(0, 4, 4, 4));

        JLabel label2 = new JLabel("a");
        label2.setFont(new Font("Arial", Font.BOLD, 18));
        label2.setHorizontalAlignment(SwingConstants.RIGHT);
        label2.setVerticalAlignment(SwingConstants.BOTTOM);
        label2.setBorder(new EmptyBorder(0, 4, 4, 4));

        panels[7][0].add(label1, BorderLayout.WEST);
        panels[7][0].add(label2, BorderLayout.SOUTH);
    }
    private void fillLeftPanels(PieceColor colorOrientation){
        for (int i = 0; i < 7; i++) {
            int labelCount;
            if(colorOrientation.equals(PieceColor.Black)){
                labelCount=8 - i;
            }else{
                labelCount=i + 1 ;
            }
            JLabel label = new JLabel(String.valueOf(labelCount));
            label.setFont(new Font("Arial", Font.BOLD, 18));
            label.setHorizontalAlignment(SwingConstants.LEFT);
            label.setVerticalAlignment(SwingConstants.TOP);
            label.setBorder(new EmptyBorder(0, 4, 4, 4));
            panels[i][0].add(label, BorderLayout.WEST);
        }
    }
    public void changeOrientation(){
        deleteLeftPanels();
        fillLeftPanels(mainPlayField.getColorOrientation());
        fillLeftBottomPanel(mainPlayField.getColorOrientation());
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        float transparency = 0.5f;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparency));
        Color color = new Color(248,194,75);
        g2d.setColor(color);
        for(Arrow arrow : displayedArrows) {
            g2d.fill(arrow.getArrowBodyPolygon(squareSize));
            g2d.fill(arrow.getArrowHeadPolygon(squareSize));
        }

    }
    public void addOrDeleteArrow(Arrow arrow) {
        if(displayedArrows.contains(arrow)) {
            displayedArrows.remove(arrow);
        }else{
            displayedArrows.add(arrow);
        }
    }
    public void addNewArrowToDisplay(Arrow arrow) {
        displayedArrows.add(arrow);
    }
    public void deleteAllArrows(){
        displayedArrows.clear();
    }
    public void deleteArrow(Arrow arrow) {
        displayedArrows.remove(arrow);
    }
    public void changeArrowsOrientation(){
        for(Arrow arrow : displayedArrows){
            arrow.changeOrientation();
        }
        repaint();
    }


}
