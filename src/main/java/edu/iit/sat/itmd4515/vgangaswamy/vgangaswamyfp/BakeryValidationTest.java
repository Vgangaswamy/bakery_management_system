package edu.iit.sat.itmd4515.vgangaswamy.vgangaswamyfp;

import edu.iit.sat.itmd4515.vgangaswamy.domain.Bakery;
import edu.iit.sat.itmd4515.vgangaswamy.domain.ProductType;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class BakeryValidationTest {

    private static Validator validator;

    @BeforeAll
    public static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    private Bakery bakery;

    @BeforeEach
    public void setUp() {
        bakery = new Bakery("Brioche", "A delicious brioche bread", ProductType.BREAD, 50.0F, 10, true);
    }


    @Test
    public void testNameValidation_Pass() {
        Set<ConstraintViolation<Bakery>> violations = validator.validate(bakery);
        Assertions.assertTrue(violations.isEmpty(), "Validation passed");
    }

    @Test
    public void testNameValidation_Fail() {
        bakery.setName("");
        Set<ConstraintViolation<Bakery>> violations = validator.validate(bakery);
        Assertions.assertFalse(violations.isEmpty(), "Validation failed");

        for (ConstraintViolation<Bakery> violation : violations) {
            Assertions.assertEquals("name", violation.getPropertyPath().toString());
        }
    }

    @Test
    public void testPriceValidation_Pass() {
        bakery.setPrice(10.0F);
        Set<ConstraintViolation<Bakery>> violations = validator.validate(bakery);
        Assertions.assertTrue(violations.isEmpty(), "Validation passed");
    }

    @Test
    public void testPriceValidation_Fail() {
        bakery.setPrice(-1.0F);
        Set<ConstraintViolation<Bakery>> violations = validator.validate(bakery);
        Assertions.assertFalse(violations.isEmpty(), "Validation failed");

        for (ConstraintViolation<Bakery> violation : violations) {
            Assertions.assertEquals("price", violation.getPropertyPath().toString());
        }
    }

    @Test
    public void testQuantityValidation_Pass() {
        bakery.setQuantity(5);
        Set<ConstraintViolation<Bakery>> violations = validator.validate(bakery);
        Assertions.assertTrue(violations.isEmpty(), "Validation passed");
    }

    @Test
    public void testQuantityValidation_Fail() {
        bakery.setQuantity(-5);
        Set<ConstraintViolation<Bakery>> violations = validator.validate(bakery);
        Assertions.assertFalse(violations.isEmpty(), "Validation failed");

        for (ConstraintViolation<Bakery> violation : violations) {
            Assertions.assertEquals("quantity", violation.getPropertyPath().toString());
        }
    }

    @Test
    public void testIsAvailableValidation_Pass() {
        bakery.setAvailable(true); // Valid available status
        Set<ConstraintViolation<Bakery>> violations = validator.validate(bakery);
        Assertions.assertTrue(violations.isEmpty(), "Validation passed");
    }
}



