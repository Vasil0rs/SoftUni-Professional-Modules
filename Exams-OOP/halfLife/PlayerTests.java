package halfLife;


import org.junit.Assert.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTests {


    @Test
    public void testConstructor() {
        Player player = new Player("vaso", 100);
        assertNotNull(player);
    }

    @Test
    public void testGetUserName() {
        Player player = new Player("vaso", 100);
        String actualName = player.getUsername();
        String expectedName = "vaso";
        assertEquals(expectedName, actualName);
    }

    @Test(expected = NullPointerException.class)
    public void testSetUserName() {
        Player player = new Player(null, 100);
    }

    @Test
    public void testGetHealth() {
        Player player = new Player("vaso", 100);
        int actualHealth = player.getHealth();
        int expectedHealth = 100;
        assertEquals(expectedHealth, actualHealth);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetHealth() {
        new Player("vaso", -100);
    }

    @Test
    public void testGetGuns() {
        Player player = new Player("vaso", 100);
        int actualLength = player.getGuns().size();
        int expectedLength = 0;
        assertEquals(expectedLength, actualLength);
    }

    @Test
    public void tesTakeDamage() {
        Player player = new Player("vaso", 100);
        player.takeDamage(20);
        int actualHealth = player.getHealth();
        int expectedHealth = 80;
        assertEquals(expectedHealth, actualHealth);
    }

    @Test(expected = NullPointerException.class)
    public void testAddGun() {
        Player player = new Player("vaso", 100);
        player.addGun(null);
    }

    @Test
    public void testValidAddGun() {
        Player player = new Player("vaso", 100);
        Gun gun = new Gun("rifle", 10);
        player.addGun(gun);
        Gun expectedGun = gun;
        Gun actualGun = player.getGun(gun.getName());
        assertEquals(expectedGun, actualGun);
    }

    @Test
    public void testRemoveGun() {
        Player player = new Player("vaso", 100);
        Gun gun = new Gun("rifle", 10);
        player.addGun(gun);
        boolean actualGun = player.removeGun(gun);
        assertTrue(actualGun);
    }
    @Test
    public void testGetGun(){
        Player player = new Player("vaso", 100);
        Gun gun = new Gun("rifle", 10);
        player.addGun(gun);
        Gun expectedGun = gun;
        Gun actualGun = player.getGun(gun.getName());
        assertEquals(expectedGun, actualGun);
    }
    @Test
    public void testGetInvalidGun(){
        Player player = new Player("vaso", 100);
        Gun gun = new Gun("rifle", 10);
        player.addGun(gun);
        Gun expectedGun = null;
        Gun actualGun = player.getGun("m16");
        assertEquals(expectedGun, actualGun);
    }
}
