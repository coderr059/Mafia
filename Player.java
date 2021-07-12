import java.util.*;

public abstract  class Player {
	int hp;
	final int pnum;
	boolean alive=true; 

	Player(int pnum1){
		pnum=pnum1;
	}  
	public void castvote() {
		Random rand=new Random();
		int[] al=Game.getalive();    //not giving away refernces of other players to a player
		int v=rand.nextInt(al.length);
		Game.poll(al[v]);
		
	}
	public void uservote(int t) {
		Game.poll(t);
	}
	@Override
	public String toString(){
		return "Player" + pnum;
	}
	@Override
	public boolean equals(Object o1) {  
		if (o1 instanceof Player) {
			Player o=(Player)o1;
			return o.pnum==pnum;
		}else { 
			return false;
		}
	}
	
}
