import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import bridges.data_src_dependent.Song;
import bridges.connect.DataFormatter;

/**
 * @author danielwest SongList.java CMSC 256 Fall 2018 Programming Project 4
 *         Song List
 * 
 *         This program pulls song data from bridges online data sheet. It then
 *         takes in a command line argument in the form of an artists name and
 *         finds the artists songs and album titles associated with the artist
 *         name. It also sorts the song names in alphabetical order. Then
 *         displays the song title, artist name and album title to the user.
 *
 */
public class SongList {

	/**
	 * @param args
	 *            Main method should take in a arg as an song artist name.
	 */
	public static void main(String[] args) {

		printHeading();
		Scanner in = new Scanner(System.in); // Scanner to be used if no arguments are passed
		String artist = null; // Holds value of argument or scanner passed in
		ArrayList<Song> songData = null; // ArrayList of all song data pulled from bridges

		// Test to make sure arguments are passed
		if (args.length == 0) {
			System.out.print("No arguments to echo, please try again: ");
			artist = in.nextLine();
		} else {
			artist = args[0];
		}

		try {
			songData = DataFormatter.getSongData();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Sorts ArrayList<Song> in alphabetical order for display
		Collections.sort(songData, new SongComparator());

		// If statement to make sure the argument passed is actually in the list
		if (searchArray(songData, artist)) {
			// Loop to display all the data in the correct order

			for (Song ele : songData) {
				if (ele.getArtist().equalsIgnoreCase(artist)) {

					System.out.println("Title: " + ele.getSongTitle());
					System.out.println("Artist: " + ele.getArtist());
					System.out.println("Album: " + ele.getAlbumTitle() + "\n");

				}
			}

		} else {
			System.out.println("Sorry but the artist you've chosen does not exist in this playlist. Please try again.");
		}
		in.close();
	}

	/**
	 * This method checks to see if there is an artist name in the array list equal
	 * to the one passed into the command line
	 * 
	 * @param data
	 *            ArrayList is data passed into the array
	 * @param name
	 *            argument passed into method for artist name
	 * @return boolean if the specific artist name is found in the ArrayList
	 */
	private static boolean searchArray(ArrayList<Song> data, String name) {
		boolean result = true;
		for (Song d : data) {
			if (d.getArtist() != null && d.getArtist().equalsIgnoreCase(name)) {
				return result = true;
			} else {
				result = false;
			}
		}
		return result;
	}

	/**
	 * Header method for class
	 */
	private static void printHeading() {
		System.out.println();
		System.out.println("Daniel C. West"); // Name of Program Author
		System.out.println("CMSC 256 Fall 2018"); // Class, Section, and Semester
		System.out.println("Programming Project 4"); // Program Number
		System.out.println("Song List"); // Program Name
		System.out.println();
	}
}
