package com.commercesciences.JSTester.finders;

import org.mozilla.javascript.ast.AstNode;
import org.mozilla.javascript.ast.FunctionNode;
import org.mozilla.javascript.ast.Name;

public class FunctionDefinitionFinder extends AbstractFinder {

    @Override
    public boolean visit(AstNode node) {
        if (wasFound()) return false;
        if (node instanceof FunctionNode) {
            FunctionNode func = (FunctionNode)node;
            Name name = func.getFunctionName();
            if (name!=null && name.getIdentifier().equals(toFind)) {
                found = true;
                return false;
            }
        }
        return true;
    }

}
