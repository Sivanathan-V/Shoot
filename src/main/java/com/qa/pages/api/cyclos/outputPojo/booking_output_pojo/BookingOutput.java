package com.qa.pages.api.cyclos.outputPojo.booking_output_pojo;

import com.qa.pages.BasePage;
import lombok.Data;

@Data
public class BookingOutput extends BasePage {
    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private BookingdatesOutput bookingdates;
    private String additionalneeds;

    @Override
    protected void waitForAppToLoad() { }

}
