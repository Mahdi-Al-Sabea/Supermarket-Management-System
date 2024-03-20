package application;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import mainClasses.Order;

public class OrderReceiptsListPane extends Pane {
		Stage primaryStage; 
		ListView<Order> listView = new ListView<>();
		ObservableList<Order> orderslist;
		public ListView<Order> getListView() {
			return listView;
		}

		OrderReceiptsListPane(ArrayList <Order> orders) {

		orderslist= FXCollections.observableArrayList(orders);
		listView.setItems(orderslist);
        listView.setCellFactory(e -> new DesigningReceiptList( primaryStage));
        VBox v = new VBox ();
        TextField l = new TextField ("BELOW IS THE LIST OF RECEIPTS :");
        TextField l2 = new TextField ("Click on any to Display:");
        l2.setFont(Font.font(25));
        l.setFont(Font.font("Times New Roman",FontWeight.BOLD,FontPosture.ITALIC,30));
        listView.setMinSize(1000, 630);
        v.getChildren().addAll(l,l2,listView);
        getChildren().add(v);
        
    }

			public ObservableList<Order> getOrderslist() {
				return orderslist;
			}
    private static class DesigningReceiptList extends ListCell<Order> {

        private final Stage primaryStage;

        DesigningReceiptList(Stage primaryStage) {
            this.primaryStage = primaryStage;
        }

        @Override
        protected void updateItem(Order order, boolean empty) {
            super.updateItem(order, empty);

            if (empty || order == null) {
                setText(null);
                setBackground(Background.EMPTY);
            } else {
                HBox receiptBox = new HBox(10);
                receiptBox.setPadding(new Insets(10));

                Rectangle colorIndicator = new Rectangle(10, 30);
                colorIndicator.setFill(Color.web("#66cc99"));

                VBox textInfo = new VBox(5);
                Label nameLabel = new Label("Customer: " + order.getCostumer().getFname()+order.getCostumer().getLname());
                Label amountLabel = new Label("Amount: " + order.getFinalprice());
                Label dateLabel = new Label("Date: " + order.getDate());

                // Set font color and weight
                nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                nameLabel.setTextFill(Color.WHITE);
                amountLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                amountLabel.setTextFill(Color.WHITE);
                dateLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                dateLabel.setTextFill(Color.WHITE);

                textInfo.getChildren().addAll(nameLabel, amountLabel, dateLabel);

                receiptBox.getChildren().addAll(colorIndicator, textInfo);

                setGraphic(receiptBox);
                int index = getIndex();
                if (index % 2 == 0) {
                    setBackground(new Background(new BackgroundFill(Color.web("#283f5d"), null, null)));
                } else {
                    setBackground(new Background(new BackgroundFill(Color.web("#4B5b55"), null, null)));
                }
                
                
                setOnMouseClicked(event -> showReceiptDetails(order));
            }
        }
        private void showReceiptDetails(Order order) {
            Stage detailStage = new Stage();

            VBox detailBox = new VBox(10);
            detailBox.setPadding(new Insets(20));

            Label detailsLabel = new Label("Detailed Information:\n" + order.toString());
            detailsLabel.setFont(Font.font(40));
            detailsLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));

            detailBox.getChildren().add(detailsLabel);

            Scene detailScene = new Scene(detailBox, 630, 400);
            detailStage.setScene(detailScene);

            // Set the title and show the stage
            detailStage.setTitle("Receipt Details");
            detailStage.initOwner(primaryStage);
            detailStage.show();
        }
    }
}
