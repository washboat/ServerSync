package com.mycompany.mywebapp.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import org.wisepersist.gwt.uploader.client.Uploader;
import org.wisepersist.gwt.uploader.client.events.*;
import org.wisepersist.gwt.uploader.client.progress.ProgressBar;

import java.util.LinkedHashMap;
import java.util.Map;

public class FileUploader{

    private final Uploader uploader = new Uploader();
    final Map<String, ProgressBar> progressBars = new LinkedHashMap<>();
    final Map<String, Image> cancelButtons = new LinkedHashMap<>();

    public FileUploader(final TransferTable table, String url ){
        uploader.setUploadURL(url);
        uploader.setWidth("150");
        uploader.setHeight("75");
        uploader.setButtonCursor(Uploader.Cursor.HAND);
        uploader.setButtonAction(Uploader.ButtonAction.SELECT_FILES);
        uploader.setStyleName("choose-button");
        uploader.setFileQueuedHandler(new FileQueuedHandler() {
            @Override
            public boolean onFileQueued(final FileQueuedEvent fileQueuedEvent) {
                final ProgressBar progressBar = new ProgressBar(0.0, 1.0, 0.0, new CancelProgressBarTextFormatter());
                progressBar.setTitle(fileQueuedEvent.getFile().getName());
                progressBar.setTitle("16px");
                progressBar.setWidth("25vw");
                progressBars.put(fileQueuedEvent.getFile().getId(), progressBar);

                final Image cancelButton = new Image();
                cancelButton.setStyleName("cancel-button");
                cancelButton.addClickHandler(new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent event) {
                        uploader.cancelUpload(fileQueuedEvent.getFile().getId(), false);
                        progressBars.get(fileQueuedEvent.getFile().getId()).setProgress(-1.0d);
                        cancelButton.removeFromParent();
                    }
                });
                cancelButtons.put(fileQueuedEvent.getFile().getId(), cancelButton);
                HorizontalPanel progressBarAndButtonPanel = new HorizontalPanel();
                progressBarAndButtonPanel.add(progressBar);
                progressBarAndButtonPanel.add(cancelButton);
                table.addRow(progressBarAndButtonPanel, fileQueuedEvent);

                return true;
            }
        });
        uploader.setUploadProgressHandler(new UploadProgressHandler() {
            @Override
            public boolean onUploadProgress(UploadProgressEvent uploadProgressEvent) {
                ProgressBar progressBar = progressBars.get(uploadProgressEvent.getFile().getId());
                progressBar.setProgress((double) uploadProgressEvent.getBytesComplete() / uploadProgressEvent.getBytesTotal());
                return true;
            }
        });
        uploader.setUploadCompleteHandler(new UploadCompleteHandler() {
            @Override
            public boolean onUploadComplete(UploadCompleteEvent uploadCompleteEvent) {
                cancelButtons.get(uploadCompleteEvent.getFile().getId()).removeFromParent();
                uploader.startUpload();
                return true;
            }
        });
        uploader.setFileDialogStartHandler(new FileDialogStartHandler() {
            @Override
            public boolean onFileDialogStartEvent(FileDialogStartEvent fileDialogStartEvent) {
                if (uploader.getStats().getUploadsInProgress() <= 0) {
                    // Clear the uploads that have completed, if none are in process
                    table.clear();
                    progressBars.clear();
                    cancelButtons.clear();
                }
                return true;
            }
        });
        uploader.setFileDialogCompleteHandler(new FileDialogCompleteHandler() {
            @Override
            public boolean onFileDialogComplete(FileDialogCompleteEvent fileDialogCompleteEvent) {
                if (uploader.getStats().getUploadsInProgress() <= 0)
                    uploader.startUpload();
                return true;
            }
        });
        uploader.setFileQueueErrorHandler(new FileQueueErrorHandler() {
            @Override
            public boolean onFileQueueError(FileQueueErrorEvent fileQueueErrorEvent) {
                Window.alert("File Queue Error due to file: " + fileQueueErrorEvent.getFile().getName() +
                        " for reason: [" + fileQueueErrorEvent.getErrorCode().toString() + "]");
                return true;
            }
        });
        uploader.setUploadErrorHandler(new UploadErrorHandler() {
            @Override
            public boolean onUploadError(UploadErrorEvent uploadErrorEvent) {
                Window.alert("Upload of file " + uploadErrorEvent.getFile().getName() + " failed due to [" +
                        uploadErrorEvent.getErrorCode().toString() + "]: " + uploadErrorEvent.getMessage());
                return true;
            }
        });
    }

    public Uploader getUploader() {
        return uploader;
    }
}

class CancelProgressBarTextFormatter extends ProgressBar.TextFormatter {
    @Override
    protected String getText(ProgressBar bar, double curProgress) {
        return curProgress < 0 ? "Cancelled" : NumberFormat.getFormat("0.0").format(100.00 * bar.getProgress()) + "%";
    }
}
