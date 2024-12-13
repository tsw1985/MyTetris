package com.gabriel.tetris;


import com.gabriel.tetris.pieces.I;
import com.gabriel.tetris.pieces.J;
import com.gabriel.tetris.pieces.L;
import com.gabriel.tetris.pieces.O;
import com.gabriel.tetris.pieces.Piece;
import com.gabriel.tetris.pieces.S;
import com.gabriel.tetris.pieces.Shape;
import com.gabriel.tetris.pieces.T;
import com.gabriel.tetris.pieces.Z;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author gabriel
 */
public class Board extends JFrame{
    
    public static int BOARD_HEIGHT = 20;
    public static int BOARD_WIDTH = 10;
    private int DELAY_MILLIS = 500;
    
    public static boolean MODE_DEBUG = false;
    //public static boolean MODE_DEBUG = true;
    
    public int RANDOM_EX = 0;

    private List<Piece> piecesList = new ArrayList();
    private Piece currentPiece;
    
    //Cells to calculate
    private int[][] cellBoard;
    private int[][] cellBoardAux;
    
    //Cells for UI
    private JButton[][] cellsBoardButtons;
    
    //Panel to add the cells
    private JPanel boardJPanel;
    
    public Board(){
        initFrame();
        initComponents();
        initListeners();
        initPieces();
        createFirstPiece();
        if(!MODE_DEBUG)
            playGame();
    }
    
    private void playGame(){
        
        Runnable myRunnable = new Runnable(){

            public void run(){
                
                while(!isGameOver()){
                    try{
                        Thread.sleep(DELAY_MILLIS);
                        keyDown();
                    }catch (Exception ex){

                    }
                }
                
                endGame();
                System.out.println("**** GAME OVER ****");
            }
        };
        
        myRunnable.run();
    }
    
    private void endGame(){
        JOptionPane.showMessageDialog(this, "You ARE A L-O-S-E-R !!!! hahahahahhaa!!");
    }
    
    private void initPieces(){

        Piece t = new T();
        Piece o = new O();
        Piece j = new J();
        Piece l = new L();
        Piece s = new S();
        Piece z = new Z();
        Piece i = new I();
        
        piecesList.add(t);
        piecesList.add(o);
        piecesList.add(j);
        piecesList.add(l);
        piecesList.add(s);
        piecesList.add(z);
        piecesList.add(i);
        
        if(!MODE_DEBUG)
            Collections.shuffle(piecesList);
        
    }
    
    private void initFrame(){
        setTitle("Tetris");
        setSize(220,360);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    private void createFirstPiece(){
        int index = MODE_DEBUG ? 6 : 0;
        currentPiece = piecesList.get(index); //6 is I without suffle
        System.out.println("CreateFirstPiece - Piece is :  " + currentPiece.getClass().getName());
        piecesList.remove(0);
    }
    
    private void initComponents(){

        //board cells
        cellsBoardButtons = new JButton[BOARD_HEIGHT][BOARD_WIDTH];
        cellBoard = new int[BOARD_HEIGHT][BOARD_WIDTH];
        cellBoardAux = new int[BOARD_HEIGHT][BOARD_WIDTH];
        
        //panel for the board cells
        boardJPanel = new JPanel(new GridLayout(BOARD_HEIGHT, BOARD_WIDTH,1,1));
        boardJPanel.setFocusable(true);
        
        getContentPane().removeAll();
        getContentPane().add(boardJPanel , BorderLayout.CENTER);
        
        //fill buttons on UI
        fillButtonsInGridLayout();
        
        //init board to calculate to 0
        initCalculateBoardCell();
        setVisible(true);
        repaint();
    }
    
    private void initListeners(){
        
        boardJPanel.addKeyListener(new KeyListener() {
            
            @Override
            public void keyTyped(KeyEvent ke) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void keyPressed(KeyEvent ke) {
                
                //UP ROTATE
                if(ke.getKeyCode() == KeyEvent.VK_UP){
                    keyUp();
                }
                
                //DOWN
                if(ke.getKeyCode() == KeyEvent.VK_DOWN){
                    keyDown();
                }
                
                //LEFT
                if(ke.getKeyCode() == KeyEvent.VK_LEFT){
                    keyLeft();
                }
                
                //RIGHT
                if(ke.getKeyCode() == KeyEvent.VK_RIGHT){
                    keyRight();
                }
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                
            }
        });
    }
    
    private Piece getNextPiece(){
        
        Piece nextPiece = null;
        
        if(piecesList.size() > 0){
            nextPiece = piecesList.get(0);
            piecesList.remove(0);
        }else{
            initPieces();
            nextPiece = piecesList.get(0);
            piecesList.remove(0);
        }
        
        System.out.println("Method : NEXT PIECE IS : " + nextPiece.getClass().getName());
        return nextPiece;
    }
    
    private Piece getFuturePieceRotated(Piece piece, int currentRotationNumber){
        
        Piece futurePiece = null;
        if( piece instanceof T) futurePiece = new T();
        if( piece instanceof O) futurePiece = new O();
        if( piece instanceof J) futurePiece = new J();
        if( piece instanceof L) futurePiece = new L();
        if( piece instanceof S) futurePiece = new S();
        if( piece instanceof Z) futurePiece = new Z();
        if( piece instanceof I) futurePiece = new I();

        futurePiece.setCustomShape(currentRotationNumber);
        futurePiece.setPositionBoardX(piece.getPositionBoardX());
        futurePiece.setPositionBoardY(piece.getPositionBoardY());
        futurePiece.rotate();
        return futurePiece;
    }
    
    //check if is game ends
    private boolean isGameOver(){
        return !isRowEmpty(1);
    }
    
    private void rotatePiece(){
        emptyCurrentPositionOfPiece(currentPiece);
        currentPiece.rotate();
        currentPiece.setPositionBoardY(currentPiece.getPositionBoardY());
        resetColorBoardCells();
        setPieceOnCellBoard(currentPiece);
        paintPieceOnBoard();
    }
    
    private void keyUp(){
        
        int currentRotationNumber = currentPiece.getCurrentNumberShape();
        Piece futurePiece = getFuturePieceRotated(currentPiece,currentRotationNumber); 

        //If piece IS NOT on a WALL
        if(futurePiece != null && !checkCollisionWithWalls(0,0, futurePiece)){
            //printAuxBoard();
            if(canRotate(futurePiece,0,0)){
                rotatePiece();
            }else{
                
                //Check if piece is free on left side
                if (isLeftSideFree(currentPiece , -1 , 0)){ 
                    if(currentPiece instanceof I){

                        currentRotationNumber = currentPiece.getCurrentNumberShape();
                        futurePiece = getFuturePieceRotated(currentPiece,currentRotationNumber); 
                        futurePiece.setPositionBoardY(futurePiece.getPositionBoardY()); 
                        futurePiece.setPositionBoardX(futurePiece.getPositionBoardX());

                        if(canMoveToLeft(futurePiece) && 
                           canRotate(futurePiece,-1,0)){
                              keyLeft();
                              rotatePiece();
                        }

                    }else{

                        keyLeft();
                        currentRotationNumber = currentPiece.getCurrentNumberShape();
                        futurePiece = getFuturePieceRotated(currentPiece,currentRotationNumber); 
                        futurePiece.rotate();
                        if(canRotate(futurePiece,0,0)){
                            rotatePiece();
                        }
                    }
                    
                }
                //checking if right side of piece is free
                else if (isLeftSideFree(currentPiece , 1 , 0)){ 

                    if(currentPiece instanceof I){

                        currentRotationNumber = currentPiece.getCurrentNumberShape();
                        futurePiece = getFuturePieceRotated(currentPiece,currentRotationNumber); 
                        futurePiece.setPositionBoardX(futurePiece.getPositionBoardX() + 2  );
                        futurePiece.rotate();
                        
                        if(canRotate(futurePiece,0,0)){
                            keyRight();
                            keyRight();
                            rotatePiece();
                        }

                    }else{
                        keyRight();
                        currentRotationNumber = currentPiece.getCurrentNumberShape();
                        futurePiece = getFuturePieceRotated(currentPiece,currentRotationNumber); 
                        futurePiece.rotate();
                        if(canRotate(futurePiece,0,0)){
                            rotatePiece();
                        }
                    }
                }
            }

        }else{
            //Piece is touching walls
            kickPieceFromWalls(currentRotationNumber);
        }
    }
    
    private void kickPieceFromWalls(int currentRotationNumber){
        
            //Check if piece is in RIGHT WALL
            if( currentPiece.getPositionBoardX() == currentPiece.getPositionXRightLimit()){
                if(currentPiece instanceof I){

                    currentRotationNumber = currentPiece.getCurrentNumberShape();
                    Piece futurePiece = getFuturePieceRotated(currentPiece,currentRotationNumber); 
                    futurePiece.setPositionBoardY(currentPiece.getPositionBoardY());
                    futurePiece.setPositionBoardX(futurePiece.getPositionBoardX() - 1  );
                    futurePiece.rotate();

                    if(canRotate(futurePiece,0,0)){
                        keyLeft();
                        rotatePiece();
                    }
                
                }else{
                    
                    keyLeft();
                    currentRotationNumber = currentPiece.getCurrentNumberShape();
                    Piece futurePiece = getFuturePieceRotated(currentPiece,currentRotationNumber); 
                    futurePiece.rotate();
                
                    if(canRotate(futurePiece,0,0)){
                        rotatePiece();
                    }
                }
            }
            
            //Check if piece is on LEFT wall
            else if( currentPiece.getPositionBoardX() == currentPiece.getPositionXLeftLimit()){
                
                // I piece on left side needs 2 rights
                if(currentPiece instanceof I){

                    currentRotationNumber = currentPiece.getCurrentNumberShape();
                    Piece futurePiece = getFuturePieceRotated(currentPiece,currentRotationNumber); 
                    futurePiece.setPositionBoardX(futurePiece.getPositionBoardX() + 2  );

                    if(canMoveToRight(currentPiece)){
                        if(canRotateIToRight(futurePiece) && canRotate(futurePiece,0,0)){
                            keyRight();
                            keyRight();
                            rotatePiece();
                        }
                    }
                    
                }else{
                    
                    keyRight();
                    currentRotationNumber = currentPiece.getCurrentNumberShape();
                    Piece futurePiece = getFuturePieceRotated(currentPiece,currentRotationNumber); 
                    futurePiece.rotate();

                    if(canRotate(futurePiece,0,0)){
                        rotatePiece();
                    }
                }
                
            }
            
            // if piece is INTO the BOARD (Not in WALLS)
            else{
                
                if(currentPiece instanceof I){
                    
                    currentRotationNumber = currentPiece.getCurrentNumberShape();
                        Piece futurePiece = getFuturePieceRotated(currentPiece,currentRotationNumber); 
                        futurePiece.setPositionBoardX(futurePiece.getPositionBoardX() + 2  );
                        futurePiece.rotate();

                    if(canMoveToRight(currentPiece)){
                        if(canRotateIToRight(futurePiece) && canRotate(futurePiece,0,0)){
                            keyRight();
                            keyRight();
                            rotatePiece();
                        }
                    }

                }else{
                    currentPiece.setCustomShape(currentRotationNumber);                
                }
            }
    }
    
    private boolean canRotateIToRight(Piece piece){
        boolean firstTry  = isPieceTouchedOnSides(1,0, piece);
        boolean secondTry = isPieceTouchedOnSides(2,0, piece);
        if ( !firstTry && !secondTry){
            return true;
        }else{
            return false;
        }
    }

    private boolean canMoveToLeft(Piece piece){
        boolean canMove = false;
        if(!checkCollisionWithWalls(-1,0, piece)){
            if(!isPieceTouchedOnSides(-1,0, piece)){
                canMove = true;
            }
        }
        return canMove;
    }
    
     private boolean canMoveToRight(Piece piece){
        boolean canMove = false;
        if(!checkCollisionWithWalls(1,0, piece)) {
            if(!isPieceTouchedOnSides(1,0, piece)){
                canMove = true;
            }
        }
        return canMove;
    }
    
    private void keyLeft(){
        
        if(!checkCollisionWithWalls(-1,0, currentPiece)){
            if(!isPieceTouchedOnSides(-1,0, currentPiece)){
                if(isPieceTouched(-1,0, currentPiece)){                            

                    copyCurrentBoardToAux();
                    currentPiece = getNextPiece();

                }else{
                    emptyCurrentPositionOfPiece(currentPiece);
                    currentPiece.setPositionBoardX(currentPiece.getPositionBoardX() - 1 );
                    System.out.println("X in LEFT : " + currentPiece.getPositionBoardX());
                    resetColorBoardCells();
                    setPieceOnCellBoard(currentPiece);
                    paintPieceOnBoard();
                }    
            }
        }
    }
    
    private void keyRight(){
        
        if(!checkCollisionWithWalls(1,0, currentPiece)) {
            if(!isPieceTouchedOnSides(1,0, currentPiece)){
                if(isPieceTouched(1,0, currentPiece)){
                    copyCurrentBoardToAux();
                    currentPiece = getNextPiece();
                }else{
                    emptyCurrentPositionOfPiece(currentPiece);
                    currentPiece.setPositionBoardX(currentPiece.getPositionBoardX() + 1 );
                    System.out.println("X in LEFT : " + currentPiece.getPositionBoardX());
                    resetColorBoardCells();
                    setPieceOnCellBoard(currentPiece);
                    paintPieceOnBoard();
                }
            }
        }
    }
    
    private void keyDown(){
        
        if(!checkCollisionWithWalls(0,1, currentPiece)){
                        
            if(isFloorTouched(currentPiece) || isPieceTouched(0,1, currentPiece)){
                
                copyCurrentBoardToAux();
                clearFullRows();
                updateMainBoard();
                paintPieceOnBoard();
                //printMainBoard();
                currentPiece = getNextPiece();
                
            }else{

                emptyCurrentPositionOfPiece(currentPiece);
                currentPiece.setPositionBoardY(currentPiece.getPositionBoardY() + 1 );
                System.out.println("KEYDOWN value Y " + currentPiece.getPositionBoardY());
                resetColorBoardCells();
                setPieceOnCellBoard(currentPiece);
                paintPieceOnBoard();
            }
        }
    }
    
    
    private void copyCurrentBoardToAux(){
        
        for(int y=0 ; y < BOARD_HEIGHT ; y++){
            for(int x= 0 ; x < BOARD_WIDTH ; x++){
                cellBoardAux[y][x] = cellBoard[y][x];
                //System.out.print(cellBoardAux[y][x]);
            }
            //System.out.print("\n");
        }
    }
    
    private void printAuxBoard(){
        
        for(int y=0 ; y < BOARD_HEIGHT ; y++){
            for(int x= 0 ; x < BOARD_WIDTH ; x++){
                System.out.print(cellBoardAux[y][x]);
            }
            System.out.print("\n");
        }
    }
    
    private void printMainBoard(){
        
        System.out.print("MAIN BOARD");
        for(int y=0 ; y < BOARD_HEIGHT ; y++){
            for(int x= 0 ; x < BOARD_WIDTH ; x++){
                System.out.print(cellBoard[y][x]);
            }
            System.out.print("\n");
        }
    }
    
    private boolean isPieceTouched(int xFuture , int yFuture , Piece piece){
        
        boolean touched = false;
        int y = yFuture + piece.getPositionBoardY();
        int x = xFuture + piece.getPositionBoardX();

        List<Shape> currentRotation = piece.getCurrentRotation();
        for (Shape shape : currentRotation){
            
            int cellInBoardY = y + shape.getTileY();
            int cellInBoardX = x + shape.getTileX();
            
            if( cellBoardAux[cellInBoardY][cellInBoardX] == 1){
                touched = true;
            }
        }
        
        return touched;
    }
    
    private boolean isPieceTouchedOnSides(int xFuture , int yFuture , Piece piece){
        
        boolean touched = false;
        int y = piece.getPositionBoardY();
        int x = xFuture + piece.getPositionBoardX();

        List<Shape> currentRotation = piece.getCurrentRotation();
        for (Shape shape : currentRotation){
            
            int cellInBoardY = y + shape.getTileY();
            int cellInBoardX = x + shape.getTileX();
            
            if( cellBoardAux[cellInBoardY][cellInBoardX] == 1){
                touched = true;
            }
        }
        return touched;
    }
    
    private boolean canRotate(Piece piece , int futureX , int futureY){
        
        boolean canRotate = true;
        int y = futureY +  piece.getPositionBoardY();
        int x = futureX + piece.getPositionBoardX();
        
        if(x < 0 || x >= BOARD_WIDTH){
            return false;
        }
        
        if(y >= BOARD_HEIGHT)
           return false;
        

        List<Shape> currentRotation = piece.getCurrentRotation();
        for (Shape shape : currentRotation){
            
            int cellInBoardY = y + shape.getTileY();
            int cellInBoardX = x + shape.getTileX();
            
             //check bottom
            if(cellInBoardY >= BOARD_HEIGHT){
                canRotate = false;
                break;
            }
            
            //check walls
            if(cellInBoardX < 0 || cellInBoardX > BOARD_WIDTH -1){
                canRotate = false;
                break;
            }
            
            //Check in board aux
            if(cellBoardAux[cellInBoardY][cellInBoardX] == 1){
                canRotate = false;
                break;
            }
        }
        
        return canRotate;
    }
    
    
    private boolean isLeftSideFree(Piece piece , int xFuture , int yFuture){
        
        boolean touched = true;
        int y = yFuture + piece.getPositionBoardY();
        int x = xFuture + piece.getPositionBoardX();

        List<Shape> currentRotation = piece.getCurrentRotation();
        for (Shape shape : currentRotation){
            
            int cellInBoardY = y + shape.getTileY();
            int cellInBoardX = x + shape.getTileX();
            
             //check bottom
            if(cellInBoardY >= BOARD_HEIGHT){
                touched = false;
                break;
            }
            
            //check walls
            if(cellInBoardX < 0 || cellInBoardX > BOARD_WIDTH -1){
                touched = false;
                break;
            }
            
            //Check in board aux
            if(cellBoardAux[cellInBoardY][cellInBoardX] == 1){
                touched = false;
                break;
            }
        }
        return touched;
    }
    
    private boolean isFloorTouched(Piece piece){
        
        boolean touch = false;
        if(piece.getPositionBoardY() == piece.getPositionYBottomLimit() ){
            touch = true;
        }
        return touch;
    }

    private boolean checkCollisionWithWalls(int xFuture , int yFuture, Piece piece){
        
        
        boolean isPosibleDraw = false;
        int y = piece.getPositionBoardY();
        int x = piece.getPositionBoardX();
      
        List<Shape> currentRotation = piece.getCurrentRotation();
        for (Shape shape : currentRotation){
            
            int cellInBoardY = y + yFuture + shape.getTileY();
            int cellInBoardX = x + xFuture + shape.getTileX();
            
            //check bottom
            if(cellInBoardY > BOARD_HEIGHT){
                isPosibleDraw = true;
                break;
            }
            
            //check walls
            if(cellInBoardX < 0 || cellInBoardX > BOARD_WIDTH -1){
                isPosibleDraw = true;
                break;
            }
        }
        
        return isPosibleDraw;

    }
    
    private void emptyCurrentPositionOfPiece(Piece piece){

        int y = piece.getPositionBoardY();
        int x = piece.getPositionBoardX();
        List<Shape> currentRotation = piece.getCurrentRotation();
        for (Shape shape : currentRotation){
            int cellInBoardY = y + shape.getTileY();
            int cellInBoardX = x + shape.getTileX();
            if (cellBoard[cellInBoardY][cellInBoardX] == 1){
                cellBoard[cellInBoardY][cellInBoardX] = 0;
            }
        }
    }
    
    private void setPieceOnCellBoard(Piece piece){
        
        int y = piece.getPositionBoardY();
        int x = piece.getPositionBoardX();
        List<Shape> currentRotation = piece.getCurrentRotation();
        for (Shape shape : currentRotation){
            int cellInBoardY = y + shape.getTileY();
            int cellInBoardX = x + shape.getTileX();
            cellBoard[cellInBoardY][cellInBoardX] = 1;
        }
    }
    
    private void paintPieceOnBoard(){
        
        for(int i=0 ; i < BOARD_HEIGHT ; i++){
            for(int j=0 ; j < BOARD_WIDTH ; j++ ){
                if(cellBoard[i][j] == 1){
                    cellsBoardButtons[i][j].setBackground(Color.RED);
                }
            }
        }
    }
    
    private void resetColorBoardCells(){
        
        for(int i=0 ; i < BOARD_HEIGHT ; i++){
            for(int j=0 ; j < BOARD_WIDTH ; j++ ){
                cellsBoardButtons[i][j].setBackground(Color.GRAY);
            }
        }
    }
    
    private void fillButtonsInGridLayout(){
        for(int y=0 ; y < BOARD_HEIGHT ; y++){
            for(int x= 0 ; x < BOARD_WIDTH ; x++){
                JButton button = new JButton();
                button.setBackground(Color.GRAY);
                button.setFocusable(false);
        
                // Test click
                Shape shape = new Shape(y,x);
                button.putClientProperty("shape", shape);
                button.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        JButton ob = (JButton)e.getSource();
                        if(ob != null){
                            Shape sh = (Shape)ob.getClientProperty("shape");
                            System.out.println("Y Button : " + sh.getTileY());
                            System.out.println("X Button : " + sh.getTileX());
                            
                            int sh_y = sh.getTileY();
                            int sh_x = sh.getTileX();
                            
                            if(cellBoard[sh_y][sh_x] == 0 && 
                               cellBoardAux[sh_y][sh_x] == 0){
                                
                                cellBoard[sh_y][sh_x] = 1;
                                cellBoardAux[sh_y][sh_x] = 1;
                                
                            }else{
                                cellBoard[sh_y][sh_x] = 0;
                                cellBoardAux[sh_y][sh_x] = 0;
                            }
                            resetColorBoardCells();
                            setPieceOnCellBoard(currentPiece);
                            paintPieceOnBoard();
                        }
                    }
                });
                // End Test click
                cellsBoardButtons[y][x] = button;
                boardJPanel.add(button);
            }
        }
    }
    
    private void initCalculateBoardCell(){
        for(int y=0 ; y < BOARD_HEIGHT ; y++){
            for(int x= 0 ; x < BOARD_WIDTH ; x++){
                cellBoard[y][x] = 0;
                cellBoardAux[y][x] = 0;
            }
        }
    }
    
    //check full line tetris
    private boolean isRowFull(int row){
        
        boolean isFull = false;
        int counterCell = 0;
        for(int i=0; i < BOARD_WIDTH ; i++){
            if(cellBoardAux[row][i] == 1){ //check later colors of pieces.
                counterCell++;
            }
        }
        
        if(counterCell == BOARD_WIDTH ){
            isFull = true;
        }
        
        return isFull;
    }
    
    //check if a row is EMPTY ( all cells to 0)
    private boolean isRowEmpty(int row){
        
        boolean isEmpty = false;
        int counterCell = 0;
        for(int i=0; i < BOARD_WIDTH ; i++){
            if(cellBoardAux[row][i] == 0){ //check later colors of pieces.
                counterCell++;
            }
        }
        
        if(counterCell == BOARD_WIDTH ){
            isEmpty = true;
        }
        
        return isEmpty;
    }
    
    //Set a row to 0
    private void clearRow(int row){
        for(int i=0; i < BOARD_WIDTH ; i++){
            cellBoardAux[row][i] = 0;
        }
    }
    
    //move a row down ( when the lines are cleaned )
    private void moveRowDown(int row, int numRows){
        
        for(int i=0; i < BOARD_WIDTH ; i++){
            cellBoardAux[row + numRows][i] = cellBoardAux[row][i];
        }
    }
    
    //clear all full lines on board
    private int clearFullRows(){
        int counter = 0;
        for(int row = BOARD_HEIGHT - 1 ; row >= 0 ; row--){
            if(isRowFull(row)){
                clearRow(row);
                counter++;
            }else if( counter > 0){
                moveRowDown(row, counter);
            }
        }
        return counter;
    }
    
    private void updateMainBoard(){
        for(int y=0 ; y < BOARD_HEIGHT ; y++){
            for(int x= 0 ; x < BOARD_WIDTH ; x++){
                cellBoard[y][x] = cellBoardAux[y][x];
            }
        }
    }
}