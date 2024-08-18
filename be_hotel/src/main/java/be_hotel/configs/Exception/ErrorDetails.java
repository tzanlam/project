package be_hotel.configs.Exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ErrorDetails {
    private Date timetamp;
    private String message;
    private String details;

    public ErrorDetails(Date timetamp, String message, String details) {
        super();
        this.timetamp = timetamp;
        this.message = message;
        this.details = details;
    }
}
