package org.Validate_sa_id;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ValidateSaId {

    public static boolean isIdNumberValid(String idNumber) {
        // Basic validation
        if (idNumber == null || idNumber.length() != 13 || !idNumber.matches("\\d+")) {
            return false;
        }

        // Extract date parts
        String yearPrefix = idNumber.charAt(0) < '2' ? "20" : "19";
        String dateString = yearPrefix + idNumber.substring(0, 6); //YYYYMMDD format
        
        // Validate date
        if (!isValidDate(dateString)) {
            return false;
        }

        // Validate checksum
        return isValidChecksum(idNumber);
    }

    private static boolean isValidDate(String dateString) {
        try {
            LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyyMMdd"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private static boolean isValidChecksum(String idNumber) {
        int sum = 0;
        for (int i = 0; i < 12; i++) {
            int digit = Character.getNumericValue(idNumber.charAt(i));
            // Multiply even index digits by 2, odd index digits by 1
            int multiplyBy = (i % 2 == 0) ? 2 : 1;
            int product = digit * multiplyBy;
            // Add the digits of the product
            sum += (product / 10) + (product % 10);
        }
        
        int checkDigit = Character.getNumericValue(idNumber.charAt(12));
        int calculatedCheckDigit = (10 - (sum % 10)) % 10;
        
        return checkDigit == calculatedCheckDigit;
    }
}