package org.courses.stateGrant.checkLogic;

import org.courses.AbstractGrant;
import org.courses.stateGrant.State;

public class Verification {
    public void verification(AbstractGrant abstractGrant) {
        State state = abstractGrant.getState();
        state.checkConditionOfGrant();
    }
}
