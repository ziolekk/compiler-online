package pl.ziolek.compileronline.compiler;

import pl.ziolek.compileronline.program.Program;
import pl.ziolek.compileronline.program.ProgramResults;

import java.io.File;
import java.io.IOException;

public class CppCompiler implements Compiler {

    private CppCompiler instance = new CppCompiler();
    private final String pathToFile = "/home/ziolek/Projects/IdeaProjects/CompilerOnline/cpp_compilations/";

    private CppCompiler() {}

    @Override
    public ProgramResults compile(Program program) {

        try {
            createFile();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

        return null;
    }

    public CppCompiler getInstance() {
        return instance;
    }

    private void createFile() throws IOException {
        String absoluteFilePath = this.pathToFile + "code.cpp";
        File file = new File(absoluteFilePath);

        if (file.createNewFile()) {
            System.out.println("CppCompiler: createFile() ok");
        } else {
            System.out.println("CppCompiler: createFile() error");
        }
    }
}
