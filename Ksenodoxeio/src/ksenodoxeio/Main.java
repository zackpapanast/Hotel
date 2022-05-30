package ksenodoxeio;

import java.util.Random;

/**
 * @author Papanastasis Zacharias
 */
public class Main {

    private static final int NUM_FLOORS = 4;
    private static final int NUM_FLOOR_ROOMS = 8;

    public static void main(String[] args) {

        Random random = new Random();

        Hotel EnastronVillas = new Hotel("EnastronVillas", NUM_FLOORS);
        System.out.println("\t*****" + EnastronVillas.getNamehotel() + "*****\n");

        for (int i = 0; i < NUM_FLOORS; i++) {//δημιουργία ξενοδοχείου
            EnastronVillas.setFloorRooms(i, NUM_FLOOR_ROOMS);
        }

        int numBeds = 2;
        int type = 1;
        int rnd = 0;

        for (int i = 0; i < NUM_FLOORS; i++) {
            for (int j = 0; j < NUM_FLOOR_ROOMS; j++) {
                rnd = random.nextInt(2);
                if (rnd == 0) {
                    numBeds = 2;
                } else {
                    numBeds = 3;
                }
                rnd = random.nextInt(2);
                if (rnd == 0) {
                    type = 1;
                } else {
                    type = 2;
                }
                EnastronVillas.getFloors(i).setRoomData(((i + 1) * 100) + j + 1, numBeds, type);
            }
        }

        int clientsID = 0;

        for (int counter = 1; counter <= 31; counter++) {

            EnastronVillas.emptyRooms(counter);//Άδειασμα δωματίων που έχει λήξει η διαμονή

            int numClients = 1;
            numClients = random.nextInt(20) + 1;//Δημιουργία τυχαίου αριθμού πελατών

            int selectFloor = 0;
            int checkOutDate = 0;

            for (int j = 0; j < numClients; j++) {
                numBeds = random.nextInt(2) + 2;
                type = random.nextInt(2) + 1;
                checkOutDate = random.nextInt(10) + 1;
                selectFloor = random.nextInt(2);
                if (selectFloor == 0) {//περίπτωσεις ανάλογα αν επιθυμεί να επιλέξει όροφο ή οχι
                    Room tmpRoom = EnastronVillas.searchRoom(numBeds, type);
                    if (tmpRoom != null) {
                        EnastronVillas.bookRoom(tmpRoom, new Client(++clientsID), (checkOutDate + counter));
                    }
                } else {
                    int numFloor = random.nextInt(NUM_FLOORS);
                    Room tmpRoom = EnastronVillas.searchRoom(numFloor, numBeds, type);
                    if (tmpRoom != null) {
                        EnastronVillas.bookRoom(tmpRoom, new Client(++clientsID), (checkOutDate + counter));
                    }
                }
            }
            //Εμφάνιση της προσωμοίωσης
            System.out.println("---------------------------------------------------------------------------------------------------------------------");
            System.out.println("--------------------------------------------DAY : " + counter + "-----------------------------------------------------------------");
            System.out.println("---------------------------------------------------------------------------------------------------------------------");
             System.out.println();
            for (int i = 0; i < NUM_FLOORS; i++) {
                System.out.println("---->Floor : " + (i + 1)+"<----");
                System.out.println();
                for (int j = 0; j < NUM_FLOOR_ROOMS; j++) {
                    System.out.print("\tRoom : " + EnastronVillas.getFloors(i).getRooms((i * 100) + j + 1).getNumber());
                    System.out.print("\tBeds : " + EnastronVillas.getFloors(i).getRooms((i * 100) + j + 1).getNumBeds());
                    System.out.print("\tType : " + EnastronVillas.getFloors(i).getRooms((i * 100) + j + 1).getType());
                    System.out.print("\tClient ID : " + (EnastronVillas.getFloors(i).getRooms((i * 100) + j + 1).getClient() != null ? EnastronVillas.getFloors(i).getRooms((i * 100) + j + 1).getClient().getId() : "No Client"));
                    System.out.print("\t\tCheck Out Date : " + EnastronVillas.getFloors(i).getRooms((i * 100) + j + 1).getCheckOutDate());
                    System.out.println();
                }
                System.out.println();
            }

        }

    }
}
