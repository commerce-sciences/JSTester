package com.commercesciences.JSTester.matchers;

// Use when you want to just stringify the right side of the assignment and compare it
// For example, if your right side is a complex operation:
// supportedCountries = "Israel".split(",");
// You can't use KeywordAssignmentToFind, because the above is not a keyword.
// Instead, use LiteralAssignmentToFind, and it will try to compare right to "Israel".split(",")
public class LiteralAssignmentMatcher extends AssignmentMatcher {
    public LiteralAssignmentMatcher(String left, String right) {
        this.right = right;
        this.left = left;
    }
}
