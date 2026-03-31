import java.util.*;
import java.io.*;

public class TabletQuizzer {

    static Scanner input = new Scanner(System.in);
    static ArrayList<String[]> players = new ArrayList<>();
    static ArrayList<String[]> questions = new ArrayList<>();
    static String fileName = "players.txt";
    static String questionFile = "questions.txt";

    public static void main(String[] args) {
        loadFromFile();
        loadQuestions();
        int choice;

        do {
            System.out.println("\n===== GRAPHIC TABLET QUIZ =====");
            System.out.println("1. Player Registration");
            System.out.println("2. Play (Tablet Questions)");
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

        if (questions.isEmpty()) {
            System.out.println("No questions found.");
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

        Collections.shuffle(questions);

        int score = 0;

        System.out.println("\n--- STARTING TABLET KNOWLEDGE QUIZ ---");

        for (int i = 0; i < questions.size(); i++) {
            String[] q = questions.get(i);

            System.out.println("\nQ" + (i + 1) + ": " + q[0]);
            System.out.println(q[1]);
            System.out.println(q[2]);
            System.out.println(q[3]);
            System.out.print("Your Answer (A/B/C): ");

            String ans = input.nextLine().trim().toUpperCase();

            if (ans.equals(q[4])) {
                System.out.println("CORRECT!");
                score++;
            } else {
                System.out.println("WRONG! Answer: " + q[4]);
            }
        }

        int currentTotal = Integer.parseInt(currentPlayer[1]);
        currentPlayer[1] = String.valueOf(currentTotal + score);

        System.out.println("\nQuiz Finished! Score: " + score + "/" + questions.size());
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

    public static void loadQuestions() {
        try (Scanner sc = new Scanner(new File(questionFile))) {
            while (sc.hasNextLine())
                questions.add(sc.nextLine().split(","));
        } catch (Exception e) {
        }
    }
}