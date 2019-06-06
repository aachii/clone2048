package game;

import java.util.Random;

public class Board {
	private int[][] board;
	private final int SPAWN_COUNT_INITIAL = 2;
	private final int SPAWN_COUNT = 1;
	
	public Board(int rows, int cols) {
		this.board = new int[cols][rows];
		init();
		spawnHelp(SPAWN_COUNT_INITIAL);
	}
	
	public String toString() {
		String ret = "";
		for (int i=0; i<board.length; i++) {
			for (int j=0; j<board[0].length; j++) {
				ret+=board[i][j]+" ";
			}
			ret+="\n";
		}
		//return ret.substring(0, ret.length()-1);
		return ret;
	}
	
	public void shiftUp() {
		shiftUpDown(0, board.length-1, 1);
	}
	
	public void shiftDown() {
		shiftUpDown(board.length-1, 0, -1);
	}
	
	public void shiftLeft() {
		shiftLeftRight(0, board[0].length-1, 1);
	}
	
	public void shiftRight() {
		shiftLeftRight(board[0].length-1, 0, -1);
	}
	
	public void spawn() {
		spawnHelp(SPAWN_COUNT);
	}
	
	public int[][] getNums() {
		return board;
	}
	
	private void shiftUpDown(int start, int end, int inc) {
		for (int i=start; i!=end; i+=inc) {
			for (int j=0; j<board[0].length; j++) {
				if (board[i][j] == 0) {
					int pull = i + inc;
					while (pull >= 0 && pull < board.length) {
						if (board[pull][j] != 0) {
							board[i][j] = board[pull][j];
							board[pull][j] = 0;
							merge(i, j, i-inc, j);
							break;
						} else {
							pull+=inc;
						}
					}
				}
			}
		}
	}
	
	private void shiftLeftRight(int start, int end, int inc) {
		for (int i=0; i<board.length; i++) {
			for (int j=start; j!=end; j+=inc) {
				if (board[i][j] == 0) {
					int pull = j + inc;
					while (pull >= 0 && pull < board.length) {
						if (board[i][pull] != 0) {
							board[i][j] = board[i][pull];
							board[i][pull] = 0;
							merge(i, j, i, j-inc);
							break;
						} else {
							pull+=inc;
						}
					}
				}
			}
		}
	}

	private void init() {
		for (int i=0; i<board.length; i++) {
			for (int j=0; j<board[0].length; j++) {
				board[i][j] = 0;
			}
		}
	}
	
	private void spawnHelp(int spawnCount) {
		int c = spawnCount;
		Random rand = new Random();
		while (c > 0) {
			while (true) {
				int i = rand.nextInt(this.board.length);
				int j = rand.nextInt(this.board[0].length);
				if (this.board[i][j] == 0) {
					this.board[i][j] = 2;
					c--;
					if (c == 0) {
						break;
					}
				}
				
			}
		}
	}
	
	private void merge(int ixf, int iyf, int ixs, int iys) {
		if (ixs >= 0 && ixs < board.length && iys >= 0 && iys < board[0].length) {
			if (board[ixf][iyf] == board[ixs][iys]) {
				board[ixs][iys] = board[ixs][iys] + board[ixs][iys];
				board[ixf][iyf] = 0;
			}
		}
	}
}
