package com.r3sist3nt.GoRust.ScanLib.model;

import com.r3sist3nt.GoRust.ScanLib.ServerPacket;

/**
 * Created by Julian on 01.11.2016.
 */
public class ServerInfo {
    private String name,map,folder,game;

    public ServerInfo(byte[] response){
        int last=6;//Ignore first 6 Header byte

        /**
         * Search Strings by looking for NULL-Terminators
         * Protocol Documentation found at: https://developer.valvesoftware.com/wiki/Server_queries
         */
        for(int k=0;k<4;k++){
            int index=last;
            while(response[index]!=0){
                index++;
            }
            byte[] str = new byte[index-last];
            System.arraycopy(response,last,str,0,index-last);

            switch (k) {
                case 0:
                    name = new String(str);
                    break;
                case 1:
                    map = new String(str);
                    break;
                case 2:
                    folder = new String(str);
                    break;
                case 3:
                    game = new String(str);
                    break;
            }

            //Set last Null Terminator index and increase Index to ignore detection in next loop
            last=index+1;
        }
    }

    public String getName() {
        return name;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }
}
