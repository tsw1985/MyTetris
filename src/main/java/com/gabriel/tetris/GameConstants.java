package com.gabriel.tetris;

/**
 * Constantes del juego Tetris
 * Centraliza todos los "magic numbers" para evitar duplicación
 * y hacer el código más mantenible.
 * 
 * @author gabriel
 */
public final class GameConstants {
    
    // Prevenir instanciación
    private GameConstants() {}
    
    // ========== TABLERO ==========
    public static final int BOARD_HEIGHT = 20;
    public static final int BOARD_WIDTH = 10;
    
    // ========== PIEZAS ==========
    public static final int MAX_ROTATIONS = 4;
    public static final int MIN_ROTATION = 0;
    public static final int MAX_ROTATION = MAX_ROTATIONS - 1; // 3
    
    public static final int INITIAL_PIECE_X = 3;
    public static final int INITIAL_PIECE_Y = 0;
    
    // ========== ESTADOS DE CELDA ==========
    public static final int CELL_EMPTY = 0;
    public static final int CELL_FILLED = 1;
    
    // ========== JUEGO ==========
    public static final int GAME_SPEED_MS = 500;
    public static final int GAME_OVER_CHECK_ROW = 1;
    
    // ========== MOVIMIENTOS ==========
    public static final int MOVE_LEFT = -1;
    public static final int MOVE_RIGHT = 1;
    public static final int MOVE_DOWN = 1;
    public static final int NO_MOVEMENT = 0;
    
    // ========== UI ==========
    public static final int GRID_SPACING = 1;
    public static final int WINDOW_WIDTH = 220;
    public static final int WINDOW_HEIGHT = 360;
    
    // ========== LÍMITES DE PIEZAS ==========
    public static final int PIECE_WIDTH_STANDARD = 3; // Para la mayoría de piezas
    public static final int PIECE_WIDTH_I = 4;        // Para la pieza I
    public static final int PIECE_HEIGHT_STANDARD = 2;
    public static final int PIECE_HEIGHT_I = 1;
}