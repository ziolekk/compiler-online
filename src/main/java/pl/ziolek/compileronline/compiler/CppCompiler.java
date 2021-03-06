package pl.ziolek.compileronline.compiler;

import pl.ziolek.compileronline.program.Program;
import pl.ziolek.compileronline.program.ProgramExecutor;
import pl.ziolek.compileronline.program.ProgramResults;
import pl.ziolek.compileronline.program.SingleOutput;

import java.io.*;
import java.util.List;

public class CppCompiler implements Compiler {

    private static CppCompiler instance = new CppCompiler();
    private ProgramResults programResults;
    private ProgramExecutor programExecutor;

    private final String PATH_TO_FOLDER = "/compilations/cpp_compilations/";
//    private final String PATH_TO_FOLDER = "/home/ziolek/Projects/IdeaProjects/CompilerOnline/cpp_compilations/";
    private final String FILE_NAME = "code.cpp";
    private final String COMPILE_FILE_NAME = "program";
    private final String CLEANUP_COMMAND = "rm";
    private final String COMPILE_COMMAND = "g++ " + PATH_TO_FOLDER + FILE_NAME + " -o " + PATH_TO_FOLDER + COMPILE_FILE_NAME;

    private CppCompiler() {
        programExecutor = ProgramExecutor.getInstance();
    }

    @Override
    public synchronized ProgramResults compile(Program program) {
        programResults = new ProgramResults(program.getId());

        try {
            createFile();
            saveCodeToFile(program.getCode());
            if (compileProgram())
                programResults.setGoodCompilation(true);
            else {
                programResults.setGoodCompilation(false);
                return programResults;
            }
            executeSingleTest(program);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            cleanUp();
        }

        return programResults;
    }

    public static CppCompiler getInstance() {
        return instance;
    }

    private void createFile() throws IOException {
        File file = new File(PATH_TO_FOLDER + FILE_NAME);

        if (file.createNewFile()) {
            System.out.println("CppCompiler: createFile() ok");
        } else {
            System.out.println("CppCompiler: createFile() error");
        }
    }

    private void saveCodeToFile(String code) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(PATH_TO_FOLDER + FILE_NAME));
        writer.write(code);
        writer.close();
    }

    private boolean compileProgram() throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec(COMPILE_COMMAND);
        process.waitFor();

        File programFile = new File(PATH_TO_FOLDER + COMPILE_FILE_NAME);
        if (programFile.exists())
            return true;
        return false;
    }

    private void executeSingleTest(Program program) throws IOException, InterruptedException {
        SingleOutput singleOutput;
        List<String> inputs = program.getInputs();
        List<Integer> maxExecutionTimeInSeconds = program.getMaxExecutionTimeForTestInSeconds();
        for (int i = 0; i < inputs.size(); i++) {
            singleOutput = programExecutor.execute(inputs.get(i), maxExecutionTimeInSeconds.get(i));
            programResults.addResultsToOutput(singleOutput.getResult(), singleOutput.getResultStatus());
        }

    }

    private void cleanUp() {
        try {
            Process process;
            process = Runtime.getRuntime().exec(CLEANUP_COMMAND + " " + PATH_TO_FOLDER + FILE_NAME);
            process.waitFor();
            process = Runtime.getRuntime().exec(CLEANUP_COMMAND + " " + this.PATH_TO_FOLDER + this.COMPILE_FILE_NAME);
            process.waitFor();
        } catch (Exception e) {
            System.out.println("cleanUp method error: " + e.getMessage());
        }
    }
}
