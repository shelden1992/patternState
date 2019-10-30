package org.courses;

import org.courses.stateGrant.State;
import org.courses.stateGrant.enums.GrantStatus;

public abstract class AbstractGrant {

    public abstract State getState();

    abstract void setState(State state);

    public abstract GrantStatus getStateInform();

}
