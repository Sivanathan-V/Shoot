package com.qa.pages.api.cyclos.outputPojo.booking_output_pojo;

import com.qa.pages.BasePage;
import lombok.Data;

@Data
public class AddUser_Output_Pojo extends BasePage {
    private int bookingid;
    private BookingOutput booking;

    @Override
    protected void waitForAppToLoad() { }
}
