/**
 *
 */
public class Runnable {

    private double min;
    private double max;
    private double urgent;
    int latestSensorData;
    private UserInterface ui;
    SensorRead sensorRead;
    private NotificationSystem ns;

    Runnable(){

        this.ui = new UserInterface();
        this.sensorRead = new SensorRead();
        this.ns = new NotificationSystem();

        setMin(this.ui.getUserInput("min"));
        setMax(this.ui.getUserInput("max"));
        setUrgent(this.ui.getUserInput("urgent"));

    }

    /**
     * Returns nothing, can only run be called by this class
     *
     */

    public void run() throws InterruptedException {


        while (true){

            this.latestSensorData = this.sensorRead.getValue();

            // inner loop Control every 30.00 ms - every other loop




            // Control min max, urgent
            if (this.latestSensorData < getMin() || this.latestSensorData > getMax()){
                this.ui.notification("Outside normal values: " + getValue(this.latestSensorData) + " C");
                if (this.latestSensorData < (getMin()-getUrgent()) ||
                        this.latestSensorData > (getUrgent()+getMax())){
                    this.ns.send("*** Urgent exceeded *** : " + getValue(this.latestSensorData) + " C");
                }
            }

            Thread.sleep(15000);

            //Read value in a specific interval
            //Notify for threshold limit breaches, NotificationSystem();

        }

    }


    public double getValue(int sensorData){

        return (double)Math.round(((sensorData*4.0/50.0)+24.0)*100.0)/100.0; //return double value, 2 decimals

    }

    private void setUrgent(double urgent) {

        //Assigns internal urgent value
        this.urgent = urgent;

    }


    private void setMin(double min) {

        //Assigns internal minimum value
        this.min = min;

    }

    private void setMax(double max) {

        //Assigns internal maximum value
        this.max = max;

    }

    private double getUrgent() {

        //returns urgent value
        return this.urgent;

    }

    private double getMin() {

        //returns minimum value
        return this.min;

    }

    private double getMax() {

        //returns maximum value
        return this.max;

    }
}

