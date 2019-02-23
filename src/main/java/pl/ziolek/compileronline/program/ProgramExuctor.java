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

    public SingleTestResult execute(String input) throws IOException, InterruptedException {


        return null;
    }
}
