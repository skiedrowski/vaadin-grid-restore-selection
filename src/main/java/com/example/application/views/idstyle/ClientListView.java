package com.example.application.views.idstyle;

import java.util.UUID;
import java.util.stream.Collectors;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.gridpro.GridPro;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("idstyle")
@Route(value = "idstyle", layout = MainLayout.class)
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
        grid.addColumn("id").setHeader("Id");
        grid.addColumn("version").setHeader("Version");
        grid.addColumn("client").setHeader("Client");
        grid.setItems(q -> clientService.fetchClients(q.getOffset(), q.getLimit()));

        final Button btnUpdateSelectedItems = new Button("Update selected");
        btnUpdateSelectedItems.addClickListener(e -> {
            final var storedSelection = grid.getSelectedItems();

            grid.getSelectedItems().forEach(c -> {
                clientService.updateClient(c.getId(), UUID.randomUUID().toString());
            });

            grid.getDataProvider().refreshAll();
            if (!storedSelection.isEmpty()) grid.asMultiSelect().select(storedSelection);

            Notification.show("Selection: " + grid.getSelectedItems().stream().map(i -> "" + i.getId() + " (" + i.getVersion() + "): " + i.getClient()).collect(Collectors.joining("\n")));
        });

        add(btnUpdateSelectedItems, grid);
    }
}
