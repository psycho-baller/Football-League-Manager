import java.io.File;

public class Main {

    public static String data;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Invalid number of arguments!");
            System.exit(1);
        }
        data = args[0];
        int numOfClubs = Integer.parseInt(args[1]);
        PremierLeagueManager plm = new PremierLeagueManager(numOfClubs);
    }
    public static File getData() {
        File file = new File(data);
        if (!file.exists() || !file.isFile() || !file.canWrite()) {
            System.err.printf("The world file %s does not exist!%n", file.getAbsoluteFile());
            System.exit(1);
        }return file;
    }
}