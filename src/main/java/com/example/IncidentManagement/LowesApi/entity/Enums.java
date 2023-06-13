package com.example.IncidentManagement.LowesApi.entity;

public class Enums {
    public enum Priority {
        Critical,
        High,
        Medium,
        Low
    }

    public enum Department {
        Development,
        IT,
        HR,
        ISG
    }

    public enum Category {
        Software_Issues,
        Hardware_Issues,
        Accessory_Issues,
    }

    public enum Status {
        New,
        In_Progress,
        Resolved,
        Rejected
    }
}
