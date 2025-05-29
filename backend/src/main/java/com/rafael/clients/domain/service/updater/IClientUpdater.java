package com.rafael.clients.domain.service.updater;

import com.rafael.clients.domain.model.Client;

public interface IClientUpdater {
    void update(Client client, Client source);
}
