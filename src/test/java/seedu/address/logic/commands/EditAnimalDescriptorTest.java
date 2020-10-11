package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEDICAL_CONDITION_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SPECIES_BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand.EditAnimalDescriptor;
import seedu.address.testutil.EditAnimalDescriptorBuilder;

public class EditAnimalDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditAnimalDescriptor descriptorWithSameValues = new EditAnimalDescriptor(DESC_AMY);
        assertTrue(DESC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AMY.equals(DESC_AMY));

        // null -> returns false
        assertFalse(DESC_AMY.equals(null));

        // different types -> returns false
        assertFalse(DESC_AMY.equals(5));

        // different values -> returns false
        assertFalse(DESC_AMY.equals(DESC_BOB));

        // different name -> returns false
        EditAnimalDescriptor editedAmy = new EditAnimalDescriptorBuilder(DESC_AMY).withName(VALID_NAME_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different ID -> returns false
        editedAmy = new EditAnimalDescriptorBuilder(DESC_AMY).withId(VALID_ID_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different species -> returns false
        editedAmy = new EditAnimalDescriptorBuilder(DESC_AMY).withSpecies(VALID_SPECIES_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different medicalConditions -> returns false
        editedAmy = new EditAnimalDescriptorBuilder(DESC_AMY)
                .withMedicalConditions(VALID_MEDICAL_CONDITION_HUSBAND).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }
}
