package game;

public class BoardTest {

	public static void main(String[] args) {
		Board b = new Board(2, 2);
		System.out.println(b.toString());
		b.shiftDown();
		b.spawn();
		System.out.println(b.toString());
		b.shiftUp();
		b.spawn();
		System.out.println(b.toString());
		b.shiftLeft();
		b.spawn();
		System.out.println(b.toString());
		b.shiftRight();
		b.spawn();
		System.out.println(b.toString());
	}

}
