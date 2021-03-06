/*******************************************************************************
 *
 * Assignment options:
 * Greedy assignment - worst load balance because it leaves last room most empty.
 * Round robin assignment: 1,2,3,1,2,3 (load balanced)
 * Random assignment of students to rooms. Roll dice, check to see if room is full.
 *    If so, roll again. (Requires array that counts students/room.)
 *
 ******************************************************************************/

public class ZoomRooms {

    // ---------------- STUDENT CODE BEGIN ----------------
    public static final int ALG_GREEDY = 0;
    public static final int ALG_RROBIN = 1;
    public static final int ALG_RANDOM = 2;
    // ---------------- STUDENT CODE END ------------------

    // Given number of students and max per room, returns the number of rooms needed.
    public static int roomsNeeded(int numStudents, int maxPerRoom) {
        // ---------------- STUDENT CODE BEGIN ----------------
        return (int) Math.ceil((double) numStudents / maxPerRoom);
        // ---------------- STUDENT CODE ALT ------------------
        // return 3;
        // ---------------- STUDENT CODE END ------------------
    }

    // Prints out the students in one room
    public static void printRoom(int room, int[] studentRooms, String[] studentNames) {
        StdOut.println(); // begin with a blank line to separate from anything above
        StdOut.println("Room: " + (room + 1)); // Zoom rooms start with number 1
        StdOut.println("----------");
        // ---------------- STUDENT CODE BEGIN ----------------
        for (int i = 0; i < studentRooms.length; i++) {
            if (room == studentRooms[i]) {
                StdOut.println(studentNames[i]);
            }
        }
        // ---------------- STUDENT CODE END ------------------
    }

    // Prints out the students in all rooms
    public static void printRooms(int numRooms, int[] studentRooms, String[] studentNames) {
        // ---------------- STUDENT CODE BEGIN ----------------
        for (int i = 0; i < numRooms; i++) {
            printRoom(i, studentRooms, studentNames);
        }
        // ---------------- STUDENT CODE ALT ------------------
        // printRoom(0, studentRooms, studentNames);
        // ---------------- STUDENT CODE END ------------------
    }

    // Read integer n, followed by n names, reading from StdIn.
    // Return array containing those names.
    public static String[] readNames() {
        // ---------------- STUDENT CODE BEGIN ----------------
        int n = StdIn.readInt();
        String[] names = new String[n];
        for (int i = 0; i < n; i++) {
            names[i] = StdIn.readString();
        }
        return names;
        // ---------------- STUDENT CODE ALT ------------------
        // return { "Ava", "Ben", "Carol", "Dan", "Emma", "Finn", "Grace" };
        // ---------------- STUDENT CODE END ------------------
    }

    // This function is provided as part of the assignment.
    // It makes a greedy assignment of students to rooms, putting the first maxPerRoom
    // students in the first room, then the next maxPerRoom in the second room, etc.
    public static int[] assignGreedy(int numRooms, String[] names, int maxPerRoom) {
        int[] assignedRooms = new int[names.length];
        for (int i = 0; i < names.length; i++) {
            int room = i / maxPerRoom;
            assignedRooms[i] = room;
        }
        return assignedRooms;
    }

    // Assign students to rooms in a round-robin fashion: first student in first room,
    // second student in second room, etc, until each room has one student. Then the
    // next student goes to the first room, then second room, etc.
    public static int[] assignRoundRobin(int numRooms, String[] names, int maxPerRoom) {
        // ---------------- STUDENT CODE BEGIN ----------------
        int[] assignedRooms = new int[names.length];
        for (int i = 0; i < names.length; i++) {
            int room = i % maxPerRoom;
            assignedRooms[i] = room;
        }
        return assignedRooms;
        // ---------------- STUDENT CODE END ------------------
    }

    public static int[] assignRandom(int numRooms, String[] names, int maxPerRoom) {
        // ---------------- STUDENT CODE BEGIN ----------------
        int[] assignedRooms = new int[names.length];
        int[] roomCount = new int[numRooms];
        for (int i = 0; i < names.length; i++) {
            int room;
            do {
                room = (int) (Math.random() * numRooms);
            } while (roomCount[room] == maxPerRoom); // tricky!
            assignedRooms[i] = room;
            roomCount[room]++;
        }
        return assignedRooms;
        // ---------------- STUDENT CODE END ------------------
    }

    public static int[] assign(int algorithm, int numRooms, String[] names, int maxPerRoom) {
        // ---------------- STUDENT CODE BEGIN ----------------
        if (algorithm == ALG_GREEDY) {
            return assignGreedy(numRooms, names, maxPerRoom);
        } else if (algorithm == ALG_RROBIN) {
            return assignRoundRobin(numRooms, names, maxPerRoom);
        } else if (algorithm == ALG_RANDOM) {
            return assignRandom(numRooms, names, maxPerRoom);
        }

        // default result
        return new int[names.length];
        // ---------------- STUDENT CODE END ------------------
    }

    public static void main(String[] args) {
        // ---------------- STUDENT CODE BEGIN ----------------
        int maxPerRoom = Integer.parseInt(args[0]);
        String[] names = readNames();
        int numRooms = roomsNeeded(names.length, maxPerRoom);
        int[] assigned = assignRandom(numRooms, names, maxPerRoom);
        printRooms(numRooms, assigned, names);
        // ---------------- STUDENT CODE END ------------------
    }
}
