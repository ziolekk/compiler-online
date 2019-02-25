package pl.ziolek.compileronline;

import pl.ziolek.compileronline.compiler.CppCompiler;
import pl.ziolek.compileronline.program.Program;
import pl.ziolek.compileronline.program.ProgramResults;

import java.util.ArrayList;

public class TestMain {

    public static void main(String[] args) {
        CppCompiler cppCompiler = CppCompiler.getInstance();
        Program program = new TestMain().getProgram();


        ProgramResults results = cppCompiler.compile(program);
        System.out.println(results.toString());

    }

    public Program getProgram() {

        ArrayList tests = new ArrayList<>();
        tests.add("69");

        return new Program(
                1,
                "#include <iostream>\n" +
                        "using namespace std;\n" +
                        "\n" +
                        "int main() {\n" +
                        "   cout << \"Hello World\";\n" +
                        "int x; cin >> x; cout << x << endl;" +
                        "}\n",
                tests
        );
    }
}
