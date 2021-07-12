import java.util.*;
public class Healer extends Player{
	static ArrayList<Healer> hlist=new ArrayList<Healer>();
	Healer(int num){
		super(num);
		hp=800;
		if (num!=-1) {
		hlist.add(this);
	}
	}		
	public void heal(){
		Random rand=new Random();
		int[] alive=Game.getalive();
		int r=rand.nextInt(alive.length);
		Game.increasehp(alive[r]);
		return;
	}
	public void userheal(int target) {
		Game.increasehp(target);
	}
}
