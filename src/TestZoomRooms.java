




public class TestZoomRooms {

    public static boolean compareArrays(int[] a, int[] b) {

    }
    public static boolean compareArrays(String[] a, String[] b) {
        
    }

    public static void reportTestResult(String testName, boolean success) {
        print("Test '");
        print(testname);
        print("': ");
        if (success) {
            println("passed");
        }
        else print("FAILED!"); // you want to grab the attention when something is WRONG
    }

    public static void testGreedyWorks1() {
        output = ZoomRooms.assignGreedy(-----);
        expectedResult = { 2, 2, 1 };
        boolean success = compareArrays(output, expectedResult);
        reportTestResult(success);
    }

    public static void testGreedyWorks2() {
        output = ZoomRooms.assignGreedy(-----);
        if (compareArrays(output, expectedResult)) {
            print("Test passed");
        }
        else print("Test failed");

    }

    public static void testAll() {
        testGreedyWorks1();
        testGreedyWorks2();
    }


}
