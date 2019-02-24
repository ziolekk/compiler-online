package pl.ziolek.compileronline.program;

import java.io.*;

public class ProgramExuctor {

    private static ProgramExuctor instance = new ProgramExuctor();

    private final String PATH_TO_FOLDER = "/home/ziolek/Projects/IdeaProjects/CompilerOnline/cpp_compilations/";
    private final String TEST_FILE_NAME = "input.in";
    private final String ABSOLUTE_PATH_TO_FILE = PATH_TO_FOLDER + TEST_FILE_NAME;
    private final String CLEAN_UP_METHOD = "rm " + PATH_TO_FOLDER + TEST_FILE_NAME;

    private ProgramExuctor() {}

    public static ProgramExuctor getInstance() {
        return instance;
    }

    public SingleTestResult execute(String input, String porgramFilePath) throws IOException, InterruptedException {


        return null;
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

    private void cleanUp() throws IOException, InterruptedException{
        Process process = Runtime.getRuntime().exec(CLEAN_UP_METHOD);
        process.waitFor();
    }
}
