package com.r3sist3nt.GoRust.ScanLib.JSONTemplates;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by maczuga on 10.11.2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServerTemplateJSON {
    Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
