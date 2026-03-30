import java.util.*;
import java.io.*;

public class TabletQuizzer {

    static Scanner input = new Scanner(System.in);
    static ArrayList<String[]> players = new ArrayList<>();
    static String fileName = "players.txt";

    public static void main(String[] args) {
        loadFromFile();
        int choice;

        do {
            System.out.println("\n===== GRAPHIC TABLET QUIZ =====");
            System.out.println("1. Player Registration");
            System.out.println("2. Play (15 Tablet Questions)");
            System.out.println("3. Leaderboard");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            while (!input.hasNextInt()) {
                System.out.println("Please enter 1-4.");
                input.next();
            }
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    registerPlayer();
                    break;
                case 2:
                    playTabletQuiz();
                    break;
                case 3:
                    listPlayers();
                    break;
                case 4:
                    saveToFile();
                    System.out.println("Saved. Goodbye!");
                    break;
            }
        } while (choice != 4);
    }

    public static void playTabletQuiz() {
        if (players.isEmpty()) {
            System.out.println("Register a player first!");
            return;
        }

        System.out.print("Enter player name: ");
        String name = input.nextLine();
        String[] currentPlayer = null;
        for (String[] p : players) {
            if (p[0].equalsIgnoreCase(name)) {
                currentPlayer = p;
                break;
            }
        }

        if (currentPlayer == null) {
            System.out.println("Player not found.");
            return;
        }

        int score = 0;

        // Question Format: {Question, A, B, C, CorrectLetter}
        String[][] qBank = {
                { "Which brand is known for the 'Intuos' and 'Cintiq' series?", "A. Huion", "B. Wacom", "C. XP-Pen",
                        "B" },
                { "What is the standard high-end pressure sensitivity level?", "A. 2048", "B. 4096", "C. 8192", "C" },
                { "Which connection type is most common for modern wireless tablets?", "A. Bluetooth", "B. VGA",
                        "C. Serial Port", "A" },
                { "What does 'LPI' stand for in tablet specs?", "A. Levels Per Inch", "B. Lines Per Inch",
                        "C. Low Price Index", "B" },
                { "Which brand produces the 'Kamvas' pen displays?", "A. Huion", "B. Gaomon", "C. Apple", "A" },
                { "A 'Pen Display' differs from a 'Pen Tablet' because it has a...?", "A. Battery",
                        "B. Built-in Screen", "C. Eraser", "B" },
                { "Which brand is famous for the 'Artist' and 'Deco' series?", "A. Wacom", "B. XP-Pen", "C. Microsoft",
                        "B" },
                { "What is the 'report rate' (PPS/RPS) of a tablet?", "A. Data sent per second", "B. Price per sale",
                        "C. Pixel per stroke", "A" },
                { "Which of these is a popular budget tablet brand?", "A. Gaomon", "B. Wacom Cintiq", "C. iPad Pro",
                        "A" },
                { "What technology do most battery-free pens use?", "A. EMR (Electromagnetic Resonance)",
                        "B. Bluetooth Only", "C. Solar Power", "A" },
                { "Which tablet is often used for the game 'Osu!'?", "A. XP-Pen G640", "B. Wacom Cintiq 24",
                        "C. iPad Mini", "A" },
                { "What is the shortcut for 'Undo' commonly mapped to tablet buttons?", "A. Ctrl+S", "B. Ctrl+Z",
                        "C. Ctrl+V", "B" },
                { "Which type of nib provides a 'paper-like' feel?", "A. Plastic", "B. Felt", "C. Metal", "B" },
                { "'Parallax' in a pen display refers to the gap between...?", "A. Pen tip and cursor",
                        "B. Screen and table", "C. Price and Quality", "A" },
                { "Which brand is considered the industry standard for professional studios?", "1. Huion", "2. Wacom",
                        "3. XP-Pen", "B" }
        };

        System.out.println("\n--- STARTING TABLET KNOWLEDGE QUIZ ---");
        for (int i = 0; i < qBank.length; i++) {
            System.out.println("\nQ" + (i + 1) + ": " + qBank[i][0]);
            System.out.println(qBank[i][1]);
            System.out.println(qBank[i][2]);
            System.out.println(qBank[i][3]);
            System.out.print("Your Answer (A/B/C): ");
            String ans = input.nextLine().trim().toUpperCase();

            if (ans.equals(qBank[i][4])) {
                System.out.println("CORRECT! +1 Point");
                score++;
            } else {
                System.out.println("WRONG! The answer was " + qBank[i][4]);
            }
        }

        int currentTotal = Integer.parseInt(currentPlayer[1]);
        currentPlayer[1] = String.valueOf(currentTotal + score);
        System.out.println("\nQuiz Finished! You scored: " + score + "/15");
    }


    public static void registerPlayer() {
        System.out.print("Enter Name: ");
        players.add(new String[] { input.nextLine(), "0" });
        System.out.println("Player registered!");
    }

    public static void listPlayers() {
        System.out.println("\n--- LEADERBOARD ---");
        for (String[] p : players)
            System.out.println(p[0] + " | Points: " + p[1]);
    }

    public static void saveToFile() {
        try (PrintWriter out = new PrintWriter(new FileWriter(fileName))) {
            for (String[] p : players)
                out.println(p[0] + "," + p[1]);
        } catch (Exception e) {
        }
    }

    public static void loadFromFile() {
        try (Scanner sc = new Scanner(new File(fileName))) {
            while (sc.hasNextLine())
                players.add(sc.nextLine().split(","));
        } catch (Exception e) {
        }
    }
}