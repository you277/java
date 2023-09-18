import java.util.InputMismatchException;
import java.util.Scanner;
import java.lang.Thread;

public class OldMain {
    static int pick = 0;
    static int divisor = 0;
    static Scanner s2 = new Scanner(System.in);
    public static void main(String[] args) throws InterruptedException {
        double d = 2.0;
        int definitelyCorrectValue = 4 / 3;
        int mult = definitelyCorrectValue * 3;
        double actuallyCorrectValue = 4.0 / 3;

        mult += 2;
        mult /= 3;
        mult *= 4;
        mult ^= 8;

        System.out.println("things:");
        System.out.println(definitelyCorrectValue);
        System.out.println(actuallyCorrectValue);

        System.out.println("\nother thing:");
        System.out.println(mult + actuallyCorrectValue / definitelyCorrectValue % d);

        Scanner s = new Scanner(System.in);
        System.out.println("\ndo u want to do the thing (y/n): ");
        String doThing = s.nextLine();

        if (!doThing.equals("y") && !doThing.equals("n")) {
            s.close();
            if (doThing.equals("y/n")) {
                System.out.println("u think ur so funny");
            } else {
                System.out.println("NOT VALID INPUT!!!");
            }
            return;
        }
        if (doThing.equals("n")) {
            System.out.println("ok");
            s.close();
            return;
        }

        Die die = new Die();
        die.setSides(6);

        // thing i might use later
        for (int i = 0; i < 10; i++) {
            System.out.println(die.roll());
        }

        int diceRollPrediction = -1;
        while (diceRollPrediction == -1) {

            System.out.println("\nwhat will the dice roll on (1-6): ");
            Scanner s2 = new Scanner(System.in);
            try {
                pick = s2.nextInt();
                s2.close();
                System.out.println(pick);
                if (pick < 1 || pick > 6) {
                    System.out.println("OUT OF RANGE!!!");
                    continue;
                }
                diceRollPrediction = pick;
            } catch (InputMismatchException e) {
                s2.close();
                System.out.println("NOT AN INT!!!!");
                Thread.sleep(500);
            }
        }
        System.out.println("ok ur pick is " + diceRollPrediction);
        s2 = new Scanner(System.in);
        try {
            System.out.println("\ngive me an int and im gonna divide mult (" + mult + ") by it: ");

            s.close();
            divisor = s2.nextInt();

//            try {
//                divisor = s2.nextInt();
//            } catch (InputMismatchException e) {
//                System.out.println("NOT AN INT!!!!");
//                return;
//            }
            s.close();
            s2.close();

            System.out.println();
            System.out.println((int)(mult / divisor));
            System.out.println("what da congerartion :tada:");

            while (true) {
                // nvm fixed it i added throws InterruptedException to the function header thing
//                try {
                // why does sleeping throw an InterruptedException?
                Thread.sleep(1000);
//                } catch(InterruptedException e) {
                //
//                }
                System.out.println("hi");
            }
        } catch (ArithmeticException e) {
            System.out.println("it errored bruh why would you input 0");
        }
    }
}
