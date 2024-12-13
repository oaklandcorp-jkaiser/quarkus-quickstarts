package org.acme.hibernate.reactive;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

import io.smallrye.mutiny.Uni;
import org.hibernate.reactive.mutiny.Mutiny.SessionFactory;

@Path("flowers")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class FlowerMutinyResource {

    @Inject
    SessionFactory sf;

    @GET
    public Uni<List<Flower>> get() {
        return sf.withStatelessTransaction(s -> {
            var cb = sf.getCriteriaBuilder();
            var query = cb.createQuery(Flower.class);
            var root = query.from(Flower.class);
            query.orderBy(cb.asc(root.get(Flower_.seed).get(Seed_.id)));
            return s.createQuery(query).getResultList();
        });
    }

}
