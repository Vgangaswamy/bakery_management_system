package edu.iit.sat.itmd4515.vgangaswamy.service;

import edu.iit.sat.itmd4515.vgangaswamy.domain.Bakery;
import edu.iit.sat.itmd4515.vgangaswamy.domain.ProductType;
import jakarta.ejb.Stateless;
import jakarta.inject.Named;

import java.util.List;
@Named
@Stateless
public class BakeryService extends AbstractService<Bakery> {
    public BakeryService() {
        super(Bakery.class);
    }

    public List<Bakery> readAll() {
        return super.readAll("Bakery.readAll");
    }

    public ProductType[] getAllProductTypes() {
        return ProductType.values();
    }
}
