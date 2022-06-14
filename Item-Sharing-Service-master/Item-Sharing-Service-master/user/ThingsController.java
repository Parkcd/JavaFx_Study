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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn;

public class ThingsController implements Initializable {

	@FXML Button myThingsmoveMypage_Btn;
	@FXML HBox mtTopHbox;
	@FXML HBox mtBtnColor;
	@FXML Button mtBtn1;
	@FXML Button mtBtn2;
	@FXML Button mtBtn3;
	@FXML Button mtBtn4;
	@FXML Button mtBtn5;
	@FXML Button mtBtn6;
	@FXML Button mtMoveUserHome;
	@FXML Label mtTitle;
	@FXML AnchorPane mtBackColor;
	@FXML Line mtLine1;
	@FXML Label mtMythingLabel;
	@FXML Line mtLine2;
	@FXML Label mtMythingsLabel;
	@FXML Button mtMoveMypageBtn;
	@FXML Button mtEraseBtn;

	ObservableList<ObservableList> mylist = FXCollections.observableArrayList();
	
	Socket socket;
	@FXML TableView<ObservableList> myThingsList;
	@FXML TableColumn my_kinds;
	@FXML TableColumn my_title;
	@FXML TableColumn my_state;
	@FXML TableColumn my_limit;
	@FXML TableColumn my_price;
	@FXML TableColumn my_like;
	@FXML Button mtNoticeBtn;
	@FXML TableColumn my_num;
	
	@FXML public void myThingsmoveUserHome() throws Exception {
		
		Stage primaryStage = new Stage();
		Stage stage = (Stage)mtTitle.getScene().getWindow();

			Parent ob = FXMLLoader.load(getClass().getResource("templates/userMain.fxml"));
			ob.getStylesheets().add(getClass().getResource("statics/userMain.css").toExternalForm());
			Scene sc = new Scene(ob);
			primaryStage.setScene(sc);
	        primaryStage.show();
	        primaryStage.setResizable(false);
			stage.close();
	}

	@FXML public void myThingsmoveMyPage() throws Exception {
		Stage primaryStage = new Stage();
		Stage stage = (Stage)mtTitle.getScene().getWindow();

			Parent ob = FXMLLoader.load(getClass().getResource("templates/myPage.fxml"));
			ob.getStylesheets().add(getClass().getResource("statics/myPage.css").toExternalForm());
			Scene sc = new Scene(ob);
			primaryStage.setScene(sc);
	        primaryStage.show();
	        primaryStage.setResizable(false);
			stage.close();
	}

	@FXML public void mtMoveLike() throws Exception{
		Stage primaryStage = new Stage();
		Stage stage = (Stage)mtTitle.getScene().getWindow();

			Parent ob = FXMLLoader.load(getClass().getResource("templates/like.fxml"));
			ob.getStylesheets().add(getClass().getResource("statics/like.css").toExternalForm());
			Scene sc = new Scene(ob);
			primaryStage.setScene(sc);
	        primaryStage.show();
	        primaryStage.setResizable(false);
			stage.close();
	}
	
	@FXML public void EraseBtn() {
		socket = MyInfo.socket;
		String[] rowData = myThingsList.getSelectionModel().getSelectedItem().toString().split(",");
		try {
	           String m = "deleteData:"+rowData[0].substring(1);
	           PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
	           
	           pw.println(m);
	           pw.flush();
	           
	           Alert eraseSuccess = new Alert(AlertType.INFORMATION);
	           eraseSuccess.setHeaderText("Confirmation");
	           eraseSuccess.setContentText("Erase Success!");
	           eraseSuccess.showAndWait();
	           
	           Stage primaryStage = new Stage();
	   			Stage stage = (Stage)mtEraseBtn.getScene().getWindow();
	   			Parent ob = FXMLLoader.load(getClass().getResource("templates/myThings.fxml"));
	   			ob.getStylesheets().add(getClass().getResource("statics/myThings.css").toExternalForm());
	   			Scene sc = new Scene(ob);
	   			primaryStage.setScene(sc);
	   	        primaryStage.show();
	   	        primaryStage.setResizable(false);
	   			stage.close();
	           
		} catch (IOException e1) {
	        e1.printStackTrace();
	     }
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		socket = MyInfo.socket;
		
		String myList = null;
		String[] temp = null;
        try {
           String m = "MyThings:"+MyInfo.my_id;
           BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
           PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
           
           pw.println(m);
           pw.flush();
           
           myList = br.readLine();
           temp = myList.split("//");
           
           if(Arrays.deepToString(temp).equals("[]")) {

        	   myThingsList.setItems(null);
        	   return;
           }
           for(int i = 0; i < temp.length; i++) {
        	   ObservableList<String> row = FXCollections.observableArrayList();
        	  
        	   String[] temp2 = temp[i].split("@@");
        	   		for(int j = 0; j<temp2.length; j++) {
        	   			if(j==3) {
        	   				if(temp2[j].equals("null"))
        	   					row.add("없음");
        	   				else
        	   					row.add(temp2[j]);
        	   			}
        	   			else {
        	   			row.add(temp2[j]);
        	   			}
        	   }
        	   	mylist.add(row);
           }
           
           myThingsList.setItems(mylist);
           
           my_num.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
               public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
                   return new SimpleStringProperty(param.getValue().get(0).toString());                        
               }                    
           });
           
           my_kinds.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
               public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
                   return new SimpleStringProperty(param.getValue().get(1).toString());                        
               }                    
           });
   			
           my_title.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
               public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
                   return new SimpleStringProperty(param.getValue().get(2).toString());                        
               }                    
           });
           
           my_state.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
               public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
                   return new SimpleStringProperty(param.getValue().get(3).toString());                        
               }                    
           });
           
           my_limit.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
               public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
                   return new SimpleStringProperty(param.getValue().get(4).toString());                        
               }                    
           });
           
           my_price.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
               public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
                   return new SimpleStringProperty(param.getValue().get(5).toString());                        
               }                    
           });
   		
   		
           
           
	} catch (IOException e1) {
        e1.printStackTrace();
     }

}

	@FXML public void mtLink() {
		Hyperlink myHyperlink = new Hyperlink();
		myHyperlink.setText("test");
		try {
			Desktop.getDesktop().browse(new URI("https://enjoyso.tistory.com/62"));
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}
}
