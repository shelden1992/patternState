package org.courses.grantContext;

import org.courses.Applicant;
import org.courses.Grant;

public interface GrantCreator {
    Grant createdGrant(Applicant applicant, String description);
}
