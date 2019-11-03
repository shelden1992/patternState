package org.courses.stateGrant;

import org.courses.stateGrant.checkLogic.ChainOFState;
import org.courses.stateGrant.enums.GrantStatus;

public interface State extends ChainOFState {
    GrantStatus getStatus();
}
