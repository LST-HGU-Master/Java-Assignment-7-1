import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import java.io.*;
/**
 * @version (20220526) set all exclamation marks to Zenkaku-character
 * @version (20261021) revised for lifecycle management, explicit trailing newline validation, and index safety
 **/
public class Prog71Test {
    InputStream originalIn;
    PrintStream originalOut;
    ByteArrayOutputStream bos;
    StandardInputStream in;

    @BeforeEach
    void before() {
        // back up binding
        originalIn  = System.in;
        originalOut = System.out;
        // modify binding
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
    public void testMain()
    {
        String[] member  = { "太郎", "花子" };
        String strHP = "150";

        for (String name : member) {
            // action
            bos = new ByteArrayOutputStream();
            System.setOut(new PrintStream(bos));

            Prog71.main(new String[]{name, strHP}); // 実行時引数

            String rawOutput = bos.toString().replace("\r\n", "\n");
            // 1. 末尾改行のチェック (print ではなく println の使用を強制)
            assertTrue(rawOutput.endsWith("\n"), 
                "Prog71.main() の出力末尾に改行がありません! 各メッセージの出力には println を使用してください。"
            );

            // 2. 行数チェック (10行分存在するか)
            String[] prints = rawOutput.split("\n");
            assertTrue(prints.length >= 10, 
                "Prog71.main() の実行結果の行数が不足しています（全10行必要です）。メッセージ出力の直後に改行（println）を入れているか確認してください。"
            );

            // 3. 各行の出力メッセージと計算結果（150 + 5 - 5 + 25 = 175）の検証
            assertEquals("勇者" + name + "を生み出しました！", prints[0].trim(), "1行目の生成メッセージが正しくありません。");
            assertEquals(name + "は、5秒座った！", prints[1].trim(), "2行目のメッセージが正しくありません。");
            assertEquals("HPが5ポイント回復した", prints[2].trim(), "3行目のメッセージが正しくありません。");
            assertEquals(name + "は、転んだ！", prints[3].trim(), "4行目のメッセージが正しくありません。");
            assertEquals("5のダメージ！", prints[4].trim(), "5行目のメッセージが正しくありません。");
            assertEquals(name + "は、25秒座った！", prints[5].trim(), "6行目のメッセージが正しくありません。");
            assertEquals("HPが25ポイント回復した", prints[6].trim(), "7行目のメッセージが正しくありません。");
            assertEquals(name + "は、逃げ出した！", prints[7].trim(), "8行目のメッセージが正しくありません。");
            assertEquals("GAMEOVER", prints[8].trim(), "9行目のメッセージが正しくありません。");
            assertEquals("最終HPは175でした", prints[9].trim(), "10行目の最終HP出力が正しくありません（初期HP150 + 5 - 5 + 25 = 175）。");
        }
    }
}
