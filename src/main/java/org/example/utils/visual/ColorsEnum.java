package org.example.utils.visual;

import java.awt.*;

public enum ColorsEnum {
    PLAY_FIELD_COLOR2(new Color(115,149,82)),
    PLAY_FIELD_COLOR1(new Color(235,236,208)),
    PLAY_FIELD_HIGHLIGHT_COLOR(new Color(211,108,80)),
    PLAY_FIELD_SHAH_COLOR(new Color(246, 55, 3)),
    WHITE_PIECE_COLOR(new Color(255, 255, 255)),
    BLACK_PIECE_COLOR(new Color(0,0,0));
    private final Color color;

    ColorsEnum(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
