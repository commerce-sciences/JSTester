package com.commercesciences.JSTester;

import com.commercesciences.JSTester.finders.AbstractFinder;
import com.commercesciences.JSTester.finders.AssignmentFinder;
import com.commercesciences.JSTester.finders.FunctionCallFinder;
import com.commercesciences.JSTester.finders.FunctionDefinitionFinder;
import com.commercesciences.JSTester.matchers.KeywordAssignmentMatcher;
import com.commercesciences.JSTester.matchers.LiteralAssignmentMatcher;
import com.commercesciences.JSTester.matchers.ObjectAssignmentMatcher;
import org.mozilla.javascript.Parser;
import org.mozilla.javascript.ast.AstRoot;

public class JSTester {


    private String codeBootstrap = "";

    private String codeToTest = "";

    public void setCodeBootstrap(String bootstrap) {
        codeBootstrap = bootstrap;
    }

    // Direct API

    public Boolean codeContains(String code, AbstractFinder finder, Object toFind) throws Exception {
        code = codeBootstrap + code;
        AstRoot root = new Parser().parse(code, null, 0);
        finder.toFind(toFind);
        root.visit(finder);
        return finder.wasFound();
    }

    // Fluid API

    public JSTester code(String code) {
        codeToTest = code;
        return this;
    }

    public Boolean hasFunctionCall(String functionName) throws Exception {
        return codeContains(codeToTest, new FunctionCallFinder(), "setDefaultBubblerOption");
    }

    public Boolean hasFunctionDefinition(String functionName) throws Exception {
        return codeContains(codeToTest, new FunctionDefinitionFinder(), functionName);
    }

    public Boolean hasObjectAssignment(String root, String objectName, String propLeft, String propRight) throws Exception {
        return codeContains(codeToTest, new AssignmentFinder(), new ObjectAssignmentMatcher(root, objectName, propLeft, propRight));
    }

    public Boolean hasLiteralAssignment(String varName, String varValue) throws Exception {
        return codeContains(codeToTest, new AssignmentFinder(), new LiteralAssignmentMatcher(varName, varValue));
    }

    public Boolean hasKeywordAssignment(String varName, String keyword) throws Exception {
        return codeContains(codeToTest, new AssignmentFinder(), new KeywordAssignmentMatcher(varName, keyword));
    }
}
