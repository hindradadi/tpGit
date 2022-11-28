package com.serveeer.servermanager.controller;

import com.serveeer.servermanager.enumeration.Status;
import com.serveeer.servermanager.model.Server;
import com.serveeer.servermanager.service.ServerServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.time.LocalDateTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
public class ServerController {

    private final ServerServiceImplementation serverServiceImplementation;
    @GetMapping("/list")
    public ResponseEntity<Response> getServers() {
        return ResponseEntity.ok(
           Response.builder()
                   .timeStamp(now())
                   .data(of("servers", serverServiceImplementation.list(8)))
                   .message("Servers retrieved")
                   .status(OK)
                   .statusCode(OK.value())
                   .build()


        );
    }
    @SuppressWarnings("checkstyle:MissingJavadocMethod")
    @GetMapping("/ping/{ipAddress}")
    public ResponseEntity<Response> pingServer(@PathVariable("ipAddress") String ipAddress) throws IOException {
        Server server = serverServiceImplementation.ping(ipAddress);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("server", server))
                        .message(server.getStatus() == Status.SERVER_UP ? "ping success" : "ping failed")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()


        );
    }
    @PostMapping("/save")
    public ResponseEntity<Response> saveServer(@RequestBody @Valid Server server) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("server", serverServiceImplementation.create(server)))
                        .message("server created")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()


        );
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getServer(@PathVariable("id") Long id) {

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("server", serverServiceImplementation.get(id)))
                        .message("server retrieved")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()


        );
    }
    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<Response> deleteServer(@PathVariable("id") Long id) {

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("server", serverServiceImplementation.delete(id)))
                        .message("server deleted")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()


        );
    }
    @GetMapping  (path = "/image/{filename}", produces = IMAGE_PNG_VALUE)
    public byte[] getServerImage(@PathVariable("filename") String filename) throws IOException {

        return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/Downloads/images/" + filename));
    }
}
