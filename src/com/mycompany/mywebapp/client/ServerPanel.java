package com.mycompany.mywebapp.client;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ServerPanel extends HorizontalPanel {
    interface Dashboard extends UiBinder<HTMLPanel, ServerPanel>{}
    private static final Dashboard dashboardBinder = GWT.create(Dashboard.class);

    @UiField
    VerticalPanel connectionInfo;
    @UiField
    HorizontalPanel ipPanel;
    @UiField
    TextBox ipField;
    @UiField
    Label ipLabel;
    @UiField
    HorizontalPanel aliasPanel;
    @UiField
    Label aliasLabel;
    @UiField
    TextBox aliasField;
    @UiField
    Button addButton;
    @UiField
    Button updateButton;
    @UiField
    Button removeButton;

    @UiHandler("addButton")
    void handleClick(ClickEvent e){
        Server newValue = new Server(ipField.getValue());
        newValue.setAlias(aliasField.getValue());
        List<Server> list = dataProvider.getList();
        list.add(newValue);
    }

    private HTMLPanel root;
    public ServerPanel(){
        root = dashboardBinder.createAndBindUi(this);
    }

    VerticalPanel possibleConnections;
//    VerticalPanel connectionInfo;
    DisclosurePanel recentConnections;
    DisclosurePanel offlineConnections;
    CellList<Server> cellList;
    ServerCell serverCell;
    ListDataProvider<Server> dataProvider;

    Map<String, Server> serverMap = new LinkedHashMap<>();


    public void initialize(){
        DecoratorPanel leftPanel = new DecoratorPanel();
        DecoratorPanel rightPanel = new DecoratorPanel();
        serverCell = new ServerCell();
        cellList = new CellList<Server>(serverCell);
        dataProvider = new ListDataProvider<>();

        recentConnections = new DisclosurePanel("Recent Connections");
        offlineConnections = new DisclosurePanel("Offline");

        possibleConnections = new VerticalPanel();

//        connectionInfo = new VerticalPanel();

        leftPanel.add(possibleConnections);
        rightPanel.add(connectionInfo);

        dataProvider.addDataDisplay(cellList);
        fillDisclosure(dataProvider);

        final SingleSelectionModel<Server> selectionModel = new SingleSelectionModel<>();
        cellList.setSelectionModel(selectionModel);
        selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
            @Override
            public void onSelectionChange(SelectionChangeEvent event) {
                Server server = selectionModel.getSelectedObject();
                ipField.setText(server.getIp());
                aliasField.setText(server.getAlias());
            }
        });


        recentConnections.add(cellList);
        possibleConnections.add(recentConnections);
        possibleConnections.add(offlineConnections);

//        ipPanel.add(ipLabel);
//        ipPanel.add(ipField);

//        connectionInfo.add(aliasPanel);
//        connectionInfo.add(ipPanel);
//        connectionInfo.add(addButton);
        this.add(leftPanel);
        this.add(rightPanel);
        this.setStyleName("deca-left");
    }

    public void fillDisclosure(ListDataProvider<Server> listDataProvider){
        Server server1 = new Server("192.168.1.76");
        server1.setAlias("GamingPC");
        server1.setStatus("Online");

        Server server2 = new Server("192.168.1.84");
        server2.setAlias("TCGLaptop");
        server2.setStatus("Offline");

        Server server3 = new Server("192.168.1.12");
        server3.setAlias("Phone");
        server3.setStatus("Online");

        serverMap.put(server1.getIp(), server1);
        serverMap.put(server2.getIp(), server2);
        serverMap.put(server3.getIp(), server3);

        listDataProvider.getList().add(server1);
        listDataProvider.getList().add(server2);
        listDataProvider.getList().add(server3);


//        listDataProvider.getList().add(new Server("192.168.1.76"));
//        listDataProvider.getList().add(new Server("192.168.1.84"));
//        listDataProvider.getList().add(new Server("192.168.1.12"));

    }






}
class ServerCell extends AbstractCell<Server> {

    @Override
    public void render(Context context, Server value, SafeHtmlBuilder sb) {
        if (value == null)
            return;
        sb.appendHtmlConstant("<table>");
        sb.appendHtmlConstant("<tr><td rowspan='3'>");
        sb.appendHtmlConstant("<td style='font-size:95%;'>");
        sb.appendEscaped(value.getAlias() + " (" +  value.getIp() + ")");
        sb.appendHtmlConstant("</td></tr></table>");
//        ListDataProvider<Server> provider = new ListDataProvider();
//        provider.addDataDisplay();
    }
}
class Server {
    private String ip;
    private Double diskSpace;
    private String status;
    private String alias;
    private String description;

    public Server(String ip) {
        this.setIp(ip);
    }

    public void setIp(String ip) {
         this.ip = ip;
    }
    public void setDiskSpace(Double diskSpace) {
         this.diskSpace = diskSpace;
    }
    public void setStatus(String status) {
         this.status = status;
    }
    public String getIp() {
        return ip;
    }
    public Double getDiskSpace() {
        return diskSpace;
    }
    public String getStatus() {
        return status;
    }
    public String getAlias() {
        return alias;
    }
    public void setAlias(String alias) {
         this.alias = alias;
     }
    public String getDescription() {
         return description;
     }
    public void setDescription(String description) {
         this.description = description;
     }
 }
