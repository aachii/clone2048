module ShiftyBlocks {
	requires transitive javafx.fxml;
    requires transitive javafx.controls;
	requires javafx.graphics;
	requires java.desktop;
	
	exports controller;
	exports game;
	opens controller to javafx.graphics, javafx.fxml;
}