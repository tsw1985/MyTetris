package com.gabriel.tetris.pieces;

import com.gabriel.tetris.Board;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gabriel
 */
public class I extends Piece{

    public I() {
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
        
        List<Shape> pieceShapeListT0 = new ArrayList();
        pieceShapeListT0.add(new Shape(1,0)); 
        pieceShapeListT0.add(new Shape(1,1)); 
        pieceShapeListT0.add(new Shape(1,2));
        pieceShapeListT0.add(new Shape(1,3));

        pieceShapeListOfList.add(pieceShapeListT0);
        
        // T 1
        List<Shape> pieceShapeListT1 = new ArrayList();
        pieceShapeListT1.add(new Shape(0,2));
        pieceShapeListT1.add(new Shape(1,2));
        pieceShapeListT1.add(new Shape(2,2));
        pieceShapeListT1.add(new Shape(3,2));
        pieceShapeListOfList.add(pieceShapeListT1);
        
        // T 2
        List<Shape> pieceShapeListT2 = new ArrayList();
        pieceShapeListT2.add(new Shape(1,0));
        pieceShapeListT2.add(new Shape(1,1));
        pieceShapeListT2.add(new Shape(1,2));
        pieceShapeListT2.add(new Shape(1,3));
        pieceShapeListOfList.add(pieceShapeListT2);
        
        // T 3
        List<Shape> pieceShapeListT3 = new ArrayList();
        pieceShapeListT3.add(new Shape(0,2));
        pieceShapeListT3.add(new Shape(1,2));
        pieceShapeListT3.add(new Shape(2,2));
        pieceShapeListT3.add(new Shape(3,2));
        pieceShapeListOfList.add(pieceShapeListT3);
        
        //positionXLeftLimit = 0;
        //positionXRightLimit = Board.BOARD_WIDTH - 4 ;
        //positionYBottomLimit = 18;
        setCoords0();
        
    }
    
    private void setCoords0(){
        positionXLeftLimit = 0;
        positionXRightLimit = Board.BOARD_WIDTH - 4;
        positionYBottomLimit = 18;
    }

    private void setCoords1(){
        positionXLeftLimit = -2 ;
        positionXRightLimit = Board.BOARD_WIDTH - 3;
        positionYBottomLimit = 16;
    }
    
    private void setCoords2(){
        positionXLeftLimit = 0; //0
        positionXRightLimit = Board.BOARD_WIDTH - 4;
        positionYBottomLimit = 18; //17
    }
    
    private void setCoords3(){
        positionXLeftLimit = -2; // -1
        positionXRightLimit = Board.BOARD_WIDTH - 3;
        positionYBottomLimit = 16; //16
    }
    
    private void debugText(String rotation , int xLeft , int xRight){
        System.out.println("ROTATION " + rotation);
        System.out.println("ROTATION " + rotation + " - XLeftLimit  = " + xLeft);
        System.out.println("ROTATION " + rotation + " - XRightLimit = " + xRight);
    }
    
    @Override
    public int rotate() {

        rotationNumber++;
        
        if(rotationNumber >= 4)
            rotationNumber = 0;
        
        switch(rotationNumber){
            
            case 0:
                setCoords0();
                break;
            
            case 1:
                setCoords1();
                break;
            
            case 2:
                setCoords2();
                break;
            
            case 3:
                setCoords3();
                break;
        }

        debugText(String.valueOf(rotationNumber),positionXLeftLimit , positionXRightLimit);
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