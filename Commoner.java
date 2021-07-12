import java.util.*;

public class Commoner extends Player{
	static ArrayList<Commoner> clist=new ArrayList<Commoner>();
	Commoner(int num){
		super(num);
		hp=1000;
		if (num!=-1) {
		clist.add(this);
		}
	}
}
