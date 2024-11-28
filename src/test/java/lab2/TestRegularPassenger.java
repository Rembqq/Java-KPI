package lab2;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.example.lab2.Person;
import org.junit.Test;


public class TestRegularPassenger {
    @Test
    public void equalsContract() {
        try {
            EqualsVerifier.forClass(Person.class).verify();
            System.out.println("Test passed: EqualsVerifier verification successful.");
        } catch (Exception e) {
            System.out.println("Test failed: " + e.getMessage());
        }
    }

}

