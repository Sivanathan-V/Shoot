package com.qa.pages.api.cyclos.inputPojo.booking_input_pojo;

import com.qa.pages.BasePage;
import lombok.Data;


@Data
public class AddUser_Input_Pojo extends BasePage {
    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private Bookingdates bookingdates;
    private String additionalneeds;

    @Override
    protected void waitForAppToLoad() { }
    public AddUser_Input_Pojo(String firstname, String lastname, int totalprice, boolean depositpaid,
                              Bookingdates bookingdates, String additionalneeds) {
        super();
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        this.bookingdates = bookingdates;
        this.additionalneeds = additionalneeds;
    }


}
