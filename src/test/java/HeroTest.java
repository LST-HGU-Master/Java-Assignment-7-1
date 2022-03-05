import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.io.*;

public class HeroTest {
    @Test
    public void testSleep()
    {
        // action
        Hero h = new Hero();
        h.name = "太郎";
        h.hp = 90;
        h.sleep();

        // assertion
        assertEquals(100, h.hp);
    }

    @Test
    public void testSit()
    {
        // action
        Hero h = new Hero();
        h.name = "太郎";
        h.hp = 90;
        h.sit(90);

        // assertion
        assertEquals(180, h.hp);
    }

    @Test
    public void testSlip()
    {
        // action
        Hero h = new Hero();
        h.name = "太郎";
        h.hp = 90;
        h.slip();

        // assertion
        assertEquals(85, h.hp);
    }

    @Test
    public void testRun()
    {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        // action
        Hero h = new Hero();
        h.name = "太郎";
        h.hp = 90;
        h.run();

        // assertion
        String[] prints = bos.toString().split("\n");
        assertEquals("太郎は、逃げ出した!", prints[0]);
        assertEquals("GAMEOVER", prints[1]);
        assertEquals("最終HPは90でした", prints[2]);

        // undo the binding in System
        System.setOut(originalOut);
    }


}
