package com.commercesciences.JSTester.matchers;

public class KeywordAssignmentMatcher extends AssignmentMatcher {
    public KeywordAssignmentMatcher(String left, String right) {
        this.right = right;
        this.left = left;
    }
}
