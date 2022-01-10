package Model;

import java.util.Scanner;

import javax.swing.JOptionPane;


/**
 * Mafia class, has inner framer class because they are a part of the mafia.
 * Has methods that allow framers to choose who to frame and the Mafioso to kill.
 */
public class Mafia extends Role
{


	/**
	 * Mafia constructor that initializes to mafia to not be townperson.
	 */
    public Mafia()
    {
        this.isTown=false;
    }
    

    public static class Framer extends Mafia{				//Inner class Framer of Mafia


		/**
		 * Allows framer to choose if they want to frame someone and who
		 * @return index of player to be framed, -1 if no one.
		 */
		public int frame() {

			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog(this, "Do you want to frame somebody tonight?", "Frame?",dialogButton);
			if(dialogResult == 0) {
				String input1 = JOptionPane.showInputDialog("Please input the index of the player you want to frame tonight.");
				return Integer.valueOf(input1);
			}
			else return -1;
			
			
			
		}

		/**
		 *
		 * @return Framer String
		 */
		public String toString(){
			return "Framer";
		}

		/**
		 *
		 * @return Framer description
		 */
		public String description()
	    {
	        return "You are a framer, a talented deceptionist who manipulates information for the Mafia, a ruthless group of \n "
	        		+ "evildoers who know each others' identities and participate \n"
	        		+ " in organized crime together. All Mafia members have the same goal; to kill anyone that will not submit to \n"
	        		+ " the Mafia. Each night, you may select \n"
	        		+ "a target to frame. A framed target will look suspicious to the sheriff if he investigates them from \n "
	        		+ "that point on. However, once the sheriff interrogates a framed target and finds them suspicious, they \n"
	        		+ "will not be fooled by the same deception again if they once again interrogate this player in the future. \n"
	        		+ "It is necessary to frame the individual again if you wish to fool the sheriff once more.\n"
	        		+ "The town is completely clueless about\n"
	        		+ " you and your fellow Mafia members' identities, but with every day they will come closer and closer\n"
	        		+ "to finding and publicly hanging you...if they're not already dead!\n";
	    }

	}

	/**
	 *
	 * Lets the mafioso decide who they want to mark (call a hit) for assassination tonight, if they choose to do so at all.
	 * @return index of player who is marked for assassination, -1 if mafioso decides not to mark anybody
	 *
	 */
	public int hit() {
    	int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(this, "Do you want to call a hit on somebody tonight?", "Call A Hit?",dialogButton);
    	if(dialogResult == 0) {
    		String input1 = JOptionPane.showInputDialog("Input the index (number) of the player you want to kill");
    		return Integer.valueOf(input1);
    	}
    	else {
        	return -1;
        }
    }





	/**
	 *
	 * @return Mafia String
	 */
    public String toString() {
    	return "Mafia";
    }

	/**
	 *
	 * @return Mafia Description
	 */
	public String description()
    {
        return "You are a mafioso, a member of the Mafia which is a ruthless group of evildoers who know each others' identities and participate\n"
        		+ " in organized crime together. All Mafia members have the same goal; to kill anyone that will not submit to\n"
        		+ " the Mafia. Each night, you may select \n"
        		+ "a target to kill. The town is completely clueless about\n"
        		+ " you and your fellow Mafia members' identities, but with every day they will come closer and closer\n"
        		+ "to finding and publicly hanging you...if they're not already dead!\n";
    }
}
