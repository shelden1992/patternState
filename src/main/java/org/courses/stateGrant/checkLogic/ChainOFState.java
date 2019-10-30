package org.courses.stateGrant.checkLogic;

public interface ChainOFState {
    void sentNextChain(ChainOFState nextChain);

    boolean commissionReview();

    void checkConditionOfGrant();
}
