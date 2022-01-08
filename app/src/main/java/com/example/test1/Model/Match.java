package com.example.test1.Model;

public class Match {

    String opp_name, draft_side, rating, result;

    //Constructor
    public Match(String opp_name, String draft_side, String rating, String result) {
        this.opp_name = opp_name;
        this.draft_side = draft_side;
        this.rating = rating;
        this.result = result;
    }

    //Setters
    public void setOpp_name(String opp_name) {
        this.opp_name = opp_name;
    }

    public void setDraft_side(String draft_side) {
        this.draft_side = draft_side;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setResult(String result) {
        this.result = result;
    }


    //Getters
    public String getOpp_name() {
        return opp_name;
    }

    public String getDraft_side() {
        return draft_side;
    }

    public String getRating() {
        return rating;
    }

    public String getResult() {
        return result;
    }


}
