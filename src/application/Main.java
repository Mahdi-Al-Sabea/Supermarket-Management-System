package application;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import mainClasses.*;

public class Main extends Application {
    private OrderReceiptsListPane or;
    private PurchaseReceipListPane pur;
	private Store Mysupermarket;
    private Stage primaryStage;
    private Menu mainMenu;
    private Menu clientsMenu;
    private Menu employeesMenu;
    private Menu ReceiptMenu ;
    private Menu Sellingmenu ;
    private Menu StoreInfoMenu;
    private Menu PurchasingMenu;
    private Menu InventoryMenu;
    private Menu StatisticsMenu ;
    private Menu PurchasingReceiptMenu;
	private ArrayList<Supplier> suppliers;
    public void start(Stage primaryStage) {
    	new Colorss();
         //:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    	 Address storeAddress=new Address("lebanon","mountLebanon","beirut","al siyyed street","mahdi al sabeh building",0000);
          Mysupermarket=new Store("mahdi and jad",storeAddress,"where all your favourate items meet","03677834","myStore123");
          
         ArrayList<Item> StoreItems=new ArrayList<Item>();
         ArrayList<Integer> StoreItemsQuantities=new ArrayList<Integer>();
         ArrayList<Item> supplierItems=new ArrayList<Item>();



         Connectionsss.Connect();


         try (PreparedStatement statement = Connectionsss.mycon.prepareStatement("SELECT * FROM itemsinstorage");
              ResultSet rs = statement.executeQuery()) {
             while (rs.next()) {
                 String name = rs.getString("name");
                 String category = rs.getString("category");
                 String barcode = rs.getString("barcode");
                 String description = rs.getString("description");
                 String imagePath = rs.getString("imagePath");
                 double price = rs.getDouble("price");
                 int quantityInStock=rs.getInt("quantityInStock");

                 Item itm=new Item(category,name,price,description,imagePath);
                 StoreItems.add(itm);
                 supplierItems.add(itm);
                 StoreItemsQuantities.add(quantityInStock);

             }
         } catch (SQLException e2) {
             e2.printStackTrace();
         }


         Address SupplierAddress=new Address("palestine","mountHaifa","beirut","al siyyed street","mahdi al sabeh building",0000);
         Supplier supplier=new Supplier("masane3",SupplierAddress,supplierItems);
         Storage myStorage=new Storage(1000,630,StoreItems,StoreItemsQuantities);
         //:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

         myStorage.setItemsInStorage(StoreItems);
         myStorage.setItemsQuantities(StoreItemsQuantities);
         Mysupermarket.setStorage(myStorage);
         //*************************************************************
         Order ro = new Order(new Employee( "jad","zalzali",500,"employee",new Address("Hamra","hii","bye","jjj","pppp",65),"hh","AM"),
     			new Client ("mahdi","sabeh","222",new Address("Hamra","hii","bye","jjj","pppp",77)),Mysupermarket);
         ro.addNewRequest(new Item("7boob","bazella",1,"hello","1647519490651.jpg"), 5);
         ro.addNewRequest(new Item("7boob","bazella",2,"hello","1647519490651.jpg"), 7);
         ro.addNewRequest(new Item("7boob","bazella",2.5,"hello","1647519490651.jpg"), 10);
         Order r2 = new Order(new Employee( "jad","zalzali",500,"employee",new Address("Hamra","hii","bye","jjj","pppp",65),"hh","AM"),
     			new Client ("mahdi","sabeh","222",new Address("Hamra","hii","bye","jjj","pppp",77)),Mysupermarket);
         r2.addNewRequest(new Item("7boob","bazella",1,"hello","1647519490651.jpg"), 5);
         r2.addNewRequest(new Item("7boob","bazella",2,"hello","1647519490651.jpg"), 7);
         r2.addNewRequest(new Item("7boob","bazella",2.5,"hello","1647519490651.jpg"), 10);
         Mysupermarket.getOrders().add(ro);
         Mysupermarket.getOrders().add(r2);

         //:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::         
         //*********************************************************************
         ArrayList<Employee> currentEmployees=new ArrayList<Employee>();
         try (PreparedStatement statement = Connectionsss.mycon.prepareStatement("SELECT * FROM employee");
    	         ResultSet resultSet = statement.executeQuery()) {
    	        while (resultSet.next()) {
    	            String fname = resultSet.getString("fname");
    	            String lname = resultSet.getString("lname");
    	            double salary = resultSet.getDouble("salary");
    	            String post = resultSet.getString("post");
    	            String email = resultSet.getString("email");
    	            String phone = resultSet.getString("phone");
    	            int zipcode = resultSet.getInt("address_zipcode");
    	            String timeshift=resultSet.getString("timeshift");
    	            String password=resultSet.getString("password");

    	            // Retrieve address information
    	            PreparedStatement statement2 = Connectionsss.mycon.prepareStatement("SELECT * FROM address WHERE zipcode = ?");
    	            statement2.setInt(1, zipcode);
    	            ResultSet resultSet2 = statement2.executeQuery();

    	            if (resultSet2.next()) {
    	                Address add = new Address(resultSet2.getString("Country"), resultSet2.getString("state"),
    	                        resultSet2.getString("city"), resultSet2.getString("street"),
    	                        resultSet2.getString("building"), resultSet2.getInt("zipcode"));

    	                // Create Employee object with address information
    	                Employee employee = new Employee(fname, lname, salary, post, add, email, phone,timeshift);
    	                currentEmployees.add(employee);

    	            }
    	        }
    	    } catch (SQLException e2) {
    	        e2.printStackTrace();
    	    }
         Mysupermarket.getEmployees().addAll(currentEmployees);
/******************************************************************/
         ArrayList<Client> clients=new ArrayList<Client>();

         try (PreparedStatement statement = Connectionsss.mycon.prepareStatement("SELECT * FROM clients");
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    String fname = resultSet.getString("First Name");
                    String lname = resultSet.getString("Last Name");
                    String Phonenb = resultSet.getString("Phonenb");
                    int nbpr = resultSet.getInt("NbPr");
                    int zipcode = resultSet.getInt("address_zipcode");

                    // Retrieve address information
                    PreparedStatement statement2 = Connectionsss.mycon.prepareStatement("SELECT * FROM address WHERE zipcode = ?");
                    statement2.setInt(1, zipcode);
                    ResultSet resultSet2 = statement2.executeQuery();

                    if (resultSet2.next()) {
                        Address add = new Address(resultSet2.getString("Country"), resultSet2.getString("state"), resultSet2.getString("city"), resultSet2.getString("street"), resultSet2.getString("building"), resultSet2.getInt("zipcode"));

                        // Create Client object with address information
                        Client c = new Client(fname, lname, Phonenb, add);
                        c.setNbPr(nbpr);
                        clients.add(c);
                    }
                }
            } catch (SQLException e2) {
                e2.printStackTrace();
            }

         Mysupermarket.getClients().addAll(clients);
         //:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

ArrayList< Supplier> suppliers = new ArrayList<>();
         Connectionsss.Connect();
 		try (PreparedStatement statement = Connectionsss.mycon.prepareStatement("SELECT * FROM supplier");
    	         ResultSet resultSet = statement.executeQuery()) {
    	        while (resultSet.next()) {
    	            String name = resultSet.getString("Name");
    	            int zipcode = resultSet.getInt("zipcode_address");
    	            // Retrieve address information
    	            PreparedStatement statement2 = Connectionsss.mycon.prepareStatement("SELECT * FROM address WHERE zipcode = ?");
    	            statement2.setInt(1, zipcode);
    	            ResultSet resultSet2 = statement2.executeQuery();

    	            if (resultSet2.next()) {
    	                Address add = new Address(resultSet2.getString("Country"), resultSet2.getString("state"),
    	                        resultSet2.getString("city"), resultSet2.getString("street"),
    	                        resultSet2.getString("building"), resultSet2.getInt("zipcode"));

    	                // Create Employee object with address information
    	                Supplier supp = new Supplier(name,add ,Mysupermarket.getStorage().getItemsInStorage() );
    	                suppliers.add(supp);

    	            }
    	        }
    	    } catch (SQLException e2) {
    	        e2.printStackTrace();
    	    }
 		Mysupermarket.setSuppliers(suppliers);
        PurchaseInvoice pur1 = new PurchaseInvoice(new Employee("jameel","karim",300,"Admin",new Address("Lebanon","---","Jounieh","chakib","saleh",3333),"ahmad@gmail.com","+96103222333","PM"),suppliers.get(0),Mysupermarket);
        				pur1.addNewRequest(StoreItems.get(1), 5);
        				pur1.addNewRequest(StoreItems.get(2), 1);
        				pur1.addNewRequest(StoreItems.get(15), 1);
        				pur1.addNewRequest(StoreItems.get(16), 2);


        Mysupermarket.getPurchases().add(pur1);
        this.primaryStage = primaryStage;
        WelcomeScene welcomepage= new WelcomeScene();
        Scene FirstScene = welcomepage.getWelcomeScene();
        primaryStage.setScene(FirstScene);
        primaryStage.show();
        mainMenu = new Menu("admin");
        clientsMenu = new Menu("admin");
        employeesMenu = new Menu("admin");
        ReceiptMenu = new Menu("admin");

    	LoginScene l = new LoginScene();

        welcomepage.getAdminButton().setOnAction(e->{
        	Scene SecondScene= new Scene (l.getlogin(),1220,630);
            SecondScene.getRoot().setStyle("-fx-background-color: lightblue");
        	primaryStage.setScene(SecondScene);
        	primaryStage.getIcons().add(new Image (getClass().getResourceAsStream("images/icon.jpg")));
        	primaryStage.show();
        	
        	l.getLogin().setOnAction(r->{
        		if(l.getUsernameTF().getText().isEmpty()||l.getPassPf().getText().isEmpty()) {
        			Alert alert = new Alert(AlertType.INFORMATION);
					alert.setHeaderText("Missing Information");
					alert.setContentText("Fill in all the Fields Please");
					alert.show();
        		}
        		else {
        		ResultSet r1=null ;
        		Connectionsss.Connect();
        		String Query="SELECT * FROM employee where fname = ? AND lname =? AND password =? AND post=?";
        		String words[]=l.getUsernameTF().getText().split(" ");
        		try(PreparedStatement statement = Connectionsss.mycon.prepareStatement(Query)){
        			statement.setString(1, words[0]);
        			statement.setString(2, words[1]);
        			statement.setString(3, words[0].substring(0,2)+"_"+words[1].substring(0,2));
        			statement.setString(4, "Admin");
               		r1= statement.executeQuery();
					if (r1.next())
								{
						 	mainMenu = new Menu("admin");
						 	clientsMenu = new Menu("admin");
						 	employeesMenu = new Menu("admin");
						 	ReceiptMenu = new Menu("admin");
						    Sellingmenu = new Menu("admin");
						    StoreInfoMenu = new Menu("admin");
						    PurchasingMenu= new Menu("admin");
						    InventoryMenu= new Menu("admin");
						    StatisticsMenu = new Menu("admin");
						    PurchasingReceiptMenu= new Menu("admin");
					setupMainScene();
     	}
					else
					{
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setHeaderText("Authentication Error");
						alert.setContentText("Username or Email are Incorrect\nPlease Try Again");
						alert.show();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		}
        	});
        	
        	});
        
        welcomepage.getEmployeeButton().setOnAction(e->{
        	Scene SecondScene= new Scene (l.getlogin(),1220,630);
            SecondScene.getRoot().setStyle("-fx-background-color: lightblue");
        	primaryStage.setScene(SecondScene);
        	primaryStage.show();
        	l.getLogin().setOnAction(r->{
        		if(l.getUsernameTF().getText().isEmpty()||l.getPassPf().getText().isEmpty()) {
        			Alert alert = new Alert(AlertType.INFORMATION);
					alert.setHeaderText("Missing Information");
					alert.setContentText("Fill in all the Fields Please");
					alert.show();
        		}
        		else {
        		ResultSet r1=null ;
        		Connectionsss.Connect();
        		String Query="SELECT * FROM employee where fname = ? AND lname =? AND password =? AND post=?";
        		String words[]=l.getUsernameTF().getText().split(" ");
        		try(PreparedStatement statement = Connectionsss.mycon.prepareStatement(Query)){
        			statement.setString(1, words[0]);
        			statement.setString(2, words[1]);
        			statement.setString(3, words[0].substring(0,2)+"_"+words[1].substring(0,2));
        			statement.setString(4, "Employee");

               		r1= statement.executeQuery();
					if (r1.next())
								{
        			 	mainMenu = new Menu("employee");
        			 	clientsMenu = new Menu("employee");
        			 	employeesMenu = new Menu("employee");
        			 	ReceiptMenu = new Menu("employee");
        			    Sellingmenu = new Menu("employee");
        			    StoreInfoMenu = new Menu("employee");
        			    PurchasingMenu= new Menu("employee");
        			    InventoryMenu= new Menu("employee");
        			    StatisticsMenu = new Menu("employee");
        			    PurchasingReceiptMenu= new Menu("employee");
        		setupMainScene();
        	}
					else
					{
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setHeaderText("Authentication Error");
						alert.setContentText("Username or Email are Incorrect\nPlease Try Again");
						alert.show();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		}
        	});
        	
        	});
        welcomepage.getManagerButton().setOnAction(e->{
        	Scene SecondScene= new Scene (l.getlogin(),1220,630);
            SecondScene.getRoot().setStyle("-fx-background-color: lightblue");
        	primaryStage.setScene(SecondScene);
        	primaryStage.show();
        	l.getLogin().setOnAction(r->{
        		if(l.getUsernameTF().getText().isEmpty()||l.getPassPf().getText().isEmpty()) {
        			Alert alert = new Alert(AlertType.INFORMATION);
					alert.setHeaderText("Missing Information");
					alert.setContentText("Fill in all the Fields Please");
					alert.show();
        		}
        		else {
        		ResultSet r1=null ;
        		Connectionsss.Connect();
        		String Query="SELECT * FROM employee where fname = ? AND lname =? AND password =? AND post=?";
        		String words[]=l.getUsernameTF().getText().split(" ");
        		try(PreparedStatement statement = Connectionsss.mycon.prepareStatement(Query)){
        			statement.setString(1, words[0]);
        			statement.setString(2, words[1]);
        			statement.setString(3, words[0].substring(0,2)+"_"+words[1].substring(0,2));
        			statement.setString(4, "Manager");

               		r1= statement.executeQuery();
					if (r1.next())
								{
        			 	mainMenu = new Menu("manager");
        			 	clientsMenu = new Menu("manager");
        			 	employeesMenu = new Menu("manager");
        			 	ReceiptMenu = new Menu("manager");
        			    Sellingmenu = new Menu("manager");
        			    StoreInfoMenu = new Menu("manager");
        			    PurchasingMenu= new Menu("manager");
        			    InventoryMenu= new Menu("manager");
        			    StatisticsMenu = new Menu("manager");
        			    PurchasingReceiptMenu= new Menu("manager");
        		setupMainScene();
        	}
        	
					else
					{
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setHeaderText("Authentication Error");
						alert.setContentText("Username or Email are Incorrect\nPlease Try Again");
						alert.show();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		}
        	});
        	
        	});
    }
    
    

    private void setupMainScene() {
    	or= new OrderReceiptsListPane(Mysupermarket.getOrders());
    	pur= new PurchaseReceipListPane(Mysupermarket.getPurchases());

        HBox mainPage = createPage(mainMenu, new ViewClientsCredentialsPane(Mysupermarket.getClients()));
        HBox clientsPage = createPage(clientsMenu,new ViewClientsCredentialsPane(Mysupermarket.getClients()) );
        HBox employeesPage = createPage(employeesMenu, new ViewEmployeesCredentialsPane(Mysupermarket.getEmployees()));
        HBox ReceiptPage = createPage(ReceiptMenu,or);
        HBox Sellingpage  = createPage(Sellingmenu,new SellingClientsPane(Mysupermarket,or));
        HBox StoreInfoPage  = createPage(StoreInfoMenu,new StoreInfoPane(Mysupermarket));
        HBox PurchasingPage = createPage(PurchasingMenu, new PurchasingStockPane(Mysupermarket,pur));
        HBox InventoryPage = createPage(InventoryMenu, new InventoryPane(Mysupermarket));
        HBox StatisticsPage = createPage(StatisticsMenu, new StatisticalPage(Mysupermarket));
        HBox PurchasingreceiptPage = createPage(PurchasingReceiptMenu,pur);

        Scene StatisticalScene = new Scene (StatisticsPage,1220,630);
        Scene ReceiptListScene = new Scene (ReceiptPage,1220,630);
        Scene employeesPageScene = new Scene(employeesPage, 1220, 630);
        Scene clientsPageScene = new Scene(clientsPage, 1220, 630);
        Scene mainPageScene = new Scene(mainPage, 1220, 630);
        Scene sellingScene = new Scene (Sellingpage,1220,630);
        Scene StoreInfoScene = new Scene(StoreInfoPage,1220,630);
        Scene StorePurchasingScene = new Scene(PurchasingPage,1220,630);
        Scene InventoryScene = new Scene (InventoryPage,1220,630);
        Scene PurchaseReceiptsScene = new Scene(PurchasingreceiptPage,1220,630);
        handleMenuButtonActions(mainMenu.getButtons(), employeesPageScene, clientsPageScene, mainPageScene,ReceiptListScene,sellingScene,StoreInfoScene,StorePurchasingScene,InventoryScene,PurchaseReceiptsScene);
        handleMenuButtonActions(clientsMenu.getButtons(), employeesPageScene, clientsPageScene, mainPageScene,ReceiptListScene,sellingScene,StoreInfoScene,StorePurchasingScene,InventoryScene,PurchaseReceiptsScene);
        handleMenuButtonActions(employeesMenu.getButtons(), employeesPageScene, clientsPageScene, mainPageScene,ReceiptListScene,sellingScene,StoreInfoScene,StorePurchasingScene,InventoryScene,PurchaseReceiptsScene);
        handleMenuButtonActions(ReceiptMenu.getButtons(), employeesPageScene, clientsPageScene, mainPageScene,ReceiptListScene,sellingScene,StoreInfoScene,StorePurchasingScene,InventoryScene,PurchaseReceiptsScene);
        handleMenuButtonActions(Sellingmenu.getButtons(), employeesPageScene, clientsPageScene, mainPageScene,ReceiptListScene,sellingScene,StoreInfoScene,StorePurchasingScene,InventoryScene,PurchaseReceiptsScene);
        handleMenuButtonActions(PurchasingMenu.getButtons(), employeesPageScene, clientsPageScene, mainPageScene,ReceiptListScene,sellingScene,StoreInfoScene,StorePurchasingScene,InventoryScene,PurchaseReceiptsScene);
        handleMenuButtonActions(StoreInfoMenu.getButtons(), employeesPageScene, clientsPageScene, mainPageScene,ReceiptListScene,sellingScene,StoreInfoScene,StorePurchasingScene,InventoryScene,PurchaseReceiptsScene);
        handleMenuButtonActions(InventoryMenu.getButtons(), employeesPageScene, clientsPageScene, mainPageScene,ReceiptListScene,sellingScene,StoreInfoScene,StorePurchasingScene,InventoryScene,PurchaseReceiptsScene);
        handleMenuButtonActions(StatisticsMenu.getButtons(), employeesPageScene, clientsPageScene, mainPageScene,ReceiptListScene,sellingScene,StoreInfoScene,StorePurchasingScene,InventoryScene,PurchaseReceiptsScene);
        handleMenuButtonActions(PurchasingReceiptMenu.getButtons(), employeesPageScene, clientsPageScene, mainPageScene,ReceiptListScene,sellingScene,StoreInfoScene,StorePurchasingScene,InventoryScene,PurchaseReceiptsScene);

        primaryStage.setScene(StatisticalScene);
        primaryStage.show();
    }

    private HBox createPage(Menu menu, Pane pageTitle) {
        StackPane pageCredentials = new StackPane();
        pageCredentials.getChildren().add(pageTitle);

        HBox page = new HBox();
        page.getChildren().addAll(menu, pageCredentials);
        return page;
    }

    private void handleMenuButtonActions(ArrayList<Button> buttons, Scene employeesPageScene,
                                         Scene clientsPageScene, Scene mainPageScene, Scene ReceiptPageScene, Scene SellingPageScene,Scene StoreInfoScene,Scene StorePurchasingScene,Scene InventoryScene,Scene purchasingreceiptsScene) {
        for (Button btn : buttons) {
            btn.setOnAction(e -> {
                switch (btn.getText()) {
                    case "employees":
                        primaryStage.setScene(employeesPageScene);
                        break;
                    case "clients":
                        primaryStage.setScene(clientsPageScene);
                        break;
                    case "inventory":
                        primaryStage.setScene(InventoryScene);
                        break;
                    case "order for clients":
                        primaryStage.setScene(SellingPageScene);
                        break;
                    case "receipt list":
                        primaryStage.setScene(ReceiptPageScene);
                        break;
                    case "purchasing supplies":
                        primaryStage.setScene(StorePurchasingScene);
                        break;
                    case "purchase reciept list":
                        primaryStage.setScene(purchasingreceiptsScene);
                        break;
                    case "store info":
                        primaryStage.setScene(StoreInfoScene);
                        break;
                    case "Log Out":
                    	primaryStage.hide();
                    	start(primaryStage);
                    	break;
                }
            });
        }
    }

    public static void main(String[] args) {
       launch(args);
    }



	
}