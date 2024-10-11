//Written by boudr055


public class WindChill {
    /*
     * Take user input from program and computes solution.
     * WC = 35.47 + 0.6215 * T - 35.75 * V^(.16) + 0.4275 * T * V^(.16)
     * T = Temperature is F, V = Wind Velocity or Speed
     */

     public static void main(String[] args) {
        double temp = Double.parseDouble(args[0]);
        double mph = Double.parseDouble(args[1]);
        double result = 35.74 + (0.6215 * temp) - 35.75 * Math.pow(mph, 0.16) + (0.4275 * (temp * Math.pow(mph, 0.16)));
        System.out.println("The wind-chill at Temperature: " + temp + " Fahrenheit and Wind Speed: " + mph + " mph is: " + result + " degrees Fahrenheit");
     }
}
