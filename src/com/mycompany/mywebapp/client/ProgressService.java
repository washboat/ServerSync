package com.mycompany.mywebapp.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("ProgressServlet")
public interface ProgressService extends RemoteService {
    String getProgress(String transferID) throws IllegalArgumentException;
}
