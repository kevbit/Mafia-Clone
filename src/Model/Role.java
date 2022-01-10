package Model;

import javax.swing.*;

/**
 * Role class: assigns the player list specific roles randomly depending on the number of players. Also keeps track of which players are townspeople.
 */
public class Role extends JComponent
{
    protected boolean isTown;

	/**
	 * Role constructor. Sets isTown to be initially true as most roles (except Mafia and Framer) are townspeople
	 */
	public Role()
    {
        isTown=true;
    }


	/**
	 * Returns an array of roles based of the number of players
	 * Number of players: 4-8 players.Recommend at least 6 players for an actually decent game.
	 * @param numOfPlayers
	 * @return list of roles
	 */
	public static Role[] returnList(int numOfPlayers){			//Returns an array of roles based of the number of players
    	Doctor doctor = Doctor.getInstance();									//Recommend at least 6-7 players for an actually decent
    	Sheriff sheriff1 = new Sheriff();
    	Mafia maf1 = new Mafia();
    	Mafia.Framer maf2 = new Mafia.Framer();
    	//Mafia maf2 = new Mafia();
    	Villager village1 = new Villager();
    	Villager village2 = new Villager();
    	Villager village3 = new Villager();
    	Vigilante vig = Vigilante.getInstance();
    	
    	
    	 if(numOfPlayers == 4) {
    		Role[] fourList = {doctor, sheriff1, maf1, village1};			//The actual array of roles in the 4 player
    		
    		return fourList;
    	}
    	
    	else if (numOfPlayers == 5) {
    		Role[] fiveList = {doctor, sheriff1, maf1, village1, village2};
    		return fiveList;
    	}
    	
    	else if (numOfPlayers == 6) {
    		Role[] sixList = {doctor, sheriff1, maf1, village1, village2, village3};
    		return sixList;
    	}
    	
    	else if (numOfPlayers == 7) {
    		Role[] sevenList = {doctor, sheriff1, maf1, village1, village2, village3, maf2};
    		return sevenList;
    	}
    	
    	else if (numOfPlayers == 8) {
    		Role[] eightList = {doctor, sheriff1, maf1, village1, village2, village3, maf2, vig};
    		
    		return eightList; 
    	}
    	
    	return null;
    }


	/**
	 * Sets whether player is townperson or not
	 * @param x
	 */
	public void setIsTown(boolean x)
    {
        isTown=x;
    }

	/**
	 * gets whether player is townsperson or not
	 * @return
	 */
	public boolean getIsTown()
    {
        return isTown;
    }

	/**
	 * Returns a null description, used as super class for child classes of role
	 * @return
	 */
	public String description() {
    	return null;
    }
    
}
