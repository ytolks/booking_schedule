package com.jt.bookingschedule.controller;

import com.jt.bookingschedule.model.Client;
import com.jt.bookingschedule.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/client")
public class ClientController {

    private ClientRepository repository;

    @Autowired
    public ClientController(ClientRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Client> getClients() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Client> findClientById(@PathVariable Long id) {
        return repository.findById(id);
    }

    @PostMapping
    public void saveClient(@RequestBody Client client) {
        repository.save(client);
    }


    @PutMapping("/{id}")
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

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
