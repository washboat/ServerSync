package com.mycompany.mywebapp.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Toolbar extends HorizontalPanel {
    public Toolbar(Button... buttons){
        for (Button button : buttons) {
            this.add(button);
        }

    }
}
