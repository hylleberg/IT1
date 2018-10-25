public class Main {

    /**
     *
     * @param args
     */
    public static void main(String args[])
    {
        Runnable monitor = new Runnable();
        // Vi fanger en fejl fra indtastningen (NumberFormatException) (i Runnable som henter fra Userinterface)
        // Dette gør at forkert/uventet input ikke crasher programmet
        // udførsel sker i UserInterface
        try {
            monitor.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
