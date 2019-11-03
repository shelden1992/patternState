package org.courses.stateGrant;

import org.apache.log4j.Logger;
import org.courses.stateGrant.checkLogic.ChainOFState;
import org.courses.stateGrant.enums.GrantStatus;

public class ConsideredSt implements State {
    private static final Logger LOG = Logger.getLogger(ConsideredSt.class);
    private static final GrantStatus status = GrantStatus.CONSIDERED;
    private ChainOFState nextChain;

    public ConsideredSt(ChainOFState nextChain) {
        this.nextChain = nextChain;
    }

    @Override
    public GrantStatus getStatus() {
        return status;
    }


    @Override
    public void checkConditionOfGrant() {
        LOG.info("Now the grant has been sent for verification, check back later GRANT STATUS");
        if (commissionReview()) {
            nextChain.checkConditionOfGrant();

        }
    }

    @Override
    public boolean commissionReview() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean commissionDecision = Math.random() < 0.8;
        if (commissionDecision) {
            LOG.info(String.format("Status Grant %s, push to next check ", status));
        } else {
            LOG.info(String.format("Status Grant %s, NOT push to next check ", status));
        }
        return commissionDecision;
    }

    @Override
    public void sentNextChain(ChainOFState nextChain) {
        this.nextChain = nextChain;
    }
}
