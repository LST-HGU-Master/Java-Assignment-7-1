import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import java.io.*;
/**
 * @version (20220526) set all exclamation marks to Zenkaku-character
 * @version (20261021) revised for try-catch removal, index safety, and local stream cleanup
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

        assertEquals(100, h.hp, "Hero.sleep() での hp の値（100への回復）が正しくありません!");

        String rawOutput = bos.toString().replace("\r\n", "\n");
        assertTrue(rawOutput.endsWith("\n"), 
            "Hero.sleep() の出力末尾に改行がありません! print ではなく println を使用しているか確認してください。"
        );

        String[] prints = rawOutput.split("\n");
        assertTrue(prints.length >= 1, "Hero.sleep() の出力行数が不足しています!");
        assertEquals(h.name + "は、眠って回復した！", prints[0], "Hero.sleep() の出力メッセージが指定と異なります!");
    }

    @Test
    public void testSit() {
        Hero h = new Hero();
        h.name = "太郎";
        h.hp = 90;
        int second = 15;
        h.sit(second);

        assertEquals(90 + second, h.hp, "Hero.sit() での hp の加算処理が正しくありません!");

        String rawOutput = bos.toString().replace("\r\n", "\n");
        assertTrue(rawOutput.endsWith("\n"), 
            "Hero.sit() の出力末尾に改行がありません! 各行のメッセージ出力後に println を使用してください。"
        );

        String[] prints = rawOutput.split("\n");
        assertTrue(prints.length >= 2, "Hero.sit() の出力行数が不足しています!");
        assertEquals(h.name + "は、" + second + "秒座った！", prints[0], "Hero.sit() の1行目出力メッセージが正しくありません!");
        assertEquals("HPが" + second + "ポイント回復した", prints[1], "Hero.sit() の2行目出力メッセージが正しくありません!");
    }

    @Test
    public void testSlip()
    {
        // action
        Hero h = new Hero();
        h.name = "太郎";
        h.hp = 90;
        h.slip();

        assertEquals(85, h.hp, "Hero.slip() での hp の減算処理（5ダメージ）が正しくありません!");

        String rawOutput = bos.toString().replace("\r\n", "\n");
        assertTrue(rawOutput.endsWith("\n"), 
            "Hero.slip() の出力末尾に改行がありません! 各行のメッセージ出力後に println を使用してください。"
        );

        String[] prints = rawOutput.split("\n");
        assertTrue(prints.length >= 2, "Hero.slip() の出力行数が不足しています!");
        assertEquals(h.name + "は、転んだ！", prints[0], "Hero.slip() の1行目出力メッセージが正しくありません!");
        assertEquals("5のダメージ！", prints[1], "Hero.slip() の2行目出力メッセージが正しくありません!");    
    }

    @Test
    public void testRun() {
        Hero h = new Hero();
        h.name = "太郎";
        h.hp = 90;
        h.run();

        String rawOutput = bos.toString().replace("\r\n", "\n");
        assertTrue(rawOutput.endsWith("\n"), 
            "Hero.run() の出力末尾に改行がありません! 各行のメッセージ出力後に println を使用してください。"
        );

        String[] prints = rawOutput.split("\n");
        assertTrue(prints.length >= 3, "Hero.run() の出力行数が不足しています!");
        assertEquals(h.name + "は、逃げ出した！", prints[0], "Hero.run() の1行目出力メッセージが正しくありません!");
        assertEquals("GAMEOVER", prints[1], "Hero.run() の2行目出力メッセージ（GAMEOVER）が正しくありません!");
        assertEquals("最終HPは" + h.hp + "でした", prints[2], "Hero.run() の3行目出力メッセージが正しくありません!");
    }

}
