package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPECIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditAnimalDescriptor;
import seedu.address.model.animal.Animal;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Animal.
 */
public class AnimalUtil {

    /**
     * Returns an add command string for adding the {@code animal}.
     */
    public static String getAddCommand(Animal animal) {
        return AddCommand.COMMAND_WORD + " " + getAnimalDetails(animal);
    }

    /**
     * Returns the part of command string for the given {@code animal}'s details.
     */
    public static String getAnimalDetails(Animal animal) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + animal.getName().fullName + " ");
        sb.append(PREFIX_ID + animal.getId().value + " ");
        sb.append(PREFIX_SPECIES + animal.getSpecies().value + " ");
        animal.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditAnimalDescriptor}'s details.
     */
    public static String getEditAnimalDescriptorDetails(EditAnimalDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getId().ifPresent(id -> sb.append(PREFIX_ID).append(id.value).append(" "));
        descriptor.getSpecies().ifPresent(species -> sb.append(PREFIX_SPECIES).append(species.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}