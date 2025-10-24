package com.kisslab.testrail;

public enum TestRailStatus {
    Passed(1),
    Blocked(2),
    Untested(3),
    Retest(4),
    Failed(5);

    private final int value;

    TestRailStatus(int i) {
        this.value = i;
    }

    public int getValue() {
        return value;
    }
}
