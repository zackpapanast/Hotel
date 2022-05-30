package ksenodoxeio;

/**
 * @author @author Papanastasis Zacharias
 */
public class Hotel {

    private String namehotel;
    private Floor[] floors;

    public Hotel(String namehotel, int num_floor) {//παρέχονται το όνομα και το πλήθος των ορόφων
        this.namehotel = namehotel;
        this.floors = new Floor[num_floor];
    }

    public String getNamehotel() {
        return namehotel;
    }

    public void setFloorRooms(int floorNumber, int roomNumber) {//αναθέτει σε συγκεκριμένο όροφο το πλήθος των δωματίων του

        this.floors[floorNumber] = new Floor(floorNumber, roomNumber);

    }

    public Floor getFloors(int floorNumber) {//επιστρέφει τον αριθμό του ορόφου
        return this.floors[floorNumber];
    }

    public Room searchRoom(int numBeds, int type) {//αναζήτηση δωματίου εάν επιθυμεί συγκεκριμένο όροφο
        Room tmpRoom = null;
        for (int i = 0; i < this.floors.length; i++) {
            tmpRoom = this.floors[i].searchRoom(numBeds, type);
            if (tmpRoom != null) {
                break;
            }
        }

        return tmpRoom;
    }

    public Room searchRoom(int floorNumber, int numBeds, int type) {//επιστροφή δωματίου
        return this.floors[floorNumber].searchRoom(numBeds, type);
    }

    public void bookRoom(Room room, Client client, int checkOutDate) {//κράτηση δωματίου
        if (room.isEmpty() == 0) {
            this.floors[((int) (room.getNumber() / 100 - 1))].getRooms(room.getNumber()).checkIn(client, checkOutDate);
        }
    }

    public void emptyRooms(int dateT) {//απελευθέρωση δωματίων για τα οποία έχει λήξει η κράτηση εκείνη τη μέρα
        for (int i = 0; i < this.floors.length; i++) {
            this.floors[i].emptyRooms(dateT);
        }
    }

}
