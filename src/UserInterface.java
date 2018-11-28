// import statements. Scanner is used for reading the input.
// da vi skal kunne tage imod input hentes Scanner ind
import java.util.Scanner;

/**
 * User Interface - prints to console; takes input
 */
class UserInterface {

    /**
     *
     */
    UserInterface(){


    }


    // er en println nødvendig når den er void? Hvad er ideen?

    /**
     * Prints notification string text
     *
     * @param text the text that gets printed
     */
    void notification(String text) {

        System.out.println(text);

    }

    /**
     * Min and max values put in *can* exceed thresholds with warnings (!!!)
     *
     * @param wantedValue whether we are asking for min/max/urgent (simply an information)
     * @return the users inputted value, returns -1 if the input value is empty.
     */

    //standardkladde til input. Dovenskab, skrive kladden en gang og genbruge den til når bruger indtaster min max urgent.

    double getUserInput(String wantedValue, double stdVal){

        try {
            System.out.println("Please input the " + wantedValue);
            Scanner scan = new Scanner(System.in);
            String value = scan.nextLine();
            return new Double(value);
        } catch(NumberFormatException e) {
            if (e.getMessage().equals("empty String")) {
                System.out.println("You did not input anything, we have chosen: " + stdVal);
                return -1;
            }
            System.out.println("Input Error!");
            return getUserInput(wantedValue, stdVal);

        }
    }


        // DISSE BRUGES ALDRIG?                             ???

    @SuppressWarnings("unused")
    void printMax() {

        //Prints max value to console
    }

    @SuppressWarnings("unused")
    void printMin() {

        //Prints min value to console
    }

    @SuppressWarnings("unused")
    void printUrgent() {

        //Prints urgent value to console
    }


    void usingEmulatedInstead() {
        System.out.println("You do not have any connected devices, so we are using the emulated value.");
    }
    void misplacementNotification(double deviance) {
        System.out.println("Deviance of " + deviance + " detected. Please check the sensor placement.");
    }
}
