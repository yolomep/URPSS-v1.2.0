/**
 * 
 */
package playersettings;
import Move.*;
import java.util.*;

/**
 * @author admin
 *
 */
public class Player {
	int maxHp, xp, level, nextXp;
	int protect, boost;
	int skipTurns, hp, money;
	int statusTurns, healTurns, protectTurns;
	Move lastMove, healMove, protectMove;
	int moveLength;
	private String name;
	List<Move> moves;
	List<Move> allMoves;
	/**
	 * quick tip on money:
	 * 150 money is rich.
	 * Since this is prototype version, money buys everything 
	 * @param playerName
	 */
	public Player(String playerName){
		maxHp = 3;
		xp = 0;
		level = 1;
		moves = new ArrayList<>();
		allMoves = new ArrayList<>();
		nextXp = 20;
		setName(playerName);
		skipTurns = 0;
		money = 10;
		moves.add(new Move("Scissors", "attack", 1, 0));
		moves.add(new Move("Protect", "protect", 0, 3));
		moves.add(new Move("PowerUp", "status", 0, 4));
		allMoves.add(new Move("Scissors", "attack", 1, 0));
		allMoves.add(new Move("Protect", "protect", 0, 3));
		allMoves.add(new Move("PowerUp", "status", 0, 4));
		moveLength = 3;
	}
	
	public Player(String playerName, int l, int mH, int m, int nX, int Xp, int mL, ArrayList<Move> move, ArrayList<Move> mo){
		maxHp = mH;
		xp = Xp;
		level = l;
		moves = move;
		nextXp = nX;
		setName(playerName);
		money = m;
		allMoves = mo;
		moveLength = mL;
	}
	/**
	 * levels up
	 *  How is xp level?
	 * level requires 10 xp
	 * level 2 requires 20 xp
	 * @param xp
	 */
	public void levelUp(int xp, int mo) {
		money += mo;
		this.xp += xp;
		if(this.xp >= nextXp) {
			System.out.println("You leveled up!");
			if(level < 100) nextXp += 20 * level + level % 10 + level/15;
			else if(level > 100) nextXp += 125 * (level - 100) + level % 5 + level/15 + 100;
			level++;
			System.out.println("You are now level: "+ level);
			if(level % 5 == 0 && level > 5) {
				System.out.println("You get a prize for reaching this level.");
				maxHp++;
				money += level;
			}
			if(level == 100) {
				System.out.println("You have reached the max level for the mortal realm.");
				System.out.println("Now your Xp will be reset, and nextXp will be calculated differently.");
				System.out.println("Take this hp boost as an award.");
				maxHp += 5;
				this.xp -= this.nextXp;
				this.nextXp = 125 * (level - 100) + level % 5 + level/15 + 100;
				print();
			}
		}
	}
	
    @Override
    public String toString() {
    	String s = name + "|Hp: " + hp + "\tLevel: " + level + "\tMax Moves: " + moveLength + "\tXp: " + xp + "\tnextXp: " + nextXp;
    	if(skipTurns > 1) s += " paralyzed for " + skipTurns + " turns";
    	else if(skipTurns == 1) s += " paralyzed for 1 turn";
    	if(statusTurns > 0) s += " can't use status for " + statusTurns + " turns";
    	return s;
    }
    public void print() {
    	System.out.println(name + "|Hp: " + maxHp + "\tLevel: " + level + "\tMax Moves: " + moveLength + "\tXp: " + xp + "\tnextXp: " + nextXp + "\tMoney: " + money);
    }
    public void printMoves() throws InterruptedException {
    	for(Move m : moves) {
    		m.print();
    		Thread.sleep(100);
    	}
    }
    
    public Move useRand() {
    	return moves.get((int)(Math.random() * moves.size()));
    }
	//when a move is used, it triggers opp's attack and your useMove
    public boolean attacked(Move move, int boostn) {
    	//what happens when you get attacked?
    	//you receive damage equal to damage+boost-protect, without changing the level
    	//protect is now 0
    	int damage = Math.max(move.getDamage() - protect + boostn, 0);
    	if(move.getRawDamage() == 0) 
    		damage = 0;
    	
    	protect = 0;
    	//protect doesn't protect spark
    	//debuffed spark, now can't spam it
    	if((move.getEffectNum() == 5 || move.getEffectNum() == 2)) {
    		if(skipTurns == 0) {
    			skipTurns += move.getLevel() + boostn;
    		}
    		else {
    			System.out.println(name + " is already paralyzed.");
    		}
    	}
    	hp -= damage;
    	if(hp <= 0) return false;
    	return true;
    }
    @Deprecated
    public boolean TAttack(Move move, int boostn) {
    	int damage = Math.max(move.getDamage() - protect + boostn, 0);
    	if(move.getDamage() == 0) 
    		damage = 0;
    	protect = 0;
    	hp -= damage;
    	if(hp <= 0) return false;
    	return true;
    }
    public boolean checkCan(Move move) {
    	if(skipTurns > 0) {
    		System.out.println(name + " is paralyzed. " + name + " can't move.");
    		return false;
    	}
    	if(statusTurns > 0 && move.getType().equals("status")) {
    		System.out.println("It seems that " + name + " can't use status moves.");
    		return false;
    	}
    	return true;
    	
    }
    public void passive() {
    	if(skipTurns > 0) {
    		skipTurns--;
    	}
    	if(statusTurns > 0) {
    		statusTurns--;
    	}
    	if(protectTurns > 0) {
    		protect += protectMove.getLevel();
    		protectTurns--;
    	}
    	if(healTurns > 0) {
    		hp += healMove.getLevel();
    		hp = Math.min(hp, maxHp);
    		healTurns--;
    	}

    }
    @Deprecated
    public int TUM(Move move) {
    	//if(!checkCan(move)) 
    		//return 0;
    	
    	int boos = boost;

    	if(move.getEffectNum() == 4) {
    		boost += move.getLevel();
    	}
    	if(move.getEffectNum() != 4 && move.getEffectNum() != 6){
    		boost = 0;
    		return boos;
    	}
    	hp = Math.min(maxHp, hp);
    	return 0;
    }
    public int useMove(Move move) {
    	System.out.println(getName() + " used move: " + move.getName());
    	//move.print();
    	//ok so basically if move isn't a boosting move, then boost is equal to 0;
    	int boos = boost;
    	if(move.getEffectNum() == 1) {
    		hp += move.getLevel() + boost;
    	}
    	else if(move.getEffectNum() == 3) {
    		protect += move.getLevel() + boost;
    	}
    	else if(move.getEffectNum() == 4) {
    		boost += move.getLevel();
    	}
    	else if(move.getEffectNum() == 5) {
    		hp += move.getLevel() + boost;
    	}
    	else if(move.getEffectNum() == 6) {
    		hp += move.getLevel() + boost;
    		//hew it was boost += move.getLevel() + boost;
    		//that was rigged too Op had to nerf
    		boost = move.getLevel();
    	}
    	else if(move.getEffectNum() == 8) {
    		hp = maxHp;
    		skipTurns = 2;
    	}
    	else if(move.getEffectNum() == 10) {
    		if(healTurns == 0) {
    			healTurns += move.getLevel() + boost;
        		healMove = move;
    		}
    		else {
    			System.out.println(name + " is already under the effects of heal.");
    		}
    	}
    	else if(move.getEffectNum() == 11) {
    		if(protectTurns == 0) {
    			protectTurns += 2;
    			protectMove = move;
    		}
    		else {
    			System.out.println(name + " has a shield already.");
    		}
    	}
    	if(move.getEffectNum() != 4 && move.getEffectNum() != 6){
    		if(hp > maxHp) hp = maxHp;
    		boost = 0;
    		return boos;
    	}
    	//System.out.println(boost);
    	hp = Math.min(maxHp, hp);
    	//idk why I have this ... probably for an update
    	lastMove = move;
    	//its a boost so return none
    	return 0;
    }
    public void printAllMoves() {
    	for(Move m : allMoves) {
    		m.print();
    	}
    }
    //completely useless
    public void changeMoves() {
    	
    }
    public void newGame() {
    	hp = maxHp;
    	protect = 0;
    	boost = 0;
    	skipTurns = 0;
    	statusTurns = 0;
    	healTurns = 0;
    	healMove = new Move("blank", "null", 0, 0);
    	lastMove = new Move("blank", "null", 0, 0);
    	protectTurns = 0;
    	protectMove = new Move("blank", "null", 0, 0);
    	System.out.println(getName() + " is preparing for combat");
    }
    public void tNG() {
    	hp = maxHp;
    	protect = 0;
    	boost = 0;
    	skipTurns = 0;
    }
	public int getHp() {
		// TODO Auto-generated method stub
		return hp;
	}
	public int getBoost() {
		// TODO Auto-generated method stub
		return boost;
	}
	public int getLevel() {
		return level;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	public String print1() {
		return level + " " + money + " " + xp + " " + nextXp + " " + maxHp + " " + moveLength;
	}
	
	public String[] getMoves(){
		String[] ret = new String[moves.size()];
		for(int i = 0; i < ret.length; i ++) 
			ret[i] = moves.get(i).getName() + " " + moves.get(i).getType() + " " + moves.get(i).getRawDamage() + " " + moves.get(i).getEffectNum() + " " +  moves.get(i).getLevel();
		return ret;
	}
	
	public String[] getMovesA(){
		String[] ret = new String[allMoves.size()];
		for(int i = 0; i < ret.length; i ++) 
			ret[i] = allMoves.get(i).getName() + " " + allMoves.get(i).getType() + " " + allMoves.get(i).getRawDamage() + " " + allMoves.get(i).getEffectNum() + " " +  allMoves.get(i).getLevel();
		return ret;
	}
	public List<Move> getMoves2(){
		return moves;
	}
	public List<Move> getAllMoves(){
		return allMoves;
		
	}
	public Move getMove(int num){
		return moves.get(num);
	}
	public Move getMoveA(int num){
		return allMoves.get(num);
	}
	public void addMove(Move m) {
		moves.add(m);
	}
	public void addAllMoves(Move m) {
		allMoves.add(m);
	}
	public int getMax() {
		return moveLength;
	}

	public int getMoney() {
		// TODO Auto-generated method stub
		return money;
	}
	@Deprecated
	public int getProtectTest() {
		return protect;
	}
	public void incMaxMove() {
		moveLength++;
	}
	public int getMaxHp() {
		return maxHp;
	}
	/**
	 * very unsafe function
	 */
	public void incMaxHp() {
		maxHp++;
	}
}
