package ksenodoxeio;

/**
 * @author Papanastasis Zacharias
 */
public class Room {

    private int number;
    private int numBeds;
    private int type;
    private Client client;
    private int checkOutDate;

    public Room(int number, int numBeds, int type) {    //δημιουργία χαρακτηριστικών δωματίου
        this.number = number;
        this.numBeds = numBeds;
        this.type = type;
        this.client = null;
        this.checkOutDate = -1;
    }

    public Client getClient() {     //μέθοδοι πρόσβασης στα πεδία
        return client;
    }

    public int getCheckOutDate() {
        return checkOutDate;
    }

    public int getNumber() {
        return number;
    }

    public int getNumBeds() {
        return numBeds;
    }

    public int getType() {
        return type;
    }

    public void checkIn(Client client, int checkOutDate) {      //μέθοδος κράτησης δωματίου

        this.client = client;
        this.checkOutDate = checkOutDate;

    }

    public void checkOut() {            //μέθοδος απευλεθέρωσης δωματίου
        this.client = null;
        this.checkOutDate = -1;
    }

    public int isEmpty() {          // έλεγχος διαθεσιμότητας δωματίου
        if (this.client == null) {
            return 0;
        }

        return -1;
    }

}
