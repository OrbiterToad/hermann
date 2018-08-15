package ch.lebois.server.data.entity;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String nickname;
    private String pcUser;

    private String os;
    private String ip;

    private String clientArch;

    private Timestamp lastseen;
    private Integer refreshtime;

    private String command;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
    private List<Message> messages;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
    private List<Image> images;

}