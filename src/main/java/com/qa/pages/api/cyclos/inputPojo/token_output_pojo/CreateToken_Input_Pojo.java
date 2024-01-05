package com.qa.pages.api.cyclos.inputPojo.token_output_pojo;

import com.qa.pages.BasePage;
import lombok.Data;

@Data
public class CreateToken_Input_Pojo extends BasePage {
    private String username;
    private String password;

    @Override
    protected void waitForAppToLoad() { }
    public CreateToken_Input_Pojo(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }


}
