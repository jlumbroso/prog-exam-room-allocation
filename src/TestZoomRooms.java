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

    public static void main(String[] args) {
        testRoomsNeeded1();
        testRoomsNeeded2();
    }

}
