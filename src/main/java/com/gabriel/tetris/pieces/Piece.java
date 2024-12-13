package com.gabriel.tetris.pieces;

import java.util.ArrayList;
import java.util.List;

/*
PIECES :
T :
    // T 0
        List<Shape> pieceShapeListT0 = new ArrayList();
        pieceShapeListT0.add(new Shape(0,1));
        pieceShapeListT0.add(new Shape(1,0));
        pieceShapeListT0.add(new Shape(1,1));
        pieceShapeListT0.add(new Shape(1,2));

        pieceShapeListOfList.add(pieceShapeListT0);
        
        // T 1
        List<Shape> pieceShapeListT1 = new ArrayList();
        pieceShapeListT1.add(new Shape(0,1));
        pieceShapeListT1.add(new Shape(1,1));
        pieceShapeListT1.add(new Shape(1,2));
        pieceShapeListT1.add(new Shape(2,1));
        pieceShapeListOfList.add(pieceShapeListT1);
        
        // T 2
        List<Shape> pieceShapeListT2 = new ArrayList();
        pieceShapeListT2.add(new Shape(1,0));
        pieceShapeListT2.add(new Shape(1,1));
        pieceShapeListT2.add(new Shape(1,2));
        pieceShapeListT2.add(new Shape(2,1));
        pieceShapeListOfList.add(pieceShapeListT2);
        
        // T 3
        List<Shape> pieceShapeListT3 = new ArrayList();
        pieceShapeListT3.add(new Shape(0,1));
        pieceShapeListT3.add(new Shape(1,0));
        pieceShapeListT3.add(new Shape(1,1));
        pieceShapeListT3.add(new Shape(2,1));

    //O:
        List<Shape> pieceShapeListO0 = new ArrayList();
        pieceShapeListO0.add(new Shape(0,0));
        pieceShapeListO0.add(new Shape(0,1));
        pieceShapeListO0.add(new Shape(1,0));
        pieceShapeListO0.add(new Shape(1,1));

        pieceShapeListOfList.add(pieceShapeListT0);

    //I

        //I 0
        List<Shape> pieceShapeListI0 = new ArrayList();
        pieceShapeListI0.add(new Shape(1,0));
        pieceShapeListI0.add(new Shape(1,1));
        pieceShapeListI0.add(new Shape(1,2));
        pieceShapeListI0.add(new Shape(1,3));

        pieceShapeListOfList.add(pieceShapeListI0);
        
        // I 1
        List<Shape> pieceShapeListI1 = new ArrayList();
        pieceShapeListI1.add(new Shape(0,2));
        pieceShapeListI1.add(new Shape(1,2));
        pieceShapeListI1.add(new Shape(2,2));
        pieceShapeListI1.add(new Shape(3,2));
        pieceShapeListOfList.add(pieceShapeListI1);
        
        // I 2
        List<Shape> pieceShapeListI2 = new ArrayList();
        pieceShapeListI2.add(new Shape(2,0));
        pieceShapeListI2.add(new Shape(2,1));
        pieceShapeListI2.add(new Shape(2,2));
        pieceShapeListI2.add(new Shape(2,3));
        pieceShapeListOfList.add(pieceShapeListI2);
        
        // I 3
        List<Shape> pieceShapeListTI3 = new ArrayList();
        pieceShapeListTI3.add(new Shape(0,1));
        pieceShapeListTI3.add(new Shape(1,1));
        pieceShapeListTI3.add(new Shape(2,1));
        pieceShapeListTI3.add(new Shape(3,1));

    // S
        // S 0
        List<Shape> pieceShapeListI0 = new ArrayList();
        pieceShapeListI0.add(new Shape(0,1));
        pieceShapeListI0.add(new Shape(0,2));
        pieceShapeListI0.add(new Shape(1,0));
        pieceShapeListI0.add(new Shape(1,1));

        pieceShapeListOfList.add(pieceShapeListI0);
        
        // S 1
        List<Shape> pieceShapeListI1 = new ArrayList();
        pieceShapeListI1.add(new Shape(0,1));
        pieceShapeListI1.add(new Shape(1,1));
        pieceShapeListI1.add(new Shape(1,2));
        pieceShapeListI1.add(new Shape(2,2));
        pieceShapeListOfList.add(pieceShapeListI1);
        
        // S 2
        List<Shape> pieceShapeListI2 = new ArrayList();
        pieceShapeListI2.add(new Shape(1,1));
        pieceShapeListI2.add(new Shape(1,2));
        pieceShapeListI2.add(new Shape(2,0));
        pieceShapeListI2.add(new Shape(2,1));
        pieceShapeListOfList.add(pieceShapeListI2);
        
        // S 3
        List<Shape> pieceShapeListTI3 = new ArrayList();
        pieceShapeListTI3.add(new Shape(0,0));
        pieceShapeListTI3.add(new Shape(1,0));
        pieceShapeListTI3.add(new Shape(1,1));
        pieceShapeListTI3.add(new Shape(2,1));

    //Z

        // Z 0
        List<Shape> pieceShapeListI0 = new ArrayList();
        pieceShapeListI0.add(new Shape(0,0));
        pieceShapeListI0.add(new Shape(0,1));
        pieceShapeListI0.add(new Shape(1,1));
        pieceShapeListI0.add(new Shape(1,2));

        pieceShapeListOfList.add(pieceShapeListI0);
        
        // Z 1
        List<Shape> pieceShapeListI1 = new ArrayList();
        pieceShapeListI1.add(new Shape(0,2));
        pieceShapeListI1.add(new Shape(1,1));
        pieceShapeListI1.add(new Shape(1,2));
        pieceShapeListI1.add(new Shape(2,1));
        pieceShapeListOfList.add(pieceShapeListI1);
        
        // Z 2
        List<Shape> pieceShapeListI2 = new ArrayList();
        pieceShapeListI2.add(new Shape(1,0));
        pieceShapeListI2.add(new Shape(1,1));
        pieceShapeListI2.add(new Shape(2,1));
        pieceShapeListI2.add(new Shape(2,2));
        pieceShapeListOfList.add(pieceShapeListI2);
        
        // Z 3
        List<Shape> pieceShapeListTI3 = new ArrayList();
        pieceShapeListTI3.add(new Shape(0,1));
        pieceShapeListTI3.add(new Shape(1,0));
        pieceShapeListTI3.add(new Shape(1,1));
        pieceShapeListTI3.add(new Shape(2,0));

    //J
        // J 0
        List<Shape> pieceShapeListI0 = new ArrayList();
        pieceShapeListI0.add(new Shape(0,0));
        pieceShapeListI0.add(new Shape(1,0));
        pieceShapeListI0.add(new Shape(1,1));
        pieceShapeListI0.add(new Shape(1,2));

        pieceShapeListOfList.add(pieceShapeListI0);
        
        // J 1
        List<Shape> pieceShapeListI1 = new ArrayList();
        pieceShapeListI1.add(new Shape(0,1));
        pieceShapeListI1.add(new Shape(0,2));
        pieceShapeListI1.add(new Shape(1,1));
        pieceShapeListI1.add(new Shape(2,1));
        pieceShapeListOfList.add(pieceShapeListI1);
        
        // J 2
        List<Shape> pieceShapeListI2 = new ArrayList();
        pieceShapeListI2.add(new Shape(1,0));
        pieceShapeListI2.add(new Shape(1,1));
        pieceShapeListI2.add(new Shape(1,2));
        pieceShapeListI2.add(new Shape(2,2));
        pieceShapeListOfList.add(pieceShapeListI2);
        
        // J 3
        List<Shape> pieceShapeListTI3 = new ArrayList();
        pieceShapeListTI3.add(new Shape(0,1));
        pieceShapeListTI3.add(new Shape(1,1));
        pieceShapeListTI3.add(new Shape(2,0));
        pieceShapeListTI3.add(new Shape(2,1));

    //L
        // L 0
        List<Shape> pieceShapeListI0 = new ArrayList();
        pieceShapeListI0.add(new Shape(0,2));
        pieceShapeListI0.add(new Shape(1,0));
        pieceShapeListI0.add(new Shape(1,1));
        pieceShapeListI0.add(new Shape(1,2));

        pieceShapeListOfList.add(pieceShapeListI0);
        
        // L 1
        List<Shape> pieceShapeListI1 = new ArrayList();
        pieceShapeListI1.add(new Shape(0,1));
        pieceShapeListI1.add(new Shape(1,1));
        pieceShapeListI1.add(new Shape(2,1));
        pieceShapeListI1.add(new Shape(2,2));
        pieceShapeListOfList.add(pieceShapeListI1);
        
        // L 2
        List<Shape> pieceShapeListI2 = new ArrayList();
        pieceShapeListI2.add(new Shape(1,0));
        pieceShapeListI2.add(new Shape(1,1));
        pieceShapeListI2.add(new Shape(1,2));
        pieceShapeListI2.add(new Shape(2,0));
        pieceShapeListOfList.add(pieceShapeListI2);
        
        // L 3
        List<Shape> pieceShapeListTI3 = new ArrayList();
        pieceShapeListTI3.add(new Shape(0,0));
        pieceShapeListTI3.add(new Shape(0,1));
        pieceShapeListTI3.add(new Shape(1,1));
        pieceShapeListTI3.add(new Shape(2,1));

*/


/**
 *
 * @author gabriel
 */
public abstract class Piece {
    
    int positionBoardY = 0;
    int positionBoardX = 3;
    
    int positionXLeftLimit = 0;
    int positionXRightLimit = 0;
    int positionYBottomLimit = 0;
    
    
    int rotationNumber = 0;
    int width = 0;
    int height = 0;
            
    int limitX = 0;
    int limitY = 0;
    
    List<List<Shape>> pieceShapeListOfList = new ArrayList();
    
    public abstract int rotate();
    public abstract List<Shape> getCurrentRotation();
    public abstract void loadShapesOnPiece();
    public abstract void setCustomShape(int shape);
    public abstract int getCurrentNumberShape();
    
    public Piece(){
       
        
    }

    public int getPositionBoardY() {
        return positionBoardY;
    }

    public void setPositionBoardY(int positionBoardY) {
        this.positionBoardY = positionBoardY;
    }

    public int getPositionBoardX() {
        return positionBoardX;
    }

    public void setPositionBoardX(int positionBoardX) {
        this.positionBoardX = positionBoardX;
    }

    public int getRotationNumber() {
        return rotationNumber;
    }

    public void setRotationNumber(int rotationNumber) {
        this.rotationNumber = rotationNumber;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getLimitX() {
        return limitX;
    }

    public void setLimitX(int limitX) {
        this.limitX = limitX;
    }

    public int getLimitY() {
        return limitY;
    }

    public void setLimitY(int limitY) {
        this.limitY = limitY;
    }

    public List<List<Shape>> getPieceShapeListOfList() {
        return pieceShapeListOfList;
    }

    public void setPieceShapeListOfList(List<List<Shape>> pieceShapeListOfList) {
        this.pieceShapeListOfList = pieceShapeListOfList;
    }

    public int getPositionXLeftLimit() {
        return positionXLeftLimit;
    }

    public void setPositionXLeftLimit(int positionXLeftLimit) {
        this.positionXLeftLimit = positionXLeftLimit;
    }

    public int getPositionXRightLimit() {
        return positionXRightLimit;
    }

    public void setPositionXRightLimit(int positionXRightLimit) {
        this.positionXRightLimit = positionXRightLimit;
    }

    public int getPositionYBottomLimit() {
        return positionYBottomLimit;
    }

    public void setPositionYBottomLimit(int positionYBottomLimit) {
        this.positionYBottomLimit = positionYBottomLimit;
    }
    
}