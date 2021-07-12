import java.util.ArrayList;
import java.util.Random;

public class Detective extends Player{
	static ArrayList<Detective> dlist=new ArrayList<Detective>();
	Detective(int num){
		super(num);
		hp=800;
		if (num!=-1) {
		dlist.add(this);
		}
	}
	public int test(){
		Random rand=new Random();
		int[] alive=Game.getalive();
		int r=rand.nextInt(alive.length);
		if (Game.tester(alive[r])) {
			return alive[r];
		}else {
			return	-1;
		}
		
		
	}public boolean usertest(int target) {
		return Game.tester(target);
	}
}
