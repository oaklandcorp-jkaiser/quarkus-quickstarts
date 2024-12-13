package org.acme.hibernate.reactive;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Seed {

    @Column(nullable = false, updatable = false)
    private Integer id;

    public Seed() {

    }

    public Seed(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
