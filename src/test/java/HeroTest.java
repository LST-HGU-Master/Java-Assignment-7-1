import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import java.io.*;
/**
 * @version (20220526)
 *  set all exclamation marks to Zenkaku-character
 **/
public class HeroTest {
    InputStream originalIn;
    PrintStream originalOut;
    ByteArrayOutputStream bos;
    StandardInputStream in;
    
    @BeforeEach
    void before() {
        //back up binding
        originalIn  = System.in;
        originalOut = System.out;
        //modify binding
        bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));
        
        in = new StandardInputStream();
        System.setIn(in);
    }
    
    @AfterEach
    void after() {
       System.setOut(originalOut);
       System.setIn(originalIn);
    }
    
    @Test
    public void testSleep()
    {
        // action
        Hero h = new Hero();
        h.name = "太郎";
        h.hp = 90;
        h.sleep();

        // assertion
        String[] prints = bos.toString().split("\r\n|\n");
        String msg = "Hero.sleep()の出力結果が指定と異なります!";
        try {
            assertEquals(100, h.hp, "Hero.sleep()でのhpの値が指定と異なります!");
            assertEquals(h.name + "は、眠って回復した！", prints[0], msg);
        } catch (AssertionError err) {
        // undo the binding in System
            after();
            throw err;
        }
        after();
    }

    @Test
    public void testSit()
    {
        // action
        Hero h = new Hero();
        h.name = "太郎";
        h.hp = 90;
        int second = 90;
        h.sit(second);

        // assertion
        String[] prints = bos.toString().split("\r\n|\n");
        String msg = "Hero.sit()の出力結果が指定と異なります!";
        try {
            assertEquals(90+second, h.hp, "Hero.sit()でのhpの変化量が指定と異なります!");
            assertEquals(h.name + "は、" + second + "秒座った！", prints[0], msg);
            assertEquals("HPが" + second +"ポイント回復した", prints[1], msg);
        } catch (AssertionError err) {
        // undo the binding in System
            after();
            throw err;
        }
        after();
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
        String[] prints = bos.toString().split("\r\n|\n");
        String msg = "Hero.slip()の出力結果が指定と異なります!";
        try {
            assertEquals(85, h.hp, "Hero.slip()でのhpの変化量が指定と異なります!");
            assertEquals(h.name + "は、転んだ！", prints[0], msg);
            assertEquals("5のダメージ！", prints[1], msg);
        } catch (AssertionError err) {
        // undo the binding in System
            after();
            throw err;
        }
        after();
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
        String[] prints = bos.toString().split("\r\n|\n");
        String msg = "Hero.run()の出力結果が指定と異なります!";
        try {
            assertEquals(h.name + "は、逃げ出した！", prints[0], msg);
            assertEquals("GAMEOVER", prints[1], msg);
            assertEquals("最終HPは" + h.hp + "でした", prints[2], msg);
        } catch (AssertionError err) {
        // undo the binding in System
            after();
            throw err;
        }
        finally {
            after();
        }
    }

}
