package com.example.test1.DB;

import android.provider.BaseColumns;

public class MatchContract {
    private MatchContract(){}
    public static class MatchEntry implements BaseColumns {
        public static final String TABLE_NAME ="MATCHES";
        public static final String ID = "ID";
        public static final String OPP_NAME = "OPP_NAME";
        public static final String DRAFTSIDE = "DRAFTSIDE";
        public static final String RATING = "RATING";
        public static final String RESULT = "RESULT";
    }
}

