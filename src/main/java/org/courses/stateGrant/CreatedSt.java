package org.courses.stateGrant;

import org.apache.log4j.Logger;
import org.courses.stateGrant.checkLogic.ChainOFState;
import org.courses.stateGrant.enums.GrantStatus;

public class CreatedSt implements State {
    private static final Logger LOG = Logger.getLogger(CreatedSt.class);
    private GrantStatus status = GrantStatus.CREATED;
    private ChainOFState nextChain;

    public CreatedSt(ChainOFState nextChain) {
        this.nextChain = nextChain;
    }

    public GrantStatus getStatus() {
        return status;
    }

    public void setStatus(GrantStatus status) {
        this.status = status;
    }

    public void checkConditionOfGrant() {
        LOG.info("Now the grant has been sent for verification, check back later GRANT STATUS " + status);
        if (commissionReview()) {
            nextChain.checkConditionOfGrant();
        }
    }

    @Override
    public boolean commissionReview() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean commissionDecision = Math.random() < 0.9;
        if (commissionDecision) {
            LOG.info(String.format("Status Grant %s, push to next check ", status));
        } else {
            LOG.info(String.format("Status Grant %s, NOT push to next check ", status));
        }
        return commissionDecision;
    }

    public void sentNextChain(ChainOFState nextChain) {
        this.nextChain = nextChain;
    }

}
