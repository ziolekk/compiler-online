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

    @Override
    public java.lang.String toString() {
        StringBuilder stringBuilder = new StringBuilder();


        stringBuilder.append("{ \"id\": ");
        stringBuilder.append(this.id);


        stringBuilder.append(", \"isGoodCompilation\": ");
        stringBuilder.append(this.isGoodCompilation);


        stringBuilder.append(", \"testResults\": [");
        if (!testResults.isEmpty()) {
            stringBuilder.append("\"" + testResults.get(0) + "\"");
            for (int i = 1; i < testResults.size(); i++)
                stringBuilder.append(", \"" + testResults.get(i) + "\"");
        }
        stringBuilder.append("], ");

        stringBuilder.append("\"testStatus\": [");
        if (!testStatus.isEmpty()) {
            stringBuilder.append("\"" + testStatus.get(0) + "\"");
            for (int i = 1; i < testStatus.size(); i++)
                stringBuilder.append(", \"" + testStatus.get(i) + "\"");
        }
        stringBuilder.append("] }");


        return stringBuilder.toString();
    }
}
