import java.util.*;
public class Main {
	
	Game g;
	Main(int n){
		g=new Game(n);
		User.setgame(g);

	}
	public static int takeint(String s) {
		boolean done=false;
		int c=0;
		while (!done) {
			System.out.println(s);
			try {
				Scanner abc=new Scanner(System.in);
				c=abc.nextInt();
				done=true;
			} catch(InputMismatchException e) {
				System.out.println("Try again with an integer ");
			}
		}return c;
	}
	
	public void printintro(int rounds) {
		System.out.println("Round " + rounds + ":");
		int[] g1=g.getalive();
		ArrayList<Player> fp=g.getralive();
		Collections.sort(fp,new Playercomparator());

		System.out.print(g1.length + " players are reamining:");
		for (int i=0;i<fp.size()-1;i++) {
			System.out.print(fp.get(i) + " ," );
		}System.out.println(fp.get(fp.size()-1));

	}
	
	public int dettest() {
		int vcheck=-1;
		if (g.getdets()!=null) {
			vcheck=g.getdets().get(0).test();

		}
		System.out.println("Detectives have chosen a player to test");
		return vcheck;
	}
	
	public void heal() {
		if (g.getheal()!=null) {
			g.getheal().get(0).heal();

		}
		System.out.println("Healers have chosen a player to heal");



		System.out.println("--End of Actions--");
	}
	
	public void maftarget() {

		g.getmafias().get(0).target();


		System.out.println("Mafias have chosen a target");
	}
	
	public void playerdead() {
		int te=g.checkhp();
		if (te==0) {
			System.out.println("No one died");
		}else {
			System.out.println("Player"+te+" died");
		}

	}
	
	public void uservoting(int vcheck,Player user) {
		if (vcheck!=-1) {
			System.out.println("Player"+vcheck+" has been voted out");
		}else {
			int r=-1;
			while (r==-1) {
				if (user.alive) {
					int uvote;
					uvote=takeint("Select a person to vote out:");
					while(true) {
						int[] du=Game.getalive();
						int f=0;
						for (int i=0;i<Game.getalive().length;i++) {
							if (uvote==du[i]) {
								f=1;
								break;
							}
						}if (f==1) {
							break;
						}else {
							System.out.println("Try again , can only select alive players");
							uvote=takeint("Select a person to vote out:");

						}

					}user.uservote(uvote);
					ArrayList<Player> tovote=g.getralive();
					for (int i=0;i<tovote.size();i++) {
						if (!user.equals(tovote.get(i))){
							tovote.get(i).castvote();
						}
					}
				} else {
					ArrayList<Player> tovote=g.getralive();
					for (int i=0;i<tovote.size();i++) {

						tovote.get(i).castvote();

					}
				}
				r=g.endvoting();
				if (r==-1) {
					System.out.println("It's a tie! , try again ");
				}else {
					System.out.println("Player"+r+" has been voted out");
				}
			}	
		}


	}
	
	public void printoutro(){
		System.out.println("Game Over");
		int win=g.winner();
		String w=win==0?"lost":"win";
		System.out.println("The Mafias have "+w);

		ArrayList<Mafia> finm=g.getmafias();

		for (int i=0;i<finm.size();i++) {
			System.out.print(finm.get(i)+ " , ");
		}System.out.println("were Mafias");


		ArrayList<Detective> find=g.getdets();

		for (int i=0;i<find.size();i++) {
			System.out.print(find.get(i)+ " , ");
		}System.out.println("were Detectives");

		ArrayList<Healer> finh=g.getheal();

		for (int i=0;i<finh.size();i++) {
			System.out.print(finh.get(i)+ " , ");
		}System.out.println("were Healers");

		ArrayList<Commoner> finc=g.getcom();

		for (int i=0;i<finc.size();i++) {
			System.out.print(finc.get(i)+ " , ");
		}System.out.println("were Commoners");
	}

}
