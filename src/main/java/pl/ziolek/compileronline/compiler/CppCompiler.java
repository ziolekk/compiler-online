package pl.ziolek.compileronline.compiler;

import pl.ziolek.compileronline.program.Program;
import pl.ziolek.compileronline.program.ProgramResults;

import java.io.*;

public class CppCompiler implements Compiler {

    private static CppCompiler instance = new CppCompiler();
    private ProgramResults programResults;
    private final String pathToFile = "/home/ziolek/Projects/IdeaProjects/CompilerOnline/cpp_compilations/";
    private final String fileName = "code.cpp ";
    private final String compileFileName = "program ";
    private final String cleanUpCommand = "rm ";
    private final String compileCommand1 = "g++ ";
    private final String compileCommand2 = "-o ";

    private CppCompiler() {}

    @Override
    public ProgramResults compile(Program program) {
        programResults = new ProgramResults(program.getId());

        try {
            createFile();
            saveCodeToFile(program.getCode());
            programResults.setGoodCompilation(compileProgram());
            cleanUp();
        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

        return null;
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

    private boolean compileProgram() throws IOException, InterruptedException {
        StringBuffer output = new StringBuffer();
        System.out.println("Execute command: " + this.compileCommand1 + this.pathToFile + this.fileName
                + this.compileCommand2 + this.pathToFile + this.compileFileName
        );
        Process process = Runtime.getRuntime().exec(
                this.compileCommand1 + this.pathToFile + this.fileName
                + this.compileCommand2 + this.pathToFile + this.compileFileName
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

    private void saveCodeToFile(String code) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(this.pathToFile + this.fileName));
        writer.write(code);
        writer.close();
    }

    private void cleanUp() throws IOException, InterruptedException {
        System.out.println("Exec command: " + this.cleanUpCommand + this.pathToFile + this.fileName);
        Process process = Runtime.getRuntime().exec(this.cleanUpCommand + this.pathToFile + this.fileName);
        process.waitFor();
    }
}
