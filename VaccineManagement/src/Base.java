import java.util.Scanner;

public class Base {
    Scanner input = new Scanner(System.in);

    public int inputInt() {
        return Integer.parseInt(input.nextLine());
    }

    public static void main(String[] args) {
        Base newBase = new Base();
        Menu menu = new Menu();

        menu.readData();

        while (true) {
            try {
                System.out.println("Start program choose: start(1)/ quit(0)");
                int answer = newBase.inputInt();
                if (answer == 0) return;
                if (answer == 1) break;
                System.out.println("Wrong input");
            } catch (Exception e) {
                System.out.println("Wrong input, expect int type.");
            }
        }


        while (true) {
            try {
                System.out.println("Choose your option: show(1) /add(2) /update(3) /delete(4) /search(5) /quit(0)");
                int answer = newBase.inputInt();
                if (answer == 0) {
                    System.out.println("Thank you for using my system!");
                    return;
                }

                switch (answer) {
                    case 1 -> menu.show();
                    case 2 -> menu.add();
                    case 3 -> menu.update();
                    case 4 -> menu.delete();
                    case 5 -> menu.search();
                    default -> System.out.println("An error has occurred!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Wrong input, expect int type!");
            }
        }

    }
}