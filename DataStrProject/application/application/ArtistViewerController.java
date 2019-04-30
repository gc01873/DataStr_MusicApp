
package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Home.Artist;
import Home.Itunes;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;



public class ArtistViewerController implements Initializable {
	public ObservableList<String> list = FXCollections.observableArrayList();
	@FXML 
	public  ListView <String>ArtistsListBox;
	public ArrayList<String> artists = Main.itunes.displayArtists();

	public void MenuScreen(ActionEvent event) throws IOException{
		Parent MenuView = FXMLLoader.load(getClass().getResource("MenuViewer.fxml"));

		Scene MenuScene = new Scene(MenuView);
		Stage Window = (Stage)((Node)event.getSource()).getScene().getWindow();
		Window.setScene(MenuScene);
		Window.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		loadData();
	}


	public  void loadData() {
		list.removeAll(list);

		/*String a ="Transport";
		String b = "Asshole";
		String c = "Work fuckin code";*/
		list.addAll(artists);
		ArtistsListBox.getItems().addAll(list);

	}
	
	public void playArtist(MouseEvent event) throws InterruptedException  {
		String Guy = ArtistsListBox.getSelectionModel().getSelectedItem();
	}
}