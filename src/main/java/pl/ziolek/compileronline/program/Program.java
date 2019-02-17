package pl.ziolek.compileronline.program;

import java.util.List;

public class Program {

    private int id;
    private String code;
    private List<String> tests;

    public Program(int id, String code, List<String> tests) {
        this.id = id;
        this.code = code;
        this.tests = tests;
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