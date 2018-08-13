package ch.lebois.server.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
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

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pcUser='" + pcUser + '\'' +
                ", os='" + os + '\'' +
                ", ip='" + ip + '\'' +
                ", clientArch='" + clientArch + '\'' +
                ", lastseen=" + lastseen +
                ", refreshtime=" + refreshtime +
                ", command='" + command + '\'' +
                ", messages=" + messages +
                '}';
    }
}
