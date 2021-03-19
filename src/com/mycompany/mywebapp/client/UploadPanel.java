package com.mycompany.mywebapp.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;

import java.util.Arrays;

public class UploadPanel extends VerticalPanel {
    Button downloadButton;
    Button openButton;
    Button resumeButton;
    Button pauseButton;
    Button cancelButton;
    Button propertiesButton;
    Toolbar toolbar = new Toolbar();
    TransferTable transferTable;
    FileUploader fileUploader;

    public UploadPanel(int spacing, String url){
        transferTable = new TransferTable();
        transferTable.getFlexCellFormatter().setColSpan(1, 0, 3);
        fileUploader = new FileUploader(transferTable, url);

        init();
        this.setSpacing(spacing);
        this.add(toolbar);
        this.add(fileUploader.getUploader());
        this.add(transferTable);


    }

    public void init(){
        downloadButton = new Button();
        openButton = new Button("open");
        resumeButton = new Button("resume");
        pauseButton = new Button("pause");
        cancelButton = new Button("cancel");
        propertiesButton = new Button("properties");
        for (Button button : Arrays.asList(downloadButton, openButton, resumeButton, pauseButton, cancelButton, propertiesButton))
            toolbar.add(button);
        toolbar.setSpacing(5);
        downloadButton.setStyleName("download-button");





    }


}
