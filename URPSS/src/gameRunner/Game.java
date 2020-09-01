/**
 * 
 */
package gameRunner;
//import java.util.*;

//import java.util.ArrayList;
//import java.util.Arrays;

//import Move.Move;
//import playersettings.Player;

//import Move.Move;
//import playersettings.Player;
/**
 * @author admin
 *
 */
public class Game {
	//private static final String DEPRECATION = "deprecation";

	//proves using experimentation that using random moves, 1/10 to tie, 9/20 to win, 9/20 to lose
	//also proves that random function is dumb
	/**
	 * @param args
	 */

	//public static void main(String[] args) {
		/*
		Player mob = new Player("Carmen", 6, 15, 0, 0, 0, 4,
				new ArrayList<Move>(Arrays.asList(new Move("Knife", "attack", 2, 7, 2),
						new Move("Protect", "protect", 0, 3, 2),
						new Move("Scissor", "attack", 1, 0, 1),
						new Move("PowerUp", "status", 0, 4, 1))), new ArrayList<Move>());
		
		Player Brokey = new Player("Brokey", 6, 15, 0, 0, 0, 4,
				new ArrayList<Move>(Arrays.asList(
						new Move("Knife", "attack", 2, 7, 2),
						new Move("Protect", "protect", 0, 3, 6),
						new Move("Scissor", "attack", 1, 0, 2),
						new Move("PowerUp", "status", 0, 4, 2))), new ArrayList<Move>());
						
		System.out.println(mob);
		System.out.println(Brokey);
		Brokey.useMove(new Move("Protect", "protect", 0, 3, 6));
		*/
		// TODO Auto-generated method stub
		//Player Me = new Player("Yolomep");
		//Me.printMoves();
		//Player Ai = new Player("Mob");
		//Ai.printMoves();
		/*
		Ai.newGame();
		Me.newGame();
		System.out.println(Me);
		//works hahahhahahaha
		while(true) {
			Move aiMove = Ai.useRand();
			Move myMove = Me.useRand();
			int bo = Me.useMove(myMove);
			int boa = Ai.useMove(aiMove);
			boolean aiLive = Ai.attacked(myMove, bo);
			boolean meLive = Me.attacked(aiMove, boa);
			System.out.println(Me);
			System.out.println(Ai);
			if(!aiLive && !meLive) {
				System.out.println("tie");
				break;
			}
			else if(!aiLive) {
				System.out.println("User Wins");
				break;
			}
			else if(!meLive) {
				System.out.println("Ai Wins");
				break;
			}
			System.out.println();
		}
		*/
		/*
		int wins = 0;
		int tie = 0;
		int loss = 0;
		int total = 10000000;
		for(int i = 0; i < total; i ++) {
			Me.tNG();
			Ai.tNG();
			while(true) {
				Move aiMove = Ai.useRand();
				Move myMove = Me.useRand();
				int bo = Me.TUM(myMove);
				int boa = Ai.TUM(aiMove);
				boolean aiLive = Ai.TAttack(myMove, bo);
				boolean meLive = Me.TAttack(aiMove, boa);
				if(!aiLive && !meLive) {
					tie++;
					break;
				}
				else if(!aiLive) {
					wins++;
					break;
				}
				else if(!meLive) {
					loss++;
					break;
				}
			}
		}
		System.out.println(tie);
		System.out.println(wins);
		System.out.println(loss);
		System.out.println(tie/((double) total));
		System.out.println(wins/(double) total);
		System.out.println(loss/(double)total);
		*/
	//}
	
	
}
