import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.io.*;

public class Prog71Test {

    @Test
    public void testMain()
    {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        StandardInputStream in = new StandardInputStream();
        System.setIn(in);

        // action
        Prog71.main(new String[]{"太郎", "150"}); // 実行時引数をテストする場合

        // assertion
        String[] prints = bos.toString().split("\n");
        assertEquals("勇者太郎を生み出しました！", prints[0]);
        assertEquals("太郎は、5秒座った!", prints[1]);
        assertEquals("HPが5ポイント回復した", prints[2]);
        assertEquals("太郎は、転んだ!", prints[3]);
        assertEquals("5のダメージ!", prints[4]);
        assertEquals("太郎は、25秒座った!", prints[5]);
        assertEquals("HPが25ポイント回復した", prints[6]);
        assertEquals("太郎は、逃げ出した!", prints[7]);
        assertEquals("GAMEOVER", prints[8]);
        assertEquals("最終HPは175でした", prints[9]);

        // action
        bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));
        Prog71.main(new String[]{"花子", "150"});

        // assertion
        prints = bos.toString().split("\n");
        assertEquals("勇者花子を生み出しました！", prints[0]);
        assertEquals("花子は、5秒座った!", prints[1]);
        assertEquals("HPが5ポイント回復した", prints[2]);
        assertEquals("花子は、転んだ!", prints[3]);
        assertEquals("5のダメージ!", prints[4]);
        assertEquals("花子は、25秒座った!", prints[5]);
        assertEquals("HPが25ポイント回復した", prints[6]);
        assertEquals("花子は、逃げ出した!", prints[7]);
        assertEquals("GAMEOVER", prints[8]);
        assertEquals("最終HPは175でした", prints[9]);

        // undo the binding in System
        System.setOut(originalOut);
    }
}
