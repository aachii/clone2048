package controller;

import game.Board;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class GameController {
	private final int BOARD_ROWS = 5;
	private final int BOARD_COLUMNS = 5;
	private final int BLOCK_SIZE = 60;
	
	private Label[][] blocks;
	private Board board;

    @FXML
    private VBox display;
    
    public void initialize() {
    	blocks = new Label[BOARD_ROWS][BOARD_COLUMNS];
    	board = new Board(BOARD_ROWS, BOARD_COLUMNS);
        updateBlocks(board.getNums());
    }
    
    private void updateBlocks(int[][] nums) {
    	display.getChildren().clear();
    	for (int i=0; i<nums.length; i++) {
    		for (int j=0; j<nums[0].length; j++) {
    			if (nums[i][j] != 0) {
    				blocks[i][j] = new Label(""+nums[i][j]);
    			} else {
    				blocks[i][j] = new Label();
    			}
    			blocks[i][j].setLayoutX(i*BLOCK_SIZE);
				blocks[i][j].setLayoutY(i*BLOCK_SIZE);
    			display.getChildren().add(blocks[i][j]);
        	}
    	}
    }
}
