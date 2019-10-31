package org.courses.grantContext;

import org.apache.log4j.Logger;
import org.courses.AbstractGrant;
import org.courses.Applicant;
import org.courses.Grant;
import org.courses.stateGrant.ConfirmedSt;
import org.courses.stateGrant.ConsideredSt;
import org.courses.stateGrant.CreatedSt;
import org.courses.stateGrant.checkLogic.Verification;
import org.courses.stateGrant.enums.GrantStatus;

import java.util.*;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;


public class GrantWebPage implements GrantContext, GrantCreator, Runnable {
    private static final Logger LOG = Logger.getLogger(GrantWebPage.class);
    private static final GrantWebPage INSTANCE = new GrantWebPage();
    private Map<Applicant, Set<AbstractGrant>> applicantAndGrant;

    private GrantWebPage() {
        this.applicantAndGrant = new HashMap<>();

    }

    public static GrantWebPage getInstance() {
        return INSTANCE;
    }

    @Override
    public void addGrant(Applicant applicant, Grant... grant) {
        if (isNull(applicant)) {
            NullPointerException nullPointerException = new NullPointerException();
            LOG.error("Applicant cannot be NULL", nullPointerException);
            throw nullPointerException;
        }
        Set<AbstractGrant> grantSet = applicantAndGrant.get(applicant);
        if (isNull(grantSet)) {
            LOG.debug(String.format("Put into applicantAndGrant %s, %s", applicant, Arrays.toString(grant)));
            applicantAndGrant.put(applicant, new HashSet<>(Arrays.asList(grant)));
            applicant.setFlagAddGrant(true);
        } else {
            LOG.debug(String.format("Add to set %s of applicant %s", grantSet, applicant));
            grantSet.addAll(Arrays.asList(grant));
            applicantAndGrant.put(applicant, grantSet);
        }
    }

    @Override
    public void deleteGrant(Applicant applicant, Grant grant) {
        if (isNull(applicant)) {
            NullPointerException nullPointerException = new NullPointerException();
            LOG.error("Applicant cannot be NULL", nullPointerException);
            throw nullPointerException;
        }
        Set<AbstractGrant> grantSet = applicantAndGrant.get(applicant);
        if (nonNull(grantSet) && !grantSet.isEmpty() && grantSet.contains(grant)) {
            grantSet.remove(grant);
            LOG.info(String.format("Delete grant %s, in applicant %s)", grant, applicant));
            if (grantSet.isEmpty()) {
                applicantAndGrant.remove(applicant);
                LOG.info(String.format("Remove applicant %s, because set of grant is empty", applicant));
                applicant.setFlagAddGrant(false);
            } else {
                applicantAndGrant.put(applicant, grantSet);
            }
        } else {
            LOG.info("Sorry, but this grant is missing");
        }
    }

    @Override
    public GrantStatus checkStatusGrant(Applicant applicant, Grant grant) {
        if (isNull(applicant)) {
            NullPointerException nullPointerException = new NullPointerException();
            LOG.error("Applicant cannot be NULL", nullPointerException);
            throw nullPointerException;
        }
        Set<AbstractGrant> grantSet = applicantAndGrant.get(applicant);
        if (!grantSet.isEmpty() && grantSet.contains(grant)) {
            for (AbstractGrant next : grantSet) {
                if (next.equals(grant)) {
                    LOG.debug("Status grant = " + next.getStateInform());
                    return next.getStateInform();

                }

            }
        }
        return GrantStatus.UNCERTAIN;
    }

    @Override
    public void run() {
        while (applicantAndGrant.isEmpty()) {
            Set<Applicant> applicants = applicantAndGrant.keySet();
            applicants.forEach(applicant -> {
                if (applicant.isFlagAddGrant()) {
                    Set<AbstractGrant> abstractGrants = applicantAndGrant.get(applicant);
                    if (nonNull(abstractGrants)) {
                        abstractGrants.forEach(grant -> new Verification().verification(grant));
                    }
                }
            });
        }
    }


    @Override
    public Grant createdGrant(Applicant applicant, String description) {
        Grant grant = new Grant(new CreatedSt(new ConsideredSt(new ConfirmedSt())), description);
        addGrant(applicant, grant);
        return grant;
    }
}
