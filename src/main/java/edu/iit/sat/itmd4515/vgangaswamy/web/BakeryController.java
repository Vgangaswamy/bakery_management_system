package edu.iit.sat.itmd4515.vgangaswamy.web;

import edu.iit.sat.itmd4515.vgangaswamy.domain.Bakery;
import edu.iit.sat.itmd4515.vgangaswamy.service.BakeryService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
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
        bakery = new Bakery();
    }

    public String saveBakeryProduct() {
        try {
            LOG.info("Saving bakery product: " + bakery);
            bakSvc.create(bakery);
            LOG.info("Product saved successfully.");
            return "createProductConfirmation.xhtml";
        } catch (Exception e) {
            LOG.severe("Error saving product: " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error saving product", e.getMessage()));
            return null; // Stay on the same page
        }
    }


    public Bakery getBakery() {
        return bakery;
    }

    public void setBakery(Bakery bakery) {
        this.bakery = bakery;
    }
}
