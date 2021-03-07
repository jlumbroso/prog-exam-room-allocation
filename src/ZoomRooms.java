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
    public static void printRoom(int room, int[] studentRooms, String[] studentNames) {
        StdOut.println(); // begin with a blank line to separate from anything above
        StdOut.println("Room: " + room);
        StdOut.println("----------");
        // ---------------- STUDENT CODE BEGIN STEP 2 ----------------
        for (int i = 0; i < studentRooms.length; i++) {
            if (room == studentRooms[i]) {
                StdOut.println(studentNames[i]);
            }
        }
        // ---------------- STUDENT CODE END STEP 2 ----------------
    }

    // Prints out the students in all rooms
    public static void printRooms(int numRooms, int[] studentRooms, String[] studentNames) {
        // ---------------- STUDENT CODE BEGIN STEP 1 ----------------
        for (int i = 1; i <= numRooms; i++) {
            printRoom(i, studentRooms, studentNames);
        }
        // ---------------- STUDENT CODE ALT ----------------
        // // For now it just prints room 1. Add a loop so that it prints all numRooms.
        // printRoom(1, studentRooms, studentNames);
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
    public static int[] assignGreedy(int numRooms, String[] names, int roomSize) {
        if (names.length == 0) {
            int[] a = { ASSIGN_GREEDY };
            return a;
        }
        int[] assignedRooms = new int[names.length];
        for (int i = 0; i < names.length; i++) {
            int room = i / roomSize + 1;
            assignedRooms[i] = room;
        }
        return assignedRooms;
    }

    // Assign students to rooms in a round-robin fashion: first student in first room,
    // second student in second room, etc, until each room has one student. Then the
    // next student goes to the first room, then second room, etc.
    public static int[] assignRoundRobin(int numRooms, String[] names, int roomSize) {
        // ---------------- STUDENT CODE BEGIN STEP 5 ----------------
        int[] assignedRooms = new int[names.length];
        for (int i = 0; i < names.length; i++) {
            int room = i % roomSize + 1;
            assignedRooms[i] = room;
        }
        return assignedRooms;
        // ---------------- STUDENT CODE ALT ----------------
        // int[] temp = [ 1, 1, 1, 2, 2 ];
        // return temp;
        // ---------------- STUDENT CODE END STEP 5 ----------------
    }

    public static int[] assignRandom(int numRooms, String[] names, int roomSize) {
        // ---------------- STUDENT CODE BEGIN ----------------
        int[] assignedRooms = new int[names.length];
        int[] roomCount = new int[numRooms];
        for (int i = 0; i < names.length; i++) {
            int room;
            do {
                room = (int) (Math.random() * numRooms);
            } while (roomCount[room] == roomSize); // tricky!
            assignedRooms[i] = room;
            roomCount[room]++;
        }
        return assignedRooms;
        // ---------------- STUDENT CODE ALT ----------------
        // int[] temp = [ 1, 1, 1, 2, 2 ];
        // return temp;
        // ---------------- STUDENT CODE END ----------------
    }
    
    // Codes for three possible algorithms for assigning students to rooms:
    public static int ASSIGN_GREEDY = 1;
    public static int ASSIGN_ROBIN  = 2;
    public static int ASSIGN_RANDOM = 3;
    
    // Assigns students to Zoom rooms. Takes two command line arguments:
    // * algorithm code = always one of the three codes above (1, 2, or 3)
    // * roomSize = capacity of each room (assume this is >= 2)
    // Also reads list of student names from StdIn.
    public static void main(String[] args) {
        int algorithm = Integer.parseInt(args[0]);
        int roomSize = Integer.parseInt(args[1]);
        String[] names = readNames();
        int numRooms = roomsNeeded(names.length, roomSize);
        int[] assigned;
        if (algorithm == ASSIGN_GREEDY)
            assigned = assignGreedy(numRooms, names, roomSize);
        else if (algorithm == ASSIGN_ROBIN)
            assigned = assignRoundRobin(numRooms, names, roomSize);
        else // must be ASSIGN_RANDOM
            assigned = assignRandom(numRooms, names, roomSize);            
        printRooms(numRooms, assigned, names);
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
