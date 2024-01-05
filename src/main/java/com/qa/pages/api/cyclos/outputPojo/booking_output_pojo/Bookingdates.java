package com.qa.pages.api.cyclos.outputPojo.booking_output_pojo;

import com.qa.pages.BasePage;
import lombok.Data;


@Data
public class Bookingdates extends BasePage {
    private String checkin;
    private String checkout;

    public Bookingdates(String checkin, String checkout) {
        super();
        this.checkin = checkin;
        this.checkout = checkout;
    }

    @Override
    protected void waitForAppToLoad() { }
}
