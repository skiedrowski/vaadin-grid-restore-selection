package com.example.application.views.defaulteq;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ClientService {
    private int refreshCount;
    public Stream<Client> fetchClients(final int offset, final int limit) {
        return getClients().stream();
    }

    private List<Client> getClients() {
        refreshCount++;
        return Arrays.asList(
                createClient(4957, "Amarachi Nkechi"),
                createClient(675, "Bonelwa Ngqawana"),
                createClient(6816, "Debashis Bhuiyan"),
                createClient(5144, "Jacqueline Asong"),
                createClient(9800, "Kobus van de Vegte"),
                createClient(3599, "Mattie Blooman"),
                createClient(3989, "Oea Romana"),
                createClient(1077, "Stephanus Huggins"),
                createClient(8942, "Torsten Paulsson"));
    }

    private Client createClient(final int id, final String client) {
        final Client c = new Client();
        c.setId(id);
        c.setClient(refreshCount + " - " + client);
        return c;
    }
}
