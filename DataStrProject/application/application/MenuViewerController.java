package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import Home.Song;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class MenuViewerController implements Initializable {
	public void MenuScreen(ActionEvent event) throws IOException{
		Parent MenuView = FXMLLoader.load(getClass().getResource("Sample.fxml"));
		Scene MenuScene = new Scene(MenuView);
		Stage Window = (Stage)((Node)event.getSource()).getScene().getWindow();
		Window.setScene(MenuScene);
		Window.show();
	}

	public void AlbumScreen(ActionEvent event) throws IOException{
		Parent MenuView = FXMLLoader.load(getClass().getResource("AlbumViewer.fxml"));
		Scene MenuScene = new Scene(MenuView);
		Stage Window = (Stage)((Node)event.getSource()).getScene().getWindow();
		Window.setScene(MenuScene);
		Window.show();
	}
	public void ArtistScreen(ActionEvent event) throws IOException{
		Parent MenuView = FXMLLoader.load(getClass().getResource("ArtistViewer.fxml"));
		Scene MenuScene = new Scene(MenuView);
		Stage Window = (Stage)((Node)event.getSource()).getScene().getWindow();
		Window.setScene(MenuScene);
		//	ArtistViewerController.loadData();
		Window.show();
	}

	public void LibraryScreen(ActionEvent event) throws IOException{
		Parent MenuView = FXMLLoader.load(getClass().getResource("LibraryViewer.fxml"));
		Scene MenuScene = new Scene(MenuView);
		Stage Window = (Stage)((Node)event.getSource()).getScene().getWindow();
		Window.setScene(MenuScene);
		Window.show();
	}
	public void MediaScreen(ActionEvent event) throws IOException{
		Parent MenuView = FXMLLoader.load(getClass().getResource("MediaPlayer.fxml"));
		Scene MenuScene = new Scene(MenuView);
		Stage Window = (Stage)((Node)event.getSource()).getScene().getWindow();
		Window.setScene(MenuScene);
		Window.show();
	}
	public static Random randomGenerator;
	public void jukeBox(ActionEvent event) throws IOException{
		Parent MenuView = FXMLLoader.load(getClass().getResource("MediaPlayer.fxml"));
		Scene MenuScene = new Scene(MenuView);
		Stage Window = (Stage)((Node)event.getSource()).getScene().getWindow();
		Window.setScene(MenuScene);
		Window.show();
		
		randomGenerator = new Random();
		System.out.println(randomGenerator);
		System.out.println(Main.itunes.getSongLibrary().getListOfSongs().size());
		int index = randomGenerator.nextInt(Main.itunes.getSongLibrary().getListOfSongs().size());
		Song JukeBox = Main.itunes.getSongLibrary().getListOfSongs().get(index);
		String path = new File("src/media/" + JukeBox.artistName + " - " + JukeBox.getName() + ".mp3" ).getAbsolutePath();
		if(path!=null) {
			MediaPlayerController.media = new Media(new File(path).toURI().toString());
			System.out.println(MediaPlayerController.media);
			MediaPlayerController.player = new MediaPlayer(MediaPlayerController.media);
			System.out.println(MediaPlayerController.player);
			
			
		}

		
		/*MediaPlayerController.mv.setMediaPlayer(MediaPlayerController.player);
		System.out.println(MediaPlayerController.player);
		MediaPlayerController.player.play();*/
		
		
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}


}
