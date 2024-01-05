package com.qa.pages.api.cyclos.inputPojo.update_booking_input_pojo;

import lombok.Data;

@Data
public class UpdateAll_Input_Pojo {
    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private Dates bookingdates;
    private String additionalneeds;

    public UpdateAll_Input_Pojo(String firstname, String lastname, int totalprice, boolean depositpaid,
                                Dates bookingdates, String additionalneeds) {
        super();
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        this.bookingdates = bookingdates;
        this.additionalneeds = additionalneeds;
    }
}
