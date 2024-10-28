package edu.iit.sat.itmd4515.vgangaswamy.service;

import edu.iit.sat.itmd4515.vgangaswamy.domain.Bakery;
import jakarta.ejb.Stateless;

import java.util.List;

@Stateless
public class BakeryService extends AbstractService<Bakery> {
    public BakeryService() {
        super(Bakery.class);
    }

    public List<Bakery> readAll() {
        return super.readAll("Bakery.readAll");
    }
}
