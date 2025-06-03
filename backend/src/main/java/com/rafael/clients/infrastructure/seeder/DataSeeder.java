package com.rafael.clients.infrastructure.seeder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import com.rafael.clients.domain.model.Address;
import com.rafael.clients.domain.model.Client;
import com.rafael.clients.domain.model.Phone;
import com.rafael.clients.domain.repository.ClientRepository;
import com.rafael.clients.domain.service.NormalizerService;

@Configuration
public class DataSeeder implements CommandLineRunner {

    private final ClientRepository clientRepository;
    private final NormalizerService normalizerService;

    public DataSeeder(ClientRepository clientRepository, NormalizerService normalizerService) {
        this.clientRepository = clientRepository;
        this.normalizerService = normalizerService;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (clientRepository.count() == 0) {
            // Cliente 1
            UUID id1 = UUID.fromString("11111111-1111-1111-1111-111111111111");
            Client client1 = createClient1(id1);
            
            // Cliente 2
            UUID id2 = UUID.fromString("22222222-2222-2222-2222-222222222222");
            Client client2 = createClient2(id2);
            
            // Cliente 3
            UUID id3 = UUID.fromString("33333333-3333-3333-3333-333333333333");
            Client client3 = createClient3(id3);
            
            // Cliente 4
            UUID id4 = UUID.fromString("44444444-4444-4444-4444-444444444444");
            Client client4 = createClient4(id4);
            
            // Cliente 5
            UUID id5 = UUID.fromString("55555555-5555-5555-5555-555555555555");
            Client client5 = createClient5(id5);
            
            // Cliente 6
            UUID id6 = UUID.fromString("66666666-6666-6666-6666-666666666666");
            Client client6 = createClient6(id6);
            
            // Cliente 7
            UUID id7 = UUID.fromString("77777777-7777-7777-7777-777777777777");
            Client client7 = createClient7(id7);

            // Salvar todos os clientes
            clientRepository.save(client1);
            clientRepository.save(client2);
            clientRepository.save(client3);
            clientRepository.save(client4);
            clientRepository.save(client5);
            clientRepository.save(client6);
            clientRepository.save(client7);

            System.out.println("✅ Seeds criadas com sucesso:");
            System.out.println("   - Cliente 1: " + id1);
            System.out.println("   - Cliente 2: " + id2);
            System.out.println("   - Cliente 3: " + id3);
            System.out.println("   - Cliente 4: " + id4);
            System.out.println("   - Cliente 5: " + id5);
            System.out.println("   - Cliente 6: " + id6);
            System.out.println("   - Cliente 7: " + id7);
        }
    }

    private Client createClient1(UUID id) {
        Client client = new Client();
        client.setId(id);
        client.setName("Cliente Teste 01");
        client.setCpf("96930962074");

        // Phones
        List<Phone> phones = new ArrayList<>();
        phones.add(createPhone("11999998888", client));
        phones.add(createPhone("+5511991237899", client));
        phones.add(createPhone("32998627891", client));

        // Adresses
        List<Address> addresses = new ArrayList<>();
        addresses.add(createAddress("São Paulo", "SP", "Rua 21","Qd 123 Lt 823", "92030-560", "232Q", "Residencial", client));
        addresses.add(createAddress("São Paulo", "SP", "Avenida Treze","Qd 12, lt 92", "85040-370", "213", "Condomínio", client));

        client.setPhoneNumbers(phones);
        client.setAddresses(addresses);
        return client;
    }

    private Client createClient2(UUID id) {
        Client client = new Client();
        client.setId(id);
        client.setName("Cliente Teste 02");
        client.setCpf("81630414042");

        // Phones
        List<Phone> phones = new ArrayList<>();
        phones.add(createPhone("+5561932523123", client));
        phones.add(createPhone("+5562991823712", client));

        // Adresses
        List<Address> addresses = new ArrayList<>();
        addresses.add(createAddress("São Paulo", "SP", "BR-060","Qd xxx Lt 222", "64604-350","325Lp", "Residencial", client));

        client.setPhoneNumbers(phones);
        client.setAddresses(addresses);
        return client;
    }

    private Client createClient3(UUID id) {
        Client client = new Client();
        client.setId(id);
        client.setName("Maria Silva Santos");
        client.setCpf("31865683078");

        // Phones
        List<Phone> phones = new ArrayList<>();
        phones.add(createPhone("21987654321", client));
        phones.add(createPhone("+5521999887766", client));

        // Adresses
        List<Address> addresses = new ArrayList<>();
        addresses.add(createAddress("Rio de Janeiro", "RJ", "Rua das Flores", "123", "41200-805", "2A3", "Apartamento", client));
        addresses.add(createAddress("Rio de Janeiro", "RJ", "Avenida Atlântica", "456", "77807-075", "3213Q", "Comercial", client));

        client.setPhoneNumbers(phones);
        client.setAddresses(addresses);
        return client;
    }

    private Client createClient4(UUID id) {
        Client client = new Client();
        client.setId(id);
        client.setName("João Pedro Oliveira");
        client.setCpf("46331704060");

        // Phones
        List<Phone> phones = new ArrayList<>();
        phones.add(createPhone("31988776655", client));
        phones.add(createPhone("+5531987123456", client));
        phones.add(createPhone("31999887766", client));

        // Adresses
        List<Address> addresses = new ArrayList<>();
        addresses.add(createAddress("Belo Horizonte", "MG", "Rua dos Inconfidentes", "789", "76811-376", "421", "Casa", client));

        client.setPhoneNumbers(phones);
        client.setAddresses(addresses);
        return client;
    }

    private Client createClient5(UUID id) {
        Client client = new Client();
        client.setId(id);
        client.setName("Ana Carolina Ferreira");
        client.setCpf("44515454048");

        // Phones
        List<Phone> phones = new ArrayList<>();
        phones.add(createPhone("85987654321", client));

        // Adresses
        List<Address> addresses = new ArrayList<>();
        addresses.add(createAddress("Fortaleza", "CE", "Avenida Beira Mar", "apt 1001", "29124-300", "200C", "Residencial", client));
        addresses.add(createAddress("Fortaleza", "CE", "Rua Major Facundo","apt 500", "69099-170", "200C", "Trabalho", client));
        addresses.add(createAddress("Fortaleza", "CE", "Praia de ","123", "58055-113", "200C", "Temporário", client));

        client.setPhoneNumbers(phones);
        client.setAddresses(addresses);
        return client;
    }

    private Client createClient6(UUID id) {
        Client client = new Client();
        client.setId(id);
        client.setName("Carlos Eduardo Lima");
        client.setCpf("28962117053");

        // Phones
        List<Phone> phones = new ArrayList<>();
        phones.add(createPhone("47999123456", client));
        phones.add(createPhone("+5547988887777", client));

        // Adresses
        List<Address> addresses = new ArrayList<>();
        addresses.add(createAddress("Blumenau", "SC", "Rua XV de Novembro", "Qd 125 Lt 2a","72327-500", null, null, client));

        client.setPhoneNumbers(phones);
        client.setAddresses(addresses);
        return client;
    }

    private Client createClient7(UUID id) {
        Client client = new Client();
        client.setId(id);
        client.setName("Fernanda Costa Almeida");
        client.setCpf("24300402043");

        // Phones
        List<Phone> phones = new ArrayList<>();
        phones.add(createPhone("51987654321", client));
        phones.add(createPhone("51999888777", client));
        phones.add(createPhone("+5551988776655", client));
        phones.add(createPhone("51987123456", client));

        // Adresses
        List<Address> addresses = new ArrayList<>();
        addresses.add(createAddress("Porto Alegre", "RS", "Rua dos Andradas", "1500", "65037-170", "2500", "Apartamento", client));
        addresses.add(createAddress("Gramado", "RS", "Rua Coberta", "1500", "59069-050", "255", "Casa de Veraneio", client));

        client.setPhoneNumbers(phones);
        client.setAddresses(addresses);
        return client;
    }

    private Phone createPhone(String phoneNumber, Client client) {
        Phone phone = new Phone(phoneNumber);

        phone = normalizerService.normalize(phone);
        phone.setClient(client);
        return phone;
    }

    private Address createAddress(String city, String state, String street, String complement, String zipCode, String number, String publicPlace, Client client) {
        Address address = new Address();
        address.setCity(city);
        address.setState(state);
        address.setStreet(street);
        address.setComplement(complement);
        address.setZipCode(zipCode);
        address.setNumber(number);
        address.setPublicPlace(publicPlace);
        address.setClient(client);
        return address;
    }
}