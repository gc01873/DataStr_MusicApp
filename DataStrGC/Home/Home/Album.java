package Home;

import java.io.Serializable;
import java.util.ArrayList;
public class Album extends Playlist implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	public Album(String PlayListName) {
		super(PlayListName);
		
		
		}
	public Album(String PlayListName, String artist) {
		super(PlayListName, artist);
		
		//ListOfSongs Albums = new ListOfSongs();
		// TODO Auto-generated constructor stub
	}
	String AlbumName = getPlayListName();
	int year; 
	String Artist;

	public String getAlbumName() {
		return AlbumName;
	}
	public void setAlbumName(String albumName) {
		AlbumName = albumName;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getArtist() {
		return Artist;
	}
	public void setArtist(String artist) {
		Artist = artist;
	}
	
	
	
}

