package ksenodoxeio;

/**
 * @author Papanastasis Zacharias
 */
public class Floor {

    private int number;
    private Room[] rooms;

    public Floor(int number, int num_floor_rooms) { //παρέχονται ο αριθμός του ορόφου και το πλήθος των δωματίων του
        this.number = number;
        this.rooms = new Room[num_floor_rooms];
    }

    public Room getRooms(int roomNumber) {//επιστρέφει τον αριθμό δωματίου
        roomNumber = roomIndexFromNumber(roomNumber);
        return rooms[roomNumber];
    }

    public void setRoomData(int number, int numBeds, int type) {//δημιουργία δωματίου με συγκεκριμένα χαρακτηριστικά

        int roomNumber = this.roomIndexFromNumber(number);

        this.rooms[roomNumber] = new Room(number, numBeds, type);

    }

    public void setRoomData(int number1, int number2, int numBeds, int type) {//δημιουργία εύρος δωματίων με κοινά χαρακτηριστικά

        for (int i = number1; i <= number2; i++) {

            int roomNumber = this.roomIndexFromNumber(i);

            this.rooms[roomNumber] = new Room(i, numBeds, type);
        }

    }

    public Room searchRoom(int numBeds, int type) { //αναζήτηση δωματίου σε αυτόν τον όροφο

        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i].getNumBeds() == numBeds && rooms[i].getType() == type) {
                return rooms[i];
            }
        }

        return null;//επιστρέφει null αν δεν βρεθεί κατάλληλο δωμάτιο
    }

    public void emptyRooms(int dateT) {//απελευθέρωση δωματίων στα οποία έχει λήξει η διαμονή
        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i].getCheckOutDate() == dateT) {
                this.rooms[i].checkOut();
            }
        }

    }

    private int roomIndexFromNumber(int roomNumber) {//επιστρέφει την αντίστοιχη θέση του πίνακα δωματίων του ορόφου

        roomNumber = (roomNumber % 100) - 1;

        if ((roomNumber > rooms.length) || (roomNumber < 0)) {
            return -1;
        }

        return roomNumber;

    }

}
