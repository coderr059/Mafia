import java.util.*;
public class Game {
	private static ArrayList<Player> alive;
	static int[] votemachine;
	final int np;
	static int n;
	static int nd;
	static int nm;
	static int nh;
	static int nc;
	
	public Game(int n1){
		
		n=n1;
		alive=new ArrayList<Player>();
		np=n1;
		votemachine=new int[np+1];
		assign();
	}
	
	public ArrayList<Player> getralive(){
		return alive;
	}
	public static void poll(int target) {
		votemachine[target]++;
	}
	public int endvoting() {
		int m=-1;
		int evicted=-1;
		for (int i=1;i<np+1;i++) {
			if (votemachine[i]>m) {
				m=votemachine[i];
				evicted=i;
			}
		}
		Arrays.sort(votemachine);
		if (votemachine[np]==votemachine[np-1]) {
			for(int i=0;i<np+1;i++) {
				votemachine[i]=0;
			}
			return -1;
		}else {
			for(int i=0;i<np+1;i++) {

				votemachine[i]=0;
			}Player p=getplayer(evicted);
			alive.remove(p);
			p.alive=false;
			updatenumber(p.pnum,p);
			return evicted;
		}
	}

	public ArrayList<Mafia> getmafias(){
		return Mafia.clg;
	}public ArrayList<Detective> getdets(){
		return Detective.dlist;
	}public ArrayList<Healer> getheal(){
		return Healer.hlist;
	}public ArrayList<Commoner> getcom(){
		return Commoner.clist;
	}
	public int usertarget(int t) {
		Player p=getplayer(t);
		try {
			if (p.getClass()==(new Mafia(-1)).getClass()) {
				return 2;
			}else {
				return 0;
			}
		}catch(NullPointerException e) {
			return 1;
		}
	}
	public int usertesting(int t) {
		Player p=getplayer(t);
		try {
			if (p.getClass()==(new Detective(-1)).getClass()) {
				return 2;
			}else {
				return 0;
			}
		}catch(NullPointerException e) {
			return 1;
		}
	}
	public int userhealing(int t) {
		Player p=getplayer(t);
		if (p==null) {
			return -1;
		}else {
			return 0;
		}
	}
	public boolean endcondition() {
		if ((nm>=n-nm) || nm==0) {
			return true;
		}else {
			return false;
		}
	}
	public int winner() {
		return nm==0?0:1;
	}
	public static Player getplayer(int target) {
		Player p=null;
		for (int i=0;i<alive.size();i++) {
			if (alive.get(i).pnum==target) {
				p=alive.get(i);
				break;
			}
		}return p;
	}
	public void assign() {
		nd=n/5;
		nm=n/5;
		nh=Math.max(1,n/10);
		nc=n-(nd+nm+nh);

		ArrayList<Integer> temp=new ArrayList<Integer>();
		int st=0;

		for (st=0;st<nd;st++) {

			temp.add(1);
		}
		for (st=0;st<nm;st++) {
			temp.add(2);
		}
		for (st=0;st<nh;st++) {

			temp.add(3);
		}
		for (st=0;st<nc;st++) {

			temp.add(4);
		}
		Collections.shuffle(temp);
		for (int i=0;i<n;i++) {
			if (temp.get(i)==1) {
				alive.add(new Detective(i+1));
			}if (temp.get(i)==2) {
				alive.add(new Mafia(i+1));
			}if (temp.get(i)==3) {
				alive.add(new Healer(i+1));
			}if (temp.get(i)==4) {
				alive.add(new Commoner(i+1));
			}
		}
	}
	public static int[] getalive() {
		int[] a=new int[alive.size()];
		for (int i=0;i<alive.size();i++) {
			a[i]=alive.get(i).pnum;
		}return a;
	}


	public int checkhp() {
		for (int i=0;i<alive.size();i++) {
			if (alive.get(i).hp==0 && alive.get(i).getClass()!=(new Mafia(-1)).getClass()) {
				Player p=alive.remove(i);
				p.alive=false;
				updatenumber(p.pnum,p);
				return p.pnum;
			}


		}return 0;
	}
	public static void updatenumber(int target,Player p) {
		//	Player p=getplayer(target);
		if (p.getClass()==(new Mafia(-1).getClass())) {
			nm--;
		}if (p.getClass()==(new Detective(-1).getClass())) {
			nd--;
		}if (p.getClass()==(new Healer(-1).getClass())) {
			nh--;
		}if (p.getClass()==(new Commoner(-1).getClass())) {
			nc--;
		}n--;
	}
	public static void mafiatestedpositive(int t) {
		Player p=getplayer(t);
		alive.remove(p);  
		updatenumber(p.pnum,p);
		p.alive=false;

	}
	public static void calchp(int target,ArrayList<Mafia> ml) {
		int thp=0;
		int ct=0;
		for (int i=0;i<ml.size();i++) {
			thp+=ml.get(i).hp;
			if (ml.get(i).hp!=0) {
				ct++;
			}

		}Player p=getplayer(target);
		int damage=p.hp/ct;
		p.hp= thp > p.hp ? 0 : p.hp-thp;
		int f=0;
		for (int i=0;i<ml.size();i++) {
			if (ml.get(i).hp < damage) {
				f=1;
				break;
			}
		}if (f==0) {
			for (int i=0;i<ml.size();i++) {
				ml.get(i).hp -= damage;

			}
		}else {
			int res=0;

			int fg=0;
			while (thp!=0 || (res!=0 && fg!=0)) {
				if (fg!=0) {
					damage=res/ct;
				}

				for (int i=0;i<ml.size();i++) {
					if (ml.get(i).hp>0) {
						ml.get(i).hp -= damage;}
					if (ml.get(i).hp<0) {
						thp-=ml.get(i).hp;
						res+=(-ml.get(i).hp);
						ml.get(i).hp=0;

						ct--;
					}else {
						thp-=damage;
					}
				}fg++;
			}

		}

	}public static void increasehp(int target) {
		Player p=getplayer(target);
		p.hp+=500;
	}
	public static boolean tester(int target) {
		Player p=getplayer(target);
		if (p.getClass()==(new Mafia(-1)).getClass()) {
			mafiatestedpositive(target);
			return true;
		}else {
			return false;
		}
	}
	public int[] asgntouser(Player p) {
		ArrayList<Player> ul=new ArrayList<Player>();
		if (p.getClass()!=(new Commoner(-1)).getClass()) {

			for (int i=0;i<alive.size();i++) {
				if (p.getClass()==alive.get(i).getClass()) {
					ul.add(alive.get(i));
				}
			}
		}else {
			for (int i=0;i<alive.size();i++) {
				if (p.getClass()==alive.get(i).getClass()) {
					ul.add(alive.get(i));
					break;
				}
			}
		}int[] arr=new int[ul.size()];
		for (int i=0;i<ul.size();i++) {
			arr[i]=ul.get(i).pnum;
		}

		return arr;
	}
}