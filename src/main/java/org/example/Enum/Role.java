package org.example.Enum;


public enum Role {
    customer,
    expert;
    public static Role getFromString(String name) {
        for (Role value : Role.values()) {
            if (value.toString().equalsIgnoreCase(name)) {
                return value;
            }
        }
        return null;
    }
    
}
