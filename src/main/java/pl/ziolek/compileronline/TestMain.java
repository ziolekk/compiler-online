package pl.ziolek.compileronline;

import pl.ziolek.compileronline.compiler.CppCompiler;
import pl.ziolek.compileronline.program.Program;

import java.util.ArrayList;

public class TestMain {

    private CppCompiler cppCompiler;

    public static void main(String[] args) {
        cppCompiler = CppCompiler.getInstance();
    }

    private Program getProgram() {
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
