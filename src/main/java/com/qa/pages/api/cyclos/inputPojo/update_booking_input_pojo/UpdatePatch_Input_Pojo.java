package com.qa.pages.api.cyclos.inputPojo.update_booking_input_pojo;

import lombok.Data;

@Data
public class UpdatePatch_Input_Pojo {
    private String firstname;
    private String lastname;

    public UpdatePatch_Input_Pojo(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }
}
