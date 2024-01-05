package com.qa.pages.api.cyclos.inputPojo.booking_input_pojo;

import com.qa.pages.BasePage;
import lombok.Data;


@Data
public class Bookingdates extends BasePage {
    private String checkin;
    private String checkout;

    @Override
    protected void waitForAppToLoad() { }

    public Bookingdates(String checkin, String checkout) {
        super();
        this.checkin = checkin;
        this.checkout = checkout;
    }

}
