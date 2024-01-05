package com.qa.pages.api.jiraPojo.payload;

import com.qa.pages.api.jiraPojo.inputPojo.PostBugInputPojo.Fields;
import com.qa.pages.api.jiraPojo.inputPojo.PostBugInputPojo.Issuetype;
import com.qa.pages.api.jiraPojo.inputPojo.PostBugInputPojo.PostBugInputPojo;
import com.qa.pages.api.jiraPojo.inputPojo.PostBugInputPojo.Project;
import com.qa.pages.api.jiraPojo.inputPojo.UpdateBugInputPojo.Fields1;
import com.qa.pages.api.jiraPojo.inputPojo.UpdateBugInputPojo.UpdateBugInputPojo;

public class Payload {

	public static PostBugInputPojo postBugPayload() {
		Project p = new Project("GOMA"); 
		Issuetype i = new Issuetype("Bug");
		Fields f = new Fields(p, " create issue", "Creating of an issue for login", i);
		PostBugInputPojo postBugInputPojo = new PostBugInputPojo(f);
		return postBugInputPojo;
	}

	public static UpdateBugInputPojo updateBugPayload() {
		Fields1 f = new Fields1("summary","Description");
		UpdateBugInputPojo updateBugInputPojo = new UpdateBugInputPojo(f);
		return updateBugInputPojo;

	}


}
