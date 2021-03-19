package com.mycompany.mywebapp.client;

import com.google.gwt.user.client.ui.*;
import org.wisepersist.gwt.uploader.client.events.FileQueuedEvent;


public class TransferTable extends FlexTable {
    public void addRow(String title, long size){
        int rowCount = this.getRowCount();
        boolean isEven = rowCount % 2 == 0;
        TransferRow verticalPanel = new TransferRow(title, size, isEven);
        this.setWidget(rowCount, 0, verticalPanel);
        this.getFlexCellFormatter().setColSpan(rowCount, 0, 3);
    }
    public void addRow(HorizontalPanel panel, FileQueuedEvent event){
        DecoratorPanel decoratorPanel = new DecoratorPanel();
        Label title = new Label(event.getFile().getName());
        VerticalPanel verticalPanel = new VerticalPanel();
        int rowCount = this.getRowCount();

        title.setStyleName("transfer-text");
        verticalPanel.setStyleName(rowCount % 2 == 0 ? "vertical-panel-even" : "vertical-panel-odd");
        verticalPanel.add(title);
        verticalPanel.add(panel);

        decoratorPanel.add(verticalPanel);

        this.setWidget(rowCount, 0, decoratorPanel);
        this.getFlexCellFormatter().setColSpan(rowCount, 0, 3);

    }
}
