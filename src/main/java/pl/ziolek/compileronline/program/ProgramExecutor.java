package pl.ziolek.compileronline.program;

import java.io.*;
import java.util.concurrent.TimeoutException;

public class ProgramExecutor {

    private static ProgramExecutor instance = new ProgramExecutor();

    private final String PATH_TO_FOLDER = "/compilations/cpp_compilations/";
    private final String TEST_FILE_NAME = "input.in";
    private final String ABSOLUTE_PATH_TO_FILE = PATH_TO_FOLDER + TEST_FILE_NAME;
    private final String EXECUTE_METHOD = PATH_TO_FOLDER + "runProgram.sh";
    private final String CLEAN_UP_METHOD = "rm " + PATH_TO_FOLDER + TEST_FILE_NAME;

    private ProgramExecutor() {}

    public static ProgramExecutor getInstance() {
        return instance;
    }

    public SingleTestResult execute(String input, Integer maxExecutionTimeInSeconds) throws IOException, InterruptedException {
        createTestFile();
        saveToFile(input);
        SingleTestResult singleTestResult = getResult(maxExecutionTimeInSeconds);
        cleanUp();
        return singleTestResult;
    }

    private void createTestFile() throws IOException {
        File file = new File(ABSOLUTE_PATH_TO_FILE);
        file.createNewFile();
    }

    private void saveToFile(String input) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(ABSOLUTE_PATH_TO_FILE));
        writer.write(input);
        writer.close();
    }

    private SingleTestResult getResult(int timeLimit) {
        StringBuffer output = new StringBuffer();

        try {
            System.out.println(EXECUTE_METHOD);
            Process process = Runtime.getRuntime().exec(EXECUTE_METHOD);

            checkExecTime(timeLimit);

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null)
                output.append(line);

        } catch (TimeoutException e) {
            return new SingleTestResult("", ResultStatus.TIMEEXCEPTION);
        } catch (OutOfMemoryError e) {
            System.out.println(e.getMessage());
            return new SingleTestResult("", ResultStatus.ERROR);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new SingleTestResult("", ResultStatus.ERROR);
        }

        return new SingleTestResult(output.toString(), ResultStatus.OK);
    }

    private void checkExecTime(int limitInSeconds) throws TimeoutException {
        int i = 0;
        boolean programWorks;
        String processPID = null;
        do {
            programWorks = false;
            try {
                Process process = Runtime.getRuntime().exec("ps -x");
                process.waitFor();
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    if (getProcessName(line).equals(PATH_TO_FOLDER + "program")) {
                        processPID = getProcessPID(line);
                        programWorks = true;
                    }
                }

                if (!programWorks)
                    return;

                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println("checkExecTime: error = " + e.getMessage());
            }
            i++;
        } while (i < limitInSeconds && programWorks);

        if (processPID != null)
            killProcess(processPID);
        throw new TimeoutException();
    }

    private String getProcessName(String line) {
        line = line.trim();
        String[] s = line.split(" ");

        return s[s.length-1];
    }

    private String getProcessPID(String line) {
        line = line.trim();
        String[] s = line.split(" ");

        return s[0];
    }

    private void killProcess(String processPID) {
        try {

            Process process = Runtime.getRuntime().exec("kill " + processPID);
            process.waitFor();

        } catch (Exception e) {
            System.out.println("killProcess error: " + e.getMessage());
        }
    }

    private void cleanUp() throws IOException, InterruptedException{
        Process process = Runtime.getRuntime().exec(CLEAN_UP_METHOD);
        process.waitFor();
    }
}
