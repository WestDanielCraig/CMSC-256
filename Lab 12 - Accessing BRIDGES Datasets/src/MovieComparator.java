import java.util.Comparator;
import bridges.data_src_dependent.ActorMovieIMDB;

public class MovieComparator implements Comparator<ActorMovieIMDB> {

	@Override
	public int compare(ActorMovieIMDB o1, ActorMovieIMDB o2) {
		return o1.getActor().compareTo(o2.getActor());

	}

}
