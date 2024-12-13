package org.acme.hibernate.reactive;

import jakarta.persistence.*;

@Entity
@Table(name = "known_flowers")
public class Flower extends Plant {

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumns({
        @JoinColumn(name = "friend", referencedColumnName = "id", insertable = false, updatable = false, nullable = false)
    })
    private Fruit friend;

    public Flower() {
        super(new Seed(), null);
    }

    public Flower(Seed seed, String name, Fruit friend) {
        super(seed, name);
        this.friend = friend;
    }

    @Override
    public String toString() {
        return "Flower{" + getSeed().getId() + "," + getName() + '}';
    }

}
