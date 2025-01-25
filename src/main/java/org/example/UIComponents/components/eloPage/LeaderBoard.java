package org.example.UIComponents.components.eloPage;

import org.example.serverConnection.CommonHttpBuilder;
import org.example.serverConnection.responses.EloResponse;
import org.example.serverConnection.responses.MultiEloResponse;

import javax.swing.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Comparator;

public class LeaderBoard extends JPanel {
    public LeaderBoard() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        MultiEloResponse response;
        try {
            response = CommonHttpBuilder.fetchAllUsersElo();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ArrayList<EloResponse> responses = response.getEloResponses();
        responses.sort(new Comparator<EloResponse>() {

            @Override
            public int compare(EloResponse o1, EloResponse o2) {
                if(o1.getCurrElo()<o2.getCurrElo()){
                    return 1;
                }else if (o1.getCurrElo()>o2.getCurrElo()){
                    return -1;
                }
                return 0;
            }
        });
        for(EloResponse elo : responses){
            add(new LeaderBoardRow(elo.getUsername(),elo.getCurrElo()));
        }
    }
}
