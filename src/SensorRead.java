import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;
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



         String sensorData;
                String[] portNames;
            portNames = SerialPortList.getPortNames();
            for(int i = 0; i < portNames.length; i++){

                }



                SerialPort serialPort = new SerialPort("/dev/tty.usbserial");
                try {
                    serialPort.openPort();//Open serial port
                    serialPort.setParams(9600, 8, 1, 0);//Set params.
                    serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
                    serialPort.setDTR(true);

                    while (true) {
                        if (serialPort.getInputBufferBytesCount() > 0) {
                            sensorData = serialPort.readString();
                            return sensorData;
                        }

                    }

                } catch (SerialPortException ex) {
                    System.out.println("Serial Port Exception: " + ex);
                } catch (InterruptedException ex) {
                    System.out.println("Wait Interrupted: " + ex);
                }
                try {
                    serialPort.setDTR(false);
                    serialPort.closePort();//Close serial port
                } catch (SerialPortException ex) {
                    System.out.println("Serial Port Exception: " + ex);
                }


            }
        }




        // her dannes den ønskede værdi i intervallet 150-200. return sørger for at der altid er en værdi.
/*
        int sensorData;
        sensorData = (int) (Math.random()*255);
        return sensorData;
*/




