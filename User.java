import java.util.*;
public class User {
		static Game g;
		public static void setgame(Game g1) {
			g=g1;
		}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Welcome to Mafia");
		int n=0;
		n=Main.takeint("Enter number of players");
		while (n<6) {
			System.out.println("Please enter integer greater than equal to 6");
			n=Main.takeint("Enter number of players");
		}
		Main main=new Main(n);
		System.out.println("Choose a character");
		System.out.println("1) Mafia");
		System.out.println("2) Detective");
		System.out.println("3) Healer");
		System.out.println("4) Commoner");
		System.out.println("5) Assign Randomly");
		int ch;
		ch=Main.takeint("Choose a character");
		if (!(ch>=1 && ch<=5)) {
			System.out.println("Please choose a number betwen 1 and 5");
			ch=Main.takeint("Choose a character");
		}
		
		if (ch==5) {
			Random rand=new Random();
			ch=rand.nextInt(4)+1;
		}
		
		
		if (ch==1) {
			int [] res=g.asgntouser(new Mafia(-1));
			System.out.println("You are player" + res[0]);
			Mafia user=null;
			ArrayList<Mafia> selc=new ArrayList<Mafia>();
			selc=g.getmafias();
			for (int i=0;i<selc.size();i++) {
				if (selc.get(i).pnum==res[0]) {
					user=selc.get(i);
					break;
				}
			}
			System.out.print("You are a Mafia , other Mafias are : [");
			for (int i=1;i<res.length;i++) {
				System.out.print("Player"+res[i]+"  ");
			}System.out.println("]");
			int rounds=1;



			while (!g.endcondition()) {  
				
				main.printintro(rounds);
				
				
				//mafia choosing
				if (user.alive) {
				int t=0;
				t=Main.takeint("Choose you target");
				while (g.usertarget(t)!=0) {
					if (g.usertarget(t)==1) {
						System.out.println("Can't choose a dead player , try again");
						t=Main.takeint("Choose your target");
					}else {
						System.out.println("You can't kill your fellow Mafia ! ");
						t=Main.takeint("Choose your target");
					}
				}user.usertarget(t);
				
				}else {
					main.maftarget();
				}
				
				//detective testing
				int vcheck=main.dettest();

				//healers healing
				main.heal();
				
				//player died 
				main.playerdead();
				
				//voting phase
				main.uservoting(vcheck,user);
				
				System.out.println("--End of round" +rounds+ "--");
				rounds++;
			}
			//game over 
			main.printoutro();
			
		}
		if (ch==2) {
			int [] res=g.asgntouser(new Detective(-1));
			System.out.println("You are player" + res[0]);
			Detective user=null;
			ArrayList<Detective> selc=new ArrayList<Detective>();
			selc=g.getdets();
			for (int i=0;i<selc.size();i++) {
				if (selc.get(i).pnum==res[0]) {
					user=selc.get(i);
					break;
				}
			}
			System.out.print("You are a Detective , other Detectives are : [");
			for (int i=1;i<res.length;i++) {
				System.out.print("Player"+res[i]+"  ");
			}System.out.println("]");
			int rounds=1;



			while (!g.endcondition()) {  
				
				main.printintro(rounds);
				
				
				//mafia choosing
				main.maftarget();
				
				//detective testing
				int vcheck=-1;
				if (user.alive) {
					int t=0;
					t=Main.takeint("Choose a player to test:");
					while (g.usertesting(t)!=0) {
						if (g.usertarget(t)==1) {
							System.out.println("Can't test a dead player , try again");
							t=Main.takeint("Choose a player to test");
						}else {
							System.out.println("You can't test your fellow Detective ! ");
							t=Main.takeint("Choose a player to test");
						}
					}if (user.usertest(t)) {
						vcheck=t;
						System.out.println("Player"+vcheck + " was a mafia");
					}else {
						System.out.println("Player" + t + " was not a mafia");
					}
					
					}else {
						vcheck=main.dettest();
					}

				//healers healing
				main.heal();
				
				//player died 
				main.playerdead();
				
				//voting phase
				main.uservoting(vcheck,user);
				
				System.out.println("--End of round" +rounds+ "--");
				rounds++;
			}
			//game over 
			main.printoutro();
			
		}
		if (ch==3) {
			int [] res=g.asgntouser(new Healer(-1));
			System.out.println("You are player" + res[0]);
			Healer user=null;
			ArrayList<Healer> selc=new ArrayList<Healer>();
			selc=g.getheal();
			for (int i=0;i<selc.size();i++) {
				if (selc.get(i).pnum==res[0]) {
					user=selc.get(i);
					break;
				}
			}
			System.out.print("You are a Healer , other Healers are : [");
			for (int i=1;i<res.length;i++) {
				System.out.print("Player"+res[i]+"  ");
			}System.out.println("]");
			int rounds=1;



			while (!g.endcondition()) {  
				
				//this is a function 
				main.printintro(rounds);
				
				
				//mafia choosing
				main.maftarget();
				
				//detective testing
				int vcheck=main.dettest();
				
				//healers healing
				if (user.alive) {
					int t=0;
					t=Main.takeint("Choose a player to heal");
					while (g.userhealing(t)==-1) {
						
							System.out.println("Can't choose a dead player , try again");
							t=Main.takeint("Choose a player to heal");
						
					}
					user.userheal(t);
					
					}else {
						main.heal();
					}
					
				
				//player died 
				main.playerdead();
				
				//voting phase
				main.uservoting(vcheck,user);
				
				System.out.println("--End of round" +rounds+ "--");
				rounds++;
			}
			//game over 
			main.printoutro();
			
		}
		if (ch==4) {
			int [] res=g.asgntouser(new Commoner(-1));
			System.out.println("You are player" + res[0]);
			Commoner user=null;
			ArrayList<Commoner> selc=new ArrayList<Commoner>();
			selc=g.getcom();
			for (int i=0;i<selc.size();i++) {
				if (selc.get(i).pnum==res[0]) {
					user=selc.get(i);
					break;
				}
			}
			System.out.print("You are a Commoner ");
			
			int rounds=1;



			while (!g.endcondition()) {  
				
				//this is a function 
				main.printintro(rounds);
				
				
				//mafia choosing
				main.maftarget();
				
				//detective testing
				int vcheck=main.dettest();
				
				//healers healing
				main.heal();
					
				
				//player died 
				main.playerdead();
				
				//voting phase
				main.uservoting(vcheck,user);
				
				System.out.println("--End of round" +rounds+ "--");
				rounds++;
			}
			//game over 
			main.printoutro();
			
		}
		
	}

}
