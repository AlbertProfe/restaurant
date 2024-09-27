package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    private String name;
    private String phoneNumber;
    private int peopleQty;
    private LocalDateTime date;
    private Table table;
    private boolean confirmed;

    // Custom toString method (optional, as @Data provides a default toString)
    @Override
    public String toString() {
        return "Booking{" +
                ", customerName='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", numberOfGuests=" + peopleQty +
                ", bookingDateTime=" + date +
                ", table=" + (table != null ? table.getName() : "Not assigned") +
                ", confirmed=" + confirmed +
                '}';
    }
}