import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Sensor (EMULATED FOR NOW)
 */
class SensorRead {

    private int emulatedValue = 225;
    public boolean emulation = false;
    /**
     *
     */
    SensorRead(){
        String[] portNames;
        portNames = SerialPortList.getPortNames();
        if (portNames.length <1) {
            this.emulation = true;
        }
    }

    SensorRead(boolean emulation) {
        String[] portNames;
        portNames = SerialPortList.getPortNames();
        this.emulation = emulation;
        if (!this.emulation && portNames.length <1) {
            this.emulation = true;
        }
    }

    /**
     * Reads a value between 150-200 from a sensor connected to a patient
     *
     * @return int
     */

    //vi laver en metode som ved kald returnerer en (emuleret) værdi fra sensoren

    double getValue(){
        if (this.emulation) {
            return emulatedValue();
        } else {
            return sensorValue();
        }

    }

    double sensorValue() {
        double sensorData = -1;
        SerialPort serialPort = new SerialPort("/dev/tty.usbserial");
        try {
            serialPort.openPort();//Open serial port
            serialPort.setParams(9600, 8, 1, 0);//Set params.
            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
            serialPort.setDTR(true);

            while (sensorData < 0.0) {
                if (serialPort.getInputBufferBytesCount() > 0) {
                    Thread.sleep(50);
                    String a = serialPort.readString();
                    String[] b = a.split("");
                    if (b.length > 6) {
                        String val = b[1] + b[2] + b[3] + b[4] + b[5];
                        try {
                            sensorData = Double.parseDouble(val);
                        }
                        catch (NumberFormatException e) {
                            try {
                                val = b[0] + b[1] + b[2] + b[3] + b[4];
                                sensorData = Double.parseDouble(val);
                            }
                            catch (NumberFormatException f) {
                                sensorData = -1;
                            }
                        }
                    }
                }

            }
        } catch (SerialPortException ex) {
            System.out.println("Serial Port Exception: " + ex);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                serialPort.setDTR(false);
                serialPort.closePort();//Close serial port
            } catch (SerialPortException e) {
                e.printStackTrace();
            }
        }
        return sensorData;
    }

    int emulatedValue() {
        double checkData =
                Math.random()+
                        Math.random()+
                        Math.random()+
                        Math.random()+
                        Math.random()+
                        Math.random()+
                        Math.random()+
                        Math.random()+
                        Math.random()+
                        Math.random();
        if (checkData > 5) {
            this.emulatedValue++;
        } else {
            this.emulatedValue--;
        }
        return this.emulatedValue;
    }
}
