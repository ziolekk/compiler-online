package pl.ziolek.compileronline;

import pl.ziolek.compileronline.compiler.CppCompiler;
import pl.ziolek.compileronline.program.Program;

import java.util.ArrayList;

public class TestMain {

    public static void main(String[] args) {
        CppCompiler cppCompiler = CppCompiler.getInstance();
        Program program = new TestMain().getProgram();


        cppCompiler.compile(program);
    }

    public Program getProgram() {
        return new Program(
                1,
                "#include <iostream>" +
                        "using namespace std;" +
                        "" +
                        "int main() {" +
                        "   cout << \"Hello World\";" +
                        "}",
                new ArrayList<>()
        );
    }
}
