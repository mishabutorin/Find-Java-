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
    public void recursive() {
        Main.main(new String[]{"-r", "-d", "testfolder/testfolder2", "new.txt"});
        Assert.assertEquals("/Users/valentinka/IdeaProjects/Find-Java-/testfolder/testfolder2/new.txt\n", output.toString());
    }

    @Test
    public void recursiveNF() {
        Main.main(new String[]{"-r", "-d", "testfolder", "name.txt"});
        Assert.assertEquals("/Users/valentinka/IdeaProjects/Find-Java-/testfolder/name.txt\n", output.toString());
    }

    @Test
    public void withoutrecursive() {
        Main.main(new String[]{"-d", "testfolder", "name.txt"});
        Assert.assertEquals("/Users/valentinka/IdeaProjects/Find-Java-/testfolder/name.txt\n", output.toString());
    }

    @Test
    public void withoutrecursiveandD() {
        Main.main(new String[]{"pom.xml"});
        Assert.assertEquals("/Users/valentinka/IdeaProjects/Find-Java-/pom.xml\n", output.toString());
    }

    @Test
    public void recursivetwo() {
        Main.main(new String[]{"-r", "-d", "testfolder", "new.txt"});
        Assert.assertEquals("/Users/valentinka/IdeaProjects/Find-Java-/testfolder/testfolder2/new.txt\n" +
                "/Users/valentinka/IdeaProjects/Find-Java-/testfolder/new.txt\n", output.toString());
    }

    @Test
    public void namefirst() {
        Main.main(new String[]{"name.txt", "-r", "-d", "testfolder"});
        Assert.assertEquals("/Users/valentinka/IdeaProjects/Find-Java-/testfolder/name.txt\n", output.toString());
    }
}
