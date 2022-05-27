import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.io.*;
/**
 * @version (20220526)
 *  set all exclamation marks to Zenkaku-character
 **/
public class Prog71Test {

    @Test
    public void testMain()
    {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream bos;
        StandardInputStream in = new StandardInputStream();
        System.setIn(in);

        String[] member  = { "太郎", "花子" };
        String strHP = "150";
        try {
            for (String name : member) {
                // action
                bos = new ByteArrayOutputStream();
                System.setOut(new PrintStream(bos));
                Prog71.main(new String[]{name, strHP}); // 実行時引数をテストする場合
            
                // assertion
                String[] prints = bos.toString().split("\r\n|\n");
                assertEquals("勇者" + name +"を生み出しました！", prints[0]);
                assertEquals(name + "は、5秒座った！", prints[1]);
                assertEquals("HPが5ポイント回復した", prints[2]);
                assertEquals(name + "は、転んだ！", prints[3]);
                assertEquals("5のダメージ！", prints[4]);
                assertEquals(name + "は、25秒座った！", prints[5]);
                assertEquals("HPが25ポイント回復した", prints[6]);
                assertEquals(name + "は、逃げ出した！", prints[7]);
                assertEquals("GAMEOVER", prints[8]);
                assertEquals("最終HPは175でした", prints[9]);
            }
        } catch  (AssertionError err) {
        // undo the binding in System
            System.setOut(originalOut);
            throw err;
        }
        // undo the binding in System
        System.setOut(originalOut);
    }
}
