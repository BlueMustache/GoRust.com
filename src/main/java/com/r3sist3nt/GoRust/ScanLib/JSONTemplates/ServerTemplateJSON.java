package com.r3sist3nt.GoRust.ScanLib.JSONTemplates;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by maczuga on 10.11.2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServerTemplateJSON {
    String addr;
    String gameport;
    String steamid;
    String name;
    String version;
    String map;
    String max_players;

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getGameport() {
        return gameport;
    }

    public void setGameport(String gameport) {
        this.gameport = gameport;
    }

    public String getSteamid() {
        return steamid;
    }

    public void setSteamid(String steamid) {
        this.steamid = steamid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getMax_players() {
        return max_players;
    }

    public void setMax_players(String max_players) {
        this.max_players = max_players;
    }
}
