package view;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import model.CSingelton;
import model.Medicine;


public class DBSearchController implements Initializable {

	private static Connection con;
	private PreparedStatement pstmt;
	private ObservableList<Medicine> data;
	private MedAppDao MedDao = new MedAppDao();

	@FXML
	private Label ManagerOrViewer;

    @FXML
    private Rectangle dbSearchRectangle;

    @FXML
    private JFXTextField textField;

    @FXML
    private JFXButton searchButton;

    @FXML
    private Text titleText;

    @FXML
    private TableView<Medicine> Table;

    @FXML
    private TableColumn<Medicine, String> colName;

    @FXML
    private TableColumn<Medicine, ImageView> imageCol;


    @FXML
    private TableColumn<Medicine, String> colCharacter;

    @FXML
    private TableColumn<Medicine, String> colEffect;

    @FXML
    private TableColumn<Medicine, String> colWarning;

    @FXML
    private TableColumn<Medicine, String> colCompany;

    @FXML
    private TableColumn<Medicine, String> colExpiration;

    @FXML
    private TableColumn<Medicine, String> colPrice;

    @FXML
    private TableColumn<Medicine, String> colStock;

    @FXML
    private JFXButton btnHome;

    @FXML
    private ImageView Searchimg;

    CSingelton Singelotn = CSingelton.getInstance();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	Table.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent event) {

		    	Searchimg.setImage(new Image(Singelotn.AryList.get(Table.getSelectionModel().getSelectedIndex())));
		    }
		  });


		if(Singelotn.getManagerOrViewer() == 1) {
    		ManagerOrViewer.setText("로그인 정보 : 관리자");
    	}
    	else {
    		ManagerOrViewer.setText("로그인 정보 : 열람자");
    	}

		if(Singelotn.getSearchText().equals(""))
			return;
		else {
			try {
				MedicineTableSet(Singelotn.getSearchText());
				textField.setText(Singelotn.getSearchText());
			} catch (Exception e) {
	        	e.printStackTrace();
	        	System.out.println("Error on Building Data");
			}
		}
	}

    @FXML
    public void handleSearch(ActionEvent event) {
        try {
        	MedicineTableSet(textField.getText());
        }
        catch (Exception e) {
        	e.printStackTrace();
        	System.out.println("Error on Building Data");
        }
        return;
    }
    @FXML
    void handlebtn(ActionEvent event) {
    	btnHome.getScene().getWindow().hide();
    }



    private void MedicineTableSet(String text) {
    	data = MedDao.searchByName(text);

    	colName.setCellValueFactory(new PropertyValueFactory<Medicine, String>("name"));
    	imageCol.setCellValueFactory(new PropertyValueFactory<Medicine, ImageView>("image"));
    	colCharacter.setCellValueFactory(new PropertyValueFactory<Medicine, String>("character"));
    	colEffect.setCellValueFactory(new PropertyValueFactory<Medicine, String>("effect"));
    	colWarning.setCellValueFactory(new PropertyValueFactory<Medicine, String>("warning"));
    	colCompany.setCellValueFactory(new PropertyValueFactory<Medicine, String>("company"));
    	colExpiration.setCellValueFactory(new PropertyValueFactory<Medicine, String>("expiration1"));
    	colPrice.setCellValueFactory(new PropertyValueFactory<Medicine, String>("price"));
    	colStock.setCellValueFactory(new PropertyValueFactory<Medicine, String>("stock"));

    	Table.setItems(null);
    	Table.setItems(data);
    }
}
