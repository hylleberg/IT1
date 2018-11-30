import jssc.*;

public class SerialPortsJSSC {
	
    static void listPorts() {
        String[] portNames = SerialPortList.getPortNames();
        for (int i = 0; i < portNames.length; i++) {
			String name = portNames[i];
			SerialPort port = new SerialPort(name);
            System.out.println(name);
			try {
			    port.openPort();
			    port.setParams(SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8,
                               SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
				System.out.println("CD: " + port.isRLSD() + "\tCTS: " + port.isCTS() +
								   "\tDSR: " + port.isDSR() + "\tRI: " + port.isRING());
				port.setRTS(true);
				port.setDTR(true);
				System.out.println("CD: " + port.isRLSD() + "\tCTS: " + port.isCTS() +
								   "\tDSR: " + port.isDSR() + "\tRI: " + port.isRING());
				port.setRTS(false);
				port.setDTR(false);
				System.out.println("CD: " + port.isRLSD() + "\tCTS: " + port.isCTS() +
								   "\tDSR: " + port.isDSR() + "\tRI: " + port.isRING());
				port.closePort();
			} catch(SerialPortException e) {
				System.err.println("Serial port exception: " + e);
			}
        }        
    }
    
    public static void main (String args[]) {
        System.out.println("Listing Com ports:");
		listPorts();
    }
}
