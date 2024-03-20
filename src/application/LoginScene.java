package application;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
public class LoginScene extends Application {
		private TextField usernameTF ;
		private Button login;
		private PasswordField passPf;
		private String password , username , user ;
        public void start(Stage stage)  {
            BorderPane loginpage = getlogin();
            Scene sc = new Scene(loginpage);
            stage.setTitle("Login Page");
            sc.getRoot().setStyle("-fx-background-color: "+Colorss.getThirdColor()+";");
            stage.setScene(sc);
            stage.show();
        }
        public BorderPane getlogin () {
        	
            BorderPane loginpage = new BorderPane();

            VBox rightContent = new VBox();
            rightContent.setAlignment(Pos.CENTER);

            Text logintitle = new Text("Log In :    ");
            Font loginfont = Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR,30 );
            logintitle.setFont(loginfont);



            VBox usernamelabel = new VBox();

            HBox usernamedesc = new HBox();
            ImageView usericon = new ImageView(new Image(getClass().getResourceAsStream("images/lock.png")));//user icon
            usericon.setFitWidth(15);
            usericon.setFitHeight(15);
            Text usernametext = new Text("Username:");
            Font usernameloginfont = Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 15);
            usernametext.setFont(usernameloginfont);

            usernamedesc.getChildren().addAll(usericon, usernametext);
            usernamedesc.setSpacing(5);

            TextField inputUsernameTf = new TextField();
            this.usernameTF=inputUsernameTf;
            inputUsernameTf.setStyle(
                    "-fx-border-color: black; " + // Border color
                            "-fx-border-width: 0 0 1 0; " + // Bottom border only
                            "-fx-background-color: transparent;" // Transparent background
            );

            usernamelabel.getChildren().addAll(usernamedesc, inputUsernameTf);

            VBox passlabel = new VBox();
            HBox passdesc = new HBox();
            ImageView passIcon = new ImageView(new Image(getClass().getResourceAsStream("images/user.png")));//password icon
            passIcon.setFitWidth(15);
            passIcon.setFitHeight(15);
            Text passtext = new Text("Password:");
            Font passloginfont = Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 15);
            passtext.setFont(passloginfont);
            
            passdesc.getChildren().addAll(passIcon, passtext);
            passdesc.setSpacing(5);

            PasswordField passinput = new PasswordField();
            this.passPf=passinput;
            passinput.setStyle(
                    "-fx-border-color: black; " +
                            "-fx-border-width: 0 0 1 0; " +
                            "-fx-background-color: transparent;"
            );

            passlabel.getChildren().addAll(passdesc, passinput);

            rightContent.setSpacing(40);
            rightContent.setAlignment(Pos.CENTER);

            ImageView leftImg = new ImageView(new Image(getClass().getResourceAsStream("images/supermarket.jpeg")));//wallpaper
            VBox leftContent = new VBox(leftImg);
            leftContent.setAlignment(Pos.CENTER);
            leftImg.setFitWidth(850);;
            leftImg.setFitHeight(600);

            Button loginpagebutton = new Button("Log In");
            this.login=loginpagebutton;
            Font loginbtnfont = Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 15);
            loginpagebutton.setFont(loginbtnfont);
            loginpagebutton.setPrefWidth(250);
            loginpagebutton.setStyle(
                    "-fx-background-color: gray;" +
                            "-fx-text-fill: white;" +
                            "-fx-border-color: transparent;" +
                            "-fx-background-radius: 100;" +
                            "-fx-padding: 10 20;"
            );
            rightContent.getChildren().addAll(logintitle, usernamelabel, passlabel, loginpagebutton);
            loginpage.setCenter(leftContent);
            loginpage.setRight(rightContent);
            BorderPane.setMargin(leftContent, new Insets(0,0,0,0));
            rightContent.setPadding(new Insets(0,20,0,0));

            rightContent.setBackground(Background.fill(Color.web("#4a646c")));
            loginpage.setBackground(Background.fill(Color.web("#4a646c")));
            leftContent.setBackground(Background.fill(Color.web("#4a646c")));;
            return loginpage;
        }
		public TextField getUsernameTF() {
			return usernameTF;
		}
		public PasswordField getPassPf() {
			return passPf;
		}
		public String getPassword() {
			return password;
		}
		public String getUsername() {
			return username;
		}
		public String getUser() {
			return user;
		}
		public Button getLogin() {
			return login;
		}
		
        
    }