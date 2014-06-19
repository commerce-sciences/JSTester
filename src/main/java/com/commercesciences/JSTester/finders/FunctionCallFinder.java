package com.commercesciences.JSTester.finders;

import org.mozilla.javascript.ast.AstNode;
import org.mozilla.javascript.ast.FunctionCall;
import org.mozilla.javascript.ast.Name;

public class FunctionCallFinder extends AbstractFinder {

    @Override public boolean visit(AstNode node) {
        if (node instanceof FunctionCall) {
            FunctionCall func = (FunctionCall)node;
            if (func.getTarget() instanceof Name) {
                Name name = (Name)func.getTarget();
                if (name.getIdentifier().equals(toFind)) {
                    found = true;
                    return false;
                }
            }
        }
        return true;
    }
}