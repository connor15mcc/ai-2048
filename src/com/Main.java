import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        Game x = new Game();
        System.out.println("Welcome to the a new game of 2048:");
        System.out.println(x);

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            if (x.gameOver()) {
                System.out.println("Sorry, you lost!");
                break;
            }
            int dir = sc.nextInt();
            if (dir > 3 || dir < 0) {
                System.out.println("Sorry, bad input! Try again");
                continue;
            }
            x.move(dir);
            System.out.println(x);
        }
    }
}