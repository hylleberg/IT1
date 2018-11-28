/**
 *
 */
class NotificationSystem {
    private boolean hasSent = false;
    /**
     * Notification System
     */
    NotificationSystem(){

    }

    /**
     *
     */
    void send(){
        if (!hasSent) {
            System.out.println("*** Doctor called ***"); //send notification to 'wireless system'; call doctor; err for red text
            this.hasSent = true;
        } else {
            System.out.println("*** Doctor already called ***");

        }
    }

}
