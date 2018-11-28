public class Main {

    /**
     *
     * @param args the input values. These are not used.
     */
    public static void main(String args[])
    {
        Runnable monitor = new Runnable();


        try {
            monitor.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
