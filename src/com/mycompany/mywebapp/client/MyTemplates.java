package com.mycompany.mywebapp.client;

import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;


public interface MyTemplates extends SafeHtmlTemplates {
    @Template("<small>{0} of {1} - Remaining time unknown</small>")
        SafeHtml status(long progress, String unit);

}
