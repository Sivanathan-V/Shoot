package com.qa.pages.api.cyclos.outputPojo.booking_output_pojo;

import com.qa.pages.BasePage;
import lombok.Data;

@Data
public class BookingdatesOutput extends BasePage {
    @Override
    protected void waitForAppToLoad() { }

    private String checkin;
    private String checkout;

}
