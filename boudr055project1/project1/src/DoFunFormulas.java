//Written by boudr055
public class DoFunFormulas {
    public static void main(String[] args) {
        FunFormulas test = new FunFormulas();

        if (args.length == 0){
            System.out.println("ERROR: 1 command line argument required: Two letter name of function to compute. ");
            textFill();
            
        }
        else{
            switch (args[0]){
            case "sd":
                test.sd();
                break;
            case "ls":
                test.ls();
                break;
            case "wi":
                test.wi();
                break;
            case "dt":
                test.dt();
                break;
            case "sa":
                test.sa();
                break;
            default:
                System.out.print("ERROR: Formula " + args[0] + " is not recognized, ");
                textFill();

            }
        }
    
        
        
    }

    public static void textFill(){
        System.out.println("Type:\n" +
        "sd compute storm distance\n" +
        "ls to compute the distance to lightning strike\n" +
        "wi to compute weight of ice cube\n" + 
        "dt to computer distance traveled\n" + 
        "sa to compute skin area");
    }
}
