package com.example.application.views.defaulteq;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.gridpro.GridPro;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@PageTitle("Defaulteq")
@Route(value = "defaulteq", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class ClientListView extends Div {

    private final GridPro<Client> grid;

    private final ClientService clientService = new ClientService();

    public ClientListView() {
        addClassName("list-view");
        setSizeFull();

        grid = new GridPro<>();
        grid.configureBeanType(Client.class, false);
        grid.setSelectionMode(SelectionMode.MULTI);
        grid.setHeight("100%");
        grid.addColumn("client").setHeader("Client");
        grid.setItems(q -> clientService.fetchClients(q.getOffset(), q.getLimit()));

        final Button btnRefresh = new Button("Refresh");
        btnRefresh.addClickListener(e -> {
            final var storedSelection = grid.getSelectedItems();
            grid.getDataProvider().refreshAll();              //(1)
            if (!storedSelection.isEmpty()) {
                grid.asMultiSelect().select(storedSelection); //(2)
            }
        });
        add(btnRefresh, grid);
    }
}
