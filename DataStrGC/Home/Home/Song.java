package Home;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

//import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Song implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	public String artistName;
	private Artist artist; 
	private Playlist Album; 


	private String path;

	
	String bip;
	Media hit; 
	public Media getHit() {
		return hit;
	}

	public void setHit(Media hit) {
		this.hit = hit;
	}
	public static MediaPlayer mediaPlayer;

	public MediaPlayer getMediaPlayer() {
		return mediaPlayer;
	}

	public void setMediaPlayer(MediaPlayer mediaPlayer) {
		this.mediaPlayer = mediaPlayer;
	}

	public static void main(String[] args) throws InterruptedException {

	}

	
	/*public Song(String name, String artist, int year,Playlist Album, String path){
		this.name = name;
		this.artistName = artist;
		this.year = year;
		this.path = path;
		this.bip = path;
		this.Album = Album;
		final JFXPanel fxP = new JFXPanel(); ;
		this.hit = new Media(new File(bip).toURI().toString());
		this.mediaPlayer = new MediaPlayer(hit);
	/*	final JFXPanel fxP = new JFXPanel(); 
		String bip = getPath();
		Media hit = new Media(new File(bip).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(hit);

	}*/

	public Song(String Name, Artist artist, Album album) {
		this.Album = album;
		this.artist = artist;
		this.artistName = artist.getName();
		this.name = Name;
		
		
	}

	public Song(String name, String path){
		this.name = name;
		this.path = path;
		this.bip = path;
	
		this.hit = new Media(new File(bip).toURI().toString());
		this.mediaPlayer = new MediaPlayer(hit);
	}

	public  MediaPlayer MediaPlayer() {
	
		Media hit = new Media(new File(this.path).toURI().toString());

		return new MediaPlayer(hit);
	}
	 

	public void Play() {

		if(mediaPlayer==null) {
			mediaPlayer = MediaPlayer();
			mediaPlayer.play();
			System.out.println(mediaPlayer.getTotalDuration());	
		
		}

		else 	{


			mediaPlayer.play();
			System.out.println("This is coming from the second if statement");	
			System.out.println(mediaPlayer.getTotalDuration());
		}
		
	}
	public void Stop() {
		if(mediaPlayer != null)
			mediaPlayer.stop();
	}


	public void Pause() {
		if(mediaPlayer != null)
			mediaPlayer.pause();
		//mediaPlayer.p
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Artist getArtist() {
		return artist;
	}
	public void setArtist(Artist artist) {
		this.artist = artist;
	}
	
	public Playlist getAlbum() {
		return Album;
	}
	public void setAlbum(Playlist playlist) {
		Album = playlist;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}

