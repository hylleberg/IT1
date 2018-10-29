/**
 *
 */
class NotificationSystem {

    /**
     * Notification System
     */
    NotificationSystem(){

    }

    /**
     *
     * @param text
     */
    void send(String text){

        //denne del sørger for en samlet sysout der håndterer tekstlinjer
        //Den er sat til at give notification i konsol,
        //men ikke til at tilkalde da dette ville give en fejl når tilkaldesystemet kaldes men ikke eksisterer

        System.out.println(text); //send notification to 'wireless system'; call doctor; err for red text
    }

}
