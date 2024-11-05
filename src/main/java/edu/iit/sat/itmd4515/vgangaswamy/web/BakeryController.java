package edu.iit.sat.itmd4515.vgangaswamy.web;

import edu.iit.sat.itmd4515.vgangaswamy.domain.Bakery;
import edu.iit.sat.itmd4515.vgangaswamy.service.BakeryService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.util.logging.Logger;

@Named
@RequestScoped
public class BakeryController {

    @EJB
    BakeryService bakSvc;

    private static final Logger LOG = Logger.getLogger(BakeryController.class.getName());

    private Bakery bakery;

    public BakeryController() {
    }
    @PostConstruct
    private void postConstruct(){
         LOG.info("Inside BakeryController.petConstruct()");
         // instantiate my model
        bakery = new Bakery();
    }

    public String saveBakeryProduct(){
        LOG.info("Inside BakeryController.saveBakeryProduct() before call to service" + bakery.toString());
        bakSvc.create(bakery);
        LOG.info("Inside BakeryController.saveBakeryProduct() after call to service" + bakery.toString());
        return "createProductConfirmation.xhtml";
    }

    public Bakery getBakery() {
        return bakery;
    }

    public void setBakery(Bakery bakery) {
        this.bakery = bakery;
    }
}
