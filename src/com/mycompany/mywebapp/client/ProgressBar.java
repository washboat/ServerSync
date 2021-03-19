package com.mycompany.mywebapp.client;

import com.google.gwt.dom.client.Element;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;


public class ProgressBar extends Widget {
    private static final String PERCENT_PATTERN = "#,##0%";
    private static final NumberFormat percentFormat = NumberFormat.getFormat(PERCENT_PATTERN);

    private final Element progress;
    private final Element percentageLabel;
    private double percentage;
    private final double max;

    public ProgressBar(){
        this(45.0, 100.0);
    }
    public ProgressBar(double value, double max) {
        assert max != 0;
        this.max = max;

        progress = DOM.createElement("progress");
        progress.setAttribute("max", Double.toString(max));
        progress.setAttribute("value", Double.toString(value));

        percentageLabel = DOM.createElement("span");
        percentage = value / max;
        percentageLabel.setInnerHTML(percentFormat.format(percentage));
        progress.insertFirst(percentageLabel);
        progress.setAttribute("class", "download");


        setElement(progress);
    }

    public void setProgress(double value) {
        progress.setAttribute("value", Double.toString(value));
        percentage = value / max;
        percentageLabel.setInnerHTML(percentFormat.format(percentage));
    }

    public void update(){
        String value = progress.getAttribute("value");
        progress.setAttribute("value", String.valueOf(Double.parseDouble(value) + 5.0));
    }
    public void update(double value){
        double current = Double.parseDouble(progress.getAttribute("value"));
        progress.setAttribute("value", String.valueOf(value));
    }

}
