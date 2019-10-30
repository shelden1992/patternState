package org.courses.stateGrant;

import org.apache.log4j.Logger;
import org.courses.stateGrant.checkLogic.ChainOFState;
import org.courses.stateGrant.enums.GrantStatus;

public class ConfirmedSt implements State {
    private static final Logger LOG = Logger.getLogger(ConfirmedSt.class);
    private static GrantStatus status = GrantStatus.CONFIRMED;

    public ConfirmedSt() {

    }

    @Override
    public GrantStatus getStatus() {
        return status;
    }

    @Override
    public void checkConditionOfGrant() {
        LOG.info("Now the grant has been sent for verification, check back later GRANT STATUS");
        if (commissionReview()) {
            LOG.info("Get congratulation you Grant approved " + status);
        } else LOG.info("Sorry, but your Grant NOT approved");
    }

    @Override
    public void sentNextChain(ChainOFState nextChain) {

    }

    @Override
    public boolean commissionReview() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return Math.random() < 0.1;
    }
}
