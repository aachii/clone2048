package controller;

import factory.ColorFactory;
import game.Board;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class GameController {
	private final int BOARD_ROWS = 4;
	private final int BOARD_COLUMNS = 4;
	private final int BLOCK_SIZE = 60;
	private final int BLOCK_SIZE_SPACE = 5;
	//private final int BLOCK_ARC = 10;
	
	private Board board;
	private ColorFactory cf = new ColorFactory();
	private boolean gameIsLost;

    @FXML
    private Pane display;
    
    @FXML
    public void initialize() {
		System.out.println("initialized");
    	board = new Board(BOARD_ROWS, BOARD_COLUMNS);
    	gameIsLost = false;
        updateBlocks(board.getNums());
	}
    
    @FXML
    void handle(KeyEvent event) {
    	if (gameIsLost) { return; }
    	boolean played = false;
    	switch (event.getCode()) {
    		case UP:
    			played = board.shiftUp(); break;
    		case DOWN:
    			played = board.shiftDown(); break;
    		case LEFT:
    			played = board.shiftLeft(); break;
    		case RIGHT:
    			played = board.shiftRight(); break;
		default:
			break;
    	}
    	if (played) {
    		board.spawn();
    		updateBlocks(board.getNums());
    	}
    	if (board.isGameOver()) {
    		gameIsLost = true;
    		System.out.println("Gameover!");
    	}
    }
    
    private void updateBlocks(int[][] nums) {
    	display.getChildren().clear();
    	for (int i=0; i<nums.length; i++) {
    		for (int j=0; j<nums[0].length; j++) {
    			Label lbl = new Label();
    			int spacing = BLOCK_SIZE + BLOCK_SIZE_SPACE;
    			lbl.setLayoutX(j*spacing);
    			lbl.setLayoutY(i*spacing);
    			lbl.setPrefSize(BLOCK_SIZE, BLOCK_SIZE);
    			lbl.getStyleClass().add("gameBlock");
    			lbl.setAlignment(Pos.CENTER);
    			lbl.setStyle("-fx-background-color: "+cf.getColor(nums[i][j])+";");
    			if (nums[i][j] != 0) {
    				lbl.setText(""+nums[i][j]);
    			}
    			display.getChildren().add(lbl);
        	}
    	}
    }
}
