import java.util.Scanner;

// da vi skal kunne tage imod input hentes Scanner ind

/**
 * User Interface - prints to console; takes input
 */
public class UserInterface {

    public UserInterface(){


    }

    /**
     * Prints notification string text
     * @param text
     */

    // er en println nødvendig når den er void? Hvad er ideen?

    public void notification(String text) {

        System.out.println(text);

    }

    /**
     * Min and max values put in *can* exceed thresholds with warnings (!!!)
     *
     * @param wantedValue
     * @return
     */

    //standardkladde til input. Dovenskab, skrive kladden en gang og genbruge den til når bruger indtaster min max urgent.

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


        // DISSE BRUGES ALDRIG?                             ???

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
