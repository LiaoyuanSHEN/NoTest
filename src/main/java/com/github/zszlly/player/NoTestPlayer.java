package com.github.zszlly.player;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.zszlly.io.CaseHolder;
import com.github.zszlly.model.Case;
import com.github.zszlly.player.mock.NoTestCasePlayer;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

public class NoTestPlayer {

    private Collection<Case> cases;

    public NoTestPlayer(Collection<Case> cases) {
        this.cases = cases;
    }

    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Collection<Case> cases = mapper.readValue(new File(args[0]), CaseHolder.class).getCases();
        NoTestPlayer player = new NoTestPlayer(cases);
        player.play();
    }

    public void play() {
        cases.forEach(NoTestCasePlayer::runCase);
    }

}
