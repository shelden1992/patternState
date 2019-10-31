package org.courses;

import org.apache.log4j.Logger;
import org.courses.grantContext.GrantWebPage;

public class Main {
    private static final Logger LOG = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        Applicant applicant = new Applicant("Den", "Shel", "Ecology");
        GrantWebPage grantWebPage = GrantWebPage.getInstance();
        new Thread(grantWebPage).start();
        Grant grant = grantWebPage.createdGrant(applicant, "Ecology grant");
        grantWebPage.checkStatusGrant(applicant, grant);
    }
}
