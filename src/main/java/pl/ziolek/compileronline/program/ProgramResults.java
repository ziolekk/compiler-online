package pl.ziolek.compileronline.program;

import java.util.ArrayList;
import java.util.List;

public class ProgramResults {

    private int id;
    private boolean isGoodCompilation;
    private List<String> outputs;
    private List<ResultStatus> outputsStatus;

    public ProgramResults(int id) {
        this.id = id;
        this.outputs = new ArrayList<>();
        this.outputsStatus = new ArrayList<>();
    }

    public void setGoodCompilation(boolean isGoodCompilation) {
        this.isGoodCompilation = isGoodCompilation;
    }

    public void addResultsToOutput(String testResult, ResultStatus resultStatus) {
        this.outputs.add(testResult);
        this.outputsStatus.add(resultStatus);
    }

    public java.lang.String toJSON() {
        StringBuilder stringBuilder = new StringBuilder();


        stringBuilder.append("{ \"id\": ");
        stringBuilder.append(this.id);


        stringBuilder.append(", \"isGoodCompilation\": ");
        stringBuilder.append(this.isGoodCompilation);


        stringBuilder.append(", \"outputs\": [");
        if (!outputs.isEmpty()) {
            stringBuilder.append("\"" + outputs.get(0) + "\"");
            for (int i = 1; i < outputs.size(); i++)
                stringBuilder.append(", \"" + outputs.get(i) + "\"");
        }
        stringBuilder.append("], ");

        stringBuilder.append("\"outputsStatus\": [");
        if (!outputsStatus.isEmpty()) {
            stringBuilder.append("\"" + outputsStatus.get(0) + "\"");
            for (int i = 1; i < outputsStatus.size(); i++)
                stringBuilder.append(", \"" + outputsStatus.get(i) + "\"");
        }
        stringBuilder.append("] }");


        return stringBuilder.toString();
    }
}
