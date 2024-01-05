package com.qa.pages.api.cyclos.payLoads;


import com.qa.pages.api.cyclos.inputPojo.booking_input_pojo.AddUser_Input_Pojo;
import com.qa.pages.api.cyclos.inputPojo.booking_input_pojo.Bookingdates;
import com.qa.pages.api.cyclos.inputPojo.token_output_pojo.CreateToken_Input_Pojo;
import com.qa.pages.BasePage;
import lombok.Data;


@Data
public class BookingPayloads extends BasePage {

    CreateToken_Input_Pojo createInputPayLoad;
    AddUser_Input_Pojo bookInputPayLoad;

    @Override
    protected void waitForAppToLoad() { }
    public CreateToken_Input_Pojo createTokenPayload(String username, String password) {

        try {
            createInputPayLoad = new CreateToken_Input_Pojo(username, password);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return createInputPayLoad;
    }

    public AddUser_Input_Pojo bookUserPayload(String firstName, String lastName, int totalPrice, boolean depositPaid,
                                              String checkIn, String checkOut, String additionalNeeds) {

        try {
            Bookingdates bookingDates = new Bookingdates(checkIn,checkOut);

            bookInputPayLoad = new AddUser_Input_Pojo( firstName, lastName,totalPrice, depositPaid,
                    bookingDates, additionalNeeds);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return bookInputPayLoad;
    }

}
