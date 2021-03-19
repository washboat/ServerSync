package com.mycompany.mywebapp.server;

import com.google.gwt.core.client.GWT;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


//@RemoteServiceRelativePath("DownloadServlet")
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        String fileName = req.getParameter("fileInfo");
        System.out.println(fileName);
        GWT.log(fileName);
        File file = new File(fileName);
        resp.setHeader("Content-Disposition", "application; filename=\"" + fileName + "\"");
        resp.addHeader("Cache-Control", "no-transform, max-age=0");
        resp.setContentType(getServletContext().getMimeType(file.getName()));
        resp.setContentLengthLong(file.length());
        resp.setBufferSize(8192);

        BufferedInputStream input = null;
        BufferedOutputStream output = null;

        try {
            InputStream inputStream = new FileInputStream(file);
            ServletOutputStream outputStream = resp.getOutputStream();

            input = new BufferedInputStream(inputStream);
            output = new BufferedOutputStream(outputStream);

            int count;
            byte[] buffer = new byte[8192]; //  buffer size is 512*16
            while ((count = input.read(buffer)) != -1) {
                output.write(buffer, 0, count);
            }
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException ex) {
                }
            }
            if (input != null) {
                try {
                    input.close();
                } catch (IOException ex) {
                }
            }
//        super.doGet(req, resp);


        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
