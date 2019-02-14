package pl.ziolek.compileronline.program;

import java.util.ArrayList;
import java.util.List;

public class ProgramResults {

    private int id;
    private List<String> testResults;
    private List<String> testStatus;

    public ProgramResults(int id) {
        this.id = id;
        this.testResults = new ArrayList<>();
        this.testStatus = new ArrayList<>();
    }
}
