package com.fleetflow;

import com.fleetflow.Dto.ClientDto;
import com.fleetflow.Entity.Client;
import com.fleetflow.Mapper.ClientMapper;
import com.fleetflow.Repository.ClientRepository;
import com.fleetflow.Service.ClientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;
    @Mock
    private ClientMapper clientMapper;


    @Test
    public void saveClient(){

       ClientDto dto =new ClientDto(2L,"sara","sara@gamil.com","CASA","23456789");
       Client client=new Client("sara","sara@email.com","casa","09987755");
       ClientDto saveDto =new ClientDto(2L,"sara","sara@email.com","casa","09987755");

       when(clientMapper.toEntity(dto)).thenReturn(client);
       when(clientRepository.save(client)).thenReturn(client);
       when(clientMapper.toDto(client)).thenReturn(saveDto);
       ClientDto result= clientService.addClient(dto);
       assertNotNull(result);

   }

   @Test
    public void should_saveClientEmailDejaExist(){
       ClientDto dto =new ClientDto(2L,"sara","sara@gamil.com","CASA","23456789");
     when(clientRepository.findByEmail(dto.getEmail())).thenReturn(true);
     assertThrows(RuntimeException.class,()->clientService.addClient(dto));
   }
}

