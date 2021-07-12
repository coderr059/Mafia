import java.util.*;
public class Mafia extends Player{
	
	static ArrayList<Mafia> clg=new ArrayList<Mafia>();
	Mafia(int num){
		super(num);
		hp=2500;
		if (num!=-1) {
		clg.add(this);
		}
	}
	public void target() {
		int[] hitlist=Game.getalive();
		int id;
		while (true) {
				int f=0;
			Random rand=new Random();
			id=rand.nextInt(hitlist.length);
			for (int j=0;j<clg.size();j++) {
				if (hitlist[id]==clg.get(j).pnum) {
					f=1;	
					break;
				}
			}if (f==0) {
				id=hitlist[id];
				break;
			}
		}Game.calchp(id,clg);
		
	}
	public void usertarget(int target) {
		Game.calchp(target,clg);
	}

}
