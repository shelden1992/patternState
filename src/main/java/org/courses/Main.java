package org.courses;

import org.apache.log4j.Logger;
import org.courses.grantContext.GrantWebPage;
import org.courses.stateGrant.ConfirmedSt;
import org.courses.stateGrant.ConsideredSt;
import org.courses.stateGrant.CreatedSt;

public class Main {
    private static final Logger LOG = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        Grant grant = new Grant(new CreatedSt(new ConsideredSt(new ConfirmedSt())), "Ecology grant");
        Applicant applicant = new Applicant("Den", "Shel", "Ecology");
        GrantWebPage grantWebPage = GrantWebPage.getInstance();
        new Thread(grantWebPage).start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            LOG.error(e);
        }
        grantWebPage.addGrant(applicant, grant);
        grantWebPage.checkStatusGrant(applicant, grant);
    }
}
