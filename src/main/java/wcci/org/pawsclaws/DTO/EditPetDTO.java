package wcci.org.pawsclaws.DTO;

/**
 * Data Transfer Object (DTO) for editing an existing pet's information.
 * Extends ErrorDataDTO to include error handling information.
 * This class allows for setting or updating the ID, name, and age of a pet.
 */
public class EditPetDTO extends ErrorDataDTO {
    private long id;
    private String name;
    private Integer age;

    /**
     * Constructor to initialize EditPetDTO with data from an existing PetDTO.
     *
     * @param pet The PetDTO object from which to copy the ID, name, and age.
     */
    public EditPetDTO(PetDTO pet) {
        this.id = pet.getId();
        this.name = pet.getName();
        this.age = pet.getAge();
    }

    /**
     * Default constructor for EditPetDTO.
     */
    public EditPetDTO() {
    }

    /**
     * Gets the unique ID of the pet.
     *
     * @return The ID of the pet.
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the unique ID of the pet.
     *
     * @param id The ID to set for the pet.
     */
    public void setId(long id) {
        this.id = id; // Assigns the provided ID to the pet
    }

    /**
     * Gets the name of the pet.
     *
     * @return The name of the pet.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the pet.
     *
     * @param name The name to set for the pet.
     */
    public void setName(String name) {
        this.name = name; // Assigns the provided name to the pet
    }

    /**
     * Gets the age of the pet.
     *
     * @return The age of the pet.
     */
    public Integer getAge() {
        return age;
    }

    /**
     * Sets the age of the pet.
     *
     * @param age The age to set for the pet.
     */
    public void setAge(Integer age) {
        this.age = age; // Assigns the provided age to the pet
    }
}
