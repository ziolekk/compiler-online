package pl.ziolek.compileronline.compiler;

import pl.ziolek.compileronline.program.Program;
import pl.ziolek.compileronline.program.ProgramResults;

public interface Compiler {

    ProgramResults compile(Program program);
}
