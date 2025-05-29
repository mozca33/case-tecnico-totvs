package com.rafael.clients.infrastructure.seeder;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import com.rafael.clients.domain.model.Address;
import com.rafael.clients.domain.model.Client;
import com.rafael.clients.domain.model.Phone;
import com.rafael.clients.domain.repository.ClientRepository;

@Configuration
public class DataSeeder implements CommandLineRunner {

    private final ClientRepository clientRepository;

    public DataSeeder(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (clientRepository.count() == 0) {
            UUID id1 = UUID.fromString("11111111-1111-1111-1111-111111111111");
            UUID id2 = UUID.fromString("22222222-2222-2222-2222-222222222222");

            Client client1 = new Client();
            client1.setId(id1);
            client1.setName("Cliente Teste 01");
            client1.setCpf("12345678909");

            Client client2 = new Client();
            client2.setId(id2);
            client2.setName("Cliente Teste 02");
            client2.setCpf("09876543210");

            Phone phone1 = new Phone();
            phone1.setPhoneNumber("11999998888");
            phone1.setClient(client1);

            Phone phone2 = new Phone();
            phone2.setPhoneNumber("+5511991237899");
            phone2.setClient(client1);

            Phone phone3 = new Phone();
            phone3.setPhoneNumber("32998627891");
            phone3.setClient(client1);

            Set<Phone> phonesClient1 = new HashSet<>();
            phonesClient1.add(phone1);
            phonesClient1.add(phone2);
            phonesClient1.add(phone3);

            Phone phone4 = new Phone();
            phone4.setPhoneNumber("+5561932523123");
            phone4.setClient(client2);

            Phone phone5 = new Phone();
            phone5.setPhoneNumber("+5562991823712");
            phone5.setClient(client2);

            Set<Phone> phonesClient2 = new HashSet<>();
            phonesClient2.add(phone4);
            phonesClient2.add(phone5);

            Address address1 = new Address();
            address1.setPublicPlace("Residencial");
            address1.setCity("São Paulo");
            address1.setState("SP");
            address1.setStreet("Rua 21");
            address1.setZipCode("01234-567");
            address1.setClient(client1);

            Address address2 = new Address();
            address2.setPublicPlace("Condomínio");
            address2.setCity("São Paulo");
            address2.setState("SP");
            address2.setStreet("Avenida Treze");
            address2.setZipCode("76543-210");
            address2.setClient(client1);

            Set<Address> addressesClient1 = new HashSet<>();
            addressesClient1.add(address1);
            addressesClient1.add(address2);

            Address address3 = new Address();
            address3.setPublicPlace("Residencial");
            address3.setCity("São Paulo");
            address3.setState("SP");
            address3.setStreet("BR-060");
            address3.setZipCode("01234-567");
            address3.setClient(client2);

            Set<Address> addressesClient2 = new HashSet<>();
            addressesClient2.add(address3);

            client1.setPhoneNumbers(phonesClient1);
            client1.setAddresses(addressesClient1);

            client2.setPhoneNumbers(phonesClient2);
            client2.setAddresses(addressesClient2);

            clientRepository.save(client1);
            clientRepository.save(client2);

            System.out.println("✅ Seed criada com sucesso: ID fixo = " + id1);
            System.out.println("✅ Seed criada com sucesso: ID fixo = " + id2);
        }
    }
}
