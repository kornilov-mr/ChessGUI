package gameControl.colorChooser;

import logic.chessPieces.PieceColor;

import java.util.Random;

public class RandomColorChooser implements ColorChooser {
    @Override
    public PieceColor getColor() {
        Random rand = new Random();
        if(rand.nextInt(1,2)==1){
            return PieceColor.Black;
        }else{
            return PieceColor.White;
        }
    }

    @Override
    public String toString() {
        return "Random";
    }
}
