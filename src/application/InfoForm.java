package application;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class InfoForm extends Pane {
	private Button submit ;
	private TextField fname,lname ,phonenb,email;
	private RadioButton selectedtimeshift,selectedpost;
	private Spinner<Double> salary;
	private ComboBox<String> Number;
	public ComboBox<String> getNumber() {
		return Number;
	}
	private TextField Street,Building,Country,City,State,Zipcode;
    public GridPane MakeGrid() {
    	 GridPane g = new GridPane();
    	 Button btn = new Button ("Add Employee");
         this.fname = new TextField("");
         this. lname = new TextField("");
         this. phonenb= createNumericTextField("");
         this. email = new TextField("");
         ComboBox<String> Number = new ComboBox<String>();
         this.Number=Number;
         Number.setValue("prefix");
         Number.getItems().addAll("+961","+555","080","080","0900","0800","333");
         Label name = new Label (" Name ");
         Label address = new Label ("Address ");
         Label ftname = new Label ("First       ");
         Label ltname = new Label ("Last        ");
         Label post = new Label ("POST");
         Label Timeshift = new Label("TimeShift");
         Label Sal = new Label("Salary ");
         this.submit = new Button("Submit");
         RadioButton EMP =new RadioButton("Employee");
         RadioButton Manager =new RadioButton("Manager");
         ToggleGroup g1 = new ToggleGroup();
         EMP.setToggleGroup(g1);
         Manager.setToggleGroup(g1);
         functionselected(Manager);
         functionselected(EMP);

         RadioButton AM =new RadioButton("AM");
         RadioButton PM =new RadioButton("PM");
         ToggleGroup g2 = new ToggleGroup();
         AM.setToggleGroup(g2);
         PM.setToggleGroup(g2);
         functionselected2(PM);
         functionselected2(AM);
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
         this. email = new TextField();
         email.setPromptText("Email ");
         Spinner <Double> salary = new Spinner<>(); 
         this.salary=salary;
         HBox h1 = new HBox();
         h1.getChildren().addAll(ftname,fname);
         HBox h2 = new HBox();
         h2.getChildren().addAll(ltname,lname);
         g.add(name, 0, 0);
         g.add(h1, 0, 1);
         g.add(h2, 1, 1);
         g.add(Number, 2, 1);
         g.add(phonenb, 3, 1);
         g.add(email, 3, 2);
         g.add(address, 0, 2);
         g.add(Country, 0, 3);
         g.add(State, 0, 4);
         g.add(City, 1, 3);
         g.add(Street, 1, 4);
         g.add(Building, 2, 3);
         g.add(Zipcode, 2, 4);
         g.add(post,0 , 6);
         g.add(EMP,0 , 7);
         g.add(Manager,0 , 8);
         g.add(Timeshift,1 , 6);
         g.add(Sal, 2, 6);
         g.add(AM,1, 7);
         g.add(PM,1 , 8);
         g.add(submit,3,9);
         SpinnerValueFactory <Double> salaries = new SpinnerValueFactory.DoubleSpinnerValueFactory(250,630,50,50);
         salary.setValueFactory(salaries);
         g.add(salary, 2,7,1,2);
         g.setVgap(10);
         g.setHgap(10);
         g.setBorder(Border.stroke(Color.LIGHTGREY)); 
         g.setMinSize(1000, 630);
         g.setMaxSize(1000, 630);
         g.setBackground(Background.fill(Color.web("#4a646c")));
         g.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14;  -fx-text-fill: white ;");

		return g;
		
         
         
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
	public TextField getEmail() {
		return email;
	}
	public RadioButton getSelectedtimeshift() {
		return selectedtimeshift;
	}
	public RadioButton getSelectedpost() {
		return selectedpost;
	}
	public Spinner<Double> getSalary() {
		return salary;
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
	public void functionselected(RadioButton r ) {
		r.setOnAction(e->{
			if (r.isSelected())
				selectedpost=r;
		});
		
	}
	public void functionselected2(RadioButton r ) {
		r.setOnAction(e->{
			if (r.isSelected())
				selectedtimeshift=r;
		});
		
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
