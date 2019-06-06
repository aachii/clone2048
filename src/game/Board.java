package game;

import java.util.Random;

public class Board {
	private int[][] board;
	private final int SPAWN_COUNT_INITIAL = 2;
	private final int SPAWN_COUNT = 1;
	private final int POSITIVE = 1;
	private final int NEGATIVE = -1;
	private final int VERTICAL = 0;
	private final int HORIZONTAL = 1;
	
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
		return ret;
	}
	
	public void shiftUp() {
		shiftHelp(0, board.length, 0, board[0].length, POSITIVE, VERTICAL);
		System.out.println("up");
	}
	
	public void shiftDown() {
		shiftHelp(board.length-1, -1, board[0].length-1, -1, NEGATIVE, VERTICAL);
		System.out.println("down");
	}
	
	public void shiftLeft() {
		shiftHelp(0, board.length, 0, board[0].length, POSITIVE, HORIZONTAL);
		System.out.println("left");
	}
	
	public void shiftRight() {
		shiftHelp(board.length-1, -1, board[0].length-1, -1, NEGATIVE, HORIZONTAL);
		System.out.println("right");
	}
	
	public void spawn() {
		spawnHelp(SPAWN_COUNT);
	}
	
	public int[][] getNums() {
		return board;
	}
	
	private void shiftHelp(int startI, int endI, int startJ, int endJ, int inc, int direction) {
		for (int i=startI; i!=endI; i+=inc) {
			for (int j=startJ; j!=endJ; j+=inc) {
				//System.out.println("x:"+i+" y:"+j);
				if (direction == VERTICAL) {
					int pull = i+inc;
					while (pull >= 0 && pull < board.length) {
						if (board[pull][j] != 0 && board[i][j] == 0) {
							board[i][j] = board[pull][j];
							board[pull][j] = 0;
							break;
						} else {
							pull+=inc;
						}
					}
					merge(i, j, i-inc, j);
				} else if (direction == HORIZONTAL) {
					int pull = j+inc;
					while (pull >= 0 && pull < board[0].length) {
						if (board[i][pull] != 0 && board[i][j] == 0) {
							board[i][j] = board[i][pull];
							board[i][pull] = 0;
							break;
						} else {
							pull+=inc;
						}
					}
					merge(i, j, i, j-inc);
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
	
	private void merge(int iif, int ijf, int iis, int ijs) {
		if (iis >= 0 && iis < board.length && ijs >= 0 && ijs < board[0].length) {
			//System.out.println("xf:"+iif+" yf:"+ijf+" xs:"+iis+" ys:"+ijs);
			if (board[iif][ijf] == board[iis][ijs]) {
				board[iis][ijs] *= 2;
				board[iif][ijf] = 0;
			} else if (board[iis][ijs] == 0) {
				board[iis][ijs] = board[iif][ijf];
				board[iif][ijf] = 0;
			}
		}
	}
}
