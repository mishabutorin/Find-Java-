import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.*;

public class MainTest {
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

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
        Assert.assertEquals("testfolder/name.txt\n", output.toString());
    }


    @Test
    public void withoutrecursiveandD() {
        Main.main(new String[]{"pom.xml"});
        Assert.assertEquals("./pom.xml\n", output.toString());
    }

    @Test
    public void recursivetwo() {
        Main.main(new String[]{"-r", "-d", "testfolder", "new.txt"});
        Assert.assertEquals("testfolder/testfolder2/new.txt\ntestfolder/new.txt\n", output.toString());
    }

    @Test
    public void namefirst() {
        Main.main(new String[]{"name.txt", "-r", "-d", "testfolder"});
        Assert.assertEquals("testfolder/name.txt\n", output.toString());
    }

    @Test
    public void n() {
        Main.main(new String[]{"-r", "-d", "testfolder", "testfolder"});
        Assert.assertEquals("testfolder/testfolder\n", output.toString());
    }
}
