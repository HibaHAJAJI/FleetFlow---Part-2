package com.fleetflow.Controller;

import com.fleetflow.Dto.ClientDto;
import com.fleetflow.Service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }


    @GetMapping("/{id}")
    @Operation(summary = "Trouver un client par ID")
    public ResponseEntity<ClientDto> getClientById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.getClientById(id));
    }

    @GetMapping("/afficher")
    public List<ClientDto> afficherTousClients(){
        return clientService.getAllClient();
    }
    @PostMapping("/ajouter")
    public ClientDto ajouterClient(@RequestBody ClientDto dto){
        return clientService.addClient(dto);
    }
    @PutMapping("/modifier/{id}")
    public ClientDto modifierClient(@PathVariable Long id, @Valid  @RequestBody ClientDto dto){
        return clientService.updateClient(id,dto);
    }
    @DeleteMapping("/supprimer/{id}")
    public void supprimerClient(@PathVariable Long id){
        clientService.deleteClient(id);
    }
}
