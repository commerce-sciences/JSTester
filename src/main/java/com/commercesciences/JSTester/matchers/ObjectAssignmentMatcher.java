package com.commercesciences.JSTester.matchers;

public class ObjectAssignmentMatcher extends AssignmentMatcher {

    private String objectName;

    public ObjectAssignmentMatcher(String root, String objectName, String propertyLeft, String propertyRight) {
        this.left = root;
        this.right = new LiteralAssignmentMatcher(propertyLeft, propertyRight);
        this.objectName = objectName;
    }

    public String getObjectName() {
        return objectName;
    }

    public LiteralAssignmentMatcher getRight() {
        return (LiteralAssignmentMatcher)right;
    }

}
