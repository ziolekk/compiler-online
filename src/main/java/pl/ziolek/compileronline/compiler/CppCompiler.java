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

        return null;
    }

    public CppCompiler getInstance() {
        return instance;
    }

    private void createFile() {

    }
}
