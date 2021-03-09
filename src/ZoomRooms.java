/*******************************************************************************
 *
 * Princeton COS126 Spring 2021.
 *
 * ZoomRooms.java
 *
 * This is the student code provided for Programming Exam 1.
 * See the PDF in the same directory for instructions.
 *
 ******************************************************************************/

public class ZoomRooms {

    // Given number of students and max per room, returns the number of rooms needed.
    // You can assume that numStudents >= 0 and that roomSize > 0.
    public static int roomsNeeded(int numStudents, int roomSize) {
        // ---------------- STUDENT CODE BEGIN STEP 4 ----------------
        return (int) Math.ceil((double) numStudents / roomSize);
        // ---------------- STUDENT CODE ALT ----------------
        // return 2;
        // ---------------- STUDENT CODE END STEP 4 ----------------
    }

    // Prints out the students in one room
    public static void printRoom(int room, int[] assignedRooms, String[] studentNames) {
        StdOut.println(); // begin with a blank line to separate from anything above
        StdOut.println("Room: " + room);
        StdOut.println("-------");
        // ---------------- STUDENT CODE BEGIN STEP 2 ----------------
        for (int i = 0; i < assignedRooms.length; i++) {
            if (room == assignedRooms[i]) {
                StdOut.println(studentNames[i]);
            }
        }
        // ---------------- STUDENT CODE END STEP 2 ----------------
    }

    // Prints out the students in all rooms
    public static void printRooms(int numRooms, int[] assignedRooms, String[] studentNames) {
        // ---------------- STUDENT CODE BEGIN STEP 1 ----------------
        for (int i = 1; i <= numRooms; i++) {
            printRoom(i, assignedRooms, studentNames);
        }
        // ---------------- STUDENT CODE ALT ----------------
        // // For now it just prints room 1. Add a loop so that it prints all numRooms.
        // printRoom(1, assignedRooms, studentNames);
        // ---------------- STUDENT CODE END STEP 1 ----------------
    }

    // Read integer n, followed by n names, reading from StdIn.
    // Return array containing those names.
    public static String[] readNames() {
        // ---------------- STUDENT CODE BEGIN STEP 3 ----------------
        int n = StdIn.readInt();
        String[] names = new String[n];
        for (int i = 0; i < n; i++) {
            names[i] = StdIn.readString();
        }
        return names;
        // ---------------- STUDENT CODE ALT ----------------
        // String[] temp = { "Ava", "Ben", "Carol", "Dan", "Emma" };
        // return temp;
        // ---------------- STUDENT CODE END STEP 3 ----------------
    }


    // This function is provided as part of the assignment.
    // It makes a greedy assignment of students to rooms, putting the first roomSize
    // students in the first room, then the next roomSize in the second room, etc.
    public static int[] assignGreedy(int roomSize, int numRooms, String[] names) {
        int[] assignedRooms = new int[names.length];
        for (int i = 0; i < names.length; i++) {
            int room = (i / roomSize) + 1;
            assignedRooms[i] = room;
        }
        return assignedRooms;
    }

    // Assign students to rooms in a round-robin fashion: first student in first room,
    // second student in second room, etc, until each room has one student. Then the
    // next student goes to the first room, then second room, etc.
    public static int[] assignRobin(int roomSize, int numRooms, String[] names) {
        // ---------------- STUDENT CODE BEGIN STEP 5 ----------------
        int[] assignedRooms = new int[names.length];
        for (int i = 0; i < names.length; i++) {
            int room = (i % numRooms) + 1;
            assignedRooms[i] = room;
            StdOut.println(i + " " + room);
        }
        return assignedRooms;
        // ---------------- STUDENT CODE ALT ----------------
        // int[] temp = { 1, 1, 1, 2, 2 };
        // return temp;
        // ---------------- STUDENT CODE END STEP 5 ----------------
    }

    public static int[] assignRandom(int roomSize, int numRooms, String[] names) {
        // ---------------- STUDENT CODE BEGIN ----------------
        int[] assignedRooms = new int[names.length];
        int[] roomCount = new int[numRooms + 1];
        for (int i = 0; i < names.length; i++) {
            int room;
            do {
                room = (int) (Math.random() * numRooms) + 1;
            } while (roomCount[room] == roomSize); // tricky!
            assignedRooms[i] = room;
            roomCount[room]++;
        }
        return assignedRooms;
        // ---------------- STUDENT CODE ALT ----------------
        // int[] temp = { 1, 1, 1, 2, 2 };
        // return temp;
        // ---------------- STUDENT CODE END ----------------
    }
    
    private static void debug(int roomSize, int numRooms, int[] assignedRooms, String[] names) {
        StdOut.println("Called debug() function.");
        StdOut.println("roomSize: " + roomSize);
        StdOut.println("numRooms: " + numRooms);
        StdOut.println("assignedRooms...");
        for (int i = 0; i < assignedRooms.length; i++) {
            StdOut.println(i + ": " + assignedRooms[i]);
        }
        StdOut.println("names...");
        for (int i = 0; i < names.length; i++) {
            StdOut.println(i + ": " + names[i]);
        }
    }

    // Assigns students to Zoom rooms. Takes two command line arguments:
    // * algorithm, always one of "greedy", "robin", or "random"
    // * roomSize = capacity of each room (assume this is >= 2)
    // Also reads list of student names from StdIn.
    public static void main(String[] args) {
        String algorithm = args[0];
        int roomSize = Integer.parseInt(args[1]);
        String[] names = readNames();
        int numRooms = roomsNeeded(names.length, roomSize);
        int[] assignedRooms;

        // Choose which algorithm
        if (algorithm.startsWith("greedy")) {
            assignedRooms = assignGreedy(roomSize, numRooms, names);
        }
        else if (algorithm.startsWith("robin")) {
            assignedRooms = assignRobin(roomSize, numRooms, names);
        }
        else { // must be "random" (the only other choice)
            assignedRooms = assignRandom(roomSize, numRooms, names);
        }

        // If it's like "greedy-debug" (or "robin-debug", etc) then use debug output.
        // Otherwise just print out the rooms.
        if (algorithm.endsWith("debug")) {
            debug(roomSize, numRooms, assignedRooms, names);
        }
        else {
            printRooms(numRooms, assignedRooms, names);
        }
    }
}

/*******************************************************************************
 *
 * Honor Code Pledge:
 * “I PLEDGE MY HONOR THAT I HAVE NOT VIOLATED THE HONOR CODE DURING THIS EXAMINATION.”
 *
 * Please type the pledge here, not using all capitol letters (so not cut-and-paste):
 *
 * [ Pledge goes here. ]
 *
 * Next please type your name as your digital signature:
 * 
 * [ Name goes here. ]
 *
 ******************************************************************************/
