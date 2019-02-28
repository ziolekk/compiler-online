package pl.ziolek.compileronline.compiler;

import pl.ziolek.compileronline.program.Program;
import pl.ziolek.compileronline.program.ProgramExuctor;
import pl.ziolek.compileronline.program.ProgramResults;
import pl.ziolek.compileronline.program.SingleTestResult;

import java.io.*;

public class CppCompiler implements Compiler {

    private static CppCompiler instance = new CppCompiler();
    private ProgramResults programResults;
    private ProgramExuctor programExuctor;

    private final String PATH_TO_FOLDER = "/home/ziolek/Projects/IdeaProjects/CompilerOnline/cpp_compilations/";
    private final String FILE_NAME = "code.cpp";
    private final String COMPILE_FILE_NAME = "program";
    private final String CLEANUP_COMMAND = "rm";
    private final String COMPILE_COMMAND = "g++ " + PATH_TO_FOLDER + FILE_NAME + " -o " + PATH_TO_FOLDER + COMPILE_FILE_NAME;

    private CppCompiler() {
        programExuctor = ProgramExuctor.getInstance();
    }

    @Override
    public ProgramResults compile(Program program) {
        programResults = new ProgramResults(program.getId());

        try {
            createFile();
            saveCodeToFile(program.getCode());
            programResults.setGoodCompilation(compileProgram());
            executeSingleTest(program);
            cleanUp();
        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

        return programResults;
    }

    public static CppCompiler getInstance() {
        return instance;
    }

    private void createFile() throws IOException {
        String absoluteFilePath = this.pathToFile + this.fileName;
        File file = new File(absoluteFilePath);

        if (file.createNewFile()) {
            System.out.println("CppCompiler: createFile() ok");
        } else {
            System.out.println("CppCompiler: createFile() error");
        }
    }

    private void saveCodeToFile(String code) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(this.pathToFile + this.fileName));
        writer.write(code);
        writer.close();
    }

    private boolean compileProgram() throws IOException, InterruptedException {
        StringBuffer output = new StringBuffer();
        System.out.println("Execute command: " + this.compileCommand1 + " " + this.pathToFile + this.fileName
                + " " +  this.compileCommand2 + " " + this.pathToFile + this.compileFileName
        );
        Process process = Runtime.getRuntime().exec(
                this.compileCommand1 + " " + this.pathToFile + this.fileName
                + " " +  this.compileCommand2 + " " + this.pathToFile + this.compileFileName
        );
        process.waitFor();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null)
            output.append(line);

        if (output.toString().equals(""))
            return true;
        return false;
    }

    private void executeSingleTest(Program program) throws IOException, InterruptedException {
        SingleTestResult singleTestResult;
        for (String input : program.getTests()) {
            singleTestResult = programExuctor.execute(input, pathToFile + compileFileName);
            programResults.addTestResults(singleTestResult.getResult(), singleTestResult.getResultStatus());
        }
    }

    private void cleanUp() throws IOException, InterruptedException {
        Process process;
        System.out.println("Exec command: " + this.cleanUpCommand + " " + this.pathToFile + this.fileName);
        process = Runtime.getRuntime().exec(this.cleanUpCommand + " " + this.pathToFile + this.fileName);
        process.waitFor();
        System.out.println("Exec command: " + this.cleanUpCommand + " " + this.pathToFile + this.compileFileName);
        process = Runtime.getRuntime().exec(this.cleanUpCommand + " " + this.pathToFile + this.compileFileName);
        process.waitFor();
    }
}
