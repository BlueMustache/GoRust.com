package com.r3sist3nt.GoRust.ScanLib.model;

import com.r3sist3nt.GoRust.ScanLib.JSONTemplates.ServerTemplateJSON;
import com.r3sist3nt.GoRust.ScanLib.JSONTemplates.Servers;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;

/**
 * UDP Masterserver is deprecated
 * Using Web API From Steam:
 * ->
 * String: https://api.steampowered.com/IGameServersService/GetServerList/v1?key=0060A0C1B81DC3902B53CE64662F489A&limit=10000&filter=\appid\252490
 */

public class Masterserver_WebAPI {
    //ToDO: Change Encoding to UTF-32
    private static String serverUri = "https://api.steampowered.com/IGameServersService/GetServerList/v1?key=0060A0C1B81DC3902B53CE64662F489A&limit=100&filter=\\appid\\252490\n";

    public LinkedList<Server> requestServerList() {
        RestTemplate restTemplate = new RestTemplate();

        ServerTemplateJSON r = restTemplate.getForObject(serverUri, ServerTemplateJSON.class);

        System.out.println("SERVER: " + r.getResponse().getServers()[0].getAddr());

        LinkedList<Server> sList = new LinkedList<Server>();
        for (int k = 0; k < r.getResponse().getServers().length; k++) {
            Servers responseServer = r.getResponse().getServers()[k];
            Server s = new Server(responseServer.getAddr(),Integer.parseInt(responseServer.getGameport()));

            sList.add(s);
        }


        return sList;
    }


}
