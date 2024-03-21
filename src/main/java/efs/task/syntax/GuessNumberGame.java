package efs.task.syntax;
import java.util.Random;
import java.util.Scanner;


public class GuessNumberGame {
    int m;
    //Do not modify main method
    public static void main(String[] args) {
        try {
            GuessNumberGame game = new GuessNumberGame(args.length > 0 ? args[0] : "");
            game.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public GuessNumberGame(String argument) {
        try {
            m = Integer.parseInt(argument);
        } catch (NumberFormatException e) {
            System.out.println(UsefulConstants.WRONG_ARGUMENT);
            throw new IllegalArgumentException();
        }
        if (m < 1 || m > UsefulConstants.MAX_UPPER_BOUND) {
            System.out.println(UsefulConstants.WRONG_ARGUMENT);
            throw new IllegalArgumentException();
        }
    }

    public void play() {
        System.out.println("<1," + m + ">");

        Scanner scanner = new Scanner(System.in);
        Random rnd = new Random();
        int attemptsLimit = (int) (Math.abs((Math.log(m) / Math.log(2))) + 1);
        int numberOfAttempts=0;
        int userNumber;
        int rndNumber = rnd.nextInt(m)+1;
        boolean isWin = false;

        while(numberOfAttempts<attemptsLimit){
            numberOfAttempts++;

            progressBar(numberOfAttempts,attemptsLimit);
            System.out.println(UsefulConstants.GIVE_ME + " Liczbe :");

            if (scanner.hasNextInt()) {
                userNumber = scanner.nextInt();
                if(userNumber == rndNumber) {
                    System.out.println(UsefulConstants.YES + " Udalo Ci sie zgadnac liczbe!");
                    isWin = true;
                    break;
                } else if (userNumber < rndNumber) {
                    System.out.println(UsefulConstants.TO_LESS + " Liczba do odgadniecia jest wieksza!");
                    continue;
                }
                else {
                    System.out.println("To " + UsefulConstants.TO_MUCH + " Liczba do odgadniecia jest mniejsza!");
                }
            } else {
                System.out.println(UsefulConstants.NOT_A_NUMBER);
                scanner.next();
            }
        }

        if(isWin){
            System.out.println(UsefulConstants.CONGRATULATIONS);
        } else{
            System.out.println(UsefulConstants.UNFORTUNATELY + " Wyczerpales limit prob!");
        }
    }
    public void progressBar(int attempts,int limit){
        String sb = "[" + "*".repeat(Math.max(0, attempts)) +
                ".".repeat(Math.max(0, limit - attempts)) +
                "]";
        System.out.println(sb);
    }
}
