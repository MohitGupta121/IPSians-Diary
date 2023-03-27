package com.mohit.ipsians_diary.utils;

import com.google.firebase.database.FirebaseDatabase;

public class FirebaseUtil {

    private static FirebaseDatabase mDatabase;

    public static FirebaseDatabase getDatabase() {
        if (mDatabase == null) {
            mDatabase = FirebaseDatabase.getInstance();
//            mDatabase.setPersistenceEnabled(true);
        }
        return mDatabase;
    }
}
