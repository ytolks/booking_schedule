package com.jt.bookingschedule.controller;

import com.jt.bookingschedule.model.Client;
import com.jt.bookingschedule.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "management/api/client")
public class ClientController {

    private ClientRepository repository;

    @Autowired
    public ClientController(ClientRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    public List<Client> getClients() {
        return repository.findAll();
    }

    @GetMapping(path = "/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    public Optional<Client> findClientById(@PathVariable Long id) {
        return repository.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('client:write')")
    public void saveClient(@RequestBody Client client) {
        repository.save(client);
    }


    @PutMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('client:write')")
    public void updateClientData(@RequestBody Client newClient,
                                 @PathVariable Long id) {

        Optional<Client> optionalClient = repository.findById(id);

        optionalClient.stream()
                .filter(client1 -> client1.getId().equals(id))
                .findFirst()
                .map(client1 -> {
                    client1.setClientEmail(newClient.getClientEmail());
                    client1.setClientPhoneNumber(newClient.getClientPhoneNumber());
                    return repository.save(newClient);
                })
                .orElseGet(() -> {
                            newClient.setId(id);
                            return repository.save(newClient);
                        }
                );

    }

    @DeleteMapping(path ="/{id}")
    @PreAuthorize("hasAuthority('client:write')")
    public void deleteClient(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
