package com.commercesciences.JSTester.finders;

import com.commercesciences.JSTester.matchers.AssignmentMatcher;
import com.commercesciences.JSTester.matchers.KeywordAssignmentMatcher;
import com.commercesciences.JSTester.matchers.LiteralAssignmentMatcher;
import com.commercesciences.JSTester.matchers.ObjectAssignmentMatcher;
import com.commercesciences.JSTester.utils.RhinoUtils;
import org.mozilla.javascript.ast.*;

public class AssignmentFinder extends AbstractFinder {
    @Override public boolean visit(AstNode node) {
        if (node instanceof ExpressionStatement) {
            ExpressionStatement statement = (ExpressionStatement)node;
            if (statement.getExpression() instanceof Assignment) {
                Assignment assignment = (Assignment)statement.getExpression();
                if (assignment.getLeft() instanceof Name) {
                    AssignmentMatcher assignmentToFind = (AssignmentMatcher)toFind;
                    Name name = (Name)assignment.getLeft();
                    if (name.getIdentifier().equals(assignmentToFind.left())) {
                        if (assignmentToFind instanceof LiteralAssignmentMatcher) {
                            String right = assignment.getRight().toSource();
                            if (right.equals(assignmentToFind.right())) {
                                found = true;
                                return false;
                            }
                        } else
                        if (assignmentToFind instanceof KeywordAssignmentMatcher && (assignment.getRight() instanceof KeywordLiteral || assignment.getRight() instanceof StringLiteral)) {
                            String right = RhinoUtils.getStringValue(assignment.getRight());
                            if (right.equals(assignmentToFind.right())) {
                                found = true;
                                return false;
                            }
                        } else
                        if (assignmentToFind instanceof ObjectAssignmentMatcher && assignment.getRight() instanceof ObjectLiteral) {
                            ObjectAssignmentMatcher objectToFind = (ObjectAssignmentMatcher)assignmentToFind;
                            // Recursively traverse the object to find the property assignment.
                            if (findInObjectLiteral(assignment, objectToFind)) {
                                found = true;
                                return false;
                            }
                        }

                    }
                }
            }
        }
        return true;
    }

    private Boolean findInObjectLiteral(InfixExpression root, ObjectAssignmentMatcher toFind) {
        String rootName = root.getLeft().toSource();
        if (root.getRight() instanceof ObjectLiteral) {
            for (ObjectProperty prop : ((ObjectLiteral) root.getRight()).getElements()) {
                if (prop.getRight() instanceof StringLiteral) {
                    String left = RhinoUtils.getStringValue(prop.getLeft());
                    String right = RhinoUtils.getStringValue(prop.getRight());
                    if (toFind.getObjectName().equals(rootName)) {
                        if (toFind.getRight().left().equals(left) && toFind.getRight().right().equals(right)) {
                            return true;
                        }
                    }
                } else {
                    Boolean found = findInObjectLiteral(prop, toFind);
                    if (found) return true;
                }

            }
        }
        return false;
    }
}
