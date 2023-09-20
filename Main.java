import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        String option = "t";
        while (!option.equals("y") && !option.equals("n")) {
            System.out.println("\ndo you want to do the thing (y/n):");
            option = s.nextLine();
            if (option.equals("y/n")) {
                System.out.println("\nyou think ur so funny");
            }
        }

        if (option.equals("n")) {
            System.out.println("ok");
            return;
        }

        Die die = new Die(6);
        int points = 0;

        System.out.println("\nif i roll 12 you die and lose all ur points");

        boolean doThing = true;
        while (doThing) {
            System.out.println("\ndo you want to roll (y/n) (points: " + points + "):");
            String input = s.nextLine();

            if (input.equals("y")) {
                int roll1 = die.roll();
                int roll2 = die.roll();
                System.out.println("rolls were " + roll1 + " and " + roll2);
                if (roll1 + roll2 == 12) {
                    doThing = false;
                    System.out.println("HAHA I GOT 12 YOU DIE NOW!!!");
                    System.out.println("-" + points + " POINTS LOL");
                    points = 0;
                } else {
                    System.out.println("ok cool you get 1 point i guess");
                    points += 1;
                }

            } else if (input.equals("n")) {
                doThing = false;
                System.out.println("ok");

            } else if (input.equals("y/n")) {
                System.out.println("STOP");

            } else {
                System.out.println("FOLLOW DIRECTIONS YOU FOOL...");
            }
        }

        System.out.println("\nfinal points: " + points);
    }
}
