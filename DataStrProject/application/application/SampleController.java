package application;

import java.io.IOException;

//import Home.Song;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class SampleController  {
	/*public void PlaySong() {
		Song DubStep = new Song("Test","Rick and Morty Theme Song [HD].mp3");
		DubStep.Play();

	}*/

	public void MenuScreen(ActionEvent event) throws IOException{
		Parent MenuView = FXMLLoader.load(getClass().getResource("MenuViewer.fxml"));
		Scene MenuScene = new Scene(MenuView);
		Stage Window = (Stage)((Node)event.getSource()).getScene().getWindow();
		Window.setScene(MenuScene);
		Window.show();
	}
}
