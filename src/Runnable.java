/**
 * Runnable - continuous process
 */
class Runnable {

    //Vi starter med værdier - som kan ændres ved input
    private double min = getValue(150);
    private double max = getValue(200);
    private double urgent = 1;

    //UserInterfase navngives med forkortels ui
    private UserInterface ui;

    //                                                      ???
    private SensorRead sensorRead;

    //NotificationSystem navngives med forkortelse ns
    private NotificationSystem ns;

    //Filewriter class
    private FileWriter fw;

    /**
     *
     */
    Runnable(){

        //UserInterface oprettes
        //SensorRead oprettes og går igang med at køre
        //Notificationsystem oprettes og aktiveres hvis værdier overskrides

        this.ui = new UserInterface();
        this.sensorRead = new SensorRead();
        this.ns = new NotificationSystem();
        this.fw = new FileWriter();

        //Nu kaldes getUserInput fra ui (UserInterface) og beder om input

        setMin(this.ui.getUserInput("minimum temperature", this.min));
        setMax(this.ui.getUserInput("maximum temperature", this.max));
        setUrgent(this.ui.getUserInput("urgent deviation", this.urgent));

    }

    /**
     * Returns nothing, can only run be called by this class
     *
     */

    void run() throws InterruptedException {

        //latestSensorData specificeres som en int

        int latestSensorData;

        //Start på løkke til at tjekke værdier hver 15000 ms, printe læst værdi hver 30.000 ms
        //løkke lavet til at count= % 2 (dvs. lige tal) så springer den videre uden at printe læst værdi


        //vi starter med at sætte count til 0 for at have et udgangspunkt

        int count = 0;

        //noinspection InfiniteLoopStatement
        while (true){

            //henter seneste værdi fra sensorRead og gemmer det i latestSensorData

            latestSensorData = this.sensorRead.getValue();
            this.fw.writeTemperatureToFile(getValue(latestSensorData));

            //count tjekkes for at være et lige tal (modulus 2) og sættes til 0 hvis det er korrekt

            count = count % 2;

            // 1. del af løkke: skal udføres hvert 30 sek dvs. når count sættes til 0
            // Hvis den udføres printes en linje i konsollen

            if (count == 0){
                this.ui.notification("Latest value in degrees Celcius: " + getValue(latestSensorData));
            }

            //herefter kontrolleres grænseværdier (uanset count) da disse skal tjekkes hvert 15 sek
            //dette fungerer ved en løkke med en løkke i.

            // Control min max, urgent
            //første if: hvis læste værdi er under min ELLER over max: print linje OG udfør næste if
            if (getValue(latestSensorData) < getMin() || getValue(latestSensorData) > getMax()){
                this.ui.notification("Outside normal values: " + getValue(latestSensorData) + " C");

                //denne if laves kun hvis første if aktiveres. Kontrollerer om læste værdi er uden for urgent-interval: printer linje
                //Denne if udføres kun hvis over/under min/max da urgent altid ligger udenfor disse og ellers ikke vil være relevant

                if (getValue(latestSensorData) < (getMin()-getUrgent()) ||
                        getValue(latestSensorData) > (getUrgent()+getMax())){
                    this.ns.send("*** Urgent exceeded : " + getValue(latestSensorData) + " C");
                }
            }

            System.out.println(".......count: " + count + " Min:" + getMin() + " Max:" + getMax() + " Urg:" + getUrgent());

            //for at løkken virker tillæges +1 således at count hver 2. gang er lige/ulige

            count++; //increment counter

            System.out.println("- - - - " + getValue(latestSensorData));

            // Debug ***: System.out.println("Count:" + count + ". Min:" + getMin() + " Max:" + getMax() + ". Urg:" + getUrgent() );
            // Debug ***: System.out.println("sensor-value: " + latestSensorData + ". c-value:" + getValue(latestSensorData));
            // Debug ***: System.out.println("************** End of loop ***************");
            // Debug ***: System.out.println("- - - - " + getValue(latestSensorData));



            //herefter er der pause i 15 sek

            Thread.sleep(15000);

            //Read value in a specific interval
            //Notify for threshold limit breaches, NotificationSystem();                    !!!!!!!!!

        }

    }

        //konvertering fra sensorData-værdi til grader celcius med 2 decimaler
        //vi henter seneste værdi som int fra sensoren

    /**
     *
     * @param sensorData
     * @return
     */
    private double getValue(int sensorData){

        //vi konverterer til en double for at få decimaler med og begrænser til 2 decimaler

        return (double)Math.round(((sensorData*4.0/50.0)+24.0)*100.0)/100.0; //return double value, 2 decimals

    }

    //herefter sikrer vi at input overholder fornuft


    //urgent må ikke være mindre end 0 da dette ville tilkalde læge uden/før sygeplejerske og ikke giver mening
    //hvis den er sat forkert nulstilles den til startværdien fra

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

    //vi tjekker at min ligger indenfor intervallet 150-200 da disse er eneste mulige værdier
    //hvis den er sat forkert nulstilles den til startværdien fra toppen

    /**
     *
     * @param min
     */
    private void setMin(double min) {

        //Assigns internal minimum value
        if (min >= getValue(0) && min < getValue(255)) {
            this.min = min;
        }
    }

    //vi tjekker at max ligger indenfor intervallerne OG at den er større end min
    //Hvis ikke den er større end min sættes den til min+0.01

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

    //her defineres at getUrgent(), getMin() og getMax() returnerer deres respektive værdier når de kaldes

    /**
     *
     */
    private double getUrgent() {

        //returns urgent value
        return this.urgent;

    }

    /**
     *
     */
    private double getMin() {

        //returns minimum value
        return this.min;

    }

    /**
     *
     */
    private double getMax() {

        //returns maximum value
        return this.max;

    }
}

