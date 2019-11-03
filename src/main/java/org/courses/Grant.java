package org.courses;

import org.courses.stateGrant.CreatedSt;
import org.courses.stateGrant.State;
import org.courses.stateGrant.enums.GrantStatus;

import java.util.StringJoiner;

public class Grant extends AbstractGrant {
    private State state;
    private String description;

    public Grant(State state, String description) {
        this.state = state;
        this.description = description;
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public void setState(State state) {

        this.state = state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Grant.class.getSimpleName() + "[", "]")
                .add("state=" + state)
                .add("description='" + description + "'")
                .toString();
    }

    @Override
    public GrantStatus getStateInform() {
        return state.getStatus();

    }
}
