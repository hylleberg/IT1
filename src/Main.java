public class Main {

    /**
     *
     * @param args the input values. These are not used.
     */
    public static void main(String args[])
    {
        //
        Runnable monitor = new Runnable();

        /* no no */
        // Vi fanger en fejl fra indtastningen (NumberFormatException) (i Runnable som henter fra Userinterface)
        // Dette gør at forkert/uventet input ikke crasher programmet
        // udførsel sker i UserInterface

        /* yes yes */
        // Vi fanger fejlen som metoden "run" kan kaste. Dette er fordi vi anvender "Thread.wait()". Fejlen der kan fanges
        // er "InterruptedException". Thread.wait anvendes for at lade programmet vente i 15 sekunder før den laver næste gennemkørsel.
        try {
            monitor.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
