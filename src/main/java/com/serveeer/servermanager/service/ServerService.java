package com.serveeer.servermanager.service;

import com.serveeer.servermanager.model.Server;

import java.io.IOException;
import java.util.Collection;

public interface ServerService {
    //creation d un serveur dans Database
    @SuppressWarnings("checkstyle:MissingJavadocMethod")
    Server create(Server server);
    Server ping(String ipAddress) throws IOException;
    Collection<Server> list(int limit);
    Server get(Long id);
    Server update(Server server);
    Boolean delete(Long id);


}
