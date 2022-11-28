package com.serveeer.servermanager.service;

import com.serveeer.servermanager.enumeration.Status;
import com.serveeer.servermanager.model.Server;
import com.serveeer.servermanager.repository.ServerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Random;

import static java.lang.Boolean.TRUE;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j

public class ServerServiceImplementation implements ServerService{
    private final ServerRepository serverRepository;
    @Override
    public Server create(Server server) {
        log.info("Saving new server : {}", server.getName());
        server.setImageUrl(setServerImage());
        return serverRepository.save(server);
    }

    private String setServerImage() {
        String[] imagesnames = {"server1.png", "server2.png", "server3.png"};
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/server/image/" + imagesnames[new Random().nextInt(1)]).toUriString();
    }

    @Override
    public Server ping(String ipAddress) throws IOException {
        log.info("pinging a server : {}", ipAddress);
        log.info("ping Amina : {}", ipAddress);
        log.info("ping Hind : {}", ipAddress);
        Server server = serverRepository.findByIpAddress(ipAddress);
        InetAddress address =InetAddress.getByName(ipAddress);
        server.setStatus(address.isReachable(10000) ? Status.SERVER_UP : Status.SERVER_DOWN);
        serverRepository.save(server);

        return server;
    }

    @Override
    public Collection<Server> list(int limit) {
        log.info("feetching all");
        return serverRepository.findAll(PageRequest.of(0,limit)).toList();
    }

    @Override
    public Server get(Long id) {
        log.info("feetching a server : {}",id);
        return serverRepository.findById(id).get();
    }

    @Override
    public Server update(Server server) {
        log.info("upadate");
        return serverRepository.save(server);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("delete");
        serverRepository.deleteById(id);
        return TRUE;
    }
}
