public class Main {
    public static void main(String[] args) {
        boolean doThing = true;

        try {
            int i = 1;
            double d = 2.0;
            double sum = d/i;
            int definitelyCorrectValue = 4/3;
            int mult = definitelyCorrectValue * 3;
            double actuallyCorrectValue = 4.0/3;

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

            if (doThing) {
                System.out.println(0 / 0);
                System.out.println("what da");
            }
        } catch (Exception e) {
            System.out.println("cool error");
            System.out.println(e);
        }
    }
}