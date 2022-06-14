package user;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.ResourceBundle;

import home.MyInfo;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn;

public class yourThingsController implements Initializable{

	ObservableList<ObservableList> mylist = FXCollections.observableArrayList();
	
	Socket socket;
	
	@FXML HBox ytTopHbox;
	@FXML Label ytTitle;
	@FXML AnchorPane ytBackColor;
	@FXML HBox ytBtnColor;
	@FXML Button ytBtn1;
	@FXML Button ytBtn2;
	@FXML Button ytBtn4;
	@FXML Button ytBtn3;
	@FXML Button ytBtn5;
	@FXML Button ytBtn6;
	@FXML Line ytLine1;
	@FXML Label ytYourthingsLabel;
	@FXML Line ytLine2;
	@FXML Button ytReturnBtn;
	@FXML Button yourThingsmoveMypage_Btn;
	@FXML Button ytMoveUserHome;
	@FXML Button ytNoticeBtn;
	@FXML Button ytMoveMypageBtn;
	@FXML TableView<ObservableList> ytItemListTable;
	@FXML TableColumn your_category;
	@FXML TableColumn your_name;
	@FXML TableColumn your_max;
	@FXML TableColumn your_price;
	@FXML TableColumn your_id;

	
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		socket = MyInfo.socket;
		
		String myList = null;
		String[] temp = null;
        try {
           String m = "YourThings:"+MyInfo.my_id;
           BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
           PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
           
           pw.println(m);
           pw.flush();
           
           myList = br.readLine();
           temp = myList.split("//");
           
           if(Arrays.deepToString(temp).equals("[]")) {

        	   ytItemListTable.setItems(null);
        	   return;
           }
           
           for(int i = 0; i < temp.length; i++) {
        	   ObservableList<String> row = FXCollections.observableArrayList();
        	  
        	   String[] temp2 = temp[i].split("@@");
        	   		for(int j = 0; j<temp2.length; j++) {
        	   			row.add(temp2[j]);
        	   }
        	   	mylist.add(row);
           }
           
           ytItemListTable.setItems(mylist);
           
           your_id.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
               public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
                   return new SimpleStringProperty(param.getValue().get(0).toString());                        
               }                    
           });
           
           your_category.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
               public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
                   return new SimpleStringProperty(param.getValue().get(1).toString());                        
               }                    
           });
           
           your_name.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
               public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
                   return new SimpleStringProperty(param.getValue().get(2).toString());                        
               }                    
           });
   			
           your_max.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
               public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
                   return new SimpleStringProperty(param.getValue().get(3).toString());                        
               }                    
           });
           
           your_price.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
               public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
                   return new SimpleStringProperty(param.getValue().get(4).toString());                        
               }                    
           });	
           
           
        } catch (IOException e1) {
        	e1.printStackTrace();
        }

	}
	@FXML public void yourThingsmoveMyPage() throws Exception {
		
		Stage primaryStage = new Stage();
		Stage stage = (Stage)ytTitle.getScene().getWindow();

			Parent ob = FXMLLoader.load(getClass().getResource("templates/myPage.fxml"));
			ob.getStylesheets().add(getClass().getResource("statics/myPage.css").toExternalForm());
			Scene sc = new Scene(ob);
			primaryStage.setScene(sc);
	        primaryStage.show();
	        primaryStage.setResizable(false);
			stage.close();
	}


	@FXML public void yourThingsmoveUserHome() throws Exception {
		
		Stage primaryStage = new Stage();
		Stage stage = (Stage)ytTitle.getScene().getWindow();

			Parent ob = FXMLLoader.load(getClass().getResource("templates/userMain.fxml"));
			ob.getStylesheets().add(getClass().getResource("statics/userMain.css").toExternalForm());
			Scene sc = new Scene(ob);
			primaryStage.setScene(sc);
	        primaryStage.show();
	        primaryStage.setResizable(false);
			stage.close();
	}
	
	@FXML public void ytMoveLike() throws Exception {
		Stage primaryStage = new Stage();
		Stage stage = (Stage)ytTitle.getScene().getWindow();

			Parent ob = FXMLLoader.load(getClass().getResource("templates/like.fxml"));
			ob.getStylesheets().add(getClass().getResource("statics/like.css").toExternalForm());
			Scene sc = new Scene(ob);
			primaryStage.setScene(sc);
	        primaryStage.show();
	        primaryStage.setResizable(false);
			stage.close();
	}
	
	@FXML public void returnAction(ActionEvent event) {
		socket = MyInfo.socket;
		
		String[] rowData = ytItemListTable.getSelectionModel().getSelectedItem().toString().split(",");
		try {
	           String m = "returnItem:"+rowData[0].substring(1);
	           System.out.println(m);
	           PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
	           
	           pw.println(m);
	           pw.flush();
	           
	           Alert eraseSuccess = new Alert(AlertType.INFORMATION);
	           eraseSuccess.setHeaderText("삐빅!");
	           eraseSuccess.setContentText("반납이 완료되었습니다!");
	           eraseSuccess.showAndWait();
	           
	           Stage primaryStage = new Stage();
	   			Stage stage = (Stage)ytTitle.getScene().getWindow();
	   			Parent ob = FXMLLoader.load(getClass().getResource("templates/yourThings.fxml"));
	   			ob.getStylesheets().add(getClass().getResource("statics/yourThings.css").toExternalForm());
	   			Scene sc = new Scene(ob);
	   			primaryStage.setScene(sc);
	   	        primaryStage.show();
	   	        primaryStage.setResizable(false);
	   			stage.close();
	           
		} catch (IOException e1) {
	        e1.printStackTrace();
	     }
}
	@FXML public void ytLink() {
		Hyperlink myHyperlink = new Hyperlink();
		myHyperlink.setText("test");
		try {
			Desktop.getDesktop().browse(new URI("https://enjoyso.tistory.com/62"));
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
}
