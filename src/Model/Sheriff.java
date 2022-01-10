package Model;

import java.util.Scanner;

import javax.swing.JOptionPane;

/**
 * Sheriff class, has logic for interrogating players.
 */
public class Sheriff extends Role
{
    /**
     * Sheriff constructor that initializes sheriff to be a townsperson.
     */
    public Sheriff()
    {
        this.isTown=true;
    }

    /**
     * Method that asks sheriff if they want to interrogate and who
     * @return index of player to be interrogated, -1 if no one.
     */
    public int Interrogate() {
    	int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(this, "Do you want to interrogate anybody tonight?", "Interrogate?", dialogButton);
    	if(dialogResult == 0) {
    		String input1 = JOptionPane.showInputDialog("Input the index (number) of the player you wish to interrogate. It is not advisable to interrogate yourself!");
    		return Integer.valueOf(input1);
    	}
    	else {
        	return -1;
        }
    }

    /**
     *
     * @return Sheriff string
     */
    public String toString() {
    	return "Sheriff";
    }

    /**
     *
     * @return Sheriff description
     */
    public String description()
    {
        return "You are the sheriff of the town, now keeping a low-profile to hide from the Mafia who would love nothing more \n "
        		+ "than being able to hunt you down and end your life, because of how big of a threat you are to them. You can \n"
        		+ "choose to interrogate any player each night to determine whether they are suspicious (a member of the Mafia)\n "
        		+ "or not.";
    }
}
