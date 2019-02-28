package pl.ziolek.compileronline.program;

import java.io.*;
import java.util.concurrent.TimeoutException;

public class ProgramExuctor {

    private static ProgramExuctor instance = new ProgramExuctor();

    private final String PATH_TO_FOLDER = "/home/ziolek/Projects/IdeaProjects/CompilerOnline/cpp_compilations/";
    private final String TEST_FILE_NAME = "input.in";
    private final String ABSOLUTE_PATH_TO_FILE = PATH_TO_FOLDER + TEST_FILE_NAME;
    private final String EXECUTE_METHOD = PATH_TO_FOLDER + "runProgram.sh";
    private final String CLEAN_UP_METHOD = "rm " + PATH_TO_FOLDER + TEST_FILE_NAME;

    private ProgramExuctor() {}

    public static ProgramExuctor getInstance() {
        return instance;
    }

    public SingleTestResult execute(String input) throws IOException, InterruptedException {
        createTestFile();
        saveToFile(input);
        SingleTestResult singleTestResult = getResult();
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

    private SingleTestResult getResult() {
        StringBuffer output = new StringBuffer();

        try {
            System.out.println(EXECUTE_METHOD);
            Process process = Runtime.getRuntime().exec(EXECUTE_METHOD);

            checkExecTime(20);

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
        do {
            programWorks = true;
            try {
                StringBuffer output = new StringBuffer();
                Process process = Runtime.getRuntime().exec("ps -x");
                process.waitFor();
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    if (getProcessName(line).equals(EXECUTE_METHOD))
                        programWorks = false;
                }

                if (!programWorks)
                    return;

                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println("checkExecTime: error = " + e.getMessage());
            }
            i++;
        } while (i < limitInSeconds && programWorks);

        throw new TimeoutException();
    }

    private String getProcessName(String line) {
        String[] s = line.split(" ");

        return s[s.length-1];
    }

    private void cleanUp() throws IOException, InterruptedException{
        Process process = Runtime.getRuntime().exec(CLEAN_UP_METHOD);
        process.waitFor();
    }
}
