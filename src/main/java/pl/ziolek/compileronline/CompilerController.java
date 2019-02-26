package pl.ziolek.compileronline;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.ziolek.compileronline.program.Program;
import pl.ziolek.compileronline.program.ProgramResults;

@RestController
@RequestMapping(value = "/")
public class CompilerController {

    @RequestMapping(value = "/compile", method = RequestMethod.GET)
    public ResponseEntity<ProgramResults> getResultsFromProgram(@RequestBody Program program) {

        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
