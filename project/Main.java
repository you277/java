import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int i = 1;
        double d = 2.0;
        double sum = d / i;
        int definitelyCorrectValue = 4 / 3;
        int mult = definitelyCorrectValue * 3;
        double actuallyCorrectValue = 4.0 / 3;

        mult += 2;
        mult /= 3;
        mult *= 4;
        mult ^= 8;

        System.out.println(sum);

        System.out.println("things:");
        System.out.println(definitelyCorrectValue);
        System.out.println(actuallyCorrectValue);

        int sumThing = i + definitelyCorrectValue;

        System.out.println("sumThing:");
        System.out.println(sumThing);
        System.out.println("other thing:");
        System.out.println(mult + actuallyCorrectValue / definitelyCorrectValue % d);

        Scanner s = new Scanner(System.in);
        System.out.println("do u want to do the thing (y/n): ");
        String doThing = s.nextLine();

        if (!doThing.equals("y") && !doThing.equals("n")) {
            System.out.println("NOT VALID INPUT!!!");
            s.close();
            return;
        }

        if (doThing.equals("n")) {
            System.out.println("ok");
            s.close();
            return;
        }
        try {
            System.out.println("give me an int and im gonna divide mult (" + mult + ") by it: ");
            String div = s.nextLine();
            s.close();

            int divisor;
            try {
                divisor = Integer.parseInt(div);
            } catch (Exception e) {
                System.out.println("NOT AN INT!!!!!!");
                return;
            }

            System.out.println(mult / divisor);
            System.out.println("what da congerartion :tada:");
        } catch (Exception e) {
            System.out.println("it errored bruh why would you input 0");
            System.out.println(e);
        }
    }
}
