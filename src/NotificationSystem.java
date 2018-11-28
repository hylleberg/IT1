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
            System.out.println("*** Doctor called ***");
            this.hasSent = true;
        } else {
            System.out.println("*** Doctor already called ***");

        }
    }

}
