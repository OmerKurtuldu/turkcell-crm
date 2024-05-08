package com.turkcell.commonpackage.events.address;

import com.turkcell.commonpackage.events.Event;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatedAddressEvent  implements Event {
    private int id;
    private String messages;
    private String status;
    private int cityId;
    private String street;
    private String houseFlatNumber;
    private String description;
    private int customerId;
}
