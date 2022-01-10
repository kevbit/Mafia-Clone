
import static org.junit.jupiter.api.Assertions.assertEquals;

import Model.*;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;



/**
 * Class with unit tests for methods like getters and setters.
 */
public class MafiaUnitTesting
{
    Doctor doctor = Doctor.getInstance();									//Recommend at least 6-7 players for an actually decent
    Sheriff sheriff1 = new Sheriff();
    Mafia maf1 = new Mafia();
    Mafia.Framer maf2 = new Mafia.Framer();
    Villager village1 = new Villager();
    Villager village2 = new Villager();
    Villager village3 = new Villager();
    Vigilante vig = Vigilante.getInstance();

    /**test Player class methods
     */
    @Test
    public void testPlayer()
    {
        Player player=new Player("Bob", 3);
        assertEquals("Bob", player.getName());
        assertEquals(3, player.getIndex());
        player.setIsAlive(false);
        assertEquals(false, player.checkPlayerAlive());
        assertEquals(false, player.getIsHealed());
        player.setIsFramed(true);
        assertEquals(true, player.getIsFramed());

        Player player2=new Player();
        player2.setCongrats("Congrats");
        assertEquals("Congrats", player2.getCongrats());
    }

    /**test Role class methods
     */
    @Test
    public void testRole()
    {
        Role role=new Role();
        assertEquals(true,role.getIsTown());
        Role[] array=Role.returnList(6);
        Role[] arrayCompare=new Role[]{doctor, sheriff1, maf1, village1, village2, village3};
        assertEquals(arrayCompare[1].toString(), array[1].toString());

    }

    /**test Doctor class methods
     */
    @Test
    public void testDoctor()
    {
        int index=doctor.heal();
        assertEquals(2,index); //assuming when we test this we enter in index 2.
    }

    /**test Mafia class methods
     */
    @Test
    public void testMafia()
    {
        int index1=maf1.hit();
        int index2=maf2.frame();
        assertEquals(0,index1); //assuming when we test mafia we enter in index 0.
        assertEquals(1,index2); //assuming when we test framer we enter in index 1.

    }

    /**test Sheriff class methods
     */
    @Test
    public void testSheriff()
    {
        int index=sheriff1.Interrogate();
        assertEquals(3,index); //assuming when we test this we enter in index 3.
    }

    /**test Vigilante class methods
     */
    @Test
    public void testVig()
    {
        int index=vig.shoot();
        assertEquals(4,index); //assuming when we test this we enter in index 4.
    }


}
