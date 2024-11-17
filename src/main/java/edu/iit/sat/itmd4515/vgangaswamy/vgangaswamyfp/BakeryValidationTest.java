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
        System.out.println("Validator initialized.");
    }

    private Bakery bakery;

    @BeforeEach
    public void setUp() {
        bakery = new Bakery("Brioche", "A delicious brioche bread", ProductType.BREAD, 50.0F, 10, true);
        System.out.println("Bakery object initialized for testing: " + bakery);
    }

    @Test
    public void testNameValidation_Pass() {
        Set<ConstraintViolation<Bakery>> violations = validator.validate(bakery);
        System.out.println("Validating bakery name (expected to pass): " + bakery.getName());
        Assertions.assertTrue(violations.isEmpty(), "Expected no validation violations for a valid bakery name.");
    }

    @Test
    public void testNameValidation_Fail() {
        bakery.setName("");
        System.out.println("Validating bakery name with an empty value (expected to fail): " + bakery.getName());
        Set<ConstraintViolation<Bakery>> violations = validator.validate(bakery);
        Assertions.assertFalse(violations.isEmpty(), "Expected validation violations due to an empty bakery name.");

        for (ConstraintViolation<Bakery> violation : violations) {
            System.out.println("Validation failed for property: " + violation.getPropertyPath() + " with message: " + violation.getMessage());
            Assertions.assertEquals("name", violation.getPropertyPath().toString());
        }
    }

    @Test
    public void testPriceValidation_Pass() {
        bakery.setPrice(10.0F);
        System.out.println("Validating bakery price (expected to pass): " + bakery.getPrice());
        Set<ConstraintViolation<Bakery>> violations = validator.validate(bakery);
        Assertions.assertTrue(violations.isEmpty(), "Expected no validation violations for a valid bakery price.");
    }

    @Test
    public void testPriceValidation_Fail() {
        bakery.setPrice(-1.0F);
        System.out.println("Validating bakery price with a negative value (expected to fail): " + bakery.getPrice());
        Set<ConstraintViolation<Bakery>> violations = validator.validate(bakery);
        Assertions.assertFalse(violations.isEmpty(), "Expected validation violations due to a negative bakery price.");

        for (ConstraintViolation<Bakery> violation : violations) {
            System.out.println("Validation failed for property: " + violation.getPropertyPath() + " with message: " + violation.getMessage());
            Assertions.assertEquals("price", violation.getPropertyPath().toString());
        }
    }

    @Test
    public void testQuantityValidation_Pass() {
        bakery.setQuantity(5);
        System.out.println("Validating bakery quantity (expected to pass): " + bakery.getQuantity());
        Set<ConstraintViolation<Bakery>> violations = validator.validate(bakery);
        Assertions.assertTrue(violations.isEmpty(), "Expected no validation violations for a valid bakery quantity.");
    }

    @Test
    public void testQuantityValidation_Fail() {
        bakery.setQuantity(-5);
        System.out.println("Validating bakery quantity with a negative value (expected to fail): " + bakery.getQuantity());
        Set<ConstraintViolation<Bakery>> violations = validator.validate(bakery);
        Assertions.assertFalse(violations.isEmpty(), "Expected validation violations due to a negative bakery quantity.");

        for (ConstraintViolation<Bakery> violation : violations) {
            System.out.println("Validation failed for property: " + violation.getPropertyPath() + " with message: " + violation.getMessage());
            Assertions.assertEquals("quantity", violation.getPropertyPath().toString());
        }
    }

    @Test
    public void testIsAvailableValidation_Pass() {
        bakery.setAvailable(true);
        System.out.println("Validating bakery availability status (expected to pass): " + bakery.isAvailable());
        Set<ConstraintViolation<Bakery>> violations = validator.validate(bakery);
        Assertions.assertTrue(violations.isEmpty(), "Expected no validation violations for a valid bakery availability status.");
    }
}
