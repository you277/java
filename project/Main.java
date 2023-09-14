import java.util.InputMismatchException;
import java.util.Scanner;
import java.lang.Thread;

public class Main {
    public static void main(String[] args) {
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

        try {
            System.out.println("\ngive me an int and im gonna divide mult (" + mult + ") by it: ");
            int divisor;
            try {
                divisor = s.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("NOT AN INT!!!!");
                return;
            }
            s.close();

            System.out.println();
            System.out.println(mult / divisor);
            System.out.println("what da congerartion :tada:");

            while (true) {
                try {
                    // why does sleeping throw an InterruptedException?
                    Thread.sleep(1000);
                } catch(InterruptedException e) {
                    //
                }
                System.out.println("hi");
            }
        } catch (ArithmeticException e) {
            System.out.println("it errored bruh why would you input 0");
        }
    }
}
