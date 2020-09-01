/**
 * 
 */
package Move;
//types of effects
//types of type: attack, status, hybrid, protect
//type is lowercase
/**
 * @author admin
 * 
 * Names of all moves:
 * "Scissors", "attack", 1, 0
 * "Protect", "protect", 0, 3
 * "PowerUp", "status", 0, 4
 * "Sparky", "status", 0, 2
 * "Knife", "attack", 1, 7
 * "Blessing", "status", 0, 6
 * "Rest", "status", 0, 8
 * "Recover", "status", 0, 1
 * "Gun", "hybrid", 1, 3
 * "R&S", "hybrid", 1, 4 (Reload and Shoot)
 * "ThunderPunch", "hybrid", 2, 2
 * "GarbageCan", "status", 0, 9
 * "DScissors", "attack", 2, 0
 * "Boom", "attack", 3, 0
 * "Healing", "status", 0, 10
 * "WideGuard", "protect", 0, 11
 */
public class Move implements Cloneable {
	String name;
	String type;
	int damage;
	int level;
	/**
	 * effect nums:
	 * 1 heals the user
	 * 2 Paralyze the opponent
	 * 3 Protects the user
	 * 4 Powers up last move
	 * 5 heals and paralyzes
	 * 6 powers up and heals
	 * 7 double damage every level
	 * 8 heals to max but paralyzes
	 */
	int effect;
	/**
	 * Move forms: name, type, damage, effect
	 * @param name
	 * @param type
	 * @param damage
	 * @param effect
	 */
	public Move(String name, String type, int damage, int effect){
		this.effect = effect;
		this.type = type;
		this.damage = damage;
		this.name = name;
		level = 1;
	}

	public Move(String name, String type, int damage, int effect, int level){
		this(name, type, damage, effect);
		this.level = level;
	}

	public void print() {
		System.out.println(name + "|Type: " + type);
		System.out.print(getEffect());
		if(damage > 0) System.out.println(" Damage: " + getDamage());
		else System.out.println();
		System.out.println("Level: " + level);
		System.out.println();
	}
	//TODO Fix SPARKY
	public String getEffect() {
		if(effect == 1) return "Heals the user";
		else if(effect == 2) return "Paralyze the opponent, doesn't stack though,\nso don't try to paralyze someone that can't move";
		else if(effect == 3) return "Protects the user";
		else if(effect == 4) return "Powers up last move";
		else if(effect == 5) return "Heals yourself and paralyzes opponent";
		else if(effect == 6) return "Powers up and heals";
		else if(effect == 7) return "Double damage every level";
		else if(effect == 8) return "Heals you to full HP but skips 2 turns\nthis move cannot be boosted,\nCan't level this move either.";
		else if(effect == 9) return "Prevents opponent from activating status moves";
		else if(effect == 10) return "Heals some HP every turn.";
		else if(effect == 11) return "Protects the user for the next two turns.";
		return "No effects";
	}
	/**
	 * Gets Damage, always use this instead of raw damage
	 */
	public int getDamage() {
		//change to reflect on feature
		if(effect == 7) return damage + damage * 2 * (level - 1);
		return damage * level;
	}
	public int getRawDamage() {
		return damage;
	}
	public int getEffectNum() {
		return effect;
	}
	public int getLevel() {
		// TODO Auto-generated method stub
		return level;
	}
	public String getName() {
		return name;
	}
	public boolean tryLevel(int money) {
		if(money < getCost()) return false;
		level++;
		/*this snippet is wrong, level now accounts for damage
		if(effect == 0) damage++;
		if(effect == 7) damage += 2;
		*/
		return true;
	}
	public String getType() {
		// TODO Auto-generated method stub
		return type;
	}
	public int getCost() {
		if((effect == 2 || effect == 5) ) return 15*level*level*level - 4*level + 10;
		if(effect == 7) return 9*level*level + 6;
		else return 9*level*level - 3*level + 6;
	}
	@Override
	public Move clone() {
		return new Move(name, type, damage, effect, level);
	}
	@Override
	public boolean equals(Object mi) {
		Move m = (Move) mi;
		if(m.getEffectNum() == effect && m.getRawDamage() == damage && m.getType().equals(type) && m.getName().equals(name)) return true;
		return false;
	}
	public boolean deepEquals(Move m) {
		if(m.getEffectNum() == effect && m.getRawDamage() == damage && m.getType().equals(type) && m.getName().equals(name) && m.getLevel() == level) return true;
		return false;
	}
	@Override
	public int hashCode() {
		int i = effect + damage*100 + level*31;
		if(type.equals("protect")) i *= 10;
		if(type.equals("attack")) i *= 8;
		i += 3152343*name.length();
		return i;
	}
}
