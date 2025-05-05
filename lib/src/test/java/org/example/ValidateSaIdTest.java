package org.example;

import org.Validate_sa_id.ValidateSaId;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class ValidateSaIdTest {

    @Test
    public void testValidIdNumber() {
        assertTrue(ValidateSaId.isIdNumberValid("9202204720082"));
        assertTrue(ValidateSaId.isIdNumberValid("8501014800085"));
    }

    @Test
    public void testInvalidLength() {
        assertFalse(ValidateSaId.isIdNumberValid("920220472008")); // Too short
        assertFalse(ValidateSaId.isIdNumberValid("92022047200823")); // Too long
    }

    @Test
    public void testNonNumeric() {
        assertFalse(ValidateSaId.isIdNumberValid("92022A4720082")); // Contains letter
        assertFalse(ValidateSaId.isIdNumberValid("92022B4720082")); // Contains letter
    }

    @Test
    public void testInvalidDate() {
        assertFalse(ValidateSaId.isIdNumberValid("9902304720082")); // Invalid date (Feb 30)
        assertFalse(ValidateSaId.isIdNumberValid("8913324720083")); // Invalid date (month 13)
    }

    @Test
    public void testInvalidChecksum() {
        assertFalse(ValidateSaId.isIdNumberValid("9202204720083")); // Checksum should be 2
        assertFalse(ValidateSaId.isIdNumberValid("8501014800086")); // Checksum should be 5
    }

    @Test
    public void testNullInput() {
        assertFalse(ValidateSaId.isIdNumberValid(null));
        assertFalse(ValidateSaId.isIdNumberValid(null));
    }

    @Test
    public void testEmptyInput() {
        assertFalse(ValidateSaId.isIdNumberValid(""));
        assertFalse(ValidateSaId.isIdNumberValid(" "));
    }
}