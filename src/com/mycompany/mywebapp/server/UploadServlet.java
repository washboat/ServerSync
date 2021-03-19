package com.mycompany.mywebapp.server;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;

public class UploadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Cache-Control", "no-transform, max-age=0");
        resp.setContentType("text/plain");
//        super.doPost(req, resp);
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

//        ProgressListener progressListener = new ProgressListener(){
//            private long megaBytes = -1;
//            public void update(long pBytesRead, long pContentLength, int pItems) {
//                long mBytes = pBytesRead / 1000000;
//                if (megaBytes == mBytes) {
//                    return;
//                }
//                megaBytes = mBytes;
//                System.out.println("We are currently reading item " + pItems);
//                if (pContentLength == -1) {
//                    System.out.println("So far, " + pBytesRead + " bytes have been read.");
//                } else {
//                    System.out.println("So far, " + pBytesRead + " of " + pContentLength
//                            + " bytes have been read.");
//                }
//            }
//        };
//        upload.setProgressListener(progressListener);

//        String attributeName = "ProgressListener";
//        HttpSession session = req.getSession();
//        session.setAttribute(attributeName, progressListener);

        try{
            BufferedInputStream bufferedInputStream;
            FileOutputStream fileOutputStream;
            BufferedOutputStream bufferedOutputStream;
            ServletOutputStream outputStream = resp.getOutputStream();

            outputStream.write("LOL".getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
            outputStream.close();


            List<FileItem> items = upload.parseRequest(req);
            Iterator<FileItem> iterator = items.iterator();

            String fieldName;
            String fileName;

            int count;
            byte[] buffer = new byte[8192];
            while (iterator.hasNext()){
                FileItem item = iterator.next();
                if (!item.isFormField()) {
                    System.out.println("abcdefg");
                    fieldName = item.getFieldName();
                    fileName = item.getName();
                    if(fileName != null){
                        fileName = FilenameUtils.getName(fileName);
                    }

//                    String contentType = item.getContentType();
//                    boolean isInMemory = item.isInMemory();
//                    long sizeInBytes = item.getSize();

//                    byte[] data = item.get();

//                    fileName = getServletContext().getRealPath("/uploadedFiles/" + fileName);
                    fileName = "C:\\Users\\trist\\Downloads\\uploadedFiles\\" + fileName;


                    bufferedInputStream = new BufferedInputStream(item.getInputStream());
                    fileOutputStream = new FileOutputStream(fileName);
                    bufferedOutputStream = new BufferedOutputStream(fileOutputStream);


                    long time = System.currentTimeMillis();
                    byte[] response = fileName.getBytes();
//                    byte[] attr = attributeName.getBytes(StandardCharsets.UTF_8);
//                    outputStream.write(response, 0, response.length);
//                    Thread.sleep(5000);
//                    outputStream.write(attr, 0, attr.length);
//                    Thread.sleep(5000);
                    byte[] t = Long.toString(time).getBytes(StandardCharsets.UTF_8);
//                    outputStream.write(t, 0, t.length);
//                    outputStream .flush();
//                    outputStream.close();
                    System.out.println("Written");
                    while ((count = bufferedInputStream.read(buffer)) != -1){
                        bufferedOutputStream.write(buffer, 0, count);
//                        outputStream.write(attributeName.getBytes(StandardCharsets.UTF_8));
                    }
                    bufferedOutputStream.close();
                    if (!item.isInMemory()){
                        item.delete();
                    }

                }
                else {
                    System.out.println("Well, Shit!");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
