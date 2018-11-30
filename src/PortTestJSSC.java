import jssc.*;

public class PortTestJSSC {

    public static void main(String[] args) {
        SerialPort serialPort = new SerialPort("/dev/tty.usbserial");
        try {
            serialPort.openPort();//Open serial port
            serialPort.setParams(9600, 8, 1, 0);//Set params.
            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
        	serialPort.setDTR(true);

			while (true) {
				if (serialPort.getInputBufferBytesCount() > 0) {
					String result = serialPort.readString();
					System.out.println("Sensor: "+result);
				} else System.out.print(".");
				java.util.concurrent.TimeUnit.MILLISECONDS.sleep(1000);
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