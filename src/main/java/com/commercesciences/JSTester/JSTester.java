package com.commercesciences.JSTester;

import com.commercesciences.JSTester.finders.AbstractFinder;
import org.mozilla.javascript.Parser;
import org.mozilla.javascript.ast.AstRoot;

public class JSTester {


    private String codeBootstrap = "";

    public void setCodeBootstrap(String bootstrap) {
        codeBootstrap = bootstrap;
    }

    public Boolean codeContains(String code, AbstractFinder finder, Object toFind) throws Exception {
        code = codeBootstrap + code;
        AstRoot root = new Parser().parse(code, null, 0);
        finder.toFind(toFind);
        root.visit(finder);
        return finder.wasFound();
    }
}
