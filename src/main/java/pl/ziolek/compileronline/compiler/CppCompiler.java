package pl.ziolek.compileronline.compiler;

import pl.ziolek.compileronline.program.Program;
import pl.ziolek.compileronline.program.ProgramResults;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CppCompiler implements Compiler {

    private static CppCompiler instance = new CppCompiler();
    private final String pathToFile = "/home/ziolek/Projects/IdeaProjects/CompilerOnline/cpp_compilations/";
    private final String fileName = "code.cpp";
    private final String cleanUpCommand = "rm code.cpp";

    private CppCompiler() {}

    @Override
    public ProgramResults compile(Program program) {

        try {
            createFile();
            saveCodeToFile(program.getCode());
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

    private void saveCodeToFile(String code) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(this.pathToFile + this.fileName));
        writer.write(code);
        writer.close();
    }

    private void cleanUp() throws IOException {
        Process process = Runtime.getRuntime().exec(this.pathToFile + this.cleanUpCommand);
    }
}
