package com.example.application.views.idstyle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.vaadin.flow.component.gridpro.GridPro;
import com.vaadin.flow.component.notification.Notification;

public class ClientService {
    private List<Client> clientList;
    //TODO nasty hack for simplicity - service should not know grid
    private GridPro<Client> grid;

    public void updateClient(final int id, final String client) {
        final Client clientI = clientList.stream().filter(c -> c.getId() == id).findFirst().get();
        clientI.setClient(client);
        clientI.setVersion(clientI.getVersion() + 1);
    }

    public Stream<Client> fetchClients(final int offset, final int limit) {
        final List<Client> clients = getClients();
        replaceSelectedWithUpdated(clients);
        return clients.stream();
    }

    /**
     * if a grid selected item is in fetchedItems (by equals/hashCode) then replace it with the newly fetched version
     */
    private void replaceSelectedWithUpdated(final List<Client> fetchedItems) {
        final var selectedItems = grid.getSelectedItems();
        final var newSelection = new HashSet<Client>();
        selectedItems.forEach(selected -> {
            final var potentiallyUpdatedSelectedItem = fetchedItems.stream().filter(fetched -> fetched.equals(selected)).findFirst().orElse(selected);
            newSelection.add(potentiallyUpdatedSelectedItem);
        });
        grid.asMultiSelect().select(newSelection);
        Notification.show("Selection: " + grid.getSelectedItems().stream().map(i -> "" + i.getId() + " (" + i.getVersion() + "): " + i.getClient()).collect(Collectors.joining("\n")));
    }


    private List<Client> getClients() {
        if (clientList == null) {
            clientList = new ArrayList<>();
            clientList.add(createClient(4957, "A"));
            clientList.add(createClient(6750, "A"));
            clientList.add(createClient(6816, "A"));
            clientList.add(createClient(5144, "A"));
            clientList.add(createClient(9800, "A"));
            clientList.add(createClient(3599, "A"));
            clientList.add(createClient(3989, "A"));
            clientList.add(createClient(1077, "A"));
            clientList.add(createClient(8942, "A"));
        }
        return clientList.stream().map(Client::copy).collect(Collectors.toList());
    }

    private Client createClient(final int id, final String client) {
        final Client c = new Client();
        c.setId(id);
        c.setClient(client);
        return c;
    }

    public void setGrid(final GridPro<Client> grid) {
        this.grid = grid;
    }
}
