package pl.ziolek.compileronline.program;

import java.util.List;

public class Program {

    private int id;
    private String code;
    private List<String> inputs;
    private List<Integer> maxExecutionTimeForTestInSeconds;

    public Program(int id, String code, List<String> inputs, List<Integer> maxExecutionTimeForTestInSeconds) {
        this.id = id;
        this.code = code;
        this.inputs = inputs;
        this.maxExecutionTimeForTestInSeconds = maxExecutionTimeForTestInSeconds;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public List<String> getInputs() {
        return inputs;
    }

    public List<Integer> getMaxExecutionTimeForTestInSeconds() {
        return maxExecutionTimeForTestInSeconds;
    }
}
