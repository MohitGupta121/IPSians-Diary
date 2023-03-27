package com.mohit.ipsians_diary.datamodels;

public class NotesReports {

    public String email, issue;
    public long timeStamp;

    public NotesReports() {
    }

    public NotesReports(String email, String issue, long timeStamp) {
        this.email = email;
        this.issue = issue;
        this.timeStamp = timeStamp;
    }
}
