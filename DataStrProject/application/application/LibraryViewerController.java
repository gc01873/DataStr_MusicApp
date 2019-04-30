package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Home.Song;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.stage.Stage;



public class LibraryViewerController implements Initializable {

	public ObservableList<String> list = FXCollections.observableArrayList();
	@FXML
	public  ListView <String>LibraryListBox;
	public ArrayList<String> Cancion = Main.itunes.displaySongs();

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
		list.addAll(Cancion);
		LibraryListBox.getItems().addAll(list);

	}

	public void playSong(MouseEvent event) throws InterruptedException, IOException  {
		String Guy = LibraryListBox.getSelectionModel().getSelectedItem();
		Song Victory=null;
		System.out.println(Victory.getName());
		Parent MenuView = FXMLLoader.load(getClass().getResource("MediaPlayer.fxml"));
		Scene MenuScene = new Scene(MenuView);
		Stage Window = (Stage)((Node)event.getSource()).getScene().getWindow();
		Window.setScene(MenuScene);
		Window.show();
		
		for (int i = 0 ;i<Main.itunes.getSongLibrary().getListOfSongs().size();i++) {
			if(Main.itunes.getSongLibrary().getListOfSongs().get(i).getName()==Guy) {
				Victory = Main.itunes.getSongLibrary().getListOfSongs().get(i);
				System.out.println(Victory.getName());
			}
		}
		System.out.println(Victory.getName());

		if(Victory!=null) {
			String path = new File("src/media/" + Victory.artistName + " - " + Victory.getName() + ".mp3" ).getAbsolutePath();
			if(path!=null) {
				MediaPlayerController.media = new Media(new File(path).toURI().toString());
				System.out.println(MediaPlayerController.media);
				MediaPlayerController.player = new MediaPlayer(MediaPlayerController.media);
				System.out.println(MediaPlayerController.player);

			}
		}
	}
	public void playAll(ActionEvent event) throws InterruptedException, IOException  {
		//Main.itunes.playStream(Main.itunes.getSongLibrary());
		Parent MenuView = FXMLLoader.load(getClass().getResource("MediaPlayer.fxml"));
		Scene MenuScene = new Scene(MenuView);
		Stage Window = (Stage)((Node)event.getSource()).getScene().getWindow();
		Window.setScene(MenuScene);
		Window.show();
		/*String path = new File("src/media/" + Main.itunes.AlbumMap.get(Guy).getListOfSongs().get(0).getArtist().Name + " - " + 
		Main.itunes.AlbumMap.get(Guy).getListOfSongs().get(0).getName() + ".mp3" ).getAbsolutePath();
if(path!=null) {
	MediaPlayerController.media = new Media(new File(path).toURI().toString());
	System.out.println(MediaPlayerController.media);
	MediaPlayerController.player = new MediaPlayer(MediaPlayerController.media);
	System.out.println(MediaPlayerController.player);


}*/
		MediaPlayerController.player = new MediaPlayer(new Media(new File("src/media/" + Main.itunes.getSongLibrary().getListOfSongs().get(0).getArtist().Name + " - " + 
				Main.itunes.getSongLibrary().getListOfSongs().get(0).getName() + ".mp3" ).toURI().toString()));//what file should be hereee */

		System.out.println("We got here");
		for (int i = 0; i < Main.itunes.getSongLibrary().getListOfSongs().size();i++ ) {
			System.out.println(Main.itunes.getSongLibrary().getListOfSongs().get(i).getName());
			System.out.println(MediaPlayerController.player);
			//System.out.println(MediaPlayerController.);
			/*while(MediaPlayerController.player.getStatus().equals(Status.UNKNOWN)) {
		System.out.println("Unknown");
		Thread.sleep(1000);

	}*/
			if(MediaPlayerController.player.getStatus().equals(Status.STOPPED)|| MediaPlayerController.player.getStatus().equals(Status.READY)) {
				System.out.println("Stopped giving new Media");

				MediaPlayerController.player = new MediaPlayer(new Media(new File("src/media/"+ Main.itunes.getSongLibrary().getListOfSongs().get(i).getArtist().Name + " - " + 
						Main.itunes.getSongLibrary().getListOfSongs().get(i).getName() + ".mp3").toURI().toString())); 
				System.out.println(Main.itunes.getSongLibrary().getListOfSongs().get(i).getArtist().Name + " - " + Main.itunes.getSongLibrary().getListOfSongs().get(i).getName() + ".mp3");
				//	Player.mPlayer.play();
				Thread.sleep(1000);
			} 
			while(MediaPlayerController.player.getStatus().equals(Status.READY)|| MediaPlayerController.player.getStatus().equals(Status.PLAYING)) { 
				if(MediaPlayerController.player.getStatus().equals(Status.PLAYING)) {
					System.out.println("Sleeping");
					Thread.sleep(1000);
					MediaPlayerController.player.setOnEndOfMedia(new Runnable(){
						public void run() {
							MediaPlayerController.player.stop(); }
					});
				}
				else {
					MediaPlayerController.player.play();
				}
				MediaPlayerController.player.setOnReady(new Runnable() {
					public void run(){
						MediaPlayerController.player.play();
					}
				});
				MediaPlayerController.player.setOnEndOfMedia(new Runnable(){
					public void run() {
						MediaPlayerController.player.stop(); }
				});
			}

		}


	}

}
