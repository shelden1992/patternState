package org.courses;

import java.util.Objects;
import java.util.StringJoiner;

public class Applicant {
    private String name;
    private String surname;
    private String education;
    private boolean flagAddGrant;

    public Applicant(String name, String surname, String education) {
        this.name = name;
        this.surname = surname;
        this.education = education;
    }

    public boolean isFlagAddGrant() {
        return flagAddGrant;
    }

    public void setFlagAddGrant(boolean flagAddGrant) {
        this.flagAddGrant = flagAddGrant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Applicant applicant = (Applicant) o;
        return Objects.equals(name, applicant.name) &&
                Objects.equals(surname, applicant.surname) &&
                Objects.equals(education, applicant.education);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Applicant.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("surname='" + surname + "'")
                .add("education='" + education + "'")
                .toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, education);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }
}
