package pl.ziolek.compileronline;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ziolek.compileronline.compiler.CppCompiler;
import pl.ziolek.compileronline.program.Program;
import pl.ziolek.compileronline.program.ProgramResults;

@RestController
@RequestMapping(value = "/")
public class CompilerController {

    private CppCompiler cppCompiler = CppCompiler.getInstance();

    @RequestMapping(value = "/compile", method = RequestMethod.POST)
    public ResponseEntity<String> getResultsFromProgram(@RequestBody Program program) {
        ProgramResults programResults = cppCompiler.compile(program);
        System.out.println("CompilerController: ProgramResults: " + programResults.toJSON());
        return new ResponseEntity<>(programResults.toJSON(), HttpStatus.OK);
    }
}
