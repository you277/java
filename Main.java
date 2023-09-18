import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        String option = "t";
        while (!newOption.equals("y") && !newOption.equals("n")) {
            System.out.println("\ndo you want to do the thing (y/n):");
            option = s.nextLine();
            if (option.equals("y/n")) {
                System.out.println("you think ur so funny");
            }
        }
        s.close();

        if (option.equals("n")) {
            System.out.println("ok");
            return;
        }
    }
}
