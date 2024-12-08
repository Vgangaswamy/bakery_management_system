package edu.iit.sat.itmd4515.vgangaswamy.web;

import edu.iit.sat.itmd4515.vgangaswamy.domain.Inventory;
import edu.iit.sat.itmd4515.vgangaswamy.service.InventoryService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class InventoryController {

    @Inject
    private InventoryService inventoryService;

    public List<Inventory> getAllInventories() {
        return inventoryService.readAll();
    }
}
