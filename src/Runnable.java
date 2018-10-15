public class Runnable {

    private double min;
    private double max;
    private double urgent;
    int latestSensorData;
    private UserInterface ui;
    SensorRead sensorRead;

    Runnable(){

        this.ui = new UserInterface();
        this.sensorRead = new SensorRead();


        setMin(this.ui.getUserInput("min"));
        setMax(this.ui.getUserInput("max"));
        setUrgent(this.ui.getUserInput("urgent"));

        run();
    }

    /**
     * Returns nothing, can only run be called by this class
     *
     */

    private void run(){


        while (true){

            this.latestSensorData = this.sensorRead.getValue();


            System.out.println(getValue(this.latestSensorData));

            //Read value in a specific interval
            //Notify for threshold limit breaches, NotificationSystem();

        }

    }


    public double getValue(int sensorData){

        return (sensorData*4.0/50.0)+24.0; //return double value

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

