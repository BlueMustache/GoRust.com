package com.r3sist3nt.GoRust.ScanLib.JSONTemplates;

/**
 * Created by maczuga on 10.11.2016.
 */
public class Response {
    public ServerTemplateJSON[] getResponse() {
        return servers;
    }

    public void ServerTemplateJSON(ServerTemplateJSON[] servers) {
        this.servers = servers;
    }

    ServerTemplateJSON[] servers;


}
