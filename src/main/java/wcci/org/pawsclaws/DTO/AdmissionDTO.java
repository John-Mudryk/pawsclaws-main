package wcci.org.pawsclaws.DTO;

import wcci.org.pawsclaws.Enums.PetType;

/**
 * Data Transfer Object (DTO) used for admitting a new pet to the shelter.
 * This class extends ErrorDataDTO to include error handling capabilities.
 * It contains details about the pet such as name, age, and type.
 */
public class AdmissionDTO extends ErrorDataDTO {
    private String name;
    private Integer age;
    private PetType petType;

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

    /**
     * Gets the type of the pet (e.g., Dog, Cat, etc.).
     *
     * @return The type of the pet.
     */
    public PetType getPetType() {
        return petType;
    }

    /**
     * Sets the type of the pet.
     *
     * @param petType The type to set for the pet.
     */
    public void setPetType(PetType petType) {
        this.petType = petType; // Assigns the provided type to the pet
    }
}
