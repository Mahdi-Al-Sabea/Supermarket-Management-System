package application;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import mainClasses.Address;
import mainClasses.Store;

public class StoreInfoPane extends Pane {

    private Store myStore;


    StoreInfoPane(Store myStore){
        

        this.myStore=myStore;
        
        GridPane storeCredentials = new GridPane();
        storeCredentials.setPadding(new Insets(20,20,20,20));
        storeCredentials.setVgap(10);
        storeCredentials.setHgap(10);



        BackgroundImage backgroundImage = new BackgroundImage(
                new Image("https://img.freepik.com/free-vector/3d-abstract-background-with-paper-cut-shape-monochrome-carving-art_1217-3747.jpg?w=900&t=st=1702155499~exp=1702156099~hmac=0d79716fbbb41f95da37bbe66a411a649abda9c8a189e80633d8f2f8c7567c79", 1000, 600, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        this.setBackground(new Background(backgroundImage));        
        TextField StoreName = new TextField(myStore.getName());
        StoreName.setEditable(false);
        Label namelabel= new Label("Store name:");
        storeCredentials.add(namelabel, 0, 0);
        storeCredentials.add(StoreName, 1, 0);

        TextField StorePhone = new TextField(myStore.getPhone());
        StorePhone.setEditable(false);
        Label Phonelabel = new Label("Store phone:");
        storeCredentials.add(Phonelabel, 0, 1);
        storeCredentials.add(StorePhone, 1, 1);


        Text Address=new Text("Address:");Address.setFill(Color.WHITE);
        storeCredentials.add(Address,0,2);

        TextField StoreCountry=new TextField(myStore.getAddress().getCountry());
        StoreCountry.setEditable(false);
        Label countrylabel = new Label("Store country:");
        storeCredentials.add(countrylabel, 0, 3);
        storeCredentials.add(StoreCountry, 1, 3);

        TextField StoreState=new TextField(myStore.getAddress().getState());
        StoreState.setEditable(false);
        Label statelabel =new Label("Store State:");
        storeCredentials.add(statelabel, 0, 4);
        storeCredentials.add(StoreState, 1, 4);

        TextField StoreCity=new TextField(myStore.getAddress().getCity());
        StoreCity.setEditable(false);
        Label citylabel = new Label("Store city:");
        storeCredentials.add(citylabel, 0, 5);
        storeCredentials.add(StoreCity, 1, 5);

        TextField StoreStreet=new TextField(myStore.getAddress().getStreet());
        StoreStreet.setEditable(false);
        Label streetlabel=new Label("Store Street:");
        storeCredentials.add(streetlabel, 0, 6);
        storeCredentials.add(StoreStreet, 1, 6);

        TextField StoreBuilding=new TextField(myStore.getAddress().getBuilding());
        StoreBuilding.setEditable(false);
        Label buildinglabel =new Label("Store Building:");
        storeCredentials.add(buildinglabel, 0, 7);
        storeCredentials.add(StoreBuilding, 1, 7);

        TextField StoreZipcode=new TextField(String.valueOf(myStore.getAddress().getZipcode()));
        StoreZipcode.setEditable(false);
        Label storezipcodelabel = new Label("Store zipCode:");
        storeCredentials.add(storezipcodelabel, 0, 8);
        storeCredentials.add(StoreZipcode, 1, 8);

        TextArea description=new TextArea(myStore.getDescription());
        description.setEditable(false);
        Label descLabel = new Label("Store description:");
        storeCredentials.add(descLabel, 0, 9);
        storeCredentials.add(description, 1, 9);
        namelabel.setStyle("-fx-text-fill: white;");
        Phonelabel.setStyle("-fx-text-fill: white;");
        countrylabel.setStyle("-fx-text-fill: white;");
        statelabel.setStyle("-fx-text-fill: white;");
        citylabel.setStyle("-fx-text-fill: white;");
        streetlabel.setStyle("-fx-text-fill: white;");
        buildinglabel.setStyle("-fx-text-fill: white;");
        descLabel.setStyle("-fx-text-fill: white;");
        storezipcodelabel.setStyle("-fx-text-fill: white;");


        Button EditInfo=new Button("Edit info");
        EditInfo.setStyle(
                "-fx-background-color: "+Colorss.getMenuColor()+"; " +
                        "-fx-text-fill: white; " +
                        "-fx-border-color: white; " +
                        "-fx-border-radius: 15; " +
                        "-fx-background-radius: 15; " +
                        "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.4), 10, 0, 0,0);");
        storeCredentials.add(EditInfo,0,10);



        Button applyStoreInfoChanges=new Button("apply");
        applyStoreInfoChanges.setStyle(
                "-fx-background-color: "+Colorss.getMenuColor()+"; " +
                        "-fx-text-fill: white; " +
                        "-fx-border-color: white; " +
                        "-fx-border-radius: 15; " +
                        "-fx-background-radius: 15; " +
                        "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.4), 10, 0, 0,0);"
        );
        applyStoreInfoChanges.setOnAction(e->{

            StoreName.setEditable(false);
            StoreName.setStyle("-fx-text-fill: black;");
            myStore.setName(StoreName.getText());

            StorePhone.setEditable(false);
            StorePhone.setStyle("-fx-text-fill: black;");
            myStore.setPhone(StorePhone.getText());

            StoreCountry.setEditable(false);
            StoreCountry.setStyle("-fx-text-fill: black;");

            StoreState.setEditable(false);
            StoreState.setStyle("-fx-text-fill: black;");

            StoreCity.setEditable(false);
            StoreCity.setStyle("-fx-text-fill: black;");

            StoreStreet.setEditable(false);
            StoreStreet.setStyle("-fx-text-fill: black;");

            StoreBuilding.setEditable(false);
            StoreBuilding.setStyle("-fx-text-fill: black;");

            StoreZipcode.setEditable(false);
            StoreZipcode.setStyle("-fx-text-fill: black;");

            description.setEditable(false);
            description.setStyle("-fx-text-fill: black;");

            mainClasses.Address newAddress=new Address(StoreCountry.getText(),StoreState.getText(),StoreCity.getText(),StoreStreet.getText(),StoreBuilding.getText(),Integer.valueOf(StoreZipcode.getText()));

            myStore.setAddress(newAddress);
            myStore.setDescription(description.getText());
            storeCredentials.getChildren().remove(applyStoreInfoChanges);

        });




        EditInfo.setOnAction(e->{
            StoreName.setEditable(true);
            StoreName.setStyle("-fx-text-fill: red;");
            StorePhone.setEditable(true);
            StorePhone.setStyle("-fx-text-fill: red;");
            StoreCountry.setEditable(true);
            StoreCountry.setStyle("-fx-text-fill: red;");
            StoreState.setEditable(true);
            StoreState.setStyle("-fx-text-fill: red;");
            StoreCity.setEditable(true);
            StoreCity.setStyle("-fx-text-fill: red;");
            StoreStreet.setEditable(true);
            StoreStreet.setStyle("-fx-text-fill: red;");
            StoreBuilding.setEditable(true);
            StoreBuilding.setStyle("-fx-text-fill: red;");
            StoreZipcode.setEditable(true);
            StoreZipcode.setStyle("-fx-text-fill: red;");
            description.setEditable(true);
            description.setStyle("-fx-text-fill: red;");
            storeCredentials.add(applyStoreInfoChanges,0,11);
        });






        storeCredentials.setMinSize(1000, 650);
        storeCredentials.setMaxSize(1000, 650);

        getChildren().add(storeCredentials);





    }

    

}