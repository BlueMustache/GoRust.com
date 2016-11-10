package com.r3sist3nt.GoRust.ScanLib.model;

import com.r3sist3nt.GoRust.ScanLib.JSONTemplates.ServerTemplateJSON;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;

/**
 * UDP Masterserver is deprecated
 * Using Web API From Steam:
 * ->
 * String: https://api.steampowered.com/IGameServersService/GetServerList/v1?key=0060A0C1B81DC3902B53CE64662F489A&limit=10000&filter=\appid\252490
 */

public class Masterserver_WebAPI {
    private static String serverUri = "https://api.steampowered.com/IGameServersService/GetServerList/v1?key=0060A0C1B81DC3902B53CE64662F489A&limit=10000&filter=\\appid\\252490\n"
    public LinkedList<Server> requestServerList() {
        RestTemplate restTemplate = new RestTemplate();
        ServerTemplateJSON = restTemplate.getForObject(serverUri,ServerTemplateJSON.class);





        return null;
    }


}
