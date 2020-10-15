package org.testwork.domain;

import lombok.Data;
import org.testwork.enums.Type;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Device device;

    private Date date;

    private Type type;

    private boolean is_read;
}
