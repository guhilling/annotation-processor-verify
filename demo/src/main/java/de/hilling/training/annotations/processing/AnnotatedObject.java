package de.hilling.training.annotations.processing;

public class AnnotatedObject {

    @EMailAddress
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
