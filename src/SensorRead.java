public class SensorRead {


    public SensorRead(){


    }

    /**
     * Reads a value between 150-200 from a sensor connected to a patient
     *
     * @return int
     */

    public int getValue(){
        int sensorData;
        sensorData = (int) (150 + Math.random()*50);
        return sensorData;
    }


}
