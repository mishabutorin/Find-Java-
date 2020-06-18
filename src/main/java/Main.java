import java.io.File;

public class Main {

    public static void main(String[] args) {
        boolean r = false;
        String path = ".";
        String name = "";
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-r": {
                    r = true;
                    break;
                }
                case "-d": {
                    if (i < args.length - 1) {
                        path = args[i + 1];
                        i++;
                    } else {
                        System.err.println("Чтобы программа работала корректно нужно ввести: [-r] [-d directory] %filename");
                        System.exit(1);
                    }
                    break;
                }
                default: {
                    name = args[i];
                }
            }
        }
        searchFile(new File(path), name, r);
    }

    private static void searchFile(File path, String name, boolean recursive) {
        if (recursive) {
            if (path.isDirectory()) {
                File[] directoryFiles = path.listFiles();
                if (directoryFiles != null) {
                    for (File file : directoryFiles) {
                        if (file.isDirectory()) {
                            searchFile(file, name, true);
                        }
                        if (name.equals(file.getName())) {
                            System.out.println(file.getPath());
                        }
                    }
                }
            }
        } else {
            File[] directoryFiles = path.listFiles();
            if (directoryFiles != null) {
                for (File file : directoryFiles) {
                    if (name.equals(file.getName())) {
                        System.out.println(file.getPath());
                    }
                }
            }
        }
    }
}



