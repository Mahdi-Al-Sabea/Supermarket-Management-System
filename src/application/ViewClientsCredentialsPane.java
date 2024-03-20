/*package application;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class ViewClientsCredentials extends Pane  {

	 ArrayList<Client> data= new ArrayList<Client>();
	    ViewClientsCredentials(){
	    	
	        Connectionsss.Connect();
            
			try (PreparedStatement statement = Connectionsss.mycon.prepareStatement("SELECT * FROM clients");
				ResultSet resultSet = statement.executeQuery();
			){ 
	            while (resultSet.next()) {
	                String fname = resultSet.getString("First Name"); 
	                String lname = resultSet.getString("Last Name"); 
	                String Phonenb = resultSet.getString("Phonenb"); 
	                int nbpr=resultSet.getInt("NbPr");
	                resultSet.getInt("address_zipcode");
	                PreparedStatement statement2 = Connectionsss.mycon.prepareStatement("SELECT * FROM address,clients where zipcode=address_zipcode");	
					ResultSet resultSet2 = statement2.executeQuery();
					while(resultSet2.next()) {
					Address add = new Address(resultSet2.getString("Country"),resultSet2.getString("state"),resultSet2.getString("city"),resultSet2.getString("street"),resultSet2.getString("building"),resultSet2.getInt("zipcode"));	 
					Client c = new Client (fname , lname , Phonenb,add); 
					c.setNbPr(nbpr);
					data.add(c);
					

					}
	            }}catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			};
		
	        InfoFormClients infoo=new InfoFormClients();
	        ClientsTable t=new ClientsTable(data);
	        t.getT().setMinSize(1020, 300);
	        t.getT().setFixedCellSize(100);
	        VBox v1=new VBox();
	        VBox v2=new VBox();
	        Button addClient = new Button("ADD Client");
	        v1.getChildren().addAll(t.getT(), addClient);
	        addClient.setAlignment(Pos.CENTER);

	        getChildren().addAll(v1);
	        GridPane p = infoo.MakeGrid();

	        addClient.setOnAction(E -> {
	            v1.getChildren().remove(addClient);
	            v1.getChildren().add(p);
	            getChildren().addAll( v2);
	        });
	            infoo.getSubmit().setOnAction(e -> {
		           	String selectQuery="INSERT INTO clients(FirstName,LastName,Phonenb,NbPr,address_zipcode) Values (?,?,?,?,?);";
		           	String selectQuery3address="INSERT INTO adress(Country,state,city,street,building,zipcode) Values(?,?,?,?,?,?);";
		            try(PreparedStatement preparedStatement2=Connectionsss.mycon.prepareStatement(selectQuery3address)){
		           		preparedStatement2.setString(1, infoo.getCountry().getText());
		           		preparedStatement2.setString(2, infoo.getState().getText());
		           		preparedStatement2.setString(3, (infoo.getCity().getText()));
		           		preparedStatement2.setString(4, (infoo.getStreet().getText()));
		           		preparedStatement2.setString(5, (infoo.getBuilding().getText()));
		           		preparedStatement2.setInt(6, (Integer.parseInt(infoo.getZipcode().getText())));

		            } catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
		           	 try(PreparedStatement preparedStatement=Connectionsss.mycon.prepareStatement(selectQuery)){
		           		Address add = new Address(infoo.getCountry().getText(),
                                infoo.getState().getText(),
                                infoo.getCity().getText(),
                                infoo.getStreet().getText(),
                                infoo.getBuilding().getText(),
                                Integer.parseInt(infoo.getZipcode().getText()));
		           		Client c = new Client (infoo.getFname().getText(),
		           				infoo.getLname().getText(),
		           				(infoo.getNumber().getValue()+""+infoo.getPhonenb().getText()),add);
		           		preparedStatement.setString(1, c.getFname());
		           		preparedStatement.setString(2, c.getLname());
		           		preparedStatement.setString(3, c.getPhone());
		           		preparedStatement.setInt(4,c.getNbPr());
		           		preparedStatement.setInt(5, Integer.parseInt(infoo.getZipcode().getText()));
		                preparedStatement.executeUpdate();

		           		Connectionsss.mycon.close();

		           	 } catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	                     

	            });

	       

	    }


	    public ArrayList<Client> getClientsData(){
	        return data;
	    }
	    
}


 */


/*package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ViewClientsCredentials extends Pane {

    private ArrayList<Client> data = new ArrayList<>();

    ViewClientsCredentials() {

        Connectionsss.Connect();

        try (PreparedStatement statement = Connectionsss.mycon.prepareStatement("SELECT * FROM clients");
                ResultSet resultSet = statement.executeQuery()) {
               while (resultSet.next()) {
                   String fname = resultSet.getString("First Name");
                   String lname = resultSet.getString("Last Name");
                   String Phonenb = resultSet.getString("Phonenb");
                   int nbpr = resultSet.getInt("NbPr");
                   resultSet.getInt("address_zipcode");
                   PreparedStatement statement2 = Connectionsss.mycon.prepareStatement("SELECT * FROM address,clients where zipcode=address_zipcode");
                   ResultSet resultSet2 = statement2.executeQuery();
                   while (resultSet2.next()) {
                       Address add = new Address(resultSet2.getString("Country"), resultSet2.getString("state"), resultSet2.getString("city"), resultSet2.getString("street"), resultSet2.getString("building"), resultSet2.getInt("zipcode"));
                       Client c = new Client(fname, lname, Phonenb, add);
                       c.setNbPr(nbpr);
                       data.add(c);
                   }
               }
           } catch (SQLException e2) {
               e2.printStackTrace();
           }

        InfoFormClients infoo = new InfoFormClients();
        ClientsTable t = new ClientsTable(data);
        t.getT().setMinSize(1020, 300);
        t.getT().setFixedCellSize(100);
        VBox v1 = new VBox();
        VBox v2 = new VBox();
        Button addClient = new Button("ADD Client");
        v1.getChildren().addAll(t.getT(), addClient);
        addClient.setAlignment(Pos.CENTER);

        getChildren().addAll(v1);
        GridPane p = infoo.MakeGrid();

        addClient.setOnAction(E -> {
            v1.getChildren().remove(addClient);
            v1.getChildren().add(p);
            getChildren().addAll(v2);
        });

        infoo.getSubmit().setOnAction(e -> {
            String selectQuery = "INSERT INTO `clients` (`First Name`, `Last Name`, `Phonenb`, `NbPr`, `address_zipcode`) VALUES(?,?,?,?,?);";
            String selectQuery3address = "INSERT INTO address(Country,state,city,street,building,zipcode) Values(?,?,?,?,?,?);";

            try (PreparedStatement preparedStatement2 = Connectionsss.mycon.prepareStatement(selectQuery3address)) {
                preparedStatement2.setString(1, infoo.getCountry().getText());
                preparedStatement2.setString(2, infoo.getState().getText());
                preparedStatement2.setString(3, (infoo.getCity().getText()));
                preparedStatement2.setString(4, (infoo.getStreet().getText()));
                preparedStatement2.setString(5, (infoo.getBuilding().getText()));
                preparedStatement2.setInt(6, (Integer.parseInt(infoo.getZipcode().getText())));
                preparedStatement2.executeUpdate();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }

            try (PreparedStatement preparedStatement = Connectionsss.mycon.prepareStatement(selectQuery)) {
                Address add = new Address(infoo.getCountry().getText(),
                        infoo.getState().getText(),
                        infoo.getCity().getText(),
                        infoo.getStreet().getText(),
                        infoo.getBuilding().getText(),
                        Integer.parseInt(infoo.getZipcode().getText()));
                Client c = new Client(infoo.getFname().getText(),
                        infoo.getLname().getText(),
                        (infoo.getNumber().getValue() + "" + infoo.getPhonenb().getText()), add);
                preparedStatement.setString(1, c.getFname());
                preparedStatement.setString(2, c.getLname());
                preparedStatement.setString(3, c.getPhone());
                preparedStatement.setInt(4, c.getNbPr());
                preparedStatement.setInt(5, Integer.parseInt(infoo.getZipcode().getText()));
                preparedStatement.executeUpdate();
                ObservableList<Client> cls = FXCollections.observableArrayList(data);
                cls.add(c);
           
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

        });
    }

    public ArrayList<Client> getClientsData() {
        return data;
    }
}
*/
package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import mainClasses.Address;
import mainClasses.Client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ViewClientsCredentialsPane extends Pane {

    private ArrayList<Client> data = new ArrayList<>();

    ViewClientsCredentialsPane(ArrayList<Client>clients) {

        data=clients;
        InfoFormClients infoo = new InfoFormClients();
        ClientsTable t = new ClientsTable(data);
        t.getT().setMinSize(1000, 300);
        t.getT().setMinSize(1000, 300);

        t.getT().setFixedCellSize(40);
        VBox v1 = new VBox();
        Button addClient = new Button("ADD Client");
        addClient.setAlignment(Pos.CENTER);
        Button delete= new Button("Delete");
        HBox hh = new HBox(addClient,delete);
        v1.getChildren().addAll(t.getT(), hh);

        getChildren().addAll(v1);
        GridPane p = infoo.MakeGrid();
        addClient.setStyle(
                "-fx-background-color: "+Colorss.getMenuColor()+"; " +
                        "-fx-text-fill: white; " +
                        "-fx-border-color: white; " +
                        "-fx-border-radius: 15; " +
                        "-fx-background-radius: 15; " +
                        "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.4), 10, 0, 0, 0);"
        );
        delete.setStyle("-fx-background-color: "+Colorss.getMenuColor()+"; " +
                        "-fx-text-fill: white; " +
                        "-fx-border-color: white; " +
                        "-fx-border-radius: 15; " +
                        "-fx-background-radius: 15; " +
                        "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.4), 10, 0, 0, 0);");
        t.getT().getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        t.getT().getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
        	Client selectedPerson;
            selectedPerson = newSelection;
            delete.setOnAction(e -> {
                if (selectedPerson != null) {
                    Connectionsss.Connect();
                    removePersonFromDatabase(selectedPerson.getFname(),selectedPerson.getLname());
                    data.remove(selectedPerson);
                    t.getT().setItems(FXCollections.observableArrayList(data));
                    t.getT().refresh();
                    Notification.showNotification("Client Deleted Successfully");
                    
                } else {
                    Alert t1 = new Alert(AlertType.ERROR);
                    t1.setContentText("No selected Employees to Delete");
                    t1.show();
                }
            });
        });
        addClient.setOnAction(E -> {
            v1.getChildren().remove(hh);
            v1.getChildren().add(p);
        });

        infoo.getSubmit().setOnAction(e -> {
        	if (infoo.getFname().getText().isEmpty()
        			||infoo.getLname().getText().isEmpty()
        			||infoo.getPhonenb().getText().isEmpty()
        			||infoo.getCountry().getText().isEmpty()
        			||infoo.getCity().getText().isEmpty()
        			||infoo.getBuilding().getText().isEmpty()
        			||infoo.getState().getText().isEmpty()
        			||infoo.getStreet().getText().isEmpty()
        			||infoo.getZipcode().getText().isEmpty()
        			||infoo.getFname().getText().isEmpty()) {
        			Alert error= new Alert(AlertType.ERROR);
        			error.setTitle("Required Fields Not Inserted");
        			error.setContentText("Please Fill in all the Fields Below ");
        			error.show();
        			
        	}
            String selectQuery = "INSERT INTO `clients` (`First Name`, `Last Name`, `Phonenb`, `NbPr`, `address_zipcode`) VALUES(?,?,?,?,?);";
            String selectQuery3address = "INSERT INTO address(Country,state,city,street,building,zipcode) Values(?,?,?,?,?,?);";
            PreparedStatement statement2;
			try {
				statement2 = Connectionsss.mycon.prepareStatement("SELECT * FROM address WHERE zipcode = ?");
				statement2.setInt(1, Integer.parseInt(infoo.getZipcode().getText()));
				statement2.setInt(1, Integer.parseInt(infoo.getZipcode().getText()));
				ResultSet resultSet2 = statement2.executeQuery();
				if (!resultSet2.next()) {
					 try(PreparedStatement preparedStatement2=Connectionsss.mycon.prepareStatement(selectQuery3address)){
				       		preparedStatement2.setString(1, infoo.getCountry().getText());
				       		preparedStatement2.setString(2, infoo.getState().getText());
				       		preparedStatement2.setString(3, (infoo.getCity().getText()));
				       		preparedStatement2.setString(4, (infoo.getStreet().getText()));
				       		preparedStatement2.setString(5, (infoo.getBuilding().getText()));
				       		preparedStatement2.setInt(6, (Integer.parseInt(infoo.getZipcode().getText())));
				       		preparedStatement2.executeUpdate();
                    } catch (NumberFormatException | SQLException e2) {
                        e2.printStackTrace();
                    }
				}

            try (PreparedStatement preparedStatement2 = Connectionsss.mycon.prepareStatement(selectQuery3address)) {
                preparedStatement2.setString(1, infoo.getCountry().getText());
                preparedStatement2.setString(2, infoo.getState().getText());
                preparedStatement2.setString(3, (infoo.getCity().getText()));
                preparedStatement2.setString(4, (infoo.getStreet().getText()));
                preparedStatement2.setString(5, (infoo.getBuilding().getText()));
                preparedStatement2.setInt(6, (Integer.parseInt(infoo.getZipcode().getText())));
                preparedStatement2.executeUpdate();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }

            try (PreparedStatement preparedStatement = Connectionsss.mycon.prepareStatement(selectQuery)) {
                Address add = new Address(infoo.getCountry().getText(),
                        infoo.getState().getText(),
                        infoo.getCity().getText(),
                        infoo.getStreet().getText(),
                        infoo.getBuilding().getText(),
                        Integer.parseInt(infoo.getZipcode().getText()));
                Client c = new Client(infoo.getFname().getText(),
                        infoo.getLname().getText(),
                        (infoo.getNumber().getValue() + "" + infoo.getPhonenb().getText()), add);
                preparedStatement.setString(1, c.getFname());
                preparedStatement.setString(2, c.getLname());
                preparedStatement.setString(3, c.getPhone());
                preparedStatement.setInt(4, c.getNbPr());
                preparedStatement.setInt(5, Integer.parseInt(infoo.getZipcode().getText()));
                preparedStatement.executeUpdate();

                // Add the new client to the data ArrayList and update the TableView
                data.add(c);
                t.getT().setItems(FXCollections.observableArrayList(data));

            } catch (SQLException e1) {
                e1.printStackTrace();
            }
			}catch (SQLException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			infoo.getFname().clear();
			infoo.getFname().clear();
			infoo.getPhonenb().clear();
		    infoo.getNumber().getSelectionModel().clearSelection();
		    infoo.getCity().clear();
		    infoo.getCountry().clear();
		    infoo.getBuilding().clear();
		    infoo.getState().clear();
		    infoo.getStreet().clear();
		    infoo.getZipcode().clear();
		    v1.getChildren().remove(p);
            v1.getChildren().add(hh);
            Notification.showNotification("Client Added Successfully");

        });
    }

    public ArrayList<Client> getClientsData() {
        return data;
    }
    private void removePersonFromDatabase(String fname,String lname) {
        // JDBC connection and SQL DELETE statement
             try(PreparedStatement preparedStatement = Connectionsss.mycon.prepareStatement("DELETE FROM clients WHERE First Name  = ? AND Last Name =?")) {

            // Set the parameter in the SQL statement
            preparedStatement.setString(1, fname);
            preparedStatement.setString(1, lname);


            // Execute the DELETE statement
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}


class InfoFormClients extends Pane {
	private Button submit ;
	private TextField fname,lname ,phonenb;
	private ComboBox<String> Number;
	private TextField Street,Building,Country,City,State,Zipcode;
    public GridPane MakeGrid() {
    	 GridPane g = new GridPane();
         this.fname = new TextField("");
         this. lname = new TextField("");
         this. phonenb= new TextField("");
         ComboBox<String> Numbers = new ComboBox<String>();
         this.Number=Numbers;
         Number.setValue("prefix");
         Number.getItems().addAll("+961","+555","080","080","0900","0800","333");
         Label name = new Label (" Name ");
         Label address = new Label ("Address ");
         Label ftname = new Label ("First       ");
         Label ltname = new Label ("Last        ");
         this.submit = new Button("Submit");
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
         this. phonenb = createNumericTextField("");
         phonenb.setPromptText("Phone number");
         HBox h1 = new HBox();
         h1.getChildren().addAll(ftname,fname);
         HBox h2 = new HBox();
         h2.getChildren().addAll(ltname,lname);
         g.add(name, 0, 0);
         g.add(h1, 0, 1);
         g.add(h2, 1, 1);
         g.add(Number, 2, 1);
         g.add(phonenb, 3, 1);
         g.add(address, 0, 2);
         g.add(Country, 0, 3);
         g.add(State, 0, 4);
         g.add(City, 1, 3);
         g.add(Street, 1, 4);
         g.add(Building, 2, 3);
         g.add(Zipcode, 2, 4);
         g.add(submit,3,9);
         g.setVgap(10);
         g.setHgap(10);
         g.setBorder(Border.stroke(Color.LIGHTGREY)); 
         g.setMinSize(1000, 300);
         g.setMaxSize(1000, 300);
         g.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14;  -fx-text-fill: white ;");
         g.setBackground(Background.fill(Color.web("#4a646c")));
		return g;
		
         
         
    }
    public ComboBox<String> getNumber() {
		return Number;
	}
	public TextField getFname() {
		return fname;
	}
	public TextField getLname() {
		return lname;
	}
	public TextField getPhonenb() {
		return phonenb;
	}
	
	public TextField getStreet() {
		return Street;
	}
	public TextField getBuilding() {
		return Building;
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
	public Button getSubmit() {
		return submit;
	}
	public void setSubmit(Button submit) {
		this.submit = submit;
	}
	private TextField createNumericTextField(String promptText) {
        // Create a TextField that only accepts numeric input
        TextField textField = new TextField();
        textField.setPromptText(promptText);

        // Add a listener to validate the input
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                Alert t = new Alert(AlertType.ERROR);
                t.setTitle("Character Detected");
                t.setContentText("Please Enter a valid Number");
                textField.setText(oldValue); // Revert to the previous value
                t.show();
            }
        });

        return textField;
    }



}
class ClientsTable extends Pane{
	private TableView<Client> t ;
	private ArrayList<Client> clients ;

    

	ClientsTable(ArrayList<Client> clients){
		this.clients=clients;
        StackPane ClientTablePlaceHolder=new StackPane();

        // Creating columns for the TableView
        TableColumn<Client, String> fnameCol = new TableColumn<>("fName");
        fnameCol.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getFname()));

        TableColumn<Client, String> lnameCol = new TableColumn<>("lName");
        lnameCol.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getLname()));

        TableColumn<Client, String> phoneCol = new TableColumn<>("Phone");
        phoneCol.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getPhone()));

        lnameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        fnameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        phoneCol.setCellFactory(TextFieldTableCell.forTableColumn());
        phoneCol.setOnEditCommit(event -> {
            Client cl = event.getRowValue();
            cl.setPhone(event.getNewValue());
            updateDatabase(cl);
            
        });
        TableView<Client> table = new TableView<>();
        table.getColumns().addAll(fnameCol,lnameCol, phoneCol);
        table.setItems(FXCollections.observableArrayList(clients));

        table.setEditable(true);
     
        fnameCol.setMinWidth(200);
        phoneCol.setMinWidth(200);
        lnameCol.setMinWidth(200);
        ClientTablePlaceHolder.getChildren().add(table);
        getChildren().add(ClientTablePlaceHolder);
        table.setStyle("-fx-font-family: Times New Roman; -fx-font-size: 17;");
        table.setStyle(
                "-fx-background-color: #cad2c5;" + /* Background color */
                        "-fx-selection-bar:"+Colorss.getThirdColor()+";" + /* Selected row color */
                        "-fx-selection-bar-non-focused :"+Colorss.getBackgroundColor()+";" + /* Selected row color when not focused */
                        "-fx-control-inner-background-alt :"+ Colorss.getBackgroundColor() +";"+ /* Alternate row color */
                        "-fx-table-cell-border-color : #354f52;" + /* Border color */
                        "-fx-text-fill: #2f3e46;" /* Text color */
        );
        table.setMinSize(450, 200);
        table.setMaxSize(450, 200);

        this.t= table;
        

    }
	public ArrayList<Client> getClients() {
		return clients;
	}

	public TableView<Client> getT() {
		return t;
	}
	
	 private void updateDatabase(Client cl) {

	    	Connectionsss.Connect();
	    	String sql = "UPDATE `clients` SET Phonenb = ? WHERE `First Name` = ? AND `Last Name` = ?";
	            try (PreparedStatement preparedStatement = Connectionsss.mycon.prepareStatement(sql)) {
	                preparedStatement.setString(1, cl.getPhone());
	                preparedStatement.setString(2, cl.getFname());
	                preparedStatement.setString(3,cl.getLname() );
	                preparedStatement.executeUpdate();
	            } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	 }
}

