package com.studyeire.StudyEireMain.constants;

public enum ConnectionFormStatus {
    ACTIVE("ACTIVE"),
    RESOLVED("RESOLVED");

    private String value;
    private ConnectionFormStatus(String val) {
        this.value = val;
    }

    private String getValue() {
        return value;
    }
    public static ConnectionFormStatus getEnumValue(String s) {
        for(ConnectionFormStatus status : ConnectionFormStatus.values()){
            if(status.getValue().equalsIgnoreCase(s))
                return status;
        }
        return ConnectionFormStatus.ACTIVE;
    }
}
