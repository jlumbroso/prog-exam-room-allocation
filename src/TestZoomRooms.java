import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

/*******************************************************************************
 *
 * Princeton COS126 Spring 2021.
 *
 * TestZoomRooms.java
 *
 * This is the student code provided for testing solutions to Programming Exam 1.
 * It is provided after the exam as an optional enrichment exercise.
 * See instructions for details.
 *
 ******************************************************************************/


public class TestZoomRooms {

    // This function compares two arrays of integers.
    // If they are the same it returns TRUE; otherwise FALSE.
    public static boolean compareArrays(int[] a, int[] b) {
        // return false if the lengths are different
        if (a.length != b.length) return false;

        // return false if one of the array elements is different
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) return false;
        }
        // they must be the same
        return true;
    }
    
    // Same as the function above except for Strings.
    // Using "overloading" of methods (same function name, different parameter types)
    public static boolean compareArrays(String[] a, String[] b) {
        if (a.length != b.length) return false;

        for (int i = 0; i < a.length; i++) {
            // same logic as above, but uses string comparison function "equals"
            if (!a[i].equals(b[i])) return false;
        }
        return true;        
    }

    // Reports whether a particular named test was a success or failure
    public static void reportTestResult(String testName, boolean success) {
        String result;
        if (success) {
            result = "success";
        }
        else {
            result = "FAILED!";
        }
        StdOut.println("Test function " + testName + "(): " + result);
    }


    // Testing the roomsNeeded function for some values
    public static void testRoomsNeeded1() {
        String testName = "testRoomsNeeded1";
        int numStudents = 0;
        int roomSize = 1;
        int expectedAnswer = 0;
        int answer = ZoomRooms.roomsNeeded(numStudents, roomSize);
        boolean success = (answer == expectedAnswer);
        reportTestResult(testName, success);
    }

    // Testing the roomsNeeded function for some values
    public static void testRoomsNeeded2() {
        String testName = "testRoomsNeeded2";
        int numStudents = 5;
        int roomSize = 3;
        int expectedAnswer = 2;
        int answer = ZoomRooms.roomsNeeded(numStudents, roomSize);
        boolean success = (answer == expectedAnswer);
        reportTestResult(testName, success);
    }


    // Test greedy assignment with empty names
    public static void testRoomGreedy1() {
        String testName = "testRoomGreedy1";

        int roomSize = 3;
        int numRooms = 3;
        String[] names = new String[] {};
        int[] expectedAnswer = new int[] { };

        int[] returnedAnswer = ZoomRooms.assignGreedy(roomSize, numRooms, names);
        
        boolean success = compareArrays(expectedAnswer, returnedAnswer);
        reportTestResult(testName, success);
    }

    // Test greedy assignment with some parameters
    public static void testRoomGreedy2() {
        String testName = "testRoomGreedy2";

        int roomSize = 3;
        int numRooms = 3;
        String[] names = new String[] {"A", "B", "C", "D"};
        int[] expectedAnswer = new int[] {0, 0, 0, 1};

        int[] returnedAnswer = ZoomRooms.assignGreedy(roomSize, numRooms, names);

        boolean success = compareArrays(expectedAnswer, returnedAnswer);
        reportTestResult(testName, success);
    }

    // Test greedy assignment with some parameters
    public static void testRoomGreedy3() {
        String testName = "testRoomGreedy3";

        int roomSize = 2;
        int numRooms = 3;
        String[] names = new String[] {"A", "B", "C", "D"};
        int[] expectedAnswer = new int[] {0, 0, 1, 1};

        int[] returnedAnswer = ZoomRooms.assignGreedy(roomSize, numRooms, names);

        boolean success = compareArrays(expectedAnswer, returnedAnswer);
        reportTestResult(testName, success);
    }

    // Test greedy assignment with some parameters
    public static void testRoomGreedy4() {
        String testName = "testRoomGreedy4";

        int roomSize = 2;
        int numRooms = 3;
        String[] names = new String[] {"A", "B", "C", "D", "E"};
        int[] expectedAnswer = new int[] {0, 0, 1, 1, 2};

        int[] returnedAnswer = ZoomRooms.assignGreedy(roomSize, numRooms, names);

        boolean success = compareArrays(expectedAnswer, returnedAnswer);
        reportTestResult(testName, success);
    }


    // Test round robin assignment with empty names
    public static void testRoomRobin1() {
        String testName = "testRoomRobin1";

        int roomSize = 3;
        int numRooms = 3;
        String[] names = new String[] {};
        int[] expectedAnswer = new int[] { };

        int[] returnedAnswer = ZoomRooms.assignRobin(roomSize, numRooms, names);
        
        boolean success = compareArrays(expectedAnswer, returnedAnswer);
        reportTestResult(testName, success);
    }

    // Test round robin assignment with full wrap-around
    public static void testRoomRobin2() {
        String testName = "testRoomRobin2";

        int roomSize = 3;
        int numRooms = 3;
        String[] names = new String[] {"A", "B", "C", "D"};
        int[] expectedAnswer = new int[] {0, 1, 2, 0};

        int[] returnedAnswer = ZoomRooms.assignRobin(roomSize, numRooms, names);

        boolean success = compareArrays(expectedAnswer, returnedAnswer);
        reportTestResult(testName, success);
    }

    // Test round robin assignment with full wrap-around, modulating room size
    public static void testRoomRobin3() {
        String testName = "testRoomRobin3";

        int roomSize = 2;
        int numRooms = 3;
        String[] names = new String[] {"A", "B", "C", "D"};
        int[] expectedAnswer = new int[] {0, 1, 2, 0};

        int[] returnedAnswer = ZoomRooms.assignRobin(roomSize, numRooms, names);

        boolean success = compareArrays(expectedAnswer, returnedAnswer);
        reportTestResult(testName, success);
    }

    // Test round robin assignment with smaller number of rooms
    public static void testRoomRobin4() {
        String testName = "testRoomRobin4";

        int roomSize = 3;
        int numRooms = 2;
        String[] names = new String[] {"A", "B", "C", "D"};
        int[] expectedAnswer = new int[] {0, 1, 0, 1};

        int[] returnedAnswer = ZoomRooms.assignRobin(roomSize, numRooms, names);

        boolean success = compareArrays(expectedAnswer, returnedAnswer);
        reportTestResult(testName, success);
    }


    public static void testRoomRandomIsValid() {
        String testName = "testRoomRandomIsValid";

        int roomSize = 2;
        int numRooms = 2;
        String[] names = new String[] {"A", "B", "C", "D"};

        int[] returnedAnswer = ZoomRooms.assignRandom(roomSize, numRooms, names);
        
        // Since response is random we cannot compare it to expected answer
        // instead, check that all names are assigned following the restrictions

        // keep track of the capacity of each room, and when student is assigned
        // to room, decrease capacity by one; fail if capacity goes below 0
        boolean success = true;
        int[] capacity = new int[] { numRooms, numRooms };
        for (int i = 0; i < returnedAnswer.length; i++) {
            int index = returnedAnswer[i];
            capacity[index] -= 1;
            if(capacity[index] < 0) {
                success = false;
                break;
            }
        }

        reportTestResult(testName, success);

    }
    

    public static void testReadNames() {

        /******************************************************************************
         * This is like the RESTRICTED SECTION in Harry Potter!!! Very advanced stuff *
         * that might be very scary!! Ignore unless you are super confident...        *
         ******************************************************************************/

        String testName = "testReadNames";

        String[] expectedNames = new String[] {"A", "B", "C", "D"};

        // We are going to simulate user input by writing to a stream that we will replace
        // the standard input with, using two advanced Java methods
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream("4\nA\nB\nC\nD\n".getBytes());
        System.setIn(in);

        // test read names
        String[] returnedNames = ZoomRooms.readNames();

        boolean success = compareArrays(expectedNames, returnedNames);
        reportTestResult(testName, success);

        // reset System.in to its original, after changing it above
        System.setIn(sysInBackup);

        /******************************************************************************
         * End RESTRICTED SECTION stuff                                               *
         ******************************************************************************/
    }


    public static void main(String[] args) {

        System.out.println("Test suite for Programming Exam 1: ZoomRooms");
        System.out.println("--------------------------------------------");
        System.out.println();

        // Calling every test we defined, in the main(), so that
        // when we call this testing program all tests are run.

        testRoomsNeeded1();
        testRoomsNeeded2();

        testRoomGreedy1();
        testRoomGreedy2();
        testRoomGreedy3();
        testRoomGreedy4();

        testRoomRobin1();
        testRoomRobin2();
        testRoomRobin3();
        testRoomRobin4();

        testRoomRandomIsValid();

        testReadNames();
    }

}
