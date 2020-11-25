package rpg_lab;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AxeTest {

    private static final int AXE_ATTACK = 10;
    private static final int AXE_DURABILITY = 10;
    private static final int DUMMY_HEALTH = 1000;
    private static final int DUMMY_EXPERIENCE = 10;

    private Axe axe;
    private Dummy dummy;

    @Before
    public void setUp() {
        this.axe = new Axe(AXE_ATTACK, AXE_DURABILITY);
        this.dummy = new Dummy(DUMMY_HEALTH, DUMMY_EXPERIENCE);
    }

    @Test
    public void testAxeDurability() {
        Axe axe = new Axe(10, 10);
        Dummy dummy = new Dummy(10, 10);

        axe.attack(dummy);
        assertEquals(AXE_DURABILITY - 1, axe.getDurabilityPoints());
    }

    @Test
    public void testAxeCanLooseAllDurability() {
        Axe axe = new Axe(10, 10);
        Dummy dummy = new Dummy(1000, 10);
        for (int i = 0; i < AXE_DURABILITY; i++) {
            axe.attack(dummy);
        }
        assertEquals(0, axe.getDurabilityPoints());
    }

    @Test(expected = IllegalStateException.class)
    public void testAttackWhitBrokenAxe() {
        Axe axe = new Axe(AXE_ATTACK, 0);
        axe.attack(dummy);
    }

}