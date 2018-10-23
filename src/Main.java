public class Main {

    /**
     *
     * @param args
     */
    public static void main(String args[])
    {
        Runnable monitor = new Runnable();
        // Vi fanger en fejl som ellers ville stoppe programmet konvertering til grader celcius med 2 decimaler
        try {
            monitor.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
