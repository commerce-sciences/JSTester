package com.commercesciences.JSTester.utils;

import org.mozilla.javascript.ast.AstNode;
import org.mozilla.javascript.ast.KeywordLiteral;
import org.mozilla.javascript.ast.Name;
import org.mozilla.javascript.ast.StringLiteral;

public class RhinoUtils {

    static public String getStringValue(AstNode element) {
        if (element instanceof StringLiteral) return ((StringLiteral) element).getValue();
        if (element instanceof KeywordLiteral) return ((KeywordLiteral)element).toSource();
        if (element instanceof Name) return ((Name)element).getIdentifier();
        return "";
    }
}
