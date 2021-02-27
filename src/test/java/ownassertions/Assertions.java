package ownassertions;

public class Assertions {

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public static void assertEquals(String expected, String result) {
        if (expected.equals(result))
            printPassedTest(getMethodName());
        else
            printErrorTest(getMethodName(), expected, result);
    }

    private static void printPassedTest(String methodName) {
        System.out.printf(ANSI_GREEN + "Test passed, testName: %s \n", methodName);
    }

    private static void printErrorTest(String methodName, String expected, String result) {
        System.out.println(ANSI_RED +
                "Test error, testName: " + methodName  + "\n" +
                "\t\tExpected val: " + expected + "\n" +
                "\t\tActual value: " + result + "\n");
    }

    private static String getMethodName() {
        StackWalker walker = StackWalker.getInstance();
        return walker.walk(frames -> frames
                .skip(2)
                .findFirst()
                .map(StackWalker.StackFrame::getMethodName))
                .orElseThrow();
    }
}
