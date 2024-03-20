package application;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class Notification   {
    static void showNotification(String message) {
        Label notification = new Label(message);
        notification.getStyleClass().add("notification");
        notification.setFont(Font.font(13));
        notification.setTextFill(Color.WHEAT);
        StackPane notificationPane = new StackPane(notification);
        notificationPane.setAlignment(Pos.TOP_CENTER);
        notificationPane.setStyle("-fx-background-color: #4a646c; -fx-padding: 10px;");

        Scene scene = new Scene(notificationPane, 300, 50);
        scene.setFill(Color.TRANSPARENT);

        Stage notificationStage = new Stage(StageStyle.TRANSPARENT);
        notificationStage.setScene(scene);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(4), new KeyValue(notificationStage.opacityProperty(), 0))
        );
        timeline.setOnFinished(event -> notificationStage.hide());
        timeline.play();
        notificationStage.show();
    }
}
