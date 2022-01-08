package com.example.test1.DB;

import android.provider.BaseColumns;

public class MatchContract {
    private MatchContract(){}
    public static class MatchEntry implements BaseColumns {
        public static final String TABLE_NAME ="MATCHES";
        public static final String ID = "ID";
        public static final String TEXT_COL1 = "OPP_NAME";
        public static final String TEXT_COL2 = "DRAFTSIDE";
        public static final String TEXT_COL3 = "RATING";
        public static final String TEXT_COL4 = "RESULT";
    }
}

