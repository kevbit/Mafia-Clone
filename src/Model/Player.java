package Model;

/**
 * Player class, keeps track of player index, username, whether they are alive, healed, targeted, framed.
 */
public class Player
{
	public int playerIndex;
    private String username;
    private Role role;
    private boolean isAlive;
    private boolean isHealed;								//Boolean to denote whether or not this particular player object is healed
    												//by the doctor on a particular night
    
    private boolean isTargeted;								//Boolean to denote whether or not this particular player object is a 
    													//target of the mafia on a particular night
    private boolean isVigTargeted;
    
    private boolean isFramed;
    private String congrats;

    /**
     * Constructor that initializes Player and their statuses.
     * @param name of player
     * @param a index of player
     */
    public Player(String name, int a)
    {
    	playerIndex = a;
    	
        username=name;

        isAlive=true;
        
        isHealed = false;
        
        isTargeted = false;
        
        isFramed = false;
    }

    /**
     * A player object with an initialized congrats message. Used for MVC later.
     */
    public Player()
    {
        congrats="";
    }

    /**
     * Sets name of player
     * @param name
     */
    public void setName(String name)
    {
        username=name;
    }

    /**
     * Gets name of player
     * @return username
     */
    public String getName()
    {
        return username;
    }

    /**
     * sets role of player
     * @param r
     */
    public void setRole(Role r)
    {
        role=r;
    }

    /**
     * gets role of player
     * @return
     */
    public Role getRole() {
    	return this.role;
    }

    /**
     * gets index of player
     * @return
     */
    public int getIndex() {
    	return this.playerIndex;
    }

    /**
     * sets player alive or dead status
     * @param val
     */
    public void setIsAlive(boolean val) {
    	this.isAlive = val;
    }

    /**
     * gets player's alive or dead status
     * @return
     */
    public boolean getIsAlive() {
    	return this.isAlive;
    }

    /**
     * Sets whether player is a target or not
     * @param val
     */
    public void setIsTarget(boolean val) {
    	this.isTargeted = val;
    }

    /**
     * gets whether a player is targeted.
     * @return
     */
    public boolean getIsTarget() {
    	return this.isTargeted;
    }

    /**
     * Sets whether Player is healed.
     * @param val
     */
    public void setIsHealed(boolean val) {
    	this.isHealed = val;
    }

    /**
     * gets whether a player is healed or not
     * @return
     */
    public boolean getIsHealed() {
    	return this.isHealed;
    }

    /**
     * sets if player is framed or not
     * @param val
     */
    public void setIsFramed(boolean val) {
    	this.isFramed = val;
    }

    /**
     * gets whether player is framed or not
     * @return
     */
    public boolean getIsFramed() {
    	return this.isFramed;
    }

    /**
     * Sets whether a player is targeted by vigilante
     * @param val
     */
    public void setVigTargeted(boolean val) {
		this.isVigTargeted = val;
	}

    /**
     * gets whether a player is targeted by vigilante
     * @return
     */
	public boolean getVigTargeted() {
		return isVigTargeted;
	}
	
    /**
     * Checks to see if the current player is alive
     * @return boolean whether the player is alive or not
     */
    public boolean checkPlayerAlive()
    {
        return isAlive;
    }

    /**
     * model part of MVC
     * sets a congrats message depending on which team won (townspeople or mafia)
     * @param str
     */
   public void setCongrats(String str)
   {
       congrats=str;
   }

    /**
     * model part of MVC
     * gets congrats message depending on which team won.
     * @return
     */
    public String getCongrats()
    {
        return congrats;
    }

	
}

