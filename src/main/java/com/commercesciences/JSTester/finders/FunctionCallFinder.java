package com.commercesciences.JSTester.finders;

import com.commercesciences.JSTester.matchers.FunctionCallMatcher;
import org.mozilla.javascript.ast.AstNode;
import org.mozilla.javascript.ast.FunctionCall;
import org.mozilla.javascript.ast.Name;
import org.mozilla.javascript.ast.StringLiteral;

public class FunctionCallFinder extends AbstractFinder {

    @Override public boolean visit(AstNode node) {
        if (wasFound()) return false;
        if (node instanceof FunctionCall) {
            FunctionCall func = (FunctionCall)node;
            if (func.getTarget() instanceof Name) {
                Name name = (Name)func.getTarget();
                // Just match function name
                if (toFind instanceof String) {
                    if (name.getIdentifier().equals(toFind)) {
                        found = true;
                        return false;
                    }
                } else {
                    FunctionCallMatcher matcher = (FunctionCallMatcher)toFind;

                    if (name.getIdentifier().equals(matcher.left())) {
                        Integer argNumber = 0;
                        // Match method's argument one by one
                        if (func.getArguments().size()==matcher.args.length) {
                            found = true;
                            for (AstNode argNode : func.getArguments()) {
                                if (argNode instanceof StringLiteral && matcher.args[argNumber] instanceof StringLiteral) {
                                    if (!((StringLiteral)argNode).getValue().equals(((StringLiteral)matcher.args[argNumber]).getValue())) {
                                        found = false;
                                        break;
                                    }
                                }
                                argNumber++;
                            }
                        }
                    }
                    if (found) {
                        return false;
                    }
                }


            }
        }
        return true;
    }
}