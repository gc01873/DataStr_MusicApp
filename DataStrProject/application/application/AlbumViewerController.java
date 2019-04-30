
package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

//import Home.MediaPlayerController;
//import Home.MYPlayer;
import Home.Playlist;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;

public class AlbumViewerController implements Initializable {

	public void MenuScreen(ActionEvent event) throws IOException{
		Parent MenuView = FXMLLoader.load(getClass().getResource("MenuViewer.fxml"));
		Scene MenuScene = new Scene(MenuView);
		Stage Window = (Stage)((Node)event.getSource()).getScene().getWindow();
		Window.setScene(MenuScene);
		Window.show();
	}
	public ObservableList<String> list = FXCollections.observableArrayList();
	@FXML 
	public  ListView <String>AlbumsListBox;
	public ArrayList<String> Albums = Main.itunes.displayAlbums();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

loadData();
	}

	public  void loadData() {
		list.removeAll(list);
		list.addAll(Albums);
		AlbumsListBox.getItems().addAll(list);

	}
	
	public void playAlbum(MouseEvent event) throws InterruptedException, IOException  {
		String Guy = AlbumsListBox.getSelectionModel().getSelectedItem();
		
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
		MediaPlayerController.player = new MediaPlayer(new Media(new File("src/media/" + Main.itunes.AlbumMap.get(Guy).getListOfSongs().get(0).getArtist().Name + " - " + 
				Main.itunes.AlbumMap.get(Guy).getListOfSongs().get(0).getName() + ".mp3" ).toURI().toString()));//what file should be hereee */

		System.out.println("We got here");
		for (int i = 0; i < Main.itunes.AlbumMap.get(Guy).getListOfSongs().size();i++ ) {
			System.out.println(Main.itunes.AlbumMap.get(Guy).getListOfSongs().get(i).getName());
			System.out.println(MediaPlayerController.player);
			//System.out.println(MediaPlayerController.);
			/*while(MediaPlayerController.player.getStatus().equals(Status.UNKNOWN)) {
				System.out.println("Unknown");
				Thread.sleep(1000);

			}*/
			if(MediaPlayerController.player.getStatus().equals(Status.STOPPED)|| MediaPlayerController.player.getStatus().equals(Status.READY)) {
				System.out.println("Stopped giving new Media");

				MediaPlayerController.player = new MediaPlayer(new Media(new File("src/media/"+ Main.itunes.AlbumMap.get(Guy).getListOfSongs().get(i).getArtist().Name + " - " + 
						Main.itunes.AlbumMap.get(Guy).getListOfSongs().get(i).getName() + ".mp3").toURI().toString())); 
				System.out.println(Main.itunes.AlbumMap.get(Guy).getListOfSongs().get(i).getArtist().Name + " - " + Main.itunes.AlbumMap.get(Guy).getListOfSongs().get(i).getName() + ".mp3");
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





