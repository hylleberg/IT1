import java.util.Scanner;

/**
 *
 */
public class UserInterface {

    public UserInterface(){


    }

    /**
     * Prints notification string text
     * @param text
     */

    public void notification(String text) {

        System.out.println(text);

    }

    /**
     * Min and max values put in *can* exceed thresholds with warnings (!!!)
     *
     * @param wantedValue
     * @return
     */

    public double getUserInput(String wantedValue){

        try {
            System.out.println("Please input the " + wantedValue +  " value");
            Scanner scan = new Scanner(System.in);
            String value = scan.nextLine();
            return new Double(value);
        } catch(NumberFormatException e) {
            System.out.println("Input Error!");
            return getUserInput(wantedValue);

        }
    }


    public void printMax() {

        //Prints max value to console
    }

    public void printMin() {

        //Prints min value to console
    }

    public void printUrgent() {

        //Prints urgent value to console
    }


}
