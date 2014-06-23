package com.commercesciences.JSTester.finders;

import org.mozilla.javascript.ast.AstNode;
import org.mozilla.javascript.ast.NodeVisitor;

abstract public class AbstractFinder implements NodeVisitor {
    protected Object toFind;
    protected Boolean found = false;

    public Boolean wasFound() {
        return this.found;
    }

    public void toFind(Object toFind) {
        this.toFind = toFind;
    }

}
