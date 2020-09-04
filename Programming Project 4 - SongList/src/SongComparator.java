import bridges.connect.DataFormatter;
import bridges.data_src_dependent.Song;
import java.util.Comparator;

/**
 * @author danielwest 
 * SongComparator.java 
 * CMSC 256 
 * Fall 2018 
 * Programming Project 4
 * 
 * This class is used to place all the objects in the Song class into alphabetical order
 * in the arraylist.
 *
 */
public class SongComparator implements Comparator<Song> {

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Song first, Song next) {
		if (compareStrings(first.getAlbumTitle(), next.getAlbumTitle()) == 0) {
			return compareStrings(first.getSongTitle(), next.getSongTitle());
		} else {
			return compareStrings(first.getAlbumTitle(), next.getAlbumTitle());
		}
	}

	/**
	 * This helper method is used in cases where the data sheet had null in an entry
	 * @param song1
	 * @param song2
	 * @return song that was compared to the other
	 */
	private static int compareStrings(String song1, String song2) {
		if (song1 == null) {
			return song2 == null ? 0 : -1;
		}
		if (song2 == null) {
			return 1;
		}
		return song1.compareTo(song2);
	}

}
