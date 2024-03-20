package application ;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.util.Duration;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class Menu extends Pane {

    private CustomMenuBtnsPane menuButtonsPane;
    private ArrayList<Button> buttons;
	private Button logoutbtn;
    Menu(String user) {



        VBox menu=new VBox();
        menu.setMinWidth(200);
        menu.setMinHeight(600);
        menu.setMaxHeight(700);


        Text welcomeTxt=new Text();
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy \n hh:mm:ss");
        
        Calendar cal = Calendar.getInstance();
        welcomeTxt.setText(dateFormat.format(cal.getTime()));
        welcomeTxt.setFont(Font.font("Arial",FontPosture.ITALIC,20));
        Timeline t = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            
                Calendar cal2 = Calendar.getInstance();

                welcomeTxt.setText(dateFormat.format(cal2.getTime()));
            
        }));

        t.setCycleCount(Timeline.INDEFINITE);
        t.play();
        StackPane welcome=new StackPane(welcomeTxt);

        welcome.setStyle("-fx-background-color:"+Colorss.getBackgroundColor()+";");
        welcome.setMinHeight(100);



        ArrayList<String> menubuttons=new ArrayList<String>();
        if (user.equals("admin")) {
            List<String> adminMenus = Arrays.asList("employees", "clients","inventory","order for clients","purchasing supplies","receipt list","purchase reciept list","store info","Log Out");
            menubuttons.addAll(adminMenus);
        } else if (user.equals("employee")) {
            List<String> EMPMenus = Arrays.asList("clients","inventory","order for clients","receipt list","Log Out");
            menubuttons.addAll(EMPMenus);
        } else if (user.equals("manager")) {
            List<String> managerMenus = Arrays.asList("employees", "clients","inventory","purchasing supplies","receipt list","purchase reciept list","Log Out");
            menubuttons.addAll(managerMenus);
        }

        menuButtonsPane=new CustomMenuBtnsPane(50,35,menubuttons);
        buttons=menuButtonsPane.getPaneButtons();

        menu.setAlignment(Pos.CENTER);
        getChildren().add(menu);
        menu.getChildren().addAll(welcome,menuButtonsPane);


    }



    public ArrayList<Button> getButtons(){
        return buttons;
    }



	public Button getLogoutbtn() {
		return logoutbtn;
	}

}



class CustomMenuBtnsPane extends Pane {

    private ArrayList<Button> paneButtons=new ArrayList<Button>();

    CustomMenuBtnsPane(int width,int height,ArrayList<String> specified){
        VBox menuButtons=new VBox();
        for (String k:specified) {
            Button btn=new Button(k);
            paneButtons.add(btn);
            btn.setMinWidth(width);
            btn.setMinHeight(height);
            btn.setBackground(Background.fill(Color.LIGHTGRAY));
            menuButtons.setStyle( "-fx-background-color:"+Colorss.getMenuColor()+"; ");
            btn.setStyle(
                    "-fx-background-color: "+Colorss.getMenuColor()+"; " +
                            "-fx-text-fill: white; " +
                            "-fx-border-color: white; " +
                            "-fx-border-radius: 15; " +
                            "-fx-background-radius: 15; " +
                            "-fx-effect-radius: dropshadow(three-pass-box, rgba(0,0,0,0.4), 10, 0, 0, 0);"
            );
            menuButtons.getChildren().add(btn);
            btn.setOnMouseMoved(e->{
                btn.setStyle(
                        "-fx-background-color: "+Colorss.getBackgroundColor()+"; " +
                                "-fx-text-fill: blue; " +
                                "-fx-border-color: white; " +
                                "-fx-border-radius: 15; " +
                                "-fx-background-radius: 15; " +
                                "-fx-effect-radius: dropshadow(three-pass-box, rgba(0,0,0,0.4), 10, 0, 0, 0);"
                );               
            });
            btn.setOnMouseExited(e->{
            	 btn.setStyle(
                         "-fx-background-color:"+Colorss.getMenuColor()+"; " +
                                 "-fx-text-fill: white; " +
                                 "-fx-border-color: white; " +
                                 "-fx-border-radius: 15; " +
                                 "-fx-background-radius: 15; " +
                                 "-fx-effect-radius: dropshadow(three-pass-box, rgba(0,0,0,0.4), 10, 0, 0, 0);"
                 );

            });
        }
        if (specified.size()==9)
        menuButtons.setPadding(new Insets(10,10,45,10));
        else
        	if (specified.size()==5)
                menuButtons.setPadding(new Insets(10,10,270,10));
        	else
        		menuButtons.setPadding(new Insets(10,10,155,10));
        menuButtons.setAlignment(Pos.CENTER);
        menuButtons.setSpacing(20);
        menuButtons.setMinWidth(200);
        getChildren().add(menuButtons);

    }


    public ArrayList<Button> getPaneButtons(){
        return paneButtons;
    }




}