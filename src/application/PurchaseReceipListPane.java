package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import mainClasses.PurchaseInvoice;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class PurchaseReceipListPane extends Pane {
    Stage primaryStage;
    ObservableList<PurchaseInvoice> purchaseslist;
    ListView<PurchaseInvoice> listView = new ListView<>();

    PurchaseReceipListPane(ArrayList<PurchaseInvoice> purchases) {
        purchaseslist = FXCollections.observableArrayList(purchases);
        listView.setItems(purchaseslist);
        listView.setCellFactory(e -> new DesigningReceiptList2(primaryStage));
        VBox v = new VBox();
        TextField l = new TextField("BELOW IS THE LIST OF RECEIPTS :");
        TextField l2 = new TextField("Click on any to Display:");
        l2.setFont(Font.font(25));
        l.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 30));
        listView.setMinSize(1000, 630);
        v.getChildren().addAll(l, l2, listView);
        getChildren().add(v);

    }

    private static class DesigningReceiptList2 extends ListCell<PurchaseInvoice> {

        private final Stage primaryStage;

        DesigningReceiptList2(Stage primaryStage) {
            this.primaryStage = primaryStage;
        }

        @Override
        protected void updateItem(PurchaseInvoice purchase, boolean empty) {
            super.updateItem(purchase, empty);

            if (empty || purchase == null) {
                setText(null);
                setBackground(Background.EMPTY);
            } else {
                DecimalFormat d = new DecimalFormat("0.##");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                HBox receiptBox = new HBox(10);
                receiptBox.setPadding(new Insets(10));

                Rectangle colorIndicator = new Rectangle(10, 30);
                colorIndicator.setFill(Color.web("#66cc99"));
                VBox textInfo = new VBox(5);
                Label nameLabel = new Label("Buyer: " + purchase.getManager().getFname() + purchase.getManager().getLname());
                Label amountLabel = new Label("Amount: " + d.format(purchase.getFinalprice()));
                Label dateLabel = new Label("Date: " + purchase.getDeliveryTime().format(formatter));

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


                setOnMouseClicked(event -> showReceiptDetails2(purchase));
            }
        }

        private void showReceiptDetails2(PurchaseInvoice purchase) {
            Stage detailStage = new Stage();

            VBox detailBox = new VBox(10);
            detailBox.setPadding(new Insets(20));

            Label detailsLabel = new Label("Detailed Information:\n" + purchase.toString());
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

    public ObservableList<PurchaseInvoice> getpurchaseslist() {
        return purchaseslist;
    }

    public ListView<PurchaseInvoice> getListView() {
        return listView;
    }

}
