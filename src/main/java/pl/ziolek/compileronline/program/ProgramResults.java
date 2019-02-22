package pl.ziolek.compileronline.program;

import java.util.ArrayList;
import java.util.List;

public class ProgramResults {

    private int id;
    private boolean isGoodCompilation;
    private List<String> testResults;
    private List<ResultStatus> testStatus;

    public ProgramResults(int id) {
        this.id = id;
        this.testResults = new ArrayList<>();
        this.testStatus = new ArrayList<>();
    }

    public void setGoodCompilation(boolean isGoodCompilation) {
        this.isGoodCompilation = isGoodCompilation;
    }

    public void addTestResults(String testResult, ResultStatus resultStatus) {
        this.testResults.add(testResult);
        this.testStatus.add(resultStatus);
    }
}
