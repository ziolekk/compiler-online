package pl.ziolek.compileronline;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.ziolek.compileronline.compiler.CppCompiler;
import pl.ziolek.compileronline.program.Program;
import pl.ziolek.compileronline.program.ProgramResults;

@RestController
@RequestMapping(value = "/")
public class CompilerController {

    private CppCompiler cppCompiler = CppCompiler.getInstance();

    @RequestMapping(value = "/compile", method = RequestMethod.POST)
    public ResponseEntity<ProgramResults> getResultsFromProgram(@RequestBody Program program) {

        return new ResponseEntity<>(cppCompiler.compile(program), HttpStatus.OK);
    }
}
