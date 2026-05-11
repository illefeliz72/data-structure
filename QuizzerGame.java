import java.util.Scanner;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Collections;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class QuizzerGame {
    static Scanner input = new Scanner(System.in);
    static ArrayList<String[]> players = new ArrayList<>();
    static ArrayList<String[]> questionBank = new ArrayList<>();

    static String playerFile = "players.txt";
    static String questionsFile = "questions.txt";

    public static void main(String[] args) {
        loadData();
        int mainChoice;

        do {
            System.out.println("\n====================================");
            System.out.println("   QUIZZER GAME: FINALS PROJECT");
            System.out.println("====================================");
            System.out.println("1. Player Registration");
            System.out.println("2. Question Bank Management");
            System.out.println("3. Play Module (Randomized Quiz)");
            System.out.println("4. Leaderboard (Sorted Scores)");
            System.out.println("5. Exit & Save Changes");
            System.out.print("Choice: ");
            mainChoice = input.nextInt();
            input.nextLine();

            switch (mainChoice) {
                case 1:
                    playerManagement();
                    break;
                case 2:
                    questionManagement();
                    break;
                case 3:
                    playModule();
                    break;
                case 4:
                    showLeaderboard();
                    break;
                case 5:
                    saveData();
                    System.out.println("Files updated. Game terminated.");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (mainChoice != 5);
    }

    public static void playerManagement() {
        System.out.println("\n[PLAYER REGISTRATION]");
        System.out.println("1. Add Player\n2. Edit Player\n3. Delete Player\n4. List Players");
        System.out.print("Choice: ");
        int sub = input.nextInt();
        input.nextLine();

        if (sub == 1) {
            System.out.print("Enter Name: ");
            String name = input.nextLine();
            players.add(new String[] { name, "0" });
            System.out.println("Player added successfully.");
        } else if (sub == 2) {
            System.out.print("Enter name to edit: ");
            String search = input.nextLine();
            for (String[] p : players) {
                if (p[0].equalsIgnoreCase(search)) {
                    System.out.print("New Name: ");
                    p[0] = input.nextLine();
                    System.out.println("Updated.");
                    return;
                }
            }
            System.out.println("Not found.");
        } else if (sub == 3) {
            System.out.print("Enter name to delete: ");
            String search = input.nextLine();
            for (int i = 0; i < players.size(); i++) {
                if (players.get(i)[0].equalsIgnoreCase(search)) {
                    players.remove(i);
                    System.out.println("Deleted.");
                    return;
                }
            }
            System.out.println("Not found.");
        } else if (sub == 4) {
            System.out.println("\n--- Player List ---");
            for (String[] p : players)
                System.out.println("Name: " + p[0] + " | High Score: " + p[1]);
        }
    }

    public static void questionManagement() {
        System.out.println("\n[QUESTION BANK]");
        System.out.println("1. Add Question\n2. Edit Question\n3. Delete Question\n4. List Questions");
        System.out.print("Choice: ");
        int sub = input.nextInt();
        input.nextLine();

        if (sub == 1) {
            System.out.print("Category: ");
            String cat = input.nextLine();
            System.out.print("Question: ");
            String q = input.nextLine();
            System.out.print("Answer: ");
            String a = input.nextLine();
            questionBank.add(new String[] { cat, q, a });
            System.out.println("Question added.");
        } else if (sub == 4) {
            for (String[] q : questionBank)
                System.out.println("[" + q[0] + "] " + q[1] + " (Ans: " + q[2] + ")");
        }
    }

    public static void playModule() {
        if (questionBank.isEmpty()) {
            System.out.println("Error: Question bank is empty.");
            return;
        }

        Stack<String[]> playStack = new Stack<>();
        ArrayList<String[]> shuffled = new ArrayList<>(questionBank);
        Collections.shuffle(shuffled);

        for (String[] q : shuffled)
            playStack.push(q);

        System.out.print("Enter your Player Name: ");
        String playerName = input.nextLine();
        int score = 0;

        System.out.println("\n--- QUIZ START ---");
        while (!playStack.isEmpty()) {
            String[] current = playStack.pop();
            System.out.println("\nCategory: " + current[0]);
            System.out.println("Q: " + current[1]);
            System.out.print("Answer: ");
            String playerAns = input.nextLine();

            if (playerAns.equalsIgnoreCase(current[2])) {
                System.out.println("Correct!");
                score += 10;
            } else {
                System.out.println("Wrong! Answer was: " + current[2]);
            }
        }

        updateHighScore(playerName, score);
        System.out.println("\nGame Over! Session Score: " + score);
    }

    public static void showLeaderboard() {
        if (players.isEmpty()) {
            System.out.println("No players found.");
            return;
        }
        players.sort((a, b) -> Integer.compare(Integer.parseInt(b[1]), Integer.parseInt(a[1])));
        System.out.println("\n--- LEADERBOARD (High Scores) ---");
        for (int i = 0; i < players.size(); i++) {
            System.out.println((i + 1) + ". " + players.get(i)[0] + " : " + players.get(i)[1]);
        }
    }

    private static void updateHighScore(String name, int score) {
        for (String[] p : players) {
            if (p[0].equalsIgnoreCase(name)) {
                if (score > Integer.parseInt(p[1]))
                    p[1] = String.valueOf(score);
                return;
            }
        }
        players.add(new String[] { name, String.valueOf(score) });
    }

    public static void saveData() {
        try (BufferedWriter pw = new BufferedWriter(new FileWriter(playerFile))) {
            for (String[] p : players)
                pw.write(p[0] + "," + p[1] + "\n");
        } catch (IOException e) {
            System.out.println("Save Error.");
        }

        try (BufferedWriter qw = new BufferedWriter(new FileWriter(questionsFile))) {
            for (String[] q : questionBank)
                qw.write(q[0] + "," + q[1] + "," + q[2] + "\n");
        } catch (IOException e) {
            System.out.println("Save Error.");
        }
    }

    public static void loadData() {
        try {
            File pf = new File(playerFile);
            if (pf.exists()) {
                BufferedReader pr = new BufferedReader(new FileReader(pf));
                String line;
                while ((line = pr.readLine()) != null)
                    players.add(line.split(","));
                pr.close();
            }
            File qf = new File(questionsFile);
            if (qf.exists()) {
                BufferedReader qr = new BufferedReader(new FileReader(qf));
                String line;
                while ((line = qr.readLine()) != null)
                    questionBank.add(line.split(","));
                qr.close();
            }
        } catch (Exception e) {
            System.out.println("New Session Initialized.");
        }
    }
}