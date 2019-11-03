package org.courses.grantContext;

import org.courses.Applicant;
import org.courses.Grant;
import org.courses.stateGrant.enums.GrantStatus;

public interface GrantContext {
    void addGrant(Applicant applicant, Grant... grant);

    void deleteGrant(Applicant applicant, Grant grant);

    GrantStatus checkStatusGrant(Applicant applicant, Grant grant);



}
