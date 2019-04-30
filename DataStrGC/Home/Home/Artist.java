package Home;

import java.io.Serializable;
import java.util.HashMap;

public class Artist implements Serializable {
	private static final long serialVersionUID = 1L;
	public String Name;
	public Playlist Discography = new Playlist(Name + " Discography ");
	int numOfArtistSongs = Discography.getNumOfSongs();
	private HashMap <String, Album> albums = new HashMap <String, Album>();
	

	public void addDisco() {
		System.out.println();
		Playlist Lib = new Playlist(Name + " Library ");
		for(Song NowPlaying :Lib.getListOfSongs()) {
			if(NowPlaying.artistName==this.getName()) {
				Discography.AddSong(NowPlaying);
			}
		}
	}
	public Artist(String Name) {
		this.Name = Name;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public Playlist getDiscography() {
		return Discography;
	}
	public void setDiscography(Playlist discography) {
		Discography = discography;
	}
	public HashMap <String, Album> getAlbums() {
		return albums;
	}
	public void setAlbums(HashMap <String, Album> albums) {
		this.albums = albums;
	}

}



