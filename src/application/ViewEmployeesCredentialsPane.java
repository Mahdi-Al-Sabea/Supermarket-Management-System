package application;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import mainClasses.Address;
import mainClasses.Employee;

public class ViewEmployeesCredentialsPane extends Pane {

    ArrayList<Employee> data;


    ViewEmployeesCredentialsPane(ArrayList <Employee> employees){
 
        this.setBackground(Background.fill(Color.web("#c9c9c9")));
    	data=employees;
        InfoForm infoo=new InfoForm();
        EmployeesTable t=new EmployeesTable();
        VBox v1=new VBox();
        Button addEmployee = new Button("ADD Employee");
        Button deleteemployee= new Button("Delete");
        GridPane grid= infoo.MakeGrid();
        HBox hh = new HBox(addEmployee,deleteemployee);
        v1.getChildren().addAll(t.CreateTable(data), hh);
        t.getT().getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        t.getT().getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
        	Employee selectedPerson;
            selectedPerson = newSelection;
            deleteemployee.setOnAction(e -> {
                if (selectedPerson != null) {
                    Connectionsss.Connect();
                    removePersonFromDatabase(selectedPerson.getId());
                    data.remove(selectedPerson);
                    t.getT().setItems(FXCollections.observableArrayList(data));
                    t.getT().refresh();
                    Notification.showNotification("Employee deleted successfully");

                    
                } else {
                    Alert t1 = new Alert(AlertType.ERROR);
                    t1.setContentText("No selected Employees to Delete");
                    t1.show();
                }
            });
        });
        addEmployee.setAlignment(Pos.CENTER);

        getChildren().addAll(v1);
        deleteemployee.setStyle( "-fx-background-color: "+Colorss.getMenuColor()+"; " +
                        "-fx-text-fill: white; " +
                        "-fx-border-color: white; " +
                        "-fx-border-radius: 15; " +
                        "-fx-background-radius: 15; " +
                        "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.4), 10, 0, 0, 0);");
        addEmployee.setStyle(
                "-fx-background-color: "+Colorss.getMenuColor()+"; " +
                        "-fx-text-fill: white; " +
                        "-fx-border-color: white; " +
                        "-fx-border-radius: 15; " +
                        "-fx-background-radius: 15; " +
                        "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.4), 10, 0, 0, 0);"
        );
        
        addEmployee.setOnAction(E -> {
            v1.getChildren().remove(hh);
            v1.getChildren().add( grid);



            infoo.getSubmit().setOnAction(e -> {
            	
            	if (infoo.getFname().getText().isEmpty()
            			||infoo.getLname().getText().isEmpty()
            			||infoo.getPhonenb().getText().isEmpty()
            			||infoo.getEmail().getText().isEmpty()
            			||infoo.getCountry().getText().isEmpty()
            			||infoo.getCity().getText().isEmpty()
            			||infoo.getBuilding().getText().isEmpty()
            			||infoo.getState().getText().isEmpty()
            			||infoo.getStreet().getText().isEmpty()
            			||infoo.getZipcode().getText().isEmpty()
            			||infoo.getFname().getText().isEmpty()
            			||infoo.getSelectedpost().getText().isEmpty()
            			||infoo.getSelectedtimeshift().getText().isEmpty()) {
            			Alert error= new Alert(AlertType.ERROR);
            			error.setTitle("Required Fields Not Inserted");
            			error.setContentText("Please Fill in all the Fields Below ");
            			error.show();
            			
            	}
            	 Connectionsss.Connect();
		           	String selectQuery="INSERT INTO employee(fname,lname,id,salary,post,timeshift,email,phone,address_zipcode,password) Values (?,?,?,?,?,?,?,?,?,?)";

		           	String selectQuery3address="INSERT INTO address(Country,state,city,street,building,zipcode) Values(?,?,?,?,?,?)";
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
							
				
		           	 try(PreparedStatement preparedStatement=Connectionsss.mycon.prepareStatement(selectQuery)){
		           		Address add = new Address(infoo.getCountry().getText(),
                             infoo.getState().getText(),
                             infoo.getCity().getText(),
                             infoo.getStreet().getText(),
                             infoo.getBuilding().getText(),
                             Integer.parseInt(infoo.getZipcode().getText()));
		           		Employee emp = new Employee (infoo.getFname().getText(),
		           				infoo.getLname().getText(),
		           				Double.parseDouble(infoo.getSalary().getValue().toString()),
		           				infoo.getSelectedpost().getText(),
		           				add,
		           				infoo.getEmail().getText(),
		           				(infoo.getNumber().getValue()+""+infoo.getPhonenb().getText()),
		           				infoo.getSelectedtimeshift().getText());
		           		preparedStatement.setString(1, emp.getFname());
		           		preparedStatement.setString(2, emp.getLname());
		           		preparedStatement.setString(3, emp.getId());
		           		preparedStatement.setDouble(4,emp.getSalary());
		           		preparedStatement.setString(5, emp.getPost());
		           		preparedStatement.setString(6, emp.getTimeshift());
		           		preparedStatement.setString(7, emp.getEmail());
		           		preparedStatement.setString(8, emp.getPhone());
		           		preparedStatement.setInt(9, (emp.getAddress().getZipcode()));
		           		preparedStatement.setString(10, (emp.getFname().substring(0, 2)+"_"+emp.getLname().substring(0, 2)));
		                preparedStatement.executeUpdate();
		                data.add(emp);
		                t.getT().setItems(FXCollections.observableArrayList(data));
		           	 }
		           	 
		           	  catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					
		           	                   
		           	  			
					}
					
            
						}catch (SQLException e3) {
							// TODO Auto-generated catch block
							e3.printStackTrace();
						}
						infoo.getFname().clear();
						infoo.getFname().clear();
						infoo.getEmail().clear();
						infoo.getPhonenb().clear();
					    infoo.getSelectedpost().setSelected(false);
					    infoo.getSelectedtimeshift().setSelected(false);
					    infoo.getSalary().getValueFactory().setValue(100.0);
					    infoo.getNumber().getSelectionModel().clearSelection();
					    infoo.getCity().clear();
					    infoo.getCountry().clear();
					    infoo.getBuilding().clear();
					    infoo.getState().clear();
					    infoo.getStreet().clear();
					    infoo.getZipcode().clear();
						v1.getChildren().removeAll(grid);
			            v1.getChildren().addAll( hh);
			            Notification.showNotification("Employee Added Successfully");
	            });
        
	        });
        
}

    public ArrayList<Employee> getEmployeeData(){
        return data;
    }
    private void removePersonFromDatabase(String id) {
        // JDBC connection and SQL DELETE statement
             try(PreparedStatement preparedStatement = Connectionsss.mycon.prepareStatement("DELETE FROM employee WHERE id = ?")) {

            // Set the parameter in the SQL statement
            preparedStatement.setString(1, id);

            // Execute the DELETE statement
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
class EmployeesTable extends Pane  {
    private TableView<Employee> t ;
    private ObservableList<Employee> data;
    public void setData(ObservableList<Employee> data) {
		this.data = data;
	}
	public void start(Stage stage)  {

    }
    @SuppressWarnings("unchecked")
    public TableView CreateTable(ArrayList<Employee> employees) {
        this.data = FXCollections.observableArrayList();
        data.addAll(employees);
        TableColumn<Employee,String> c1 = new TableColumn <>("First Name");
        TableColumn<Employee,String> c2 = new TableColumn <>("Last Name ");
        TableColumn<Employee,String> c3 = new TableColumn <>("Phone Number");
        TableColumn<Employee,String> c4 = new TableColumn <>("Email");
        TableColumn<Employee,String> c5 = new TableColumn <>("country");
        TableColumn<Employee,String> c6 = new TableColumn <>("Id");
        TableColumn<Employee,Double> c7 = new TableColumn <>("Salary");
        TableColumn<Employee,String> c8 = new TableColumn <>("Post");
        TableColumn<Employee,String> c9 = new TableColumn <>("Timeshift");

        c1.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getFname()));
        c2.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getLname()));
        c3.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getPhone()));
        c4.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getEmail()));
        c5.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getAddress().getCountry()));
        c6.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().getId()));
        c7.setCellValueFactory(cellData-> new SimpleObjectProperty(cellData.getValue().getSalary()));
        c8.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().getPost()));
        c9.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getTimeshift()));

        c1.setCellFactory(TextFieldTableCell.forTableColumn());
        c2.setCellFactory(TextFieldTableCell.forTableColumn());
        c3.setCellFactory(TextFieldTableCell.forTableColumn());
        c4.setCellFactory(TextFieldTableCell.forTableColumn());
        c5.setCellFactory(TextFieldTableCell.forTableColumn());
        c6.setCellFactory(TextFieldTableCell.forTableColumn());
        c8.setCellFactory(TextFieldTableCell.forTableColumn());
        c9.setCellFactory(TextFieldTableCell.forTableColumn());

        c1.setOnEditCommit(event -> {
            Employee emp = event.getRowValue();
            emp.setFname(event.getNewValue());
            updateDatabase(emp);
            
        });
        c2.setOnEditCommit(event -> {
            Employee emp = event.getRowValue();
            emp.setLname(event.getNewValue());
            updateDatabase(emp);
            
        });
        c3.setOnEditCommit(event -> {
            Employee emp = event.getRowValue();
            emp.setPhone(event.getNewValue());
            updateDatabase(emp);
            
        });
        
        c4.setOnEditCommit(event -> {
            Employee emp = event.getRowValue();
            emp.setEmail(event.getNewValue());
            updateDatabase(emp);
            
        });
        c5.setOnEditCommit(event -> {
            Employee emp = event.getRowValue();
            emp.getAddress().setCountry(event.getNewValue());
            updateDatabase(emp);
            
        });
        
        c7.setOnEditCommit(event -> {
            Employee emp = event.getRowValue();
            emp.setSalary(event.getNewValue());
            updateDatabase(emp);
            
        });
        c8.setOnEditCommit(event -> {
            Employee emp = event.getRowValue();
            emp.setPost(event.getNewValue());
            updateDatabase(emp);
            
        });
        c9.setOnEditCommit(event -> {
            Employee emp = event.getRowValue();
            emp.setTimeShift(event.getNewValue());
            updateDatabase(emp);
            
        });
        
        
        c1.setPrefWidth(100);
        c2.setPrefWidth(100);
        c3.setPrefWidth(120);
        c4.setPrefWidth(120);
        c5.setPrefWidth(100);
        c6.setPrefWidth(120);
        c7.setPrefWidth(100);
        c8.setPrefWidth(100);
        c9.setPrefWidth(100);

        TableView<Employee> tableview = new TableView<Employee>();
        tableview.getColumns().addAll(c1,c2,c3,c4,c5,c6,c7,c8,c9);
        tableview.setItems(data);
        tableview.setStyle("-fx-font-family: Times New Roman; -fx-font-size: 17;");
        tableview.setFixedCellSize(50);
        tableview.setStyle(
                "-fx-background-color: #cad2c5;" + /* Background color */
                        "-fx-selection-bar: #009DC4 ;"+
                        "-fx-selection-bar-non-focused: "+Colorss.getBackgroundColor() +";"+ /* Selected row color when not focused */
                        "-fx-control-inner-background-alt:"+Colorss.getBackgroundColor()+";" + /* Alternate row color */
                        "-fx-table-cell-border-color: #354f52;" + /* Border color */
                        "-fx-text-fill: #2f3e46;" /* Text color */
        );
        tableview.setMinWidth(1000);
        tableview.setEditable(true);
        tableview.setMaxHeight(300);
        this.t=tableview;
        return tableview;
    }
    public ObservableList<Employee> getData() {
        return data;
    }
    public TableView<Employee> getT() {
        return t;
    }
    
    private void updateDatabase(Employee emp) {
    	Connectionsss.Connect();
            String sql = "UPDATE employee SET fname =?, lname =? , id =? , salary =? , post=? ,timeshift =? , email =? ,phone =? , address_zipcode=? WHERE id =?;"; // Update this query accordingly
            try (PreparedStatement preparedStatement = Connectionsss.mycon.prepareStatement(sql)) {
                preparedStatement.setString(1, emp.getFname());
                preparedStatement.setString(2, emp.getLname());
                preparedStatement.setString(3,emp.getId());
                preparedStatement.setDouble(4,emp.getSalary() );
                preparedStatement.setString(5,emp.getPost() );
                preparedStatement.setString(6,emp.getTimeshift() );
                preparedStatement.setString(7,emp.getEmail() );
                preparedStatement.setString(8,emp.getPhone() );
                preparedStatement.setInt(9,emp.getAddress().getZipcode());
                preparedStatement.setString(10,emp.getId());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }
    
}
