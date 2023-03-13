package com.synesis.hrmis.enums;

public enum EmploymentType {
    FULL_TIME("Full-time"),
    CONTRACTUAL("Contractual");

    private final String label;

    private EmploymentType(String label) { this.label = label; }

    @Override
    public String toString() { return label; }
}
