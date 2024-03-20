package application;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import mainClasses.Store;


public class InventoryPane extends Pane {

   
    

    public InventoryPane(Store store) {
        
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10, 10, 10, 10));

        // Create categories
        ListView<String> categoriesListView = new ListView<>();
        categoriesListView.getItems().addAll("beverages", "snacks","sauce","canned products","Grains");

        // Create products for each category
        ListView<String> productsListView = new ListView<>();

        // Display quantities for selected product
        Label quantityLabel = new Label("Quantities:");

        // Create ImageViews for displaying images
        ImageView imageView = new ImageView();
        imageView.setFitWidth(300);
        imageView.setFitHeight(300);
        Label Categorieslabel=new Label("Categories:");
        Label productimagelabel=new Label("Product Image:");
        Label productslabel=new Label("Products:");
        Categorieslabel.setFont(Font.font(25));
        productslabel.setFont(Font.font(25));
        productimagelabel.setFont(Font.font(25));
        productsListView.setPrefSize(200, 200);
        categoriesListView.setPrefSize(200,200);

        // Create a VBox for layout
        VBox vbox = new VBox(10);
        VBox vbox2 = new VBox(10);

        vbox.getChildren().addAll(Categorieslabel, categoriesListView,
                productslabel, productsListView );
        vbox2.getChildren().addAll(productimagelabel, imageView , quantityLabel);
        quantityLabel.setVisible(false);
        quantityLabel.setFont(Font.font("Times New Roman",FontPosture.ITALIC,25));
        HBox hbox=new HBox(vbox,vbox2);
        


        // Update product list and quantities label when a category is selected
        categoriesListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            productsListView.getItems().clear();
            quantityLabel.setText("Quantities:");
            for (int i = 0; i < store.getStorage().getItemsInStorage().size(); i++) {
                if (newValue.equals(store.getStorage().getItemsInStorage().get(i).getCategory())) {
                    productsListView.getItems().add(store.getStorage().getItemsInStorage().get(i).getName());
                }
            }
        });
        categoriesListView.setMinWidth(300);
        productsListView.setMinWidth(300);
        // Update quantities label and display image when a product is selected
        productsListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        	quantityLabel.setVisible(true);
            Image productImage=null;
            int quant=0 ;
            // Display image for the selected product (Replace with actual image paths)
            for(int i =0 ;i<store.getStorage().getItemsInStorage().size();i++) {
            	if (store.getStorage().getItemsInStorage().get(i).getName().equals(productsListView.getSelectionModel().getSelectedItem())) {
            			             productImage = new Image(getClass().getResourceAsStream(store.getStorage().getItemsInStorage().get(i).getImagePath()));
            							quant=store.getStorage().getItemsQuantities().get(i);
            }
            }
            quantityLabel.setText("Quantities: " + quant);
            imageView.setImage(productImage);
        });
        hbox.setPadding(new Insets(50,100,100,50));
        hbox.setSpacing(200);
        vbox2.setPadding(new Insets(50,50,50,50));
        getChildren().add(hbox);
        hbox.setBackground(Background.fill(Color.web("#4a646c")));
    }
}
