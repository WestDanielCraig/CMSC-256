import bridges.connect.Bridges;
import bridges.connect.DataFormatter;
import bridges.data_src_dependent.ActorMovieIMDB;
import java.util.*;

public class BridgesSorting {

	public static void main(String[] args) {

		ArrayList<ActorMovieIMDB> movieData = null;

		try {
			movieData = DataFormatter.getActorMovieIMDBData(200);

		} catch (Exception e) {
			e.printStackTrace();
		}

		Collections.sort(movieData, new MovieComparator());

		for (ActorMovieIMDB ele : movieData) {
			if (ele.getMovie().equals("Being_John_Malkovich_(1999)"))
				System.out.println(ele.getActor());
		}
		


	}

}
