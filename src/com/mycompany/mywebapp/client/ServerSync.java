package com.mycompany.mywebapp.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ServerSync implements EntryPoint {
    /**
     * The message displayed to the user when the server cannot be reached or
     * returns an error.
     */
    private static final String SERVER_ERROR = "An error occurred while "
            + "attempting to contact the server. Please check your network "
            + "connection and try again.";

    /**
     * Create a remote service proxy to talk to the server-side Greeting service.
     */
    private final GreetingServiceAsync greetingService = (GreetingServiceAsync) GWT.create(GreetingService.class);
    private final ProgressServiceAsync progressService = (ProgressServiceAsync) GWT.create(ProgressService.class);

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {


        LayoutPanel mainUploadPanel = new LayoutPanel();
        LayoutPanel mainServerPanel = new LayoutPanel();
        LayoutPanel mainDownloadPanel = new LayoutPanel();
        final TabLayoutPanel tabPanel = new TabLayoutPanel(2.0, Style.Unit.EM);
        UploadPanel uploadPanel = new UploadPanel(5, GWT.getModuleBaseURL() + "UploadServlet");
        ServerPanel serverPanel = new ServerPanel();
        serverPanel.initialize();
        mainDownloadPanel.add(new HTML("This is the downloadPanel!"));
//        mainServerPanel.add(new HTML("This is the partnerPanel!"));
        mainServerPanel.add(serverPanel);

        LayoutPanel layoutPanel = new LayoutPanel();


        mainUploadPanel.add(tabPanel);
        mainUploadPanel.setWidgetLeftWidth(tabPanel, 0, Style.Unit.PCT, 0, Style.Unit.PCT);
        mainUploadPanel.setWidgetBottomHeight(tabPanel, 0, Style.Unit.PCT, 0, Style.Unit.PCT);
        mainUploadPanel.forceLayout();
        mainUploadPanel.setWidgetLeftWidth(tabPanel, 0, Style.Unit.PCT, 100, Style.Unit.PCT);
        mainUploadPanel.setWidgetBottomHeight(tabPanel, 0, Style.Unit.PCT, 100, Style.Unit.PCT);
        mainUploadPanel.animate(750);


//        uploadPanel.add(new Button("Submit", new ClickHandler() {
//
//            @Override
//            public void onClick(ClickEvent event) {
////                GWT.log(fileUpload.getFilename());
////                formPanel.submit();
//                String fileInfo = "Mortal Kombat 11 (3).mp4";
//                String url = GWT.getModuleBaseURL() + "DownloadServlet?fileInfo=" + fileInfo;
////                Window.open(url, "_blank", "status=0,toolbar=0,menubar=0,location=0");
//                Window.open(url, "_blank", "");
////                Window.Location.replace(url);
//
//            }
//        }));


        tabPanel.add(uploadPanel, "send");
        tabPanel.add(new HTML("Receive"), "receive");
        tabPanel.add(new HTML("Peers"), "peers");
        tabPanel.selectTab(0);

        // We can add style names to widgets


        // Add the nameField and sendButton to the RootPanel
        // Use RootPanel.get() to get the entire body element

        RootLayoutPanel root = RootLayoutPanel.get();
        layoutPanel.add(mainUploadPanel);
        layoutPanel.setWidgetRightWidth(mainUploadPanel, 0, Style.Unit.PCT, 50, Style.Unit.PCT);
        layoutPanel.setWidgetTopHeight(mainUploadPanel, 0, Style.Unit.PCT, 50, Style.Unit.PCT);
        layoutPanel.add(mainDownloadPanel);
        layoutPanel.setWidgetRightWidth(mainDownloadPanel, 0, Style.Unit.PCT, 50, Style.Unit.PCT);
        layoutPanel.setWidgetBottomHeight(mainDownloadPanel, 0, Style.Unit.PCT, 50, Style.Unit.PCT);
        layoutPanel.add(mainServerPanel);
        layoutPanel.setWidgetLeftWidth(mainServerPanel, 0, Style.Unit.PCT, 50, Style.Unit.PCT);
//        layoutPanel.setWidg

//        splitLayoutPanel.addNorth(mainUploadPanel, 1200);
        root.add(layoutPanel);


//        downloadButton.addClickHandler(new ClickHandler() {
//            public void onClick(ClickEvent event) {
//                uploadTable.addRow("Title", 1021);
//                TransferRow row = (TransferRow) uploadTable.getWidget(1, 0);
//                row.getProgressBar().update();

//                ServiceDefTarget greet = (ServiceDefTarget) greetingService;
//                greet.setServiceEntryPoint(GWT.getModuleBaseURL() + "GreetingService");
//                GWT.log(GWT.getModuleName() + "GreetingService");
//                greetingService.greetServer("hello", new AsyncCallback<String>() {
//                    public void onFailure(Throwable caught) {
//                        // Show the RPC error message to the user
//                        dialogBox.setText("Remote Procedure Call - Failure");
//                        serverResponseLabel.addStyleName("serverResponseLabelError");
//                        serverResponseLabel.setHTML(SERVER_ERROR);
//                        dialogBox.center();
//                        closeButton.setFocus(true);
//                        GWT.log("ERROR");
//                    }
//
//                    public void onSuccess(String result) {
//                        dialogBox.setText("Remote Procedure Call");
//                        serverResponseLabel.removeStyleName("serverResponseLabelError");
//                        serverResponseLabel.setHTML(result);
//                        dialogBox.center();
//                        closeButton.setFocus(true);
//                        GWT.log("SUCCESS!");
//                    }
//                });

//                DownloadServlet downloadServlet = (DownloadServlet) GWT.create(DownloadServlet)
//                String fileInfo = "design.png";
//                String url = GWT.getModuleBaseURL() + "DownloadServlet?fileInfo=" + fileInfo;
//                Window.open(url, "_blank", "status=0,toolbar=0,menubar=0,location=0");
//        progressBar.update();
//            }
//        });

    }
}






