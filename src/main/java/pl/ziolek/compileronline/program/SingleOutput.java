package pl.ziolek.compileronline.program;

public class SingleOutput {

    private String result;
    private ResultStatus resultStatus;

    public SingleOutput(String result, ResultStatus resultStatus) {
        this.result = result;
        this.resultStatus = resultStatus;
    }

    public String getResult() {
        return result;
    }

    public ResultStatus getResultStatus() {
        return resultStatus;
    }
}
