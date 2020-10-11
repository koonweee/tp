package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ANIMAL;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.animal.Id;
import seedu.address.model.animal.Name;
import seedu.address.model.animal.Species;
import seedu.address.model.medicalcondition.MedicalCondition;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_ID = "+651234";
    private static final String INVALID_SPECIES = " ";
    private static final String INVALID_MEDICAL_CONDITION = "#friend";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_SPECIES = "Cat";
    private static final String VALID_ID = "123456";
    private static final String VALID_MEDICAL_CONDITION_1 = "friend";
    private static final String VALID_MEDICAL_CONDITION_2 = "neighbour";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_ANIMAL, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_ANIMAL, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parseId_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseId((String) null));
    }

    @Test
    public void parseId_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseId(INVALID_ID));
    }

    @Test
    public void parseId_validValueWithoutWhitespace_returnsId() throws Exception {
        Id expectedId = new Id(VALID_ID);
        assertEquals(expectedId, ParserUtil.parseId(VALID_ID));
    }

    @Test
    public void parseId_validValueWithWhitespace_returnsTrimmedId() throws Exception {
        String idWithWhitespace = WHITESPACE + VALID_ID + WHITESPACE;
        Id expectedId = new Id(VALID_ID);
        assertEquals(expectedId, ParserUtil.parseId(idWithWhitespace));
    }

    @Test
    public void parseSpecies_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseSpecies((String) null));
    }

    @Test
    public void parseSpecies_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseSpecies(INVALID_SPECIES));
    }

    @Test
    public void parseSpecies_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Species expectedSpecies = new Species(VALID_SPECIES);
        assertEquals(expectedSpecies, ParserUtil.parseSpecies(VALID_SPECIES));
    }

    @Test
    public void parseSpecies_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String speciesWithWhiteSpace = WHITESPACE + VALID_SPECIES + WHITESPACE;
        Species expectedSpecies = new Species(VALID_SPECIES);
        assertEquals(expectedSpecies, ParserUtil.parseSpecies(speciesWithWhiteSpace));
    }

    @Test
    public void parseMedicalCondition_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseMedicalCondition(null));
    }

    @Test
    public void parseMedicalCondition_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseMedicalCondition(INVALID_MEDICAL_CONDITION));
    }

    @Test
    public void parseMedicalCondition_validValueWithoutWhitespace_returnsMedicalCondition() throws Exception {
        MedicalCondition expectedMedicalCondition = new MedicalCondition(VALID_MEDICAL_CONDITION_1);
        assertEquals(expectedMedicalCondition, ParserUtil.parseMedicalCondition(VALID_MEDICAL_CONDITION_1));
    }

    @Test
    public void parseMedicalCondition_validValueWithWhitespace_returnsTrimmedMedicalCondition() throws Exception {
        String medicalConditionWithWhitespace = WHITESPACE + VALID_MEDICAL_CONDITION_1 + WHITESPACE;
        MedicalCondition expectedMedicalCondition = new MedicalCondition(VALID_MEDICAL_CONDITION_1);
        assertEquals(expectedMedicalCondition, ParserUtil.parseMedicalCondition(medicalConditionWithWhitespace));
    }

    @Test
    public void parseMedicalConditions_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseMedicalConditions(null));
    }

    @Test
    public void parseMedicalConditions_collectionWithInvalidMedicalConditions_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseMedicalConditions(Arrays
                .asList(VALID_MEDICAL_CONDITION_1, INVALID_MEDICAL_CONDITION)));
    }

    @Test
    public void parseMedicalConditions_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseMedicalConditions(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseMedicalConditions_collectionWithValidMedicalConditions_returnsMedicalConditionSet()
            throws Exception {
        Set<MedicalCondition> actualMedicalConditionSet = ParserUtil.parseMedicalConditions(Arrays
                .asList(VALID_MEDICAL_CONDITION_1, VALID_MEDICAL_CONDITION_2));
        Set<MedicalCondition> expectedMedicalConditionSet = new HashSet<MedicalCondition>(Arrays.asList(
                new MedicalCondition(VALID_MEDICAL_CONDITION_1), new MedicalCondition(VALID_MEDICAL_CONDITION_2)));

        assertEquals(expectedMedicalConditionSet, actualMedicalConditionSet);
    }
}
