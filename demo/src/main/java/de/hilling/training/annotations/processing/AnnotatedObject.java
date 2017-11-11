package de.hilling.training.annotations.processing;

public class AnnotatedObject {

    @EMailAddress
    private Integer email;

    public Integer getEmail() {
        return email;
    }

    public void setEmail(Integer email) {
        this.email = email;
    }
}
