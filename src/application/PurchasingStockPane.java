package application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mainClasses.*;

import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

public class PurchasingStockPane extends Pane {
    DecimalFormat decimalFormat=new DecimalFormat("0.##");
    SelectedItemsTable t;
    ArrayList<Integer>quant= new ArrayList<Integer>();
	ArrayList<Item>itemssel= new ArrayList<Item>();
	StorePurchasingOrder items;
    private Store myStore;
    private Supplier supplier;
    private PurchaseReceipListPane pur;
    private  Text total=new Text("0");
    PurchasingStockPane(Store myStore, PurchaseReceipListPane pur){
        this.myStore=myStore;
        this.pur=pur;






        SelectedItemsTable t = new SelectedItemsTable();
        SuppliersAvailable suppavailable = new SuppliersAvailable(myStore.getSuppliers());





        //--------------------------------------
        VBox calcul=new VBox();
        Button kousa=new Button("Done");
        kousa.setStyle(
                "-fx-background-color: "+Colorss.getMenuColor()+"; " +
                        "-fx-text-fill: white; " +
                        "-fx-border-color: white; " +
                        "-fx-border-radius: 15; " +
                        "-fx-background-radius: 15; " +
                        "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.4), 10, 0, 0, 0);"
        );
        calcul.getChildren().addAll(kousa);
        calcul.setSpacing(10);
        calcul.setAlignment(Pos.CENTER);
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
        	Supplier selectedSupplier = suppavailable.getW().getSelectionModel().getSelectedItem();

        	if (selectedSupplier == null) {
        	    Alert alert = new Alert(Alert.AlertType.WARNING);
        	    alert.setTitle("No Selection");
        	    alert.setHeaderText("No Supplier Selected");
        	    alert.setContentText("Please select a supplier from the table.");
        	    alert.showAndWait();
        	} 
        	    else
        	    {
        	        this.supplier = selectedSupplier;

        	

            ArrayList<Item> selectedItemsOrder = new ArrayList<>(t.getObservableList());
            ArrayList<Integer> selectedItemsQuantity = new ArrayList<>(t.getObsquant());


            Date currentDate = new Date();




            Address managerAddress=new Address("palestine","mountHaifa","beirut","al siyyed street","mahdi al sabeh building",0000);
            Employee manager=new Employee("mousa","kazem",300,"manager",managerAddress,"ms123","8123324");
            PurchaseInvoice managerPurchase=new PurchaseInvoice(manager,supplier,myStore);

            for (int i = 0; i < selectedItemsOrder.size(); i++) {
                Item a = selectedItemsOrder.get(i);//selectedItem of index i;
                Integer b = selectedItemsQuantity.get(i);//selectedItemQuantity of index i
                managerPurchase.addNewRequest(a, b);
            }

            selectedItemsOrder.clear();
            selectedItemsQuantity.clear();




            t.getW().getItems().clear(); // Clears all rows from the TableView
            t.getObservableList().clear();
            t.getObsquant().clear();
            

            //--------------------------------------

            showBuyReceiptDetails(managerPurchase);

            updateDatabase(myStore.getStorage().getItemsInStorage(),myStore.getStorage().getItemsQuantities());


            for (int i=0;i<myStore.getStorage().getItemsInStorage().size();i++){
                Item l=myStore.getStorage().getItemsInStorage().get(i);
                Integer q=myStore.getStorage().getItemsQuantities().get(i);
            }


            //--------------------------------------

            pur.getpurchaseslist().add(managerPurchase);

        	    }
        	    });
       


//=============================================================================================





        for (int i =0;i<items.getcards().size();i++) {
        	ItemCard c= items.getcards().get(i);
        	items.getcards().get(i).getButton().addEventHandler(javafx.event.ActionEvent.ACTION, e->{
        		total.setText(decimalFormat.format(Double.parseDouble(total.getText())+c.getItemQuantity().getValue()*c.getItem().getPrice())) ;
        	});
        }


        VBox controls=new VBox(t.getW(),suppavailable.getW(),calcul); controls.setAlignment(Pos.CENTER);
        HBox h = new HBox(items,controls);
        controls.setSpacing(20);
        h.setSpacing(5);


        getChildren().add(h);




    }



    private void showBuyReceiptDetails(PurchaseInvoice invoice) {
        Stage detailStage = new Stage();

        VBox detailBox = new VBox(10);
        detailBox.setPadding(new Insets(20));

        Label detailsLabel = new Label("Detailed Information:\n" + invoice.toString());
        detailsLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        detailBox.getChildren().add(detailsLabel);

        Scene detailScene = new Scene(detailBox, 500, 500);
        detailStage.setScene(detailScene);

        // Set the title and show the stage
        detailStage.setTitle("Receipt Details");

        detailStage.show();
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








class SelectedItemsTable extends Pane{
    ArrayList<Item> selectedItems= new ArrayList<Item>();
    ObservableList<Item> observableList ;
    ObservableList<Integer> obsquant ;



    TableView<Item> w = new TableView<Item> ();
    public TableView<Item> getW() {
        return w;
    }


    ArrayList<Integer> selectedItemsQuantity= new ArrayList<Integer>();

    @SuppressWarnings("unchecked")
    SelectedItemsTable(){



        TableView<Item> SelectedItemsTable = new TableView<Item>();
        ObservableList<Item> observableList = FXCollections.observableArrayList(selectedItems);
        ObservableList<Integer> obsquant = FXCollections.observableArrayList(selectedItemsQuantity);

        this.observableList=observableList;
        this.obsquant=obsquant;
        // Add columns and configure the TableView
        TableColumn<Item, String> itemNameColumn = new TableColumn<>("Item Name");
        itemNameColumn.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().getName()));
        TableColumn<Item, String> barcodeColumn = new TableColumn<>("Barcode");
        barcodeColumn.setCellValueFactory(cellData-> new SimpleStringProperty (cellData.getValue().getBarcode()));

        TableColumn<Item, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(cellData -> {
            int index = observableList.indexOf(cellData.getValue());
            ObjectProperty<Integer> quantityProperty = new SimpleObjectProperty<Integer>((obsquant.get(index)));
            return quantityProperty;
        });
        quantityColumn.setMinWidth(100);
        TableColumn<Item, Double> PriceColumn = new TableColumn<>("Price/unit");
        PriceColumn.setCellValueFactory(cellData-> new SimpleObjectProperty<Double> (cellData.getValue().getPrice()));

        SelectedItemsTable.getColumns().addAll(itemNameColumn, barcodeColumn, quantityColumn,PriceColumn);
        SelectedItemsTable.setItems(observableList);

        SelectedItemsTable.setEditable(true);
        SelectedItemsTable.setMaxHeight(250);
        SelectedItemsTable.setMaxWidth(450);
        SelectedItemsTable.setMinHeight(200);
        SelectedItemsTable.setMinWidth(450);
        this.w=SelectedItemsTable;
        this.selectedItems=selectedItems;
        this.selectedItemsQuantity=selectedItemsQuantity;



    }
    public ArrayList<Item> getSelectedItems() {
        return selectedItems;
    }

    public ArrayList<Integer> getSelectedItemsQuantity() {
        return selectedItemsQuantity;
    }


    public ObservableList<Item> getObservableList() {
        return observableList;
    }
    public ObservableList<Integer> getObsquant() {
        return obsquant;
    }

}

class Controls extends Pane{

    Controls(ArrayList<Client> clients, ArrayList<Item> selectedItems, ArrayList<Integer> selectedItemsQuantity){
        VBox controlsMain=new VBox();

        SelectedItemsTable c=new SelectedItemsTable();
        controlsMain.getChildren().addAll(new ClientsTable(clients),c);




        getChildren().add(controlsMain);
    }




}




class SelectedItemInfo{
    private String selectedItemName;
    private int selectedItemQuantity;
    private double selectedItemPrice;
    SelectedItemInfo(Item selectedItem,int selectedItemQuantity){
        this.selectedItemQuantity=selectedItemQuantity;
        this.selectedItemName=selectedItem.getName();
        this.selectedItemPrice=selectedItem.getPrice();
    }
    public String getItemName() {
        return selectedItemName;
    }
    public int getItemQuantity() {
        return selectedItemQuantity;
    }
    public double getPrice() {
        return selectedItemPrice;
    }





}















class StoreItemsOrdering extends Pane{

    ArrayList<Item> selectedItems=new ArrayList<Item>();

    ArrayList<Integer> selectedItemsQuantity=new ArrayList<Integer>();

    StoreItemsOrdering(ArrayList<Item> storeItems, SelectedItemsTable T){

        StackPane itemCardsBox=new StackPane();
        itemCardsBox.setStyle("-fx-background-color: #265073; -fx-background-radius: 5;");
        GridPane items=new GridPane();
        items.setPadding(new Insets(5));
        items.setVgap(5);
        items.setHgap(5);
        int i=0,j=0;
        for(Item item:storeItems){
            if(i==3){i=0;j++;}
            ItemCard card=new ItemCard(item);
            card.getButton().setOnAction(e->{
                T.getSelectedItems().add(item);
                T.getSelectedItemsQuantity().add((card.itemQuantity.getValue()));

            });

            items.add(new ItemCard(item),i,j);
            i++;
        }
        itemCardsBox.getChildren().add(items);
        ScrollPane itemsScroll=new ScrollPane(itemCardsBox);
        itemsScroll.setMaxHeight(650);
        itemsScroll.setMaxWidth(300);
        getChildren().add(itemsScroll);

    }


    public ArrayList<Item> getSelectedItems(){
        return selectedItems;
    }

    public ArrayList<Integer> getSelectedItemsQuantity(){
        return selectedItemsQuantity;
    }


}


class ItemCard extends Pane{

    Item item;

    Button button;

    Spinner<Integer> itemQuantity;

    ItemCard(Item item){

        this.item=item;

        BorderPane card=new BorderPane();



        card.setPadding(new Insets(10,10,10,10));
        card.setStyle("-fx-background-color: white; -fx-background-radius: 5;");
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        Text itemsName=new Text(item.getName());
        itemsName.setFont(Font.font(12));
        Text itemsPrice=new Text(currency.format((item.getPrice())));
        HBox info=new HBox(itemsName,itemsPrice);
        info.setSpacing(20);

        ImageView itemImage=null;
        InputStream stream = getClass().getResourceAsStream(item.getImagePath());
        if (stream != null) {
            Image image = new Image(stream);
            itemImage = new ImageView(image);
            itemImage.setFitWidth(60);
            itemImage.setFitHeight(70);
            card.setCenter(itemImage);
        } else {
            System.err.println("Failed to load the image.");
        }

        HBox controls=new HBox();
        Spinner<Integer> itemQuantity=new Spinner<Integer>();
        SpinnerValueFactory<Integer> p = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1 ,1);
        itemQuantity.setValueFactory(p);
        this.itemQuantity=itemQuantity;
        itemQuantity.setMaxWidth(70);
        Button addItem=new Button("Add");
        addItem.setStyle(
                "-fx-background-color: "+Colorss.getMenuColor()+"; " +
                        "-fx-text-fill: white; " +
                        "-fx-border-color: white; " +
                        "-fx-border-radius: 15; " +
                        "-fx-background-radius: 15; " +
                        "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.4), 10, 0, 0, 0);"
        );
        this.button=addItem;
        controls.setSpacing(10);
        controls.getChildren().addAll(itemQuantity,addItem);
        controls.setPadding(new Insets(5,5,5,5));






        card.setCenter(itemImage);
        card.setBottom(controls);
        card.setTop(info);
        card.setMinWidth(160);
        getChildren().add(card);

    }


    public Button getButton(){
        return button;
    }


    public Spinner<Integer> getItemQuantity(){
        return itemQuantity;
    }

    public Item getItem(){
        return item;
    }


}

class SuppliersAvailable extends Pane{
    ObservableList<Supplier> observableList ;
    TableView<Supplier> w = new TableView<Supplier> ();
    public TableView<Supplier> getW() {
        return w;
    }

    @SuppressWarnings("unchecked")
    SuppliersAvailable(ArrayList <Supplier> suppliers){

        w = new TableView<Supplier>();
        observableList = FXCollections.observableArrayList(suppliers);
        TableColumn<Supplier, String> SuppName = new TableColumn<>("Supplier Name ");
        SuppName.setCellValueFactory(cellData-> new SimpleStringProperty (cellData.getValue().getName()));

        TableColumn<Supplier, String> SuppCountry = new TableColumn<>("Country");
        SuppCountry.setCellValueFactory(cellData-> new SimpleStringProperty (cellData.getValue().getAddress().getCountry()));

        TableColumn<Supplier, Integer> SuppCity = new TableColumn<>("City");
        SuppCity.setCellValueFactory(cellData-> new SimpleObjectProperty(cellData.getValue().getAddress().getZipcode()));

        w.getColumns().addAll(SuppName,SuppCountry ,SuppCity );
        w.setItems(observableList);

        w.setEditable(true);
        w.setMaxHeight(200);
        w.setMaxHeight(200);
        w.setMaxWidth(450);
        w.setMinWidth(450);
        getChildren().add(w);
    }
}










class StorePurchasingOrder extends Pane{
    DecimalFormat decimalFormat=new DecimalFormat("0.##");

    private ArrayList<ItemCard> cards=new ArrayList<ItemCard>();
    public ArrayList<ItemCard> getcards() {
        return cards;
    }
    private ArrayList<String> checkRepeat = new ArrayList<>();

    StorePurchasingOrder(ArrayList<Item> storeItems, SelectedItemsTable t ){


        StackPane itemCardsBox=new StackPane();
        itemCardsBox.setStyle("-fx-background-color: #c9c9c9; -fx-background-radius: 5;");
        GridPane items=new GridPane();

        items.setPadding(new Insets(10,10,10,10));
        items.setVgap(15);
        items.setHgap(15);

        int i=0,j=0;



        for(Item item:storeItems){
            ItemCard c = new ItemCard(item);
            if(i==3){i=0;j++;}
            cards.add(c);
            items.add(c,i,j);
            c.getButton().setOnAction(e -> {
                // Checking for duplicates
                if (checkRepeat.contains(c.getItem().getName())) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error");
                    alert.setHeaderText("You can't enter the same item more than once.");
                    alert.setContentText("Error");
                    alert.showAndWait();
                    return; // Exit the action here to prevent further processing
                }

                checkRepeat.add(c.getItem().getName());
                t.getObsquant().add(c.getItemQuantity().getValue());
                t.getObservableList().add(c.getItem());

            });
            i++;
        }

        itemCardsBox.getChildren().add(items);
        ScrollPane itemsScroll=new ScrollPane();
        itemsScroll.setContent(itemCardsBox);
        itemsScroll.setMinHeight(600);
        itemsScroll.setMaxHeight(600);
        itemsScroll.setMinWidth(550);
        itemsScroll.setMaxWidth(550);
        VBox v = new VBox();
        v.getChildren().addAll(itemsScroll,t);
        getChildren().add(v);
    }


    public ArrayList<String> getCheckRepeat() {
        return checkRepeat;
    }
}
class DeliveryForm extends Pane {


    private CheckBox  deliveryCheck;
    private TextField Street,Building,Country,City,State,Zipcode;



    public VBox MakeDelForm() {

        VBox delPane=new VBox();


        GridPane g = new GridPane();
        this. Country = new TextField();
        Country.setPromptText("Country");
        this. State = new TextField();
        State.setPromptText("State");
        this. Street = new TextField();
        Street.setPromptText("Street");
        this. Building = new TextField();
        Building.setPromptText("Building");
        this. City = new TextField();
        City.setPromptText("City");
        this. Zipcode = createNumericTextField("");
        Zipcode.setPromptText("Zipcode");
        TextField t =new TextField("Please Fill The Required Information About Your Location : ");

        t.setEditable(false);
        g.add(t,0,0,3,1);
        g.add(Country,0,1);
        g.add(City,1,1);
        g.add(State, 2,1);
        g.add(Street, 0, 2);
        g.add(Building,1,2);
        g.add(Zipcode,2,2);

        g.setVgap(10);
        g.setHgap(10);


        this.deliveryCheck=new CheckBox("is delivered?");
        deliveryCheck.setSelected(true);
        deliveryCheck.setOnAction(e->{
            if(deliveryCheck.isSelected()){
                delPane.getChildren().add(g);
            }
            else {
                delPane.getChildren().remove(1);
            }


        });


        delPane.getChildren().addAll(deliveryCheck,g);


        return delPane;

    }


    public CheckBox getDeliveryCheck(){
        return deliveryCheck;
    }


    public TextField getStreet() {
        return Street;
    }


    public TextField getBuilding() {
        return Building;
    }

    public void setBuilding(TextField building) {
        Building = building;
    }

    public TextField getCountry() {
        return Country;
    }

    public TextField getCity() {
        return City;
    }

    public TextField getState() {
        return State;
    }


    public TextField getZipcode() {
        return Zipcode;
    }
    private TextField createNumericTextField(String promptText) {
        // Create a TextField that only accepts numeric input
        TextField textField = new TextField();
        textField.setPromptText(promptText);

        // Add a listener to validate the input
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                Alert t = new Alert(AlertType.ERROR);
                t.setTitle("Character Detected ! ");
                t.setContentText("Please Enter a valid Number");
                textField.setText(oldValue); // Revert to the previous value
                t.show();
            }
        });

        return textField;
    }
}








