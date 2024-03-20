package application;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class WelcomeScene extends Application {
		private Button employeeButton,adminButton,managerButton;
		private Scene welcomeScene;
		
		public WelcomeScene() {
        Image backgroundImage = new Image("https://img.freepik.com/premium-photo/empty-top-wooden-table-with-supermarket-blur-background_36051-467.jpg");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitWidth(1220);
        backgroundImageView.setFitHeight(600);
        this.adminButton = new Button("Admin");
        this.managerButton = new Button("Manager");
        this.employeeButton = new Button("Employee");
        employeeButton.setMinSize(400, 100);
        managerButton.setMinSize(400, 100);
        adminButton.setMinSize(400, 100);
        adminButton.setBackground(Background.EMPTY);
        managerButton.setBackground(Background.EMPTY);
        employeeButton.setBackground(Background.EMPTY);
        employeeButton.setBorder(Border.stroke(Color.DARKSLATEGREY));
        
        adminButton.setBorder(Border.stroke(Color.DARKSLATEGREY));
        managerButton.setBorder(Border.stroke(Color.DARKSLATEGREY));
        adminButton.setTextFill(Color.DARKSLATEGREY);
        employeeButton.setTextFill(Color.DARKSLATEGREY);
        managerButton.setTextFill(Color.DARKSLATEGREY);
        managerButton.setFont(Font.font("Times New Roman",FontWeight.BOLD,30));
        adminButton.setFont(Font.font("Times New Roman",FontWeight.BOLD,30));
        employeeButton.setFont(Font.font("Times New Roman",FontWeight.BOLD,30));

        employeeButton.setOnMouseMoved(e->{
        	employeeButton.setBackground(Background.fill(Color.GAINSBORO));
        });
        employeeButton.setOnMouseExited(e->{
        	employeeButton.setBackground(Background.EMPTY);
        });
        adminButton.setOnMouseMoved(e->{
        	adminButton.setBackground(Background.fill(Color.GAINSBORO));
        });
        adminButton.setOnMouseExited(e->{
        	adminButton.setBackground(Background.EMPTY);
        });
        managerButton.setOnMouseMoved(e->{
        	managerButton.setBackground(Background.fill(Color.GAINSBORO));
        });
        managerButton.setOnMouseExited(e->{
        	managerButton.setBackground(Background.EMPTY);
        });
        
        // Set button actions (you need to implement these methods)
        

        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(adminButton, managerButton, employeeButton);

        // Create a stack pane to hold the background image and the vbox
        StackPane layout = new StackPane();
        layout.getChildren().addAll(backgroundImageView, vbox);
        this.welcomeScene = new Scene (layout,1220,600);

		}
		
		
		
 
	public Button getEmployeeButton() {
			return employeeButton;
		}




		public Button getAdminButton() {
			return adminButton;
		}




		public Button getManagerButton() {
			return managerButton;
		}




		public Scene getWelcomeScene() {
			return welcomeScene;
		}




	@Override
	public void start(Stage arg0) throws Exception {
		
	}

    
}
