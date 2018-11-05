/**
 * Sensor (EMULATED FOR NOW)
 */
class SensorRead {

    /**
     *
     */
    SensorRead(){


    }

    /**
     * Reads a value between 150-200 from a sensor connected to a patient
     *
     * @return int
     */

    //vi laver en metode som ved kald returnerer en (emuleret) værdi fra sensoren

    int getValue(){

        // her dannes den ønskede værdi i intervallet 150-200. return sørger for at der altid er en værdi.

        int sensorData;
        sensorData = (int) (Math.random()*255);
        return sensorData;
    }


}
