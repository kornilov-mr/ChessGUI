package org.example.serverConnection.responses;

import java.util.ArrayList;

public class MultiEloResponse {
    private ArrayList<EloResponse> eloResponses = new ArrayList<>();

    public MultiEloResponse() {
    }

    public MultiEloResponse(ArrayList<EloResponse> eloResponses) {
        this.eloResponses = eloResponses;
    }

    public ArrayList<EloResponse> getEloResponses() {
        return eloResponses;
    }

    public void setEloResponses(ArrayList<EloResponse> eloResponses) {
        this.eloResponses = eloResponses;
    }
}
