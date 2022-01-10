package Controller;

import java.util.*;
import java.util.concurrent.BlockingQueue;

import javax.swing.*;
import Model.Doctor;
import Model.Mafia;
import Model.Player;
import Model.Role;
import Model.Sheriff;
import Model.Vigilante;
import Model.Villager;
import View.GameUI;
import Controller.Game;
import Controller.MafiaMessage;
import Controller.TownMessage;
import Controller.Message;


/**
 * Class Game is in charge of the entire game logic and game flow. This class is what calls methods from Role and its subclasses to run.
 */
public class Game extends JComponent
{
   public static int numOfPlayers;
    static int numOfMafia;
    public static ArrayList<Player> playerList;
    public static ArrayList<Player> shuffledList; //to get a random order of roles.
    static Iterator<Player> iter;


    static Role[] roleList;
    static ArrayList<Player> mafiaList;				//Arraylist containing members of the mafia

    static ArrayList<Player> toRemove = new ArrayList<Player>();

    boolean mafWin = false;
    boolean townWin = false;

    boolean vigPunish = false;

    static boolean removed = false;
    static int frameCount = 0;
    static int vigDeath = 0;


    private GameUI newUI;
    private Player p;



    static BlockingQueue<Message> queue; //used at the end of the game, when we click on buttons to show a specific message.


    /**
     * Game constructor
     * @param queue
     * @param g-View object
     */
    public Game(BlockingQueue<Message> queue,GameUI g)
    {
        this.queue=queue;
        newUI=g;
    }


    /**
     * Instantiates the view class in game
     */
    public void setUI()
    {
        newUI = new GameUI(queue, playerList);
        newUI.ViewRun();
    }


    /**
     * prints the playerlist, used for debugging purposes.
     */
    public static void print() {						//Prints the playerList
        for(Player pl : playerList) {
          //  System.out.print(pl.getName()+", ");
            int dialogResult = JOptionPane.showConfirmDialog(null,
                    pl.getName()+", ",
                    "Enter Name",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE);
        }
        System.out.println();
    }


    /**
     * Actually marks the doctors' targeted player as healed
     * @param index of player to be healed
     */
    public static void targetHeal(int index) {
        playerList.get(index).setIsHealed(true);
    }


    /**
     * Actually marks the mafias' desired player as an assassination target
     * @param index of player to be killed
     */
    public static void targetHit(int index) {
        if(index != -1)
            playerList.get(index).setIsTarget(true);
    }


    /**
     * Checks whether targeted player is a town member or not
     * @param index of player to be interrogated
     * @return isTown value unless player is framed.
     */
    public static boolean targetInterrogate(int index) {
        if(index != -1 && !playerList.get(index).getIsFramed())
            return playerList.get(index).getRole().getIsTown();

        else if(index != -1 && playerList.get(index).getIsFramed()) {
            playerList.get(index).setIsFramed(false);
            return false;
        }
        else return true;
    }

    /**
     * When framer from Mafia chooses to frame a certain townperson, will be in use with sheriff
     * @param index of person to be framed
     */
    public static void targetFramed(int index) {
        if(index != -1)
            playerList.get(index).setIsFramed(true);
    }

    /**
     * Actually marks who will be killed
     * @param index of person vigilante wants to kill
     */
    public static void targetVigHit(int index) {
        if(index != -1)
            playerList.get(index).setVigTargeted(true);
    }


    /**
     * removes a player from array list when player is killed.
     */
    public static void removePlayer() {					//Only called when a kill goes through without the target being healed

        iter = playerList.iterator();
        while (iter.hasNext()) {
            if(removed) {
                while(iter.hasNext()) {
                    iter.next().playerIndex--;
                }

                break;
            }

            Player pl = iter.next();

            if(!pl.checkPlayerAlive()) {
                iter.remove();
                removed = true;
            }

        }
        removed = false;
    }


    /**
     * Method that runs the game flow
     */
    public void play()
    {
        /**
         * FIRST DAY PHASE: ASSIGNING ROLES
         *
         * Uses a shuffling procedure based on default randomness so that players have no way of making associations between
         * the entered username order displayed in the PlayerList and the assigning of roles.
         *
         */

        if(numOfPlayers < 7) {
            numOfMafia = 1;
        }

        if(numOfPlayers >= 7 && numOfPlayers <= 9) {
            numOfMafia = 2;
        }
        if(numOfPlayers >=10 && numOfPlayers <=13) {
            numOfMafia = 3;
        }
        if(numOfPlayers >=14) {
            numOfMafia = 4;
        }
        mafiaList = new ArrayList<Player>(numOfMafia);

        Collections.shuffle(shuffledList);			//Shuffled copy of playerList to use during role-assigning procedure

       // System.out.println("Here are your roles. Please hand the device in the same order you entered names, shown again below \n");
        JOptionPane.showConfirmDialog(null,
                "Here are your roles. Please hand the device in the same order you entered names.",
                "Roles",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE);
        //Game.print();
        roleList = Role.returnList(numOfPlayers);
        int indexCounter = 0;

        for(Player pl : shuffledList) {			//For each player in the playerlist, assign a role to them (through random
            // shuffling or some other random mechanism that ensures all roles are taken
            pl.setRole(roleList[indexCounter]);
            indexCounter++;
        }

        for(Player pl : playerList) {
            //System.out.println(pl.getName()+", your role is "+ pl.getRole()+". "+ pl.getRole().description()+ "\n");
            JOptionPane.showConfirmDialog(null,
                    pl.getName()+", your role is "+ pl.getRole()+". "+ pl.getRole().description(),
                    "Roles",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

            if(!pl.getRole().getIsTown()){
                mafiaList.add(pl);


                for(Player player: playerList) {
                    if (numOfPlayers < 7) {
                        //System.out.println("You are the only member of the Mafia!");
                        JOptionPane.showConfirmDialog(null,
                                "You are the only member of the Mafia!",
                                "Mafia",
                                JOptionPane.DEFAULT_OPTION,
                                JOptionPane.PLAIN_MESSAGE);
                        break;
                    }
                    else if(!player.getRole().getIsTown() && !player.equals(pl)) {
                       // System.out.println("Your mafia partner is "+player.getName());
                        JOptionPane.showConfirmDialog(null,
                                "Your mafia partner is "+player.getName(),
                                "Mafia",
                                JOptionPane.DEFAULT_OPTION,
                                JOptionPane.PLAIN_MESSAGE);
                    }


                }

            }
            JOptionPane.showConfirmDialog(null,
                    "Please hand your device to the next person.",
                    "ALERT",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

        }

        while(!mafWin && !townWin) {
            if(vigPunish) {
                vigDeath++;
            }

            /**
             * NIGHT PHASE
             * Searches through the playerList (which will be in the order in which the players entered their names) to decide
             * which night action prompt to run.
             *
             */

            JOptionPane.showConfirmDialog(null,
                    "Round will be updated.",
                    "ALERT",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE);
            newUI.updateRound();

            for(Player p : playerList) {


                if(p.getRole().toString().equals("Doctor")){
                    Doctor doc = (Doctor) roleList[0];
                    System.out.print(p.getName()+", ");
                    JOptionPane.showConfirmDialog(null,
                            p.getName()+", ",
                            "Doctor",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.PLAIN_MESSAGE);
                    targetHeal(doc.heal());
                    JOptionPane.showConfirmDialog(null,
                            "Please hand your device to the next person.",
                            "ALERT",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.PLAIN_MESSAGE);

                }


                else if(p.getRole().toString().equals("Sheriff")) {
                    Sheriff sher = (Sheriff) roleList[1];
                   // System.out.print(p.getName()+", ");
                    JOptionPane.showConfirmDialog(null,
                            p.getName()+", ",
                            "Sheriff",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.PLAIN_MESSAGE);
                    if(!targetInterrogate(sher.Interrogate())) {
                        //System.out.println("Your target is suspicious!");
                        JOptionPane.showConfirmDialog(null,
                                "Your target is suspicious!",
                                "Sheriff",
                                JOptionPane.DEFAULT_OPTION,
                                JOptionPane.PLAIN_MESSAGE);
                    }
                    else //System.out.println("Your target seems innocent.");
                    {JOptionPane.showConfirmDialog(null,
                                "Your target seems innocent!",
                                "Sheriff",
                                JOptionPane.DEFAULT_OPTION,
                                JOptionPane.PLAIN_MESSAGE);}
                    JOptionPane.showConfirmDialog(null,
                            "Please hand your device to the next person.",
                            "ALERT",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.PLAIN_MESSAGE);
                }


                else if(p.getRole().toString().equals("Mafia")) {
                    Mafia mafioso = (Mafia) roleList[2];
                    //System.out.print(p.getName()+", ");
                    JOptionPane.showConfirmDialog(null,
                            p.getName()+", ",
                            "Mafia",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.PLAIN_MESSAGE);
                    targetHit(mafioso.hit());
                    JOptionPane.showConfirmDialog(null,
                            "Please hand your device to the next person.",
                            "ALERT",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.PLAIN_MESSAGE);
                }

                else if(p.getRole().toString().equals("Villager")) {
                    //Villagers won't get to do much, we could display a pop up for them telling them to act like
                    //they are doing stuff
                    JOptionPane.showConfirmDialog(null,
                            p.getName()+", ",
                            "Villager",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.PLAIN_MESSAGE);
                  //  System.out.println("as a villager, you don't have a night action. Just sleep tight and hope you don't die!");
                    JOptionPane.showConfirmDialog(null,
                            "as a villager, you don't have a night action. Just sleep tight and hope you don't die!",
                            "Villager",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.PLAIN_MESSAGE);
                    JOptionPane.showConfirmDialog(null,
                            "Please hand your device to the next person.",
                            "ALERT",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.PLAIN_MESSAGE);

                }


                else if(p.getRole().toString().equals("Framer")) {
                    Mafia.Framer framer = (Mafia.Framer) roleList[6];

                    if(numOfMafia == mafiaList.size()) {			//If a framer is still alive in the game and the number
                        //of mafia members is the same as they started off with
                        //then framer just does the regular job
                        JOptionPane.showConfirmDialog(null,
                                p.getName()+", ",
                                "Framer",
                                JOptionPane.DEFAULT_OPTION,
                                JOptionPane.PLAIN_MESSAGE);
                        targetFramed(framer.frame());
                    }

                    else {
                        JOptionPane.showConfirmDialog(null,
                                p.getName()+", ",
                                "Framer",
                                JOptionPane.DEFAULT_OPTION,
                                JOptionPane.PLAIN_MESSAGE);
                        targetHit(framer.hit());								//Other wise, the framer has to take the job of the mafioso
                    }
                    JOptionPane.showConfirmDialog(null,
                            "Please hand your device to the next person.",
                            "ALERT",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.PLAIN_MESSAGE);
                }

                else if(p.getRole().toString().equals("Vigilante")) {
                    Vigilante vig = (Vigilante) roleList[7];
                    JOptionPane.showConfirmDialog(null,
                            p.getName()+", ",
                            "Vigilante",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.PLAIN_MESSAGE);
                    if(vigPunish) {
                        vig.punishment();
                    }
                    else targetVigHit(vig.shoot());

                    JOptionPane.showConfirmDialog(null,
                            "Please hand your device to the next person.",
                            "ALERT",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.PLAIN_MESSAGE);
                }
        	


            }
            //Last part of night phase, deciding who lives and dies
            for(Player a : playerList) {
                if(a.getIsFramed()) {
                    frameCount++;
                }

                if(vigDeath == 1 && vigPunish && shuffledList.get(7).getIsAlive()) {

                    shuffledList.get(7).setIsAlive(false);


                }

                if(a.getIsTarget() && !a.getIsHealed()) {				//If a player is targeted by the mafia and not healed, they die
                    a.setIsAlive(false);




                   JOptionPane.showConfirmDialog(null,
                            a.getName()+" was killed by a member of the Mafia. Their role was "+ a.getRole().toString(),
                            "Someone was killed!",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.PLAIN_MESSAGE);
                    if(!playerList.get(a.getIndex()).getRole().getIsTown()) {
                        numOfMafia = numOfMafia - 1;
                        mafiaList.remove(playerList.get(a.getIndex()));
                    }
                }

                if(a.getIsTarget() && a.getIsHealed()) {
                    JOptionPane.showConfirmDialog(null,
                            "A player was targeted by the Mafia but was nursed back to health by the doctor!",
                            "Someone was almost killed!",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.PLAIN_MESSAGE);
                }

                if(a.getVigTargeted() && !a.getIsHealed()) {
                    a.setIsAlive(false);




                    JOptionPane.showConfirmDialog(null,
                            a.getName()+" was killed by the vigilante. Their role was"+ a.getRole().toString(),
                            "Someone was killed!",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.PLAIN_MESSAGE);


                    if(a.getRole().getIsTown()) {							//If the vigilante shot a town member, they are added to the death queue
                        //and will suicide tonight
                        vigPunish = true;
                    }

                }

                if(a.getVigTargeted() && a.getIsHealed()) {
                   JOptionPane.showConfirmDialog(null,
                            "A player was targeted by the vigilante but was nursed back to health by the doctor!",
                            "Someone was almost killed!",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.PLAIN_MESSAGE);

                }


                if(a.getVigTargeted() && a.getIsTarget()) {				//If a player is both targeted by the mafia and the
                    //vigilante, they die indefinitely, no matter their heal status
                    a.setIsAlive(false);


                    if(a.getIsHealed()) {					//If the doctor also heals that target, it can only protect from one attack (the vigilante's)
                       JOptionPane.showConfirmDialog(null,
                                a.getName()+" was killed by a member of the Mafia. Their role was"+ a.getRole().toString(),
                                "Someone was killed!",
                                JOptionPane.DEFAULT_OPTION,
                                JOptionPane.PLAIN_MESSAGE);
                    }
                    else {
                        JOptionPane.showConfirmDialog(null,
                                a.getName()+" was killed by a member of the Mafia and the vigilante. Their role was"+ a.getRole().toString(),
                                "Someone was killed!",
                                JOptionPane.DEFAULT_OPTION,
                                JOptionPane.PLAIN_MESSAGE);
                        vigPunish = true;
                    }
                }
                //Reset player a to all defaults; if they are dead it won't
                //matter as they will be removed from the game and if they are
                //alive then we need to reset these variables anyways
                if(frameCount > 3) {
                    a.setIsFramed(false);

                }


                a.setIsHealed(false);
                a.setIsTarget(false);
                a.setVigTargeted(false);


            }

            removePlayer();
            removePlayer();

              newUI.updatePlayers(playerList);						//Updates the UI after changes are made to the playerList

            /**
             * DAY PHASE
             *
             * The voting of this phase happens in person, you have a certain amount of time to discuss allegations, accuse
             * people, defend yourself, and at the end of that amount of time, the town will
             *
             */

            JOptionPane.showConfirmDialog(null,
                    "Round will be updated.",
                    "ALERT",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE);
            newUI.updateRound();

            //Precheck win conditions before hanging a player
            if(numOfMafia > playerList.size()-numOfMafia) {			//Mafia win condition
                mafWin = true;
                break;
            }

            if(numOfMafia == 0 || mafiaList.isEmpty()) {									//Town win condition
                townWin = true;
                break;
            }

            Scanner s = new Scanner(System.in);
            String removeIndex1 = JOptionPane.showInputDialog("Enter the index of the player the town has decided to hang today. Enter -1 if there is no consensus.");
            int removeIndex=Integer.valueOf(removeIndex1);
            if(removeIndex != -1) {
                playerList.get(removeIndex).setIsAlive(false);
                JOptionPane.showConfirmDialog(null,
                        "The town has decided to hang "+playerList.get(removeIndex).getName()+". Their role was "+playerList.get(removeIndex).getRole().toString()+".",
                        "Someone was killed!",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.PLAIN_MESSAGE);
                  if(!playerList.get(removeIndex).getRole().getIsTown()) {
                    numOfMafia = numOfMafia -1;
                    mafiaList.remove(playerList.get(removeIndex));

                }

            }

            removePlayer();
            newUI.updatePlayers(playerList);



            if(numOfMafia > playerList.size()-numOfMafia) {			//Mafia win condition
                mafWin = true;
            }

            if(numOfMafia == 0 || mafiaList.isEmpty()) {									//Town win condition
                townWin = true;
            }
        }

        if(mafWin) {
            JOptionPane.showConfirmDialog(null,
                    "Game over. The Mafia win.",
                    "GAME OVER",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE);
        }
        if(townWin) {
            JOptionPane.showConfirmDialog(null,
                    "Game over. The town wins.",
                    "GAME OVER",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE);
        }


        p=new Player();
        //the controller part of MVC.
        while (newUI.isDisplayable()) {
            Message message = null;
            try {
                message = queue.take();
            } catch (InterruptedException exception) {
                // do nothing
            }

            if(message.getClass() == MafiaMessage.class) {
                // button mafia was clicked, sets congrats message on panel
                MafiaMessage mafiaMessage = (MafiaMessage) message;
                p.setCongrats(mafiaMessage.getCongrats());
                newUI.updateCongrats(p.getCongrats());

            }
            else if(message.getClass() == TownMessage.class) {
                // button town was clicked, sets congrats message on panel
                TownMessage townMessage = (TownMessage) message;
                p.setCongrats(townMessage.getCongrats());
                newUI.updateCongrats(p.getCongrats());
            }

        }



    }








}
