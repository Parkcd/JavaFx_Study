package p963;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class RootController implements Initializable {
	@FXML private Label IblTime;
	@FXML private Button btnStart;
	@FXML private Button btnStop;
	
	private boolean stop;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnStart.setOnAction(event->handleBtnStart(event));
		btnStop.setOnAction(event->handleBtnStop(event));
		}
	public void handleBtnStart(ActionEvent e) {
		stop = false;
		Thread thread = new Thread() {
			@Override
			public void run() {
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
				while(!stop) {
					String strTime = sdf.format(new Date());
					Platform.runLater(()->{
						IblTime.setText(strTime);
					});
					try { Thread.sleep(100); } catch (InterruptedException e) {}
				}
			};
		};
		thread.setDaemon(true);
		thread.start();
	}
	
	
	private void handleBtnStop(ActionEvent event) {
		stop = true;
	}


	

}
