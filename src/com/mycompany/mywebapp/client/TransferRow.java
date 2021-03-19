package com.mycompany.mywebapp.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;


public class TransferRow extends VerticalPanel{
    private String title;
    private ProgressBar progressBar;
    private long size;
    private String readableSize;
    public static final MyTemplates template = GWT.create(MyTemplates.class);
    public TransferRow(String title, long size, boolean isEven){
        this.title = title;
        this.size = size;
        this.progressBar = new ProgressBar();

        String styleName = isEven ? "vertical-panel-even" : "vertical-panel-odd";
        this.setStyleName(styleName);
        this.add(new HTML(title));
        this.add(new HTML(template.status(size, this.getStyleName()).asString()));
        this.add(progressBar);

        this.setSpacing(0);
//        this.add(new HTML("<small>0.0 B of " + size + " - Remaining time unknown</small>"));
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

}
