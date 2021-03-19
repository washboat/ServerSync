package com.mycompany.mywebapp.server;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.mycompany.mywebapp.client.ProgressService;
import org.apache.commons.fileupload.ProgressListener;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ProgressServiceImpl extends RemoteServiceServlet implements ProgressService {
    private HttpSession session;

    @Override
    public String getProgress(String transferID) throws IllegalArgumentException {
        String info = getServletContext().getServerInfo();
        ProgressListener progressListener = (ProgressListener) session.getAttribute(transferID);
//        progressListener.toString();
        return progressListener.toString();
    }

    @Override
    public String processCall(String payload) throws SerializationException {
        HttpServletRequest req = getThreadLocalRequest();
        session = req.getSession();
        return super.processCall(payload);
    }
}
