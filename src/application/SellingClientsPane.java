package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mainClasses.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;


public class SellingClientsPane extends Pane {

	ArrayList<Integer>quant= new ArrayList<Integer>();
	ArrayList<Item>itemssel= new ArrayList<Item>();
	StorePurchasingOrder items;
    DecimalFormat decimalFormat=new DecimalFormat("0.##");
    OrderReceiptsListPane or ;
    Store myStore;
    SelectedItemsTable t;
    private  Text total=new Text("0");
	SellingClientsPane(Store store, OrderReceiptsListPane or){
        this.myStore=store;
         t = new SelectedItemsTable();
        ClientsTable C=new ClientsTable(myStore.getClients());


        DeliveryForm deliveryForm=new DeliveryForm();
        VBox deliveryLayout=deliveryForm.MakeDelForm();

        //--------------------------------------
        VBox calcul=new VBox();
        TextField pays=new TextField();
        Label payslb=new Label("Enter value payed",pays);
        payslb.setContentDisplay(ContentDisplay.RIGHT);
        Text change=new Text("change:");
        Button kousa=new Button("done");
        kousa.setStyle(
                "-fx-background-color: #055592; " +
                        "-fx-text-fill: white; " +
                        "-fx-border-color: white; " +
                        "-fx-border-radius: 15; " +
                        "-fx-background-radius: 15; " +
                        "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.4), 10, 0, 0, 0);"
        );
        Text totallabel = new Text("Total");
        HBox totals = new HBox(totallabel,total);
        totals.setAlignment(Pos.CENTER);
        totals.setSpacing(20);
        calcul.getChildren().addAll(totals,payslb,change,kousa);
        calcul.setSpacing(10);
        calcul.setAlignment(Pos.CENTER);
//--------------------------------------



//===========================================================================================================

        //--------------------------------------
        
        t.getW().setMaxHeight(150);

        final Client[] selectedClient = {null}; // Use an array to hold the reference

        C.getT().getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedClient[0] = C.getT().getSelectionModel().getSelectedItem();
            }
        });

        
        change.setText("change:");

        StorePurchasingOrder items=new StorePurchasingOrder(myStore.getStorage().getItemsInStorage(),t);
        this.items=items;
        for (int i =0;i<items.getcards().size();i++) {
        	ItemCard c= items.getcards().get(i);
        	items.getcards().get(i).getButton().addEventHandler(javafx.event.ActionEvent.ACTION, e->{
        		boolean found = false;
        		for(int j =0 ;j<items.getCheckRepeat().size();j++) {
        			if (items.getCheckRepeat().get(j).equals(c.getItem().getName())) {
        				found = true;
        			}
        		}
        		if (found==false)
        		total.setText(decimalFormat.format(Double.parseDouble(total.getText())+(c.getItemQuantity().getValue()*c.getItem().getPrice()))) ;
        		quant.add(c.getItemQuantity().getValue());
        		itemssel.add(c.getItem());
        		
        	});
        }
        t.getW().setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                handleDoubleClick(t.getW());
            }
            
        });
       
		kousa.setOnAction(e -> {
            ArrayList<Item> selectedItemsOrder = new ArrayList<>(t.getObservableList());
            ArrayList<Integer> selectedItemsQuantity = new ArrayList<>(t.getObsquant());

            int flag=0;
            for (int i = 0; i < selectedItemsOrder.size(); i++) {
                Item a = selectedItemsOrder.get(i);//selectedItem of index i;
                Integer b = selectedItemsQuantity.get(i);//selectedItemQuantity of index i

                for (int j = 0; j < myStore.getStorage().getItemsInStorage().size(); j++){
                    Item itemInStore=myStore.getStorage().getItemsInStorage().get(j);

                    if(a.getName().equals(itemInStore.getName())){

                        Integer quantityOfItemInStore=myStore.getStorage().getItemsQuantities().get(j);

                        if((quantityOfItemInStore-b)<0){
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Error");
                            alert.setHeaderText("not enough of "+a.getName()+" in storage");
                            alert.setContentText("Please inform the clients the lack of the product or inform the manager for a restock.");
                            alert.showAndWait();
                            flag=-1;
                            t.getW().getItems().clear(); // Clears all rows from the TableView
                            t.getObservableList().clear();
                            t.getObsquant().clear();

                        }

                    }
                }
            }

             if (selectedClient[0]==null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Client Not Selected");
                alert.setContentText("Please select a client from the clients table.");
                alert.showAndWait();

                

            } else if (pays.getText().equals("") || !pays.getText().matches("-?\\d+(\\.\\d+)?")) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("please enter a value in change ");
                alert.setContentText("enter the value the client pays in change textfield.");
                alert.showAndWait();

            } else {


                Date currentDate = new Date();
                Order myOrder=null;
                if(deliveryForm.getDeliveryCheck().isSelected()){
                	if(deliveryForm.getStreet().getText().isEmpty()||
                			deliveryForm.getStreet().getText().isEmpty()||
                			deliveryForm.getCity().getText().isEmpty()||
                			deliveryForm.getBuilding().getText().isEmpty()||
                			deliveryForm.getState().getText().isEmpty()||
                			deliveryForm.getCountry().getText().isEmpty()||
                			deliveryForm.getZipcode().getText().isEmpty()
                			) {
                		Alert tt = new Alert(AlertType.ERROR);
                		tt.setContentText("Please Fill in the Delivery Form if you need .\nOtherwise Unselect the Delivery Choice");
                		tt.show();
                	}
                	else {
                    Address addressTo=new Address(deliveryForm.getCountry().getText(),deliveryForm.getState().getText(),deliveryForm.getCity().getText(),deliveryForm.getStreet().getText(),deliveryForm.getBuilding().getText(),Integer.parseInt(deliveryForm.getZipcode().getText()));
                    Delivery OrderDelivery=new Delivery(myStore.getAddress(),addressTo,myStore.getEmployees().get(1),"moutsik");
                    myOrder = new Order(myStore.getEmployees().get(0), selectedClient[0], myStore,OrderDelivery);
                	}
                }
                else  
                    myOrder = new Order(myStore.getEmployees().get(0), selectedClient[0], myStore);
                	
                
               for (int i = 0; i < selectedItemsOrder.size(); i++) {
                    Item a = selectedItemsOrder.get(i);//selectedItem of index i;
                    Integer b = selectedItemsQuantity.get(i);//selectedItemQuantity of index i
                    myOrder.addNewRequest(a, b);
                }

                selectedItemsOrder.clear();
                selectedItemsQuantity.clear();


                if(Double.parseDouble(pays.getText())<myOrder.getFinalprice()){

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error");
                    alert.setHeaderText("the amout the client payed is insuffcient\n"
                    		+ "There are vat taxes.");
                    alert.setContentText("error");
                    alert.showAndWait();

                }
                else {




                    t.getW().getItems().clear(); // Clears all rows from the TableView
                    t.getObservableList().clear();
                    t.getObsquant().clear();
                    items.getCheckRepeat().clear();



                    //--------------------------------------


                    for (int i = 0; i < myStore.getStorage().getItemsInStorage().size(); i++) {
                        Item l = myStore.getStorage().getItemsInStorage().get(i);
                        Integer q = myStore.getStorage().getItemsQuantities().get(i);
                    }


                    //--------------------------------------
                
                    showReceiptDetails(myOrder);

                    myStore.getOrders().add(myOrder);
                    updateDatabase(myStore.getStorage().getItemsInStorage(), myStore.getStorage().getItemsQuantities());
                    or.getOrderslist().add(myOrder);
                    total.setText("0");
                    double payedValue = Double.parseDouble(pays.getText());
                    double remainder = payedValue - myOrder.getFinalprice();
                    change.setText("Change: " + decimalFormat.format(remainder));
                }
                }
            
        });
        
        
//=============================================================================================




        VBox controls=new VBox(C.getT(),t.getW(),deliveryLayout,calcul,kousa); controls.setAlignment(Pos.CENTER);
        HBox h = new HBox(items,controls);//need h
        
      getChildren().add(h);







    }

	
private void handleDoubleClick(TableView<Item> tableView) {
    Item selectedItem = tableView.getSelectionModel().getSelectedItem();
    if (selectedItem != null) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Deletion");
        alert.setHeaderText("Are you sure you want to delete this item?");
        alert.setContentText(selectedItem.getName());

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Remove the item from the table view
            tableView.getItems().remove(selectedItem);
            Notification.showNotification(selectedItem.getName()+ " is Removed from the List");
            total.setText(decimalFormat.format(Double.parseDouble(total.getText())-quant.get(itemssel.indexOf(selectedItem))*itemssel.get(itemssel.indexOf(selectedItem)).getPrice()));
            items.getCheckRepeat().remove(selectedItem.getName());
        }
    }
}

    private void showReceiptDetails(Order order) {
        Stage detailStage = new Stage();

        VBox detailBox = new VBox(10);
        detailBox.setPadding(new Insets(20));

        Label detailsLabel = new Label("Detailed Information:\n" + order.toString());
        detailsLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        detailBox.getChildren().add(detailsLabel);

        Scene detailScene = new Scene(detailBox, 500, 500);
        detailStage.setScene(detailScene);

        // Set the title and show the stage
        detailStage.setTitle("Receipt Details");
        detailStage.show();
    }




    private void updateDatabase(ArrayList<Item> StoreItems, ArrayList<Integer> StoreItemsQuantities) {
        Connectionsss.Connect();
        String sql = "UPDATE itemsinstorage SET barcode = ?, quantityInStock = ? WHERE name = ?;";

        try (PreparedStatement preparedStatement = Connectionsss.mycon.prepareStatement(sql)) {
            for (int i = 0; i < StoreItems.size(); i++) {
                preparedStatement.setString(1, StoreItems.get(i).getBarcode());
                preparedStatement.setInt(2, StoreItemsQuantities.get(i));
                preparedStatement.setString(3, StoreItems.get(i).getName());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }






}