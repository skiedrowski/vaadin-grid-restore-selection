package com.example.application.views.idstyle;

import java.util.Objects;

public class Client {

    private int id;

    private int version;
    private String client;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(final int version) {
        this.version = version;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Client client = (Client) o;
        return id == client.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    Client copy() {
        final Client copy = new Client();
        copy.setId(id);
        copy.setVersion(version);
        copy.setClient(client);
        return copy;
    }
}
