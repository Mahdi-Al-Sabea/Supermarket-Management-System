module Programming3Project {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.logging;
	requires javafx.graphics;
	requires javafx.base;
	requires java.sql;
	
	opens application to javafx.graphics, javafx.fxml,javafx.base;
    opens mainClasses to javafx.base, javafx.fxml, javafx.graphics;

}
