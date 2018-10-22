public class Main {

    /**
     *
     * @param args
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
