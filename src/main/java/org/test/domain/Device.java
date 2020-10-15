package org.test.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "devices")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Project project;

    @JoinColumn(name = "serial_number")
    private String serialNumber;

    @OneToMany
    private List<Event> events;
}
