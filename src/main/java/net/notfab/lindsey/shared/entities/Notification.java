package net.notfab.lindsey.shared.entities;

import lombok.Data;
import net.notfab.lindsey.shared.converters.JsonArrayStringConverter;
import net.notfab.lindsey.shared.enums.NotificationStatus;
import org.json.JSONArray;

import javax.persistence.*;

@Data
@Entity
public class Notification {

    @Id
    private Long id;

    private Long target;
    private String message;

    @Convert(converter = JsonArrayStringConverter.class)
    private JSONArray params;

    @Enumerated(EnumType.ORDINAL)
    private NotificationStatus status;

}
