package org.acme.hibernate.reactive;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "known_fruits")
@NamedQuery(name = "Fruits.findAll", query = "SELECT f FROM Fruit f ORDER BY f.seed.id")
public class Fruit extends Plant {

    @OneToMany(mappedBy = "friend", fetch = FetchType.LAZY)
    private List<Flower> friends;

    public Fruit() {
        super(new Seed(), null);
    }

    public Fruit(Seed seed, String name) {
        super(seed, name);
    }

    public List<Flower> getFriends() {
        return friends;
    }

    @Override
    public String toString() {
        return "Fruit{" + getSeed().getId() + "," + getName() + '}';
    }

}
