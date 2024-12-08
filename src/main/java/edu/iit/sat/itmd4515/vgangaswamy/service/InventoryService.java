package edu.iit.sat.itmd4515.vgangaswamy.service;

import edu.iit.sat.itmd4515.vgangaswamy.domain.Inventory;
import jakarta.ejb.Stateless;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class InventoryService extends AbstractService<Inventory> {

    private static final Logger LOG = Logger.getLogger(InventoryService.class.getName());

    public InventoryService() {
        super(Inventory.class);
    }

    public List<Inventory> readAll() {
        List<Inventory> inventories = super.readAll("Inventory.readAll");
        LOG.info("Retrieved Inventories: " + inventories);
        return inventories;
    }
}
