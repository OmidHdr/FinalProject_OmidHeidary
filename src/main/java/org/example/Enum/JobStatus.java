package org.example.Enum;

public enum JobStatus {

    waitingForSeggestion,
    waitingForExpert,
    expertOnWay,
    started,
    finished,
    payed;

    public static JobStatus getFromString(String name) {
        for (JobStatus value : JobStatus.values()) {
            if (value.toString().equalsIgnoreCase(name)) {
                return value;
            }
        }
        return null;
    }

}
