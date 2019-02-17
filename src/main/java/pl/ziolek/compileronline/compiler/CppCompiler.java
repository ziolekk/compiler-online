package pl.ziolek.compileronline.compiler;

import pl.ziolek.compileronline.program.Program;
import pl.ziolek.compileronline.program.ProgramResults;

public class CppCompiler implements Compiler {

    private CppCompiler instance = new CppCompiler();

    private CppCompiler() {}

    @Override
    public ProgramResults compile(Program program) {
        return null;
    }

    public CppCompiler getInstance() {
        return instance;
    }
}
