import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        String option = "t";
        boolean validOption = false;
        while (validOption == false) {
            System.out.println("\ndo you want to do the thing (y/n):");
            String newOption = s.nextLine();
            if (newOption.equals("y") || newOption.equals("n")) {
                option = newOption;
                validOption = true;
                break;
            }
            if (newOption.equals("y/n")) {
                System.out.println("you think ur so funny");
            }
        }

        if (option.equals("n")) {
            System.out.println("ok");
            return;
        }
    }
}
