package Home;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;

//import application.Main;
//import application.Player;

import java.util.Random;
import java.util.Scanner;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;



public class Itunes implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HashMap<String,Album> AlbumMap = new HashMap<String, Album>();
	public HashMap<String,Artist> ArtistMap = new HashMap<String, Artist>();

	private final  Playlist songLibrary = new Playlist("Library");
	public static Random randomGenerator;
	//static application.MediaPlayerController Controlla;
	//static public	MediaPlayer play = Controlla.getPlayer();
	public static void main(String[] args) throws InterruptedException, FileNotFoundException {
		randomGenerator = new Random();



		/*Itunes itunes = new Itunes();
		itunes.loadSongs();
		itunes.displayAllSongs();
		serializeContainer(itunes);*/

	Itunes itunes = deserializer();// Why is this duplicate variable
		//playStream(itunes.ArtistMap.get("Bryce Vine").getAlbums().get("Coachella"));
		//playStream(itunes.getSongLibrary());
		//playSong("Kanye West - Stronger");
		//itunes.songSearch("Default");
		//	System.out.println(itunes.displayArtists());
		//System.out.println(itunes.ArtistMap.values().toString());
		//	System.out.println(itunes.AlbumMap.));
		//System.out.println(itunes.ArtistMap.get("Bryce Vine").getAlbums().toString());
		//	System.out.println(itunes.displayAlbums());
		/*for(Song s : itunes.AlbumMap.get("Rapper").getListOfSongs()) {
			System.out.println(s.getName());
		}*/
		//System.out.println(itunes.songLibrary.getListOfSongs().size());
		//see if the artistMap has songs
		//System.out.println(itunes.ArtistMap.get("Bryce Vine").getAlbums().get("A").AlbumName);
		//itunes.songSearch("Juice WRLD");
		//itunes.jukeBox();
	}


	public void loadSongs() throws FileNotFoundException {
		//C:\Users\gc01873\eclipse-workspace\DataStrGC
		Scanner scan = new Scanner(new File("C:\\Users\\gc01873\\eclipse-workspace\\DataStrGC\\songs.txt"));//what should be added here
		while(scan.hasNextLine()){
			String temp1 = scan.nextLine();
			addSongs(temp1);
		}
	}



	public void addSongs(String line) {
		String[] p = line.split(" - ");
		System.out.println("The artist is " + p[0]);
		Scanner albumInput = new Scanner(System.in);
		System.out.println("What is the album of the artist");
		String alb = albumInput.nextLine();
		Song progress;

		if(!ArtistMap.containsKey(p[0])) {
			ArtistMap.put(p[0], new Artist(p[0]));

			ArtistMap.get(p[0]).getAlbums().put(alb, new Album(alb,p[1])) ;

			AlbumMap.put(alb, ArtistMap.get(p[0]).getAlbums().get(alb));

		}
		if(ArtistMap.containsKey(p[0]) && !ArtistMap.get(p[0]).getAlbums().containsKey(alb)){
			ArtistMap.get(p[0]).getAlbums().put(alb, new Album(alb,p[1]));
		}
		progress= new Song(p[1],ArtistMap.get(p[0]),ArtistMap.get(p[0]).getAlbums().get(alb));

		ArtistMap.get(p[0]).getAlbums().get(alb).getListOfSongs().add(progress);
		AlbumMap.put(alb, ArtistMap.get(p[0]).getAlbums().get(alb));
		AlbumMap.get(alb).AddSong(progress);
		getSongLibrary().getListOfSongs().add(progress);
	}

	//may need to switch input to an album
	public static void playStream(Playlist playlist) throws InterruptedException {
		JFXPanel jxp = new JFXPanel();
		System.out.println("Look!");

		MediaPlayerController.player = new MediaPlayer(new Media(new File("C:\\Users\\gc01873\\eclipse-workspace\\DataStrGC\\Home\\" + playlist.getListOfSongs().get(0).getArtist().Name + " - " + 
				playlist.getListOfSongs().get(0).getName() + ".mp3" ).toURI().toString()));//what file should be hereee */

		System.out.println("We got here");
		for (int i = 0; i < playlist.getListOfSongs().size();i++ ) {
			System.out.println(playlist.getListOfSongs().get(i).getName());
			while(MediaPlayerController.player.getStatus().equals(Status.UNKNOWN)) {
				System.out.println("Unknown");
				Thread.sleep(1000);

			}
			if(MediaPlayerController.player.getStatus().equals(Status.STOPPED)|| MediaPlayerController.player.getStatus().equals(Status.READY)) {
				System.out.println("Stopped giving new Media");

				MediaPlayerController.player = new MediaPlayer(new Media(new File("C:\\Users\\gc01873\\eclipse-workspace\\DataStrGC\\Home\\"+ playlist.getListOfSongs().get(i).getArtist().Name + " - " + 
						playlist.getListOfSongs().get(i).getName() + ".mp3").toURI().toString())); 
				System.out.println(playlist.getListOfSongs().get(i).getArtist().Name + " - " + playlist.getListOfSongs().get(i).getName() + ".mp3");
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

	
	public  void songSearch(String Search) throws InterruptedException {
		if(ArtistMap.containsKey(Search)) {			
			System.out.println("Found Artist");
			//		System.out.println("Which album?");
			//for(Ar)
			//	playStream((ArtistMap.get(Search).getAlbums()).displayAlbum(Search)); //May have to work on default
		}
		if(AlbumMap.containsKey(Search)) {
			System.out.println("Found Album!");
			playStream(AlbumMap.get(Search));
		}
		//See to finish song method
		else if(!ArtistMap.containsKey(Search) && !AlbumMap.containsKey(Search)) {
			for(Song sSong :songLibrary.getListOfSongs()) {
				if(sSong.getName()==Search) {
					sSong.Play();//is this method still applicable
				}

			}
			System.out.println("Sorry! you done goofed! No such media exists!");	 
		}
	}

	/*public void jukeBox() {
		int index = randomGenerator.nextInt(getSongLibrary().getListOfSongs().size());
		System.out.println(index);
		//Controlla
		Song JukeBox = getSongLibrary().getListOfSongs().get(index);
		play = new MediaPlayer(new Media(new File("C:\\Users\\sm06156\\eclipse-workspace\\DataStrGC\\Home\\Home\\" + JukeBox.artistName + " - " + 
				JukeBox.getName() + ".mp3" ).toURI().toString()));
		play.setOnReady(new Runnable() {
			public void run(){
				play.play();
			}
		});
		play.setOnEndOfMedia(new Runnable(){
			public void run() {
				play.stop(); 
			}

		});
	}*/

	public static void pause() {
		if(MediaPlayerController.player.getStatus().equals(Status.PLAYING)) {
			MediaPlayerController.player.pause();
			System.out.println("Player Paused from itunes!");
		}
		else
		{System.out.println("Song is not paused");
		}
	}

	public void stop() {
		if(MediaPlayerController.player.getStatus().equals(Status.PLAYING)) {
			{
				MediaPlayerController.player.stop();
				System.out.println("Media Player is Stopped From the Itunes app");
			}
		}
	}



	public ArrayList<String> displayArtists() {
		ArrayList<String> artists = new ArrayList<String>();
		for(Map.Entry<String, Artist>temp: this.ArtistMap.entrySet()){
			artists.add(temp.getKey());
		}
		return artists;
	}

	public ArrayList<String> displayAlbums() {
		ArrayList<String> albs = new ArrayList<String>();
		for(Entry<String, Album> temp: this.AlbumMap.entrySet()){
			albs.add(temp.getKey());
		}
		return albs;
	}
	//Returns array of albums
	/*public ArrayList<Album> displayAlbums(String a) {
		ArrayList<Album> albs = new ArrayList<Album>();
		HashMap<String,Album> artistsAlbum = ArtistMap.get(a).getAlbums();
		for(Entry<String, Album> temp: artistsAlbum.entrySet()){
			albs.add(temp.getValue());
		}
		return albs;
	}*/

	public ArrayList<String>displaySongs(){
		ArrayList<String> Canciones = new ArrayList<String>();
		for(Song w : getSongLibrary().getListOfSongs()) {
			Canciones.add(w.artistName +" - "+ w.getName());

		}
		return Canciones;
	}



	public void displayAllSongs() {
		for(Map.Entry<String, Artist>temp:ArtistMap.entrySet()) {
			Artist artistTemp = temp.getValue();
			for(Map.Entry<String, Album> tempAlbum : artistTemp.getAlbums().entrySet()) {
				Album tempAlb = tempAlbum.getValue();
				for(Song s : tempAlb.getListOfSongs()) {
					System.out.println(artistTemp.getName() + " " + tempAlb.AlbumName+ " " + s.getName());
				}
			}
		}
	}

	public static void serializeContainer(Itunes itunes) {
		try {
			FileOutputStream fileOut = new FileOutputStream("C:\\Users\\gc01873\\eclipse-workspace\\DataStrGC\\output.ser");//
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(itunes);
			out.close();
			fileOut.close();
			System.out.println("Serialized data is saved in ....");

		} catch (IOException i) {
			i.printStackTrace();
		}
	}


	public static Itunes deserializer() {
		Itunes superSerialGuis = null;
		try {
			FileInputStream fileIn = new FileInputStream("C:\\Users\\gc01873\\eclipse-workspace\\DataStrGC\\output.ser");//
			System.out.println(fileIn.getFD());
			ObjectInputStream in = new ObjectInputStream(fileIn);
			superSerialGuis = (Itunes) in.readObject();
			in.close();
			fileIn.close();

		} catch (IOException i ) {
			i.printStackTrace();
		} catch (ClassNotFoundException c) {
			System.out.println("Oops class not found deserealizer");
			c.printStackTrace();
		}

		return superSerialGuis;
	}//may need to make it void, we'll see
	public Album addAlbum(Album x) {
		if(AlbumMap.containsValue(x)) {
			System.out.println("Album has already been added!");

			return x;
		}
		else

			AlbumMap.put(x.getAlbumName().toLowerCase(), x);

		return x;
	}

	public void removeAlbum(Album x) {
		if(AlbumMap.isEmpty()) {
			System.out.println("Cannot remove what is not there young padawan");
		}
		if(!AlbumMap.containsKey(x.getAlbumName().toLowerCase())) {
			System.out.println("There does not seem to be a key by that link");
		}
		if(AlbumMap.containsKey(x.getAlbumName().toLowerCase())) {
			AlbumMap.remove((x.getAlbumName().toLowerCase()));
		}
	}

	public Artist addArtist(Artist x) {
		if(ArtistMap.containsValue(x)) {
			System.out.println("Artist has already been added!");

			return x;
		}
		else

			ArtistMap.put(x.getName().toLowerCase(), x);

		return x;
	}

	public void removeArtist(Artist x) {
		if(ArtistMap.isEmpty()) {
			System.out.println("Cannot remove what is not there young padawan");
		}
		if(!ArtistMap.containsKey(x.getName().toLowerCase())) {
			System.out.println("There does not seem to be a key by that link");
		}
		if(ArtistMap.containsKey(x.getName().toLowerCase())) {
			AlbumMap.remove((x.getName().toLowerCase()));
		}


	}


	public Playlist getSongLibrary() {
		return songLibrary;
	}
}

