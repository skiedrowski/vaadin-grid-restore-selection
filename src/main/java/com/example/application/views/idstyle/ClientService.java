package com.example.application.views.idstyle;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClientService {
    private List<Client> clientList;

    public void updateClient(final int id, final String client) {
        final Client clientI = clientList.stream().filter(c -> c.getId() == id).findFirst().get();
        clientI.setClient(client);
        clientI.setVersion(clientI.getVersion() + 1);
    }

    public Stream<Client> fetchClients(final int offset, final int limit) {
        return getClients().stream();
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
}
