package edu.iit.sat.itmd4515.vgangaswamy.service;

import edu.iit.sat.itmd4515.vgangaswamy.domain.Ingredient;
import edu.iit.sat.itmd4515.vgangaswamy.domain.Inventory;
import jakarta.ejb.Stateless;

import java.util.List;

@Stateless
public class InventoryService extends AbstractService<Inventory> {
    public InventoryService() {
        super(Inventory.class);
    }
    public List<Inventory> readAll() {
        return super.readAll("Inventory.readAll");
    }
}
