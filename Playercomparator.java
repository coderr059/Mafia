import java.util.*;
public class Playercomparator implements Comparator<Player>{
	@Override
	public int compare(Player p1,Player p2) {
		return p1.pnum-p2.pnum;
	}
}
