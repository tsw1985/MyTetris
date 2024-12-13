package com.gabriel.tetris.pieces;

import com.gabriel.tetris.Board;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gabriel
 */
public class O extends Piece{

    public O() {
        loadShapesOnPiece();
    }
    
    @Override
    public void setCustomShape(int shape) {
        rotationNumber = shape;
        
    }
    
    
    @Override
    public int getCurrentNumberShape() {
        return rotationNumber;
    }
    
     @Override
    public void loadShapesOnPiece() {
        
        // T 0
        List<Shape> pieceShapeListT0 = new ArrayList();
        pieceShapeListT0.add(new Shape(0,0));
        pieceShapeListT0.add(new Shape(0,1));
        pieceShapeListT0.add(new Shape(1,0));
        pieceShapeListT0.add(new Shape(1,1));

        pieceShapeListOfList.add(pieceShapeListT0);
        
        setCoords0();
        
    }
    
    private void setCoords0(){
        positionXLeftLimit = 0;
        positionXRightLimit = Board.BOARD_WIDTH - 3;
        positionYBottomLimit = 18;
    }


    
   private void debugText(String rotation , int xLeft , int xRight){
        System.out.println("ROTATION " + rotation);
        System.out.println("ROTATION " + rotation + " - XLeftLimit  = " + xLeft);
        System.out.println("ROTATION " + rotation + " - XRightLimit = " + xRight);
    }
   
    @Override
    public int rotate() {

        rotationNumber = 0;
        
        switch(rotationNumber){
            
            case 0:
                setCoords0();
                debugText("0",positionXLeftLimit,positionXRightLimit);
                break;
        }
        
        System.out.println("ROTATION NUMBER VALUE: " + rotationNumber);
        
        return rotationNumber;
    }
    

    @Override
    public List<Shape> getCurrentRotation() {
        if(rotationNumber <= 3){
            return pieceShapeListOfList.get(rotationNumber);
        }
        return null;
    }

    

    
}