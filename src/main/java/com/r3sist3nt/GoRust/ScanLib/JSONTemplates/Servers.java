package com.r3sist3nt.GoRust.ScanLib.JSONTemplates;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by maczuga on 10.11.2016.
 */
@JsonIgnoreProperties
public class Servers {

    String addr;
    String gameport;
    String max_players;

    public String getGameport() {
        return gameport;
    }

    public void setGameport(String gameport) {
        this.gameport = gameport;
    }

    public String getMax_players() {
        return max_players;
    }

    public void setMax_players(String max_players) {
        this.max_players = max_players;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}
