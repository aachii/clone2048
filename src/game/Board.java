package game;

import java.util.Arrays;
import java.util.OptionalInt;
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
		this.board = new int[rows][cols];
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
	
	public boolean shiftUp() {
		System.out.println("up");
		return shiftHelp(0, board.length, 0, board[0].length, POSITIVE, VERTICAL);
	}

	public boolean shiftDown() {
		System.out.println("down");
		return shiftHelp(board.length-1, -1, board[0].length-1, -1, NEGATIVE, VERTICAL);
	}
	
	public boolean shiftLeft() {
		System.out.println("left");
		return shiftHelp(0, board.length, 0, board[0].length, POSITIVE, HORIZONTAL);
	}
	
	public boolean shiftRight() {
		System.out.println("right");
		return shiftHelp(board.length-1, -1, board[0].length-1, -1, NEGATIVE, HORIZONTAL);
	}
	
	public boolean spawn() {
		if (!isGameOver()) {
			spawnHelp(SPAWN_COUNT);
			return true;
		}
		return false;
	}
	
	public boolean isGameOver() {
		for (int i=0; i<board.length; i++) {
			for (int j=0; j<board[0].length; j++) {
				if (board[i][j] == 0) {
					return false;
				} else if (hasEqualNeighbour(i, j)) {
					return false;
				}
			}
		}
		return true;
	}

	public int[][] getNums() {
		return board;
	}
	
	private boolean shiftHelp(int startI, int endI, int startJ, int endJ, int inc, int direction) {
		boolean ret = false;
		for (int i=startI; i!=endI; i+=inc) {
			for (int j=startJ; j!=endJ; j+=inc) {
				//System.out.println("x:"+i+" y:"+j);
				if (direction == VERTICAL) {
					int pull = i+inc;
					while (pull >= 0 && pull < board.length) {
						if (board[pull][j] != 0 && board[i][j] == 0) {
							board[i][j] = board[pull][j];
							board[pull][j] = 0;
							ret = true;
							break;
						} else {
							pull+=inc;
						}
					}
					if (merge(i, j, i-inc, j, inc, true)) { ret = true; }
				} else if (direction == HORIZONTAL) {
					int pull = j+inc;
					while (pull >= 0 && pull < board[0].length) {
						if (board[i][pull] != 0 && board[i][j] == 0) {
							board[i][j] = board[i][pull];
							board[i][pull] = 0;
							ret = true;
							break;
						} else {
							pull+=inc;
						}
					}
					if (merge(i, j, i, j-inc, inc, true)) { ret = true; }
				}
			}
		}
		return ret;
	}

	private void init() {
		for (int i=0; i<board.length; i++) {
			for (int j=0; j<board[0].length; j++) {
				board[i][j] = 0;
			}
		}
	}
	
	private void spawnHelp(int spawnCount) {
		OptionalInt smallest = Arrays.stream(board).flatMapToInt(x -> Arrays.stream(x)).min();
		if (smallest.getAsInt() > 0) {
			return; // do not spawn anything if no empty cell is present
		}
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
	
	private boolean merge(int iif, int ijf, int iis, int ijs, int inc, boolean allowMerge) {
		boolean played = false;
		if (iis >= 0 && iis < board.length && ijs >= 0 && ijs < board[0].length && board[iif][ijf] > 0) {
			//System.out.println("xf:"+iif+" yf:"+ijf+" xs:"+iis+" ys:"+ijs);
			if (board[iif][ijf] == board[iis][ijs] && allowMerge) {
				board[iis][ijs] *= 2;
				board[iif][ijf] = 0;
				played = true;
			} else if (board[iis][ijs] == 0) {
				board[iis][ijs] = board[iif][ijf];
				board[iif][ijf] = 0;
				if (iif == iis) {
					merge(iis, ijs, iis, ijs-inc, inc, false);
				} else if (ijf == ijs) {
					merge(iis, ijs, iis-inc, ijs, inc, false);
				}
				played = true;
			}
		}
		return played;
	}
	
	private boolean hasEqualNeighbour(int i, int j) {
		if (i+1 < board.length && i-1 >= 0) {
			if (board[i][j] == board[i+1][j] || board[i][j] == board[i-1][j]) {
				return true;
			}
		}
		if (j+1 < board[0].length && j-1 >= 0) {
			if (board[i][j] == board[i][j+1] || board[i][j] == board[i][j-1]) {
				return true;
			}
		}
		return false;
	}
}
