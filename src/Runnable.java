/**
 * Runnable - continuous process
 */
class Runnable {


    private double min = 0;
    private double max = 0;
    private double urgent = 1;
    private boolean emulated = false;

    private UserInterface ui;
    private SensorRead sensorRead;
    private NotificationSystem ns;
    private WriteToFile fw;

    /**
     *
     */
    Runnable(){
        this.ui = new UserInterface();
        this.sensorRead = new SensorRead(this.emulated);
        if (this.sensorRead.emulation != false) {
            this.ui.usingEmulatedInstead();
            this.min = getValue(150);
            this.max = getValue(200);
        }
        else {
            this.min = getValue(37.5);
            this.max = getValue(38.5);
        }
        this.ns = new NotificationSystem();
        this.fw = new WriteToFile();
        setMin(this.ui.getUserInput("minimum temperature", this.min));
        setMax(this.ui.getUserInput("maximum temperature", this.max));
        setUrgent(this.ui.getUserInput("urgent deviation", this.urgent));

    }

    /**
     * Returns nothing, can only run be called by this class
     *
     */

    void run() throws InterruptedException {
        double previousSensorData = -1;
        double latestSensorData;
        int count = 0;
        while (true){

            latestSensorData = this.sensorRead.getValue();
            if (((latestSensorData - previousSensorData) > 2 || (latestSensorData - previousSensorData) < -2) && previousSensorData != -1) {
                this.ui.misplacementNotification(Math.abs(latestSensorData-previousSensorData));
            }
            previousSensorData = latestSensorData;
            this.fw.writeTemperatureToFile(getValue(latestSensorData));

            count = count % 2;

            if (count == 0){
                this.ui.notification("Latest value in degrees Celcius: " + getValue(latestSensorData));
            }
            if (getValue(latestSensorData) < getMin() || getValue(latestSensorData) > getMax()){
                this.ui.notification("Outside normal values: " + getValue(latestSensorData) + " C");

                if (getValue(latestSensorData) < (getMin()-getUrgent()) ||
                        getValue(latestSensorData) > (getUrgent()+getMax())){
                    this.ui.notification("*** Urgent exceeded : " + getValue(latestSensorData) + " C ***");
                    this.ns.send();
                }
            }

            //Debug: System.out.println(".......count: " + count + " Min:" + getMin() + " Max:" + getMax() + " Urg:" + getUrgent());

            count++; //increment counter

            System.out.println("- - - - " + getValue(latestSensorData));

            Thread.sleep(15000);

        }

    }

    /**
     *
     * @param sensorData
     * @return
     */
    private double getValue(double sensorData){

        if (this.sensorRead.emulation) {
            return (double)Math.round(((sensorData*4.0/50.0)+24.0)*100.0)/100.0; //return double value, 2 decimals
        } else {
            return sensorData;
        }

    }

    /**
     *
     * @param urgent
     */
    private void setUrgent(double urgent) {

        //Assigns internal urgent value
        if (urgent >= 0) {
            this.urgent = urgent;
        }
    }

    /**
     *
     * @param min
     */
    private void setMin(double min) {

        if (min >= getValue(0) && min < getValue(255)) {
            this.min = min;
        }
    }

    /**
     *
     * @param max
     */
    private void setMax(double max) {

        //Assigns internal maximum value
        if (max > getValue(0) && max <= getValue(255)) {
            this.max = max;
        }
        if (this.max < this.min) {
            this.max = this.min + 0.01; //Making sure that max is not less than min
        }
    }

    /**
     *
     */
    private double getUrgent() {

        return this.urgent;

    }

    /**
     *
     */
    private double getMin() {

        return this.min;

    }

    /**
     *
     */
    private double getMax() {

        return this.max;

    }
}

