package com.serveeer.servermanager.model;

import com.serveeer.servermanager.enumeration.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Server {

    @SuppressWarnings("checkstyle:JavadocVariable")
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    @SuppressWarnings("checkstyle:JavadocVariable")
    @Column(unique =  true)
    @NotEmpty(message = "Ip address cannot be empty or null")
    private String ipAddress;
    @SuppressWarnings("checkstyle:JavadocVariable")
    private String name;
    @SuppressWarnings("checkstyle:JavadocVariable")
    private String memory;
    @SuppressWarnings("checkstyle:JavadocVariable")
    private String type;
    @SuppressWarnings("checkstyle:JavadocVariable")
    private String imageUrl;
    @SuppressWarnings("checkstyle:JavadocVariable")
    private Status status;

}
