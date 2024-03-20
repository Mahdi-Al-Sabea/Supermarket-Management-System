package application;

import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import mainClasses.Store;

public class StatisticalPage extends Pane {

    private Store myStore;
    String[] image_local_paths = {
            "images/images1.jpg",
            "images/images2.jpg",
            "images/images3.jpg",
            "images/images4.jpg",
            "images/images5.jpg",
            "images/images6.jpg",
            "images/images7.jpg",
            "images/images8.jpg",
    };
    public static int currentImageIndex = 0;

    StatisticalPage(Store myStore) {

        this.myStore = myStore;

        // Create a grid layout
        

        BarChart<String, Number> barChart = createBarChart();
        updateBarChartData(barChart, myStore);

        VBox totalSales = createStatBox("nb of sales", String.valueOf(myStore.getOrders().size()), "images/sales.png");
        totalSales.setMinWidth(200);


        VBox totalItems = createStatBox("total number of items", String.valueOf(myStore.getStorage().getItemsInStorage().size()), "images/items.png");
        totalItems.setMinWidth(200);
        VBox numberOfClientsBox = createStatBox("nb of customers", String.valueOf(myStore.getClients().size()), "images/customers.png");
        numberOfClientsBox.setMinWidth(200);

        VBox numberOfEmployeesBox = createStatBox("nb of employees", String.valueOf(myStore.getEmployees().size()), "images/employees.jpg");
        numberOfEmployeesBox.setMinWidth(200);

        HBox eachbox=new HBox(totalSales,totalItems,numberOfClientsBox,numberOfEmployeesBox);
        eachbox.setSpacing(20);
        eachbox.setPadding(new Insets(5,5,5,5));
        ImageView imageView = new ImageView();
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(3), e -> changeImage(imageView))
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        ImageView imageView2 = new ImageView(new Image(getClass().getResourceAsStream("images/supermarket.jpeg")));
        ImageView imageView3 = new ImageView();
        Timeline timeline3 = new Timeline(
                new KeyFrame(Duration.seconds(5), e -> changeImage(imageView3))
        );
        timeline3.setCycleCount(Timeline.INDEFINITE);
        timeline3.play();
        HBox h = new HBox(imageView, imageView2, imageView3);
        imageView2.setFitHeight(200);
        imageView3.setFitHeight(200);
        imageView.setFitHeight(200);
        imageView2.setFitWidth(300);
        imageView3.setFitWidth(300);
        imageView.setFitWidth(300);
        h.setSpacing(20);
        VBox container = new VBox(eachbox,barChart,h);
        container.setAlignment(Pos.CENTER);
        container.setMinSize(1000, 630);
        h.setAlignment(Pos.CENTER);
        eachbox.setAlignment(Pos.CENTER);

        barChart.setMaxHeight(300);
        barChart.setMaxWidth(600);

        getChildren().add(container);
        this.setBackground(Background.fill(Color.web("#e3e1cf")));
       
    }

    private BarChart<String, Number> createBarChart() {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);

        return barChart;
    }

    @SuppressWarnings("unchecked")
    private void updateBarChartData(BarChart<String, Number> barChart, Store myStore) {
        double totalExpenses = myStore.getTotalExpenses();
        double totalSales = myStore.getTotalSales();

        XYChart.Series<String, Number> expensesSeries = new XYChart.Series<>();
        expensesSeries.setName("Expenses");

        XYChart.Series<String, Number> salesSeries = new XYChart.Series<>();
        salesSeries.setName("Sales");

        expensesSeries.getData().add(new XYChart.Data<>("Expenses",totalExpenses));
        salesSeries.getData().add(new XYChart.Data<>("Sales", totalSales));

        barChart.getData().addAll(expensesSeries, salesSeries);
    }

    private VBox createStatBox(String labelText, String valueText, String imagePath) {
        VBox box = new VBox();
        box.setMinHeight(100);
        box.setMaxWidth(200);

        ImageView icon = new ImageView(new Image(getClass().getResourceAsStream(imagePath)));
        icon.setFitHeight(70);
        icon.setFitWidth(60);

        Text label = new Text(labelText);
        label.setFont(Font.font(15));
        Text value = new Text(valueText);
        value.setFont(Font.font(10));
        box.getChildren().addAll(icon, label, value);
        box.setAlignment(Pos.CENTER);
        box.setSpacing(10);
        box.setStyle("-fx-background-color: white; -fx-background-radius: 10;");
        box.setBorder(Border.stroke(Color.GRAY));
        GridPane.setMargin(box, new Insets(10, 10, 10, 10));

        return box;
    }

    private void changeImage(ImageView imageView) {
        if (currentImageIndex < image_local_paths.length) {
            Image newImage = new Image(getClass().getResourceAsStream(image_local_paths[currentImageIndex]));
            imageView.setImage(newImage);
            currentImageIndex = (currentImageIndex + 1) % image_local_paths.length;
        }
    }
}
