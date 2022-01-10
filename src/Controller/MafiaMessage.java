package Controller;

/**
 * MafiaMessage implements message, part of the mvc, in charge of changing Mafia's message
 */
public class MafiaMessage implements Message {


    String congrats;

    /**
     * constructor for MafiaMessage
     * @param con: message to input
     */
    public MafiaMessage(String con) {
        congrats=con;

    }

    /**
     * Getter for Mafia Button's congrats message
     * @return message congrats mafia
     */
    public String getCongrats()
    {
        return "congrats mafia!";
    }


}