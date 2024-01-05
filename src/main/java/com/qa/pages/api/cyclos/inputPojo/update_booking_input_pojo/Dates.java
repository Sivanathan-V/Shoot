package com.qa.pages.api.cyclos.inputPojo.update_booking_input_pojo;

import lombok.Data;

@Data
public class Dates {
    private String checkin;
    private String checkout;

    public Dates(String checkin, String checkout) {
        super();
        this.checkin = checkin;
        this.checkout = checkout;
    }
}
