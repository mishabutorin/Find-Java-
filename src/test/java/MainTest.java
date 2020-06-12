import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

public class MainTest {
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    Main main = new Main();

    @org.junit.Test

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(output));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
    }

    @Test
    public void recursiveNF() {
        Main.main(new String[]{"-r", "-d", "testfolder", "name.txt"});
        File path = new File ("testfolder/name.txt");
        Assert.assertEquals(path.getAbsolutePath() + "\n", output.toString());
    }


    @Test
    public void withoutrecursiveandD() {
        Main.main(new String[]{"pom.xml"});
        File path = new File ("pom.xml");
        Assert.assertEquals(path.getAbsolutePath() + "\n", output.toString());
    }

    @Test
    public void recursivetwo() {
        Main.main(new String[]{"-r", "-d", "testfolder", "new.txt"});
        File path = new File ("testfolder/testfolder2/new.txt");
        File path0 = new File("testfolder/new.txt");
        Assert.assertEquals(path.getAbsolutePath() + "\n" + path0.getAbsolutePath() + "\n", output.toString());
    }

    @Test
    public void namefirst() {
        Main.main(new String[]{"name.txt", "-r", "-d", "testfolder"});
        File path = new File ("testfolder/name.txt");
        Assert.assertEquals(path.getAbsolutePath() + "\n", output.toString());
    }

    @Test
    public void n() {
        main.main(new String[]{"-r", "-d", "testfolder", "testfolder.txt"});
        File path = new File ("testfolder/testfolder.txt");
        Assert.assertEquals(path.getAbsolutePath() + "\n", output.toString());
    }
}
