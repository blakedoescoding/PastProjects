//Written by boudr055
import java.util.Scanner;

public class ComputeBMI {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Weight in pounds: ");
        double weight = scanner.nextDouble();
        System.out.print("Enter Height in inches: ");
        double height = scanner.nextDouble();

        ComputeBMI calculator = new ComputeBMI();
        System.out.println("For weight: " + weight + " and height: " + height + " inches");
        System.out.println("The BMI is: " + calculator.calcBMI(weight, height));
        

    }

    public double calcBMI(double weight, double height) {
        double BMI = 703 * weight / (Math.pow(height, 2));
        return BMI;
    }
}
