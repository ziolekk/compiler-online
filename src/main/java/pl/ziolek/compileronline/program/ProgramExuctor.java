package pl.ziolek.compileronline.program;

public class ProgramExuctor {

    private static ProgramExuctor instance = new ProgramExuctor();

    private ProgramExuctor() {}

    public static ProgramExuctor getInstance() {
        return instance;
    }
}
