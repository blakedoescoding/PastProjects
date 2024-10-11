//Written by boudr055
import java.util.Scanner;

public class FunFormulas {

    public double getInput(String promptStr){
        Scanner scanner = new Scanner(System.in);
        double input;
        while (true){
            System.out.println(promptStr);
            input = scanner.nextDouble();
            if (input < 0){
                System.out.println("ERROR: please enter a non-negative number!!!");
                continue;
                //continue statement pushes to start of while loop
            }
            break;
            //once successful we break out of while loop
        }
        return input;
    }


    public void sd(){
        //duration of storm
        double diameterOfStorm = getInput("Enter diameter of storm in miles: ");
        double timeHours = Math.sqrt((Math.pow(diameterOfStorm, 3) / 216));
        System.out.println("The storm will last: " + timeHours + " hours");
    }

    public void ls(){
        double numberOfSeconds = getInput("Enter in a number");
        double distanceAwayInFeet = 1100 * numberOfSeconds;
        System.out.println(distanceAwayInFeet);
    }

    public void wi(){
        double edgeOfCubeInches = getInput("Enter a number");
        double weightOfIceCube = 0.033 * Math.pow(edgeOfCubeInches, 3);
        System.out.println(weightOfIceCube);
    }

    public void dt(){
        double mph = getInput("Enter Speed in miles-per-hour: ");
        double timeInHours = getInput("Enter time in hours: ");
        
        double distanceTraveled = timeInHours * mph;
        System.out.println("Distance traveled is: " + distanceTraveled + " miles");
    }

    public void sa(){
        double weightPound = getInput("Enter your weight in pounds") * .4536;
        double heightCenti = getInput("Enter your height in inches") * 2.54;
        double BSA = ((Math.sqrt(weightPound * heightCenti)) / 60);
        System.out.println(BSA);
    }
}
