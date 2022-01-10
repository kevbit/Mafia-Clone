package Controller;

/**
 * TownMessage implements message, part of the mvc, in charge of changing Mafia's message
 */
public class TownMessage implements Message
{
    String congrats;

    /**
     * constructor for TownMessage
     * @param con: message to input
     */
    public TownMessage(String con) {
        congrats=con;

    }

    /**
     * Getter for Town Button's congrats message
     * @return message congrats town
     */
    public String getCongrats()
    {
        return "congrats town!";
    }
}
