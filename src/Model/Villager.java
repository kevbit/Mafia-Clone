package Model;

/**
 * Villager class, no special role, just helps to interact with villager players
 */
public class Villager extends Role
{

    /**
     * Constructor that initializes villager to be a townsperson
     */
    public Villager()
    {
        this.isTown=true;
    }


    /**
     *
     * @return Villager String
     */
    public String toString() {
    	return "Villager";
    }

    /** Method for notifying/interacting with the villager, telling them they have no night action but to pretend like they
     are doing something*/
    public String description()
    {
        return "You are a simple villager, a townsperson trying to live your life peacefully in the town you've always known as home. But\n"
        		+ " living among your neighbors are people who seek to kill anybody who will not submit to the Mafia. Your goal\n"
        		+ "is to work with the other townsmembers to find and hang all hidden Mafia members. Just note, you can't\n "
        		+ "trust everybody!";
    }
}
