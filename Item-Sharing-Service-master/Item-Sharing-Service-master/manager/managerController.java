package manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.ResourceBundle;

import home.MyInfo;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.control.Label;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;

public class managerController implements Initializable{
	
	
	Socket socket;
	@FXML Button mmNoticeBtn;
	@FXML AnchorPane mmBackColor;
	@FXML HBox mmtopHbox;
	@FXML Label mmMoveUserHome;
	@FXML Button mmbtn1;
	@FXML Button mmbtn2;
	@FXML Button mmbtn3;
	@FXML Button mmbtn4;
	@FXML Button mmbtn5;
	@FXML Button mmbtn6;
	@FXML Label mmTotalLabel;
	@FXML Label mmNumTotal;
	@FXML TableView<ObservableList> mmItemListTable;
	@FXML TableColumn nCol;
	@FXML TableColumn mmNameCol;
	@FXML TableColumn mmIdCol;
	@FXML TableColumn mmPriceCol;
	@FXML TableColumn mmRentCol;
	@FXML Button mmEraseBtn;
	@FXML Label managerTitle;
	@FXML HBox mmBtnColor;
	ObservableList<ObservableList> mylist = FXCollections.observableArrayList();
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		socket = MyInfo.socket;
		
		String cnt = null;
		try {
	           String m = "itemsCnt:";
	           BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
	           PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
	           
	           pw.println(m);
	           pw.flush();
	           
	          cnt = br.readLine();
	          
	          mmNumTotal.setText(cnt);
	          
	        } catch (IOException e1) {
	           e1.printStackTrace();
	        }
		
		String myList = null;
		String[] temp = null;
        try {
           String m = "loadItemList:";
           BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
           PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
           
           pw.println(m);
           pw.flush();
           
           myList = br.readLine();
           temp = myList.split("//");
           
           System.out.println(Arrays.deepToString(temp));
           
           if(Arrays.deepToString(temp).equals("[]")) {

        	   mmItemListTable.setItems(null);
        	   return;
           }
           
           for(int i = 0; i < temp.length; i++) {
        	   ObservableList<String> row = FXCollections.observableArrayList();
        	   String[] temp2 = temp[i].split("@@");
        	   		for(int j = 0; j<temp2.length; j++) {
        	   			if(j==6) {
        	   				if(temp2[j].toString().equals("0")) 
        	   					row.add("가능");
        	   				else 
        	   					row.add("불가능");
        	   				
        	   			}else
        	   				row.add(temp2[j]);
        	   		}
        	   	mylist.add(row);
           	}
           
           mmItemListTable.setItems(mylist);
           
           nCol.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
               public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
                   return new SimpleStringProperty(param.getValue().get(0).toString());                        
               }                    
           });
           
           mmNameCol.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
               public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
                   return new SimpleStringProperty(param.getValue().get(1).toString());                        
               }                    
           });
   			
           mmIdCol.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
               public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
                   return new SimpleStringProperty(param.getValue().get(3).toString());                        
               }                    
           });
           
           mmRentCol.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
               public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {   
                   return new SimpleStringProperty(param.getValue().get(6).toString());                        
               }                    
           });
           
           mmPriceCol.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
               public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
                   return new SimpleStringProperty(param.getValue().get(5).toString());                        
               }                    
           });		
           
           
        } catch (IOException e1) {
        	e1.printStackTrace();
        }

	}

	@FXML public void managerErase() {
		socket = MyInfo.socket;
		String[] rowData = mmItemListTable.getSelectionModel().getSelectedItem().toString().split(",");
		if(rowData[6].substring(1, 2).equals("1")) {
     	   Alert eraseSuccess = new Alert(AlertType.ERROR);
	           eraseSuccess.setHeaderText("Erase Fail");
	           eraseSuccess.setContentText("Please select things not rented");
	           eraseSuccess.showAndWait();
        }else {
        	try {
        		String m = "deleteData:"+rowData[0].substring(1);
        		PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
	           
	           pw.println(m);
	           pw.flush();
	           
	           Alert eraseSuccess = new Alert(AlertType.CONFIRMATION);
	           eraseSuccess.setHeaderText("Confirmation");
	           eraseSuccess.setContentText("Erase Success!");
	           eraseSuccess.showAndWait();
	           
	           Stage primaryStage = new Stage();
	   			Stage stage = (Stage)managerTitle.getScene().getWindow();
	   			Parent ob = FXMLLoader.load(getClass().getResource("templates/managerMain.fxml"));
	   			ob.getStylesheets().add(getClass().getResource("statics/managerMain.css").toExternalForm());
	   			Scene sc = new Scene(ob);
	   			primaryStage.setScene(sc);
	   	        primaryStage.show();
	   	        primaryStage.setResizable(false);
	   			stage.close();
	           
        	} catch (IOException e1) {
        		e1.printStackTrace();
        	}
        	
        }
	}
	
	@FXML public void mmbtn1() {
		CategorySort("-1");
	}
	
	
	@FXML public void mmbtn2() {
		CategorySort("기내용품");
	}
	@FXML public void mmbtn3() {
		CategorySort("캐리어");
	}
	@FXML public void mmbtn4() {
		CategorySort("카메라장비");
	}
	@FXML public void mmbtn5() {
		CategorySort("생필품");
	}
	@FXML public void mmbtn6() {
		CategorySort("기타");
	}
	
	
	@SuppressWarnings("unchecked")
	public void CategorySort(String cat) {
		mylist.clear();
		socket = MyInfo.socket;

		String myList = null;
		String[] temp = null;
        try {
           String m = "loadCat:"+cat;
           BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
           PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
           
           pw.println(m);
           pw.flush();
           
           myList = br.readLine();
           temp = myList.split("//");
           
           if(Arrays.deepToString(temp).equals("[]")) {

        	   mmItemListTable.setItems(null);
        	   return;
           }
           for(int i = 0; i < temp.length; i++) {
        	   ObservableList<String> row = FXCollections.observableArrayList();
        	  
        	   String[] temp2 = temp[i].split("@@");
        	   		for(int j = 0; j<temp2.length; j++) {
        	   			if(j==7) {
        	   				if(temp2[j].toString().equals("0")) 
        	   					row.add("가능");
        	   				else 
        	   					row.add("불가능");
        	   				
        	   			}else
        	   				row.add(temp2[j]);
        	   }
        	   		mylist.add(row);
           }
		
           mmItemListTable.setItems(mylist);
           
           nCol.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
               public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
                   return new SimpleStringProperty(param.getValue().get(0).toString());                        
               }                    
           });
           
           mmNameCol.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
               public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
                   return new SimpleStringProperty(param.getValue().get(1).toString());                        
               }                    
           });
   			
           mmIdCol.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
               public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
                   return new SimpleStringProperty(param.getValue().get(3).toString());                        
               }                    
           });
           
           mmRentCol.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
               public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {   
                   return new SimpleStringProperty(param.getValue().get(7).toString());                        
               }                    
           });
           
           mmPriceCol.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
               public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
                   return new SimpleStringProperty(param.getValue().get(5).toString());                        
               }                    
           });		
           
		
    	} catch (IOException e1) {
            e1.printStackTrace();
         }
		
		
	}
	

}
