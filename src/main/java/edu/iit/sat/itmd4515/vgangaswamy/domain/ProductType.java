package edu.iit.sat.itmd4515.vgangaswamy.domain;

public enum ProductType {
    BREAD("Bread"),
    PASTRY("Pastry"),
    CAKES("Cakes");

    private final String label;

    ProductType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
