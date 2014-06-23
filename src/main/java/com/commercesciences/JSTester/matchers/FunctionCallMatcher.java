package com.commercesciences.JSTester.matchers;

import org.mozilla.javascript.ast.AstNode;
import org.mozilla.javascript.ast.KeywordLiteral;
import org.mozilla.javascript.ast.StringLiteral;
import org.mozilla.javascript.Token;

import java.util.List;

public class FunctionCallMatcher extends AssignmentMatcher {

    public AstNode[] args;

    public FunctionCallMatcher(String functionName, String...args) {
        this.left = functionName;
        this.args = new AstNode[args.length];
        Integer i = 0;
        for (String arg : args) {
            StringLiteral literal = new StringLiteral();
            literal.setValue(arg);
            this.args[i] = literal;
            i++;
        }
    }

    public FunctionCallMatcher(String functionName, List<Object> args) {
        this.left = functionName;
        this.args = new AstNode[args.size()];
        Integer i = 0;
        for (Object arg : args) {
            AstNode literal = null;
            if (arg instanceof String) {
                literal = new StringLiteral();
                ((StringLiteral)literal).setValue((String)arg);
            } else
            if (arg instanceof Boolean) {
                literal = new KeywordLiteral();
                ((KeywordLiteral)literal).setType((Boolean)arg?Token.TRUE : Token.FALSE);
            }
            this.args[i] = literal;
            i++;
        }

    }
}
