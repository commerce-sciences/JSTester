package com.commercesciences.JSTester.matchers;

abstract public class AssignmentMatcher {
    protected String left;
    protected Object right;

    public AssignmentMatcher(){}

    public String left() {
        return left;
    }

    public Object right() {
        return right;
    }
}
