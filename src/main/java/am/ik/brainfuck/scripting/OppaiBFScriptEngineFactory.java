package am.ik.brainfuck.scripting;

import java.util.Arrays;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;

public class OppaiBFScriptEngineFactory implements ScriptEngineFactory {
    private static final OppaiBFScriptEngine ENGINE = new OppaiBFScriptEngine();
    private static final List<String> NAMES = Arrays.asList("oppaibf");
    private static final List<String> EXTENSIONS = Arrays.asList("obf");

    @Override
    public String getEngineName() {
        return "oppaibf script";
    }

    @Override
    public String getEngineVersion() {
        return "0.1";
    }

    @Override
    public List<String> getExtensions() {
        return EXTENSIONS;
    }

    @Override
    public String getLanguageName() {
        return "oppaibf";
    }

    @Override
    public String getLanguageVersion() {
        return "??";
    }

    @Override
    public String getMethodCallSyntax(String obj, String m, String... args) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> getMimeTypes() {
        return Arrays.asList();
    }

    @Override
    public List<String> getNames() {
        return NAMES;
    }

    @Override
    public String getOutputStatement(String toDisplay) {
        return toDisplay;
    }

    @Override
    public Object getParameter(String key) {
        return null;
    }

    @Override
    public String getProgram(String... statements) {
        StringBuilder sb = new StringBuilder();
        for (String statement : statements) {
            sb.append(statement);
        }
        return sb.toString();
    }

    @Override
    public ScriptEngine getScriptEngine() {
        return ENGINE;
    }

}
