package gameRunner;
import java.util.*;
import java.util.Map.Entry;

import Move.Move;
import playersettings.Player;

import java.io.*;
public class FullGame {

	public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println("       .");
		System.out.println("  --- /|\\");
		System.out.println(" | T /(|)\\");
		System.out.println("=== / $|$ \\");
		System.out.println("   /...:...\\");
		System.out.println("T.J. CODES PRESENTS:");
		System.out.println();
		Thread.sleep(1000);
		Scanner in = new Scanner(System.in);
		System.out.println("URPSS v1.1.5");
		System.out.println("T.J. CODES");
		int choice = getInput(in, new int[] {1, 2}, "1 Start a New Game \n2 Continue From Save");
		Player player = null;
		int stage = 0;
		int[] storyMode = new int[4];
		int ranking = 1000;
		String[] randNames = new String[] {"Harry","Ross","Bruce","Chris",
                "Carolyn","Morgan",
                "Albert","Walker",
                "Randy","Reed",
                "Larry","Barnes",
                "Lois","Wilson",
                "Jesse","Campbell",
                "Ernest","Rogers",
                "Theresa","Patterson",
                "Henry","Simmons",
                "Michelle","Perry",
                "Frank","Brian",
                "Shirley", "John"};
		if(choice == 2) {
			try {
				Scanner file = new Scanner(new File("UrpssSave.in"));
				System.out.println("Getting Data ...");
				String name = file.next();
				int level = file.nextInt();
				int money = file.nextInt();
				int xp = file.nextInt();
				int nextxp = file.nextInt();
				int health = file.nextInt();
				ArrayList<Move> moves = new ArrayList<>();
				ArrayList<Move> allMoves = new ArrayList<>();
				int moveL = file.nextInt();
				int moveN = file.nextInt();
				for(int i = 0; i < moveN; i ++) {
					String mn = file.next();
					String mt = file.next();
					int dam = file.nextInt();
					int eff = file.nextInt();
					int lev = file.nextInt();
					moves.add(new Move(mn, mt, dam, eff, lev));
				}
				moveN = file.nextInt();
				for(int i = 0; i < moveN; i ++) {
					String mn = file.next();
					String mt = file.next();
					int dam = file.nextInt();
					int eff = file.nextInt();
					int lev = file.nextInt();
					allMoves.add(new Move(mn, mt, dam, eff, lev));
				}
				stage = file.nextInt();
				ranking = file.nextInt();
				for(int i = 0; i < 4; i++) {
					storyMode[i] = file.nextInt();
				}
				file.close();
				System.out.println("Generating Player ...");
				Thread.sleep(500);
				player = new Player(name, level, health, money, nextxp, xp, moveL, moves, allMoves);
			}
			catch(FileNotFoundException e) {
				System.out.println("Save file not found.");
				System.out.println("Creating New Game ...");
				System.out.println();
				Thread.sleep(500);
				choice = 1;
			}
			catch(Exception e) {
				System.out.println("Save file corrupted.");
				System.out.println("Creating New Game ...");
				System.out.println();
				Thread.sleep(500);
				choice = 1;
			}
		}
		if(choice == 1) {
			System.out.println("What is your name?");
			String name = in.nextLine();
            System.out.println("Generating Player ...");
            Thread.sleep(500);
            player = new Player(name);
		}
		/*
		for(int i = 0; i < 50; i++) {
			System.out.println("\b\r");
		}
		*/
		while(true) {
			System.out.println();
			System.out.println("\tMain Menu");
			int input = getInput(in, 0, 7, "1 Save and Quit\n2 New Battle\n3 Check Stats\n4 Move Menu\n5 Shop\n6 About\n7 Credits");
			/*
			for(int i = 0; i < 15; i ++) {
				System.out.println("\n");
			}
			System.out.println("\r");
			*/
			//----------------------------------------SAVE CODE: ====================================================
			if(input == 1) {
				System.out.println("\tSAVING ...\nDON'T EXIT THE PROGRAM");
				PrintWriter out = new PrintWriter(new File("UrpssSave.in"));
				out.println(player.getName());
				out.println(player.print1());
				String[] mText = player.getMoves();
				out.println(mText.length);
				for(String s : mText)
					out.println(s);
				mText = player.getMovesA();
				out.println(mText.length);
				for(String s : mText)
					out.println(s);
				out.println(stage);
				out.println(ranking);
				for(int i : storyMode) out.println(i);
				out.close();
				Thread.sleep(200);
				System.out.println();
				System.out.println("SUCCESSFULLY SAVED");
				System.out.print("ENDING PROGRAM ");
				for(int i = 0; i < 3; i ++) {
					Thread.sleep(300);
					System.out.print(".");
				}
				System.out.println("\n");
				break;
			}
			else if(input == 2) {
				System.out.println("Press 0 to go back");
				int input2 = getInput(in, 0, 4, "1 Quick Battle\n2 Ranked battle\n3 Training\n4 Story");
				if(input2 == 1) {
					Player Ai = new Player("BoB", 42, 50, 0, 0, 0, 4,
							new ArrayList<Move>(Arrays.asList(new Move("Sparky", "status", 0, 2, 5),
									new Move("Blessing", "status", 0, 6, 10),
									new Move("Gun", "hybrid", 1, 3, 20),
									new Move("PowerUp", "status", 0, 4, 10))), new ArrayList<Move>());
					playMatch(in, player, Ai, 1);
				}
				else if(input2 == 2) {
					System.out.println("Welcome to Ranked Matches");
					System.out.println();
					System.out.println("Generating your next opponent ...");
					String s = randNames[(int)(Math.random() * randNames.length)];
					Move[] choose = null;
					int can = 0;
					int hp = 3;
					int level = (int)(Math.random() * 5) + 1;
					if(ranking < 1200) {
						can = 3;
						choose = new Move[] {
								new Move("Scissors", "attack", 1, 0, (int)(Math.random() * 2) + 1),
								new Move("Protect", "protect", 0, 3),
								new Move("PowerUp", "status", 0, 4),
								new Move("Sparky", "status", 0, 2),
						};
					}
					else if(ranking < 1400) {
						can = 3;
						hp = 5;
						level = (int)(Math.random() * 5) + 7;
						choose = new Move[] {
								new Move("Scissors", "attack", 1, 0, (int)(Math.random() * 3) + 2),
								new Move("Protect", "protect", 0, 3, (int)(Math.random() * 4) + 2),
								new Move("PowerUp", "status", 0, 4, (int)(Math.random() * 2) + 1),
								new Move("Sparky", "status", 0, 2, (int)(Math.random() * 2) + 1),
								new Move("Knife", "attack", 1, 7, (int)(Math.random() * 1) + 1),
								new Move("Recover", "status", 0, 1),
								new Move("WideGuard", "protect", 0, 11),
						};
					}
					else if(ranking < 1600) {
						can = 4;
						hp = 30 + (int)(Math.random() * 10);
						level = (int)(Math.random() * 15) + 20;
						choose = new Move[] {
								new Move("Scissors", "attack", 1, 0, (int)(Math.random() * 5) + 5),
								new Move("Protect", "protect", 0, 3, (int)(Math.random() * 6) + 2),
								new Move("PowerUp", "status", 0, 4, (int)(Math.random() * 2) + 3),
								new Move("Sparky", "status", 0, 2, (int)(Math.random() * 2) + 1),
								new Move("Knife", "attack", 1, 7, (int)(Math.random() * 4) + 1),
								new Move("Recover", "status", 0, 1, (int)(Math.random() * 5) + 1),
								new Move("Gun", "hybrid", 1, 3, (int)(Math.random() * 5) + 1),
								new Move("Rest", "status", 0, 8),
								new Move("Blessing", "status", 0, 6),
						};
					}
					ArrayList<Move> chosmove = new ArrayList<>(Arrays.asList(getRandom(choose, can)));
					Player Ai = new Player(s, level, hp, 0, 0, 0, can, chosmove, new ArrayList<Move>());
					int won = playMatch(in, player, Ai, 0);
					if(won == 1) {
						ranking += (int)(Math.random() * 15) + 10;
					}
					else if(won == 0) {
						ranking += (int)(Math.random() * 10) - 5;
					}
					else {
						ranking -= (int)(Math.random() * 14) + 7;
					}
					if(ranking < 1000) ranking = 1000;
					System.out.println("Your new ranking: " + ranking);
					System.out.println();
					System.out.println("PRESS ENTER: ");
					in.nextLine();
				}
				else if(input2 == 3) {
					Player Ai = new Player("Trainer");
					if(player.getLevel() > 20) {
						System.out.println("\tCongradulations.\nYou have unlocked a new Trainer.\nWould you like to try it out?");
						boolean input3 = getInput(in);
						if(input3) {
							Ai = new Player("Trainer", 23, 25, 0, 0, 0, 4,
									new ArrayList<Move>(Arrays.asList(new Move("Sparky", "status", 0, 2, 3),
											new Move("Protect", "protect", 0, 3, 9),
											new Move("Gun", "hybrid", 1, 3, 7),
											new Move("PowerUp", "status", 0, 4, 8))), new ArrayList<Move>());
						}
					}
					playMatch(in, player, Ai, 0);
				}
				else if(input2 == 4) {
					//--------------------------------------------------STORY MODE-------------------------------------------
					System.out.println("STORY MODE:");
					if(stage == 0) {
						System.out.println("Welcome young player");
						System.out.println("You shall go on a journey to meet new people\nand become the ultimate master.");
						System.out.println("Now lets begin our first fight ...");
						System.out.println("But first, let me explain three moves.");
						System.out.println("Scissors does 1 damage for every level.");
						System.out.println("PowerUp increases the level\nof the next used move.\nIt is the only move that \"stacks\"");
						System.out.println("Protect protects 1 damage, but becareful\nusing protect on a powerUp wastes a turn");
						System.out.println("You are ready. Let's begin ");
						for(int i = 0; i < 3; i ++) {
							Thread.sleep(200);
							System.out.print(".");
						}
						System.out.println("\n");
						Thread.sleep(100);
						Player mob = new Player("Jeff");
						int won = playMatch(in, player, mob, 0);
						if(won == 1) {
							System.out.println("You have won your first match");
							System.out.println("\tCongradulations!");
							System.out.println("You are ready to meet the world.");
							System.out.println("But first, let's go back to the MAIN MENU.");
							System.out.println("PRESS ENTER: ");
							in.nextLine();
							stage++;
						}
						else 
							printLoss1(in);
						
					}
					else if(stage == 1) {
						System.out.println("Aye, this is a time of peril.");
						System.out.println("The world is failing, and an evil\nking has taken over the world.\nuse your skills to defeat this king.");
						System.out.println("and save the world.\nbegin in the bronze league\nand fight many enimies.");
						System.out.println("Go on and journey the world.");
						Thread.sleep(500);
						System.out.println();
						System.out.println("You set out to the streets.");
						System.out.println("\"Hey what ya doing?\" said a evil looking thug.");
						System.out.println("Fight me if ya wanna live.");
						Player mob = new Player("Low-class Thug");
						System.out.println();
						int won = playMatch(in, player, mob, 0);
						if(won == 1) {
							System.out.println("You beat the thug.");
							System.out.println("The thug seems to have more money in his pocket.");
							System.out.println("Do you want to steal his money?");
							boolean yes = getInput(in);
							if(yes) {
								System.out.println("You steal his money.\nThat's not a good idea.");
								if(Math.random() > 0.6) {
									System.out.println("You narrowly miss some police");
									System.out.println("You find 10 coins in the thug's pocket");
									System.out.println();
									Thread.sleep(300);
									player.levelUp(0, 10);
								}
								else {
									System.out.println("The police see you\nand you run away, dropping the money.");
									System.out.println("You couldn't pocket the money.");
								}
							}
							System.out.println("Go back to the MAIN MENU.");
							System.out.println("PRESS ENTER: ");
							in.nextLine();
							stage++;
						}
						else 
							printLoss1(in);
					}
					else if(stage >= 2 && stage < 5) {
						System.out.println("Be quick! Finish off these thugs!");
						Thread.sleep(500);
						System.out.println();
						Player mob = new Player("Low-class Thug");
						System.out.println();
						int won = playMatch(in, player, mob, 0);
						if(won == 1) {
							if(stage == 2) System.out.println("You beat a thug.");
							else if(stage == 3) System.out.println("You beat another thug");
							else if(stage == 4) System.out.println("You have won against all the thugs\nyou are now eligable to fight\nthe first bronze league boss.");
							System.out.println("Take this time to rest.");
							System.out.println("Go back to the MAIN MENU.");
							System.out.println("PRESS ENTER: ");
							in.nextLine();
							stage++;
						}
						else 
							printLoss1(in);
					}
					else if(stage == 5) {
						System.out.println("First boss! His name is Carmen.");
						System.out.println("Watch out for his knife attacks.");
						System.out.println("They are a sure kill.");
						Thread.sleep(500);
						System.out.println();
						Player mob = new Player("Carmen", 10, 15, 0, 0, 0, 4,
								new ArrayList<Move>(Arrays.asList(new Move("Knife", "attack", 1, 7, 2),
										new Move("Protect", "protect", 0, 3, 2),
										new Move("Scissor", "attack", 1, 0, 1),
										new Move("PowerUp", "status", 0, 4, 1))), new ArrayList<Move>());
						System.out.println();
						int won = playMatch(in, player, mob, 0);
						if(won == 1) {
							System.out.println("You have won against Carmen.\nNow go fight the boss, but first");
							System.out.println("take this time to rest.");
							System.out.println("Go back to the MAIN MENU.");
							System.out.println("PRESS ENTER: ");
							in.nextLine();
							stage++;
						}
						else 
							printLoss1(in);
					}
					else if(stage == 6) {
						System.out.println("\tBronze League Boss: Brokey.");
						System.out.println("He's brutal.");
						System.out.println("He makes sure you can't win.");
						System.out.println("His protect is very high,\nso be sure to stock up PowerUps");
						Thread.sleep(500);
						System.out.println();
						Player Brokey = new Player("Brokey", 12, 15, 0, 0, 0, 4,
								new ArrayList<Move>(Arrays.asList(
										new Move("Knife", "attack", 1, 7, 2),
										new Move("Protect", "protect", 0, 3, 6),
										new Move("Scissor", "attack", 1, 0, 2),
										new Move("PowerUp", "status", 0, 4, 2))), new ArrayList<Move>());
						System.out.println();
						int won = playMatch(in, player, Brokey, 0);
						if(won == 1) {
							if(storyMode[0] >= 1) {
								stage = storyMode[1];
								System.out.println("Great Job! Now you can go back to the Silver League.");
								System.out.println("First, take this time to rest.");
							}
							else {
								System.out.println("You have won against Brokey\nnow you are a Bronze champion.");
								System.out.println("But be aware, level up fast.");
								System.out.println("The crazy king will come after you.");
								System.out.println("Take this time to rest before the Silver League.");
								stage++;
							}
							System.out.println("Go back to the MAIN MENU.");
							System.out.println("PRESS ENTER: ");
							in.nextLine();
						}
						else 
							printLoss1(in);
					}
					else if(stage == 7) {
						System.out.println("Wow! You managed to become the bronze champion.");
						System.out.println("Great Job. Now you will face harder opponents.");
						System.out.println("You will also see more moves.");
						System.out.println("But be aware, losing means that you will have to rechallenge\nthe bronze league and beat Brokey again.");
						System.out.println("Becareful, some of THE CRAZY's aids are spying on you.");
						System.out.println("There's a man that want's to fight you. Go and beat him.");
						storyMode[0]++;
						System.out.println();
						Thread.sleep(500);
						Player mob = new Player("Man", 10, 15, 0, 0, 0, 4,
								new ArrayList<Move>(Arrays.asList(new Move("Knife", "attack", 2, 7, 2),
										new Move("Protect", "protect", 0, 3, 3),
										new Move("Scissor", "attack", 1, 0, 2),
										new Move("PowerUp", "status", 0, 4, 2))), new ArrayList<Move>());
						int won = playMatch(in, player, mob, 0);
						if(won == 1) {
							System.out.println("Nice Job! Now lets move on to the next match.\nFirst, take this time to rest.");
							System.out.println("Go back to the MAIN MENU.");
							System.out.println("PRESS ENTER: ");
							in.nextLine();
							stage++;
						}
						else if(won == 0) 
							printTie(in);
						else {
							System.out.println("\tYou have Lost ...\nThis means that you will need to fight the Bronze League.\n"
									+ "Don't worry, after you win you will go back to this stage.\nFirst, let's go back to the MAIN MENU.");
							System.out.println("Train your skills and you will succeed.");
							System.out.println("PRESS ENTER: ");
							in.nextLine();
							storyMode[1] = stage;
							stage = 5;
						}
					}
					else if(stage == 8) {
						System.out.println("Hew! You made it pretty far.");
						System.out.println("I assume you are already above level 10\nso you must have already checked out the shop");
						System.out.println("Getting new moves is very important.");
						if(player.getLevel() < 10) {
							System.out.println(player.getName() + ": Ah, I'm not at level 10 yet.");
							System.out.println("Wait ... You're saying that you're not at level 10 yet?");
							System.out.println("Well better level up quickly!");
							System.out.println("It's a miracle you got this far.");
						}
						System.out.println("Well congrats and good luck!");
						System.out.println();
						System.out.println("A strange man comes near you.\nHe holds a special weapon as he walks near you.\nYou prepare for combat.");
						Thread.sleep(500);
						Player mob = new Player("Strange Man", 11, 13, 0, 0, 0, 3,
								new ArrayList<Move>(Arrays.asList(new Move("Gun", "hybrid", 1, 3, 2),
										new Move("Protect", "protect", 0, 3, 4),
										new Move("PowerUp", "status", 0, 4, 4))), new ArrayList<Move>());
						int won = playMatch(in, player, mob, 0);
						if(won == 1) {
							System.out.println("Nice Job! Now lets move on to the next match.\nFirst, take this time to rest.");
							System.out.println("Go back to the MAIN MENU.");
							System.out.println("PRESS ENTER: ");
							in.nextLine();
							stage++;
						}
						else if(won == 0) 
							printTie(in);
						else {
							System.out.println("\tYou have Lost ...\nThis means that you will need to fight the Bronze League.\n"
									+ "Don't worry, after you win you will go back to this stage.\nFirst, let's go back to the MAIN MENU.");
							System.out.println("Train your skills and you will succeed.");
							System.out.println("PRESS ENTER: ");
							in.nextLine();
							storyMode[1] = stage;
							stage = 5;
						}
					}
					else if(stage >= 9 && stage <= 13) {
						System.out.println("It's another mob battling session!");
						System.out.println("Grid up the xp!");
						if(stage == 10) System.out.println("You got a couple more mobs to go!");
						else if(stage == 11) System.out.println("You got some mobs left to beat!");
						else if(stage == 12) System.out.println("Two more mobs left!");
						else if(stage == 13) System.out.println("One more to go.");
						Move[] choose = new Move[] {
								new Move("Scissors", "attack", 1, 0, (int)(Math.random() * 3) + 3),
								new Move("Protect", "protect", 0, 3, (int)(Math.random() * 4) + 2),
								new Move("PowerUp", "status", 0, 4, (int)(Math.random() * 2) + 4),
								new Move("Sparky", "status", 0, 2, (int)(Math.random()) + 3),
								new Move("Knife", "attack", 1, 7, 4),
								new Move("Recover", "status", 0, 1, 4),
						};
						ArrayList<Move> chosmove = new ArrayList<>(Arrays.asList(getRandom(choose, 4)));
						Player Mob = new Player("Free Xp Mob", 13, 17, 0, 0, 0, 4, chosmove, new ArrayList<Move>());
						int won = playMatch(in, player, Mob, 0);
						if(won == 1) {
							if(stage == 13) {
								System.out.println("Nice Job! Your next match will be against the Silver leader's aide.\nTake some time to rest.");
							}
							else {
								System.out.println("Great Job! Now keep going!\nTake some time to rest.");
							}
							System.out.println("Go back to the MAIN MENU.");
							System.out.println("PRESS ENTER: ");
							in.nextLine();
							stage++;
						}
						else if(won == 0) 
							printTie(in);
						else {
							System.out.println("\tYou have Lost ...\nThis means that you will need to fight the Bronze League.\n"
									+ "Don't worry, after you win you will go back to this stage.\nFirst, let's go back to the MAIN MENU.");
							System.out.println("Train your skills and you will succeed.");
							System.out.println("PRESS ENTER: ");
							in.nextLine();
							storyMode[1] = stage;
							stage = 5;
						}
					}
					else if(stage == 14) {
						System.out.println("It's Drampa! Beat him and you can fight the Silver League boss.");
						System.out.println("He loves to use boom, so becareful.");
						System.out.println("In fact, I think his boom is level 9.");
						System.out.println("Or was it level 8? His moveset's peculiar.");
						Player Drampa = new Player("Drampa", 20, 24, 0, 0, 0, 3,
								new ArrayList<Move>(Arrays.asList(new Move("Boom", "attack", 3, 0, 9),
										new Move("Boom", "attack", 3, 0, 8),
										new Move("PowerUp", "status", 0, 4, 4))), new ArrayList<Move>());
						int won = playMatch(in, player, Drampa, 0);
						if(won == 1) {
							System.out.println("Nice Job! Your next match will be against the Silver leader.\nTake some time to rest.");
							System.out.println("Go back to the MAIN MENU.");
							System.out.println("PRESS ENTER: ");
							in.nextLine();
							stage++;
						}
						else if(won == 0) 
							printTie(in);
						else {
							if(storyMode[0] >= 2) {
								System.out.println("Well, this means that you will need to try again.");
								System.out.println("Go back to the Main Menu.");
								System.out.println("PRESS ENTER: ");
								in.nextLine();
								continue;
							}
							System.out.println("\tYou have Lost ...\nThis means that you will need to fight the Bronze League.\n"
									+ "Don't worry, after you win you will go back to this stage.\nFirst, let's go back to the MAIN MENU.");
							System.out.println("Train your skills and you will succeed.");
							System.out.println("PRESS ENTER: ");
							in.nextLine();
							storyMode[1] = stage;
							stage = 5;
						}
					}
					else if(stage == 15) {
						System.out.println("The Silver League boss is Mastly.");
						System.out.println("He loves to use boom too, so becareful.");
						System.out.println("I think he also has sparky.");
						Player Mastly = new Player("Mastly", 24, 26, 0, 0, 0, 4,
								new ArrayList<Move>(Arrays.asList(new Move("Boom", "attack", 3, 0, 10),
										new Move("Boom", "attack", 3, 0, 10),
										new Move("PowerUp", "status", 0, 4, 5),
										new Move("Sparky", "status", 0, 2, 4))), new ArrayList<Move>());
						int won = playMatch(in, player, Mastly, 0);
						if(won == 1) {
							if(storyMode[0] >= 2) {
								System.out.println("Nice job! Now you can go back to where you were before.");
								System.out.println("But first, go back to the Main Menu.");
								System.out.println("PRESS ENTER: ");
								in.nextLine();
								stage = storyMode[1];
								continue;
							}
							System.out.println("Hew! You did it. You beat the silver league.\nNow you can go to the gold league.\nYou're on step closer to fighting The CRAZY King.");
							System.out.println("Here's some extra money.\nI deposited the money in your profile.");
							player.levelUp(0, 40);
							System.out.println("First, go and upgrade your moves.");
							System.out.println("Go back to the MAIN MENU.");
							System.out.println("PRESS ENTER: ");
							in.nextLine();
							stage++;
							storyMode[0]++;
						}
						else if(won == 0) 
							printTie(in);
						
						else {
							if(storyMode[0] >= 2) {
								System.out.println("Ah ... You can try again.");
								System.out.println("PRESS ENTER: ");
								in.nextLine();
								continue;
							}
							System.out.println("\tYou have Lost ...\nThis means that you will need to fight the Bronze League.\n"
									+ "Don't worry, after you win you will go back to this stage.\nFirst, let's go back to the MAIN MENU.");
							System.out.println("Train your skills and you will succeed.");
							System.out.println("PRESS ENTER: ");
							in.nextLine();
							storyMode[1] = stage;
							stage = 5;
						}
					}
					else if(stage == 16) {
						if(storyMode[2] == 0) {
							System.out.println("Wow! Nice. You're now eligible to take on the Gold league.");
							System.out.println("Why don't I explain some perks first?");
							System.out.println("Do you want to here them?");
							boolean hear = getInput(in);
							if(hear) {
								System.out.println("Ok so basically, you will get better moves,\nand when you win a match, you can get extra money.");
								System.out.println("Now you can be even with your opponents.");
								System.out.println("Also, you get a gold league discount in the shop.");
								System.out.println("It's mostly so you can increase your maxHp.");
								System.out.println("Also, I'm going to give you one extra Hp\nfor making it this far and listening to me.");
								player.incMaxHp();
							}
							System.out.println();
							Thread.sleep(300);
							System.out.println("Now before you go, remember, since The CRAZY King is spying on you,\nlosing means refighting the previous league's bosses.");
							System.out.println("Now test your skills against this Gold League contestant.");
							storyMode[2]++;
						}
						else {
							System.out.println("Well try to win against him again.");
							System.out.println("It's that Gold League contestant.");
						}
						Player mob = new Player("Gold player", 30, 28, 0, 0, 0, 4,
								new ArrayList<Move>(Arrays.asList(new Move("R&S", "hybrid", 1, 4, 3),
										new Move("Gun", "hybrid", 1, 3, 9),
										new Move("PowerUp", "status", 0, 4, 5),
										new Move("Sparky", "status", 0, 2, 4))), new ArrayList<Move>());
						int won = playMatch(in, player, mob, 0);
						if(won == 1) {
							System.out.println("Good job. Now you are ready to continue on.");
							System.out.println("Go back to the MAIN MENU.");
							System.out.println("PRESS ENTER: ");
							in.nextLine();
							stage++;
						}
						else if(won == 0) 
							printTie(in);
						
						else {
							System.out.println("\tYou have Lost ...\nThis means that you will need to fight the Silver League.\n"
									+ "Don't worry, after you win you will go back to this stage.\nFirst, let's go back to the MAIN MENU.");
							System.out.println("Train your skills and you will succeed.");
							System.out.println("PRESS ENTER: ");
							in.nextLine();
							storyMode[1] = stage;
							stage = 14;
						}
						
					}
					else if(stage >= 17 && stage <= 19) {
						if(stage == 17) {
							System.out.println("Time to start the grind, no?");
							System.out.println("Now many gold players will come and try to kill you.");
							System.out.println("Beat them all to get closer to the Gold boss");
						}
						else {
							System.out.println("Go Go Go! Beat them all!");
						}
						Move[] choose = new Move[] {
								new Move("Scissors", "attack", 1, 0, (int)(Math.random() * 2) + 13),
								new Move("Protect", "protect", 0, 3, (int)(Math.random() * 6) + 5),
								new Move("PowerUp", "status", 0, 4, (int)(Math.random() * 2) + 4),
								new Move("Sparky", "status", 0, 2, (int)(Math.random() * 2) + 3),
								new Move("Knife", "attack", 1, 7, (int)(Math.random() * 2) + 3),
								new Move("Recover", "status", 0, 1, (int)(Math.random() * 7) + 4),
								new Move("Boom", "attack", 3, 0, (int)(Math.random() * 7) + 4),
								new Move("Healing", "status", 0, 10, 3)
						};
						ArrayList<Move> chosmove = new ArrayList<>(Arrays.asList(getRandom(choose, 4)));
						while(!notAllStatus(chosmove)) chosmove = new ArrayList<>(Arrays.asList(getRandom(choose, 4)));
						Player Mob = new Player("Gold League Contestant", 35, 31, 0, 0, 0, 4, chosmove, new ArrayList<Move>());
						int won = playMatch(in, player, Mob, 0);
						if(won == 1) {
							System.out.println("Good job. Now go fight your next opponent.");
							System.out.println("Go back to the MAIN MENU.");
							System.out.println("PRESS ENTER: ");
							in.nextLine();
							stage++;
						}
						else if(won == 0) 
							printTie(in);
						
						else {
							System.out.println("\tYou have Lost ...\nThis means that you will need to fight the Silver League.\n"
									+ "Don't worry, after you win you will go back to this stage.\nFirst, let's go back to the MAIN MENU.");
							System.out.println("Train your skills and you will succeed.");
							System.out.println("PRESS ENTER: ");
							in.nextLine();
							storyMode[1] = stage;
							stage = 14;
						}
					}
					else if(stage == 20) {
						System.out.println("Hey! I think its time you made a decision.");
						System.out.println("You've gone very far, but I see that the future is dim.");
						System.out.println("You're going to require some help.");
						int input3 = getInput(in, new int[] {0, 1, 2}, "Do you want some extra Xp or more money?\n0 Give me some time to think about it.\n1 I want some extra Xp please.\n2 I need more money.");
						if(input3 == 0) {
							System.out.println("Ok. Take this time to go back to the Main Menu\nand think about your decision.");
							System.out.println("PRESS ENTER: ");
							in.nextLine();
							continue;
						}
						else if(input3 == 1) {
							System.out.println("You have recieved 200xp.");
							player.levelUp(200, 0);
						}
						else if(input3 == 2) {
							System.out.println("You have recieved 75 coins.");
							player.levelUp(0, 75);
						}
						System.out.println("Very nice, you are now prepared to fight your next opponent.");
						System.out.println("Go back to the main menu to rest.");
						System.out.println("PRESS ENTER: ");
						in.nextLine();
						stage++;
					}
					else if(stage == 21) {
						System.out.println("Yo! It's time for you to take on the Gold League.");
						System.out.println("Your opponent is Jerald.");
						System.out.println("I don't have much information about him.");
						System.out.println("However, I know he has a lot of Hp.");
						System.out.println("I think he also has a new move.");
						System.out.println();
						Thread.sleep(100);
						Player Jerald = new Player("Jerald", 60, 40, 0, 0, 0, 4,
								new ArrayList<Move>(Arrays.asList(new Move("Rest", "status", 0, 8),
										new Move("Gun", "hybrid", 1, 3, 8),
										new Move("PowerUp", "status", 0, 4, 9),
										new Move("WideGuard", "protect", 0, 11, 7))), new ArrayList<Move>());
						int won = playMatch(in, player, Jerald, 0);
						if(won == 1) {
							System.out.println("It's the Gold Leauge boss next.\nTake a break.");
							System.out.println("Go back to the MAIN MENU.");
							System.out.println("PRESS ENTER: ");
							in.nextLine();
							stage++;
						}
						else if(won == 0) 
							printTie(in);
						
						else {
							System.out.println("\tYou have Lost ...\nThis means that you will need to fight the Silver League.\n"
									+ "Don't worry, after you win you will go back to this stage.\nFirst, let's go back to the MAIN MENU.");
							System.out.println("Train your skills and you will succeed.");
							System.out.println("PRESS ENTER: ");
							in.nextLine();
							storyMode[1] = stage;
							stage = 14;
						}
					}
					else if(stage == 22) {
						System.out.println("It's the Gold League boss.");
						System.out.println("His name is Surkae.");
						System.out.println("That's all I know.");
						System.out.println();
						Thread.sleep(100);
						Player Surkae = new Player("Surkae", 50, 50, 0, 0, 0, 5,
								new ArrayList<Move>(Arrays.asList(new Move("R&S", "hybrid", 1, 4, 13),
										new Move("GarbageCan", "status", 0, 9),
										new Move("PowerUp", "status", 0, 4, 9),
										new Move("ThunderPunch", "hybrid", 2, 2, 8),
										new Move("Sparky", "status", 0, 2, 12))), new ArrayList<Move>());
						int won = playMatch(in, player, Surkae, 0);
						if(won == 1) {
							System.out.println("Oh boy. You did it. You beat the Gold League.");
							System.out.println("You have surivived the first arc.");
							System.out.println("You have come in contact with many new moves and challenging opponents.");
							System.out.println("You will soon fight The Crazy King.");
							System.out.println("To celebrate your victory,");
							System.out.println("Let's go back to the MAIN MENU.");
							System.out.println("You will need to enter a new realm and take on the intermediate leagues.");
							System.out.println("PRESS ENTER: ");
							in.nextLine();
							stage++;
						}
						else if(won == 0) 
							printTie(in);
						
						else {
							System.out.println("\tYou have Lost ...\nThis means that you will need to fight the Silver League.\n"
									+ "Don't worry, after you win you will go back to this stage.\nFirst, let's go back to the MAIN MENU.");
							System.out.println("Train your skills and you will succeed.");
							System.out.println("PRESS ENTER: ");
							in.nextLine();
							storyMode[1] = stage;
							stage = 14;
						}
					}
					
					//-------------------------------------------END OF STORY MODE=================================================
				}
			}
			else if(input == 3) {
				player.print();
				System.out.println("Badges: ");
				if(storyMode[0] >= 1) System.out.print("Beginner leagues: Bronze badge .(B). ");
				if(storyMode[0] >= 2) System.out.print("Silver badge .(S). ");
				if(storyMode[0] >= 3) System.out.print("Gold badge .(G). ");
				if(storyMode[0] >= 4) System.out.print("\nIntermediate leagues: Sapphire badge *(<S>)* ");
				if(storyMode[0] >= 5) System.out.print("Ruby badge *(<R>)* ");
				if(storyMode[0] >= 6) System.out.print("Emerald badge *(<E>)* ");
				/*
				 * Special Leagues: 
				 * Amethyst
				 * Lapis Lazuli
				 * Topaz
				 * 
				 * Pro Leagues:
				 * Expert
				 * Veteran
				 * Master
				 * 
				 * Divine Leagues:
				 * Gold Quartz
				 * Sage Jade
				 * Crimson Coral
				 * 
				 * Ultimate Leagues:
				 * Pearl
				 * Platinum
				 * Diamond
				 * 
				 * One Step Arc:
				 * Half-Step
				 * Quarter-Step
				 * The Gate
				 * 
				 * Realm of Crazy:
				 * The Keep
				 * The Throne Room
				 * The Throne
				 * 
				 * Fight with Crazy
				 * Fight with ???
				 * ???
				 * ???
				 */
				System.out.println();
				System.out.println("PRESS ENTER: ");
				in.nextLine();
				System.out.println();
				System.out.println("Moves:");
				System.out.println();
				player.printAllMoves();
				System.out.println("Current Moves: ");
				player.printMoves();
				System.out.println("PRESS ENTER: ");
				in.nextLine();
				Thread.sleep(50);
			}
			else if(input == 5) {
				System.out.println("SHOP: ");
				if(player.getLevel() >= 10) {
					shop(in, player, storyMode);
					while(player.getAllMoves().size() == 0 || !notAllStatus((ArrayList<Move>) player.getAllMoves())) {
						System.out.println("Please go back to the shop and buy more moves.");
						shop(in, player, storyMode);
					}
				}
				else {
					System.out.println("Sorry, shop is closed");
					System.out.println("Unlock the shop at level 10");
					System.out.println("PRESS ENTER: ");
					in.nextLine();
				}
				Thread.sleep(50);
			}
			else if(input == 6) {
				System.out.println("idk how to play this game");
				System.out.println("try stuff out yourself");
				System.out.println("JK. Just fight matches");
				System.out.println("Fiddle around with trainer,\nif you think you are ready,\ntry story mode. There should\nbe an explaination on how to play.");
				System.out.println("Max Moves is the maximum number\nof moves you can hold");
				System.out.println("That's all you need to know");
				System.out.println("Also, hint: when you unlock the shop,");
				System.out.println("you can pretend to buy a move to check out its stats.");
				System.out.println("The shop owner isn't going to tell you anything\nabout the move unless you show interest in it.");
				System.out.println("Also, don't rage when you misclick because effects don't stack.");
				System.out.println("PRESS ENTER: ");
				in.nextLine();
				Thread.sleep(50);
			}
			else if(input == 7) {
				//idk why I have this as elif. Probably so I can add more. 
				System.out.println("\tURPSS");
				System.out.println("    Version ß 0.3");
				System.out.println("DEVELOPED BY T.J. CODES");
				System.out.println("About this game: \nCredits to RL for idea\n");
				System.out.println("PRESS ENTER: ");
				in.nextLine();
				Thread.sleep(50);
			}
			else if(input == 4) {
				System.out.println("MOVE MENU: \n\tWelcome to the Move Menu.\nHow may I help you?");
				int input6 = -1;
				while(input6 != 0) {
					input6 = getInput(in, 0, 2, "0 Go Back\n1 Change Moves\n2 Level Up a Move");
					if(input6 == 1) {
						System.out.println("Pick the Move you want\nto add or change.\n");
						System.out.println("ALL MOVES: ");
						List<Move> moves = player.getAllMoves();
						String s = "";
						int i = 1;
						for(Move mve : moves) {
							s += i + " " + mve.getName() + "\n";
							i++;
						}
						s += "Pick a Move Number, 0 to go back";
						int input4 = getInput(in, 0, moves.size(), s);
						if(input4 == 0) {
							continue;
						}
						Move myMove = player.getMoveA(input4 - 1);
						List<Move> currentMove = player.getMoves2();
						if(currentMove.size() >= player.getMax()) {
							String s2 = "";
							System.out.println("Pick the move you want to swap.");
							i = 1;
							for(Move mve : currentMove) {
								s2 += i + " " + mve.getName() + "\n";
								i++;
							}
							s2 += "Pick a Move Number, 0 to go back";
							int input5 = getInput(in, 0, currentMove.size(), s2);
							if(input5 == 0) continue;
							currentMove.set(input5 - 1, myMove);
						}
						else {
							player.addMove(myMove);
						}
					}
					else if(input6 == 2) {
						System.out.println("Which move do you want to level up?");
						System.out.println("You have " + player.getMoney() + " coins");
						System.out.println("\tMoves:");
						List<Move> moves = player.getMoves2();
						String s2 = "";
						int i = 1;
						for(Move mve : moves) {
							s2 += i + " " + mve.getName() + " Level: " + mve.getLevel() + " Cost: " + mve.getCost() + "\n";
							i++;
						}
						s2 += "\nPick a Move Number, 0 to go back";
						int input5 = getInput(in, 0, moves.size(), s2);
						if(input5 == 0) continue;
						System.out.println("Are you sure you want\nto level up this move?\n");
						Move picked = moves.get(input5 - 1);
						moves.get(input5 - 1).print();
						if(getInput(in)) {
							int previous = picked.getCost();
							Move copyp = picked.clone();
							if(picked.tryLevel(player.getMoney())) {
								System.out.println("Move successfully leveled up.");
								picked.print();
								//too bad had to do it in O(n) time rip. So much for customization
								for(Move m : player.getAllMoves()) {
									if(m.equals(copyp)) {
										m.tryLevel(player.getMoney());
										break;
									}
								}
								player.levelUp(0, -previous);
								player.print();
							}
							else {
								System.out.println("Sorry, you're too broke");
								System.out.println("Try again when you have more money");
								System.out.println("Returning ...");
								System.out.println();
							}
						}
						else {
							System.out.println("Ok then ...");
							System.out.println();
						}
					}
				}
				
			}
		}
		in.close();
		System.out.println("Successfully exited the game");
		System.out.println("\t...");
	}
	
	private static boolean notAllStatus(ArrayList<Move> chosmove) {
		// TODO Auto-generated method stub
		for(Move m : chosmove) {
			if(m.getType().equals("attack")) return true;
			if(m.getType().equals("hybrid")) return true;
		}
		return false;
	}

	private static void shop(Scanner in, Player player, int[] storyMode) {
		// TODO Auto-generated method stub
		Map<Move, Integer> canBuy = new HashMap<>();
		//changed the canBuy adding inside while loop because of sell function.
		//now its super slow
		//I hate this spaghetti code
		while(true) {
			if(player.getLevel() >= 10) {
				canBuy.put(new Move("Scissors", "attack", 1, 0), 10);
				canBuy.put(new Move("Protect", "protect", 0, 3), 10);
				canBuy.put(new Move("PowerUp", "status", 0, 4), 10);
				canBuy.put(new Move("DScissors", "attack", 2, 0), 25);
				canBuy.put(new Move("Boom", "attack", 3, 0), 45);
		    }
			if(player.getLevel() >= 20) {
				canBuy.put(new Move("Knife", "attack", 1, 7), 60);
				canBuy.put(new Move("Sparky", "status", 0, 2), 100);
			}
			if(player.getLevel() >= 30) {
				canBuy.put(new Move("Recover", "status", 0, 1), 70);
				canBuy.put(new Move("Gun", "hybrid", 1, 3), 90);
			}
			if(player.getLevel() >= 40) {
				canBuy.put(new Move("Rest", "status", 0, 8), 120);
				canBuy.put(new Move("Healing", "status", 0, 10), 100);
			}
			if(player.getLevel() >= 50) {
				canBuy.put(new Move("Blessing", "status", 0, 6), 180);
				canBuy.put(new Move("R&S", "hybrid", 1, 4), 160);
			}
			if(player.getLevel() >= 80) {
				canBuy.put(new Move("ThunderPunch", "hybrid", 2, 2), 250);
				canBuy.put(new Move("GarbageCan", "status", 0, 9), 200);
			}
			System.out.println("What do you want to do today?");
			//lol old security code
			System.out.println("You have " + player.print1().split(" ")[1] + " coins.");
			List<Move> can = player.getAllMoves();
			for(Move move : can) 
				//rip have to manually do this.
				//if(canBuy.containsKey(move)) canBuy.remove(move);
				//move.print();
				//finally this works sorry people if you are reading this. This bug took me so long to fix.
				if(containsKey(canBuy, move)) canBuy.entrySet().removeIf(e -> e.getKey().equals(move));
			
			int input = getInput(in, new int[] {0, 1, 2}, "0 Leave\n1 Buy\n2 Sell");
			if(input == 0) break;
			else if(input == 1){
				//enter shop to buy
				System.out.println("\tBuy: ");
				String dis = "What move do you want to buy?\n";
				int i = 1;
				for(Entry<Move, Integer> ent : canBuy.entrySet()) {
					dis += i + " Move: " + ent.getKey().getName() + " cost: " + ent.getValue() + "\n";
					i++;
				}
				dis += i + " New Move slot: " + 125*player.getMax() + "\n";
				i++;
				if(storyMode[0] >= 2)
					dis += i + " Max Hp +1: " + 200*player.getMaxHp() + "\n";
				else 
					dis += i + " Max Hp +1: " + 250*player.getMaxHp() + "\n";
				
				int input2 = getInput(in, 0, canBuy.size() + 2, dis);
				if(input2 == 0) continue;
				else if(input2 == canBuy.size() + 1) {
					System.out.println("Do you really want to increase your Move Size by 1?");
					boolean want = getInput(in);
					if(want) {
						if(125*player.getMax() <= player.getMoney()) {
							player.levelUp(0, -125*player.getMax());
							player.incMaxMove();
						}
						else {
							System.out.println("Too bad. You're broke.");
							System.out.println("Come back when you have more money.");
							System.out.println();
						}
					}
					else {
						System.out.println("Ok then ...");
						System.out.println();
					}
					continue;
				}
				else if(input2 == canBuy.size() + 2) {
					System.out.println("Do you really want to increase your Max Hp by 1?");
					boolean want = getInput(in);
					if(want) {
						if(250*player.getMaxHp() <= player.getMoney() && storyMode[0] < 2) {
							player.levelUp(0, -250*player.getMaxHp());
							player.incMaxHp();
						}
						else if(200*player.getMaxHp() <= player.getMoney() && storyMode[0] >= 2) {
							player.levelUp(0, -200*player.getMaxHp());
							player.incMaxHp();
						}
						else {
							System.out.println("Too bad. You're broke.");
							System.out.println("Come back when you have more money.");
							System.out.println();
						}
					}
					else {
						System.out.println("Ok then ...");
						System.out.println();
					}
					continue;
				}
				i = 1;
				Move bmove = null;
				for(Move mve : canBuy.keySet()) {
					if(i == input2) bmove = mve;
					i++;
				}
				System.out.println("Do you want to buy this move?");
				System.out.println();
				bmove.print();
				boolean want = getInput(in);
				if(want) {
					if(canBuy.get(bmove) <= player.getMoney()) {
						player.levelUp(0, -canBuy.get(bmove));
						player.addAllMoves(bmove);
					}
					else {
						System.out.println("Too bad. You're broke.");
						System.out.println("Come back when you have more money.");
						System.out.println();
					}
				}
				else {
					System.out.println("Ok then ...");
					System.out.println();
				}
			}
			else if(input == 2) {
				System.out.println("\tSELL: ");
				String s = "What do you want to sell?\n0 to go back\n";
				List<Move> allMoves = player.getAllMoves();
				List<Move> onHand = player.getMoves2();
				for(int i = 1; i <= allMoves.size(); i ++) {
					s += i + " " + allMoves.get(i - 1).getName() + " sell for: " + allMoves.get(i - 1).getCost()*5/7 + "\n";
				}
				
				int input2 = getInput(in, 0, allMoves.size(), s);
				if(input2 == 0) continue;
				else {
					System.out.println("Are you sure you want to sell this move?");
					Move sell = allMoves.get(input2 - 1);
					sell.print();
					System.out.println("You will be able to sell this for: " + sell.getCost()*5/7);
					boolean want = getInput(in);
					if(want) {
						onHand.remove(sell);
						allMoves.remove(sell);
						player.levelUp(0, sell.getCost()*5/7);
						System.out.println("Thank you for doing business.");
					}
					else {
						System.out.println("Ok then ...");
					}
				}
			}
		}
	}
    private static boolean containsKey(Map<Move, Integer> canBuy, Move move) {
		// TODO Auto-generated method stub
    	for(Move move1 : canBuy.keySet()) {
    		//move1.print();
    		if(move1.equals(move)) return true;
    	}
    	
		return false;
	}

	//pretty much useless now
	public static String getInput(Scanner in, String[] type, String text) {
		System.out.println(text);
		String input = in.nextLine();
		while(!check(input, type)) {
			System.out.println("This isn't valid input. Try again.");
			System.out.println(text);
			input = in.nextLine();
		}
		return input;
	}
	//also useless
	public static boolean getInput(Scanner in) {
		System.out.println("Yes or No?");
		String input = in.nextLine();
		String[] type = new String[] {"Yes", "No", "Y", "N", "n", "y", "YES", "NO", "yeah", "YEH", "YEAH", "Yeah", "Ya", "ya", "Nope", "nope", "Ye", "no", "yes"};
		while(!check(input, type)) {
			System.out.println("This isn't valid input. Try again.");
			System.out.println("Yes or No?");
			input = in.nextLine();
		}
		type = new String[] {"No", "N", "n", "NO", "nope", "no", "Nope"};
		if(check(input, type)) return false;
		return true;
	}
	//now both functions are safe lol, had to sacrifice nextInt() rip
	public static int getInput(Scanner in, int[] type, String text) {
		System.out.println(text);
		int input;
		while(true) {
			String s = in.next();
			try {
		        input = Integer.parseInt(s);
		    } catch (final NumberFormatException e) {
		    	System.out.println("This isn't valid input. Try again.");
				System.out.println(text);
				continue;
		    }
			if(check(input, type)) break;
			System.out.println("This isn't valid input. Try again.");
			System.out.println(text);
		}
		in.nextLine();
		return input;
	}
	//now both functions are safe lol, had to sacrifice nextInt() rip
	public static int getInput(Scanner in, int min, int max, String text) {
		System.out.println(text);
		int input;
		while(true) {
			String s = in.next();
			try {
		        input = Integer.parseInt(s);
		    } catch (final NumberFormatException e) {
		    	System.out.println("This isn't valid input. Try again.");
				System.out.println(text);
				continue;
		    }
			if(input >= min && input <= max) break;
			System.out.println("This isn't valid input. Try again.");
			System.out.println(text);
		}
		in.nextLine();
		return input;
	}
	
	public static boolean check(String toCheck, String[] avalible) {
		for(String s: avalible) 
			if(s.equals(toCheck)) return true;
		
		return false;
	}
	
	public static boolean check(int toCheck, int[] avalible) {
		for(int s: avalible) 
			if(s == toCheck) return true;
		
		return false;
	}
	public static boolean check(Move toCheck, Move[] avalible) {
		for(Move s: avalible) 
			if(s.equals(toCheck)) return true;
		
		return false;
	}
	//returns if you won.
	public static int playMatch(Scanner in, Player player, Player Ai, int AiLevel) throws InterruptedException {
		Ai.newGame();
		player.newGame();
		System.out.println(Ai);
		while(true) {
			Move myMove = null;
			int input3 = getInput(in, 0, 4, "What do you do?\n1 Pick Move\n2 Check Moves\n3 Check Status\n4 Forfeit");
			if(input3 == 1) {
				String[] moves = player.getMoves();
				String s = "";
				int i = 1;
				for(String mve : moves) {
					s += i + " " + mve.split(" ")[0] + "\n";
					i++;
				}
				s += "Pick a Move Number, 0 to go back";
				int input4 = getInput(in, -1, moves.length, s);
				if(input4 == 0) {
					continue;
				}
				myMove = player.getMove(input4 - 1);
			}
			else if(input3 == 2) {
				player.printMoves();
				System.out.println("PRESS ENTER: ");
				in.nextLine();
				continue;
			}
			else if(input3 == 3) {
				System.out.println(player);
				continue;
			}
			else if(input3 == 4) {
				System.out.println("\tYou Lose");
				System.out.println("You get a consolation prize");
				int x = 1 + (int)(Math.random() * 5);
				int m = 1 + (int)(Math.random() * 2);
				String coin = " coin";
				if(m != 1) coin += "s";
				System.out.println("You get " + m + coin + " and " + x + " xp");
				player.levelUp(x, m);
				System.out.println("PRESS ENTER: ");
				in.nextLine();
				return -1;
			}
			Move aiMove = null;
			if(AiLevel == 0) {
				aiMove = Ai.useRand();
			}
			else if(AiLevel == 1) {
				//idk what this Ai is. Probably needs improvement
				List<Move> moves = Ai.getMoves2();
				boolean getMove = false;
				for(Move mve : moves) {
					if(mve.getEffectNum() == 6 && !getMove) {
                        double rand = Math.random();
                        if(rand > 0.40) {
                        	aiMove = mve;
                        	getMove = true;
                        	break;
                        }
					}
					else if(mve.getDamage() > Ai.getHp() && !getMove) {
						aiMove = mve;
						getMove = true;
						break;
					}
				}
				if(!getMove) {
					aiMove = Ai.useRand();
				}
			}
			boolean aiLive = true;
			boolean meLive = true;
			int bo = 0;
			int boa = 0;
			boolean playerCan = player.checkCan(myMove);
			boolean aiCan = Ai.checkCan(aiMove);
			if(playerCan) {
				bo = player.useMove(myMove);
			}
			if(aiCan) {
				boa = Ai.useMove(aiMove);
			}
			if(playerCan && aiCan) {
				aiLive = Ai.attacked(myMove, bo);
				meLive = player.attacked(aiMove, boa);
			}
			else if(playerCan) {
				aiLive = Ai.attacked(myMove, bo);
			}
			else if(aiCan) {
				meLive = player.attacked(aiMove, boa);
			}
			player.passive();
			Ai.passive();
			System.out.println(Ai);
			if(!aiLive && !meLive) {
				System.out.println("Tie");
				int x = 1 + (int)(Math.random() * 7 * Ai.getLevel());
				int m = 1 + (int)(Math.random() * 3 * Ai.getLevel()/3);
				String coin = " coin";
				if(m != 1) coin += "s";
				System.out.println("You get " + m + coin + " and " + x + " xp");
				player.levelUp(x, m);
				System.out.println("PRESS ENTER: ");
				in.nextLine();
				return 0;
			}
			else if(!aiLive) {
				System.out.println("\tYou Win");
				System.out.println("Congradulations!");
				int x = 8 + (int)(Math.random() * 15 * Ai.getLevel());
				int m = 5 + (int)(Math.random() * 5 * Ai.getLevel()/3);
				String coin = " coin";
				if(m != 1) coin += "s";
				System.out.println("You get " + m + coin + " and " + x + " xp");
				player.levelUp(x, m);
				System.out.println("PRESS ENTER: ");
				in.nextLine();
				return 1;
			}
			else if(!meLive) {
				System.out.println("\tAi Wins");
				System.out.println("\tYou Lose");
				System.out.println("You get a consolation prize");
				int x = 1 + (int)(Math.random() * 5);
				int m = 1 + (int)(Math.random() * 2);
				String coin = " coin";
				if(m != 1) coin += "s";
				System.out.println("You get " + m + coin + " and " + x + " xp");
				player.levelUp(x, m);
				System.out.println("PRESS ENTER: ");
				in.nextLine();
				return -1;
			}
			System.out.println();
		}
	}
	
	public static Move[] getRandom(Move[] arr, int n) {
	    Move[] result = new Move[n];
	    int len = arr.length;
	   int[] taken = new int[len];
	    while (n-- > 0) {
	         int x = (int) Math.floor(Math.random() * len);
	         result[n] = arr[check(x, taken) ? taken[x] : x];
	         taken[x] = check(--len, taken) ? taken[len] : len;
	    }
	    return result;
	}
	
	public static void printTie(Scanner in) {
		System.out.println("\tIt's a tie!\nDon't worry, you can try again.\nFirst, let's go back to the MAIN MENU.");
		System.out.println("Train your skills and you will succeed.");
		System.out.println("PRESS ENTER: ");
		in.nextLine();
	}
	
	public static void printLoss1(Scanner in) {
		System.out.println("\tYou have Lost ...\nDon't worry, you can try again\nlet's go back to the MAIN MENU first.");
		System.out.println("Train your skills and come back later.");
		System.out.println("PRESS ENTER: ");
		in.nextLine();
	}
	/*
	static boolean arePermutations(int arr1[], int arr2[]) { 
        // Creates an empty hashMap hM 
        HashMap<Integer, Integer> hM = new HashMap<Integer, Integer>(); 
  
        // Traverse through the first array and add elements to hash map 
        for (int i = 0; i < arr1.length; i++) 
        { 
            int x = arr1[i]; 
            if (hM.get(x) == null) 
                hM.put(x, 1); 
            else
            { 
                int k = hM.get(x); 
                hM.put(x, k+1); 
            } 
        } 
  
        // Traverse through second array and check if every element is 
        // present in hash map 
        for (int i = 0; i < arr2.length; i++) 
        { 
            int x = arr2[i]; 
  
            // If element is not present in hash map or element 
            // is not present less number of times 
            if (hM.get(x) == null || hM.get(x) == 0) 
                return false; 
  
            int k = hM.get(x); 
            hM.put(x, k-1); 
        } 
        return true; 
    } 
    */
}
