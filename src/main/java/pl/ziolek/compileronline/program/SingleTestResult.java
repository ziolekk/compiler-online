package pl.ziolek.compileronline.program;

public class SingleTestResult {

    private String result;
    private ResultStatus resultStatus;

    public SingleTestResult(String result, ResultStatus resultStatus) {
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