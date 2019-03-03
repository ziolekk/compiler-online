package pl.ziolek.compileronline.program;

import java.util.List;

public class Program {

    private int id;
    private String code;
    private List<String> tests;
    private List<Integer> maxExecutionTimeForTestInSeconds;

    public Program(int id, String code, List<String> tests, List<Integer> maxExecutionTimeForTestInSeconds) {
        this.id = id;
        this.code = code;
        this.tests = tests;
        this.maxExecutionTimeForTestInSeconds = maxExecutionTimeForTestInSeconds;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public List<String> getTests() {
        return tests;
    }
}
