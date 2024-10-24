package wcci.org.pawsclaws.DTO;

import wcci.org.pawsclaws.Enums.PetType;

/**
 * Data Transfer Object (DTO) for representing the status of a pet.
 * This class provides detailed information regarding the pet's name, type, and
 * current status, including any relevant values or messages associated with it.
 * It extends from ErrorDataDTO to incorporate potential error information.
 */
public class StatusDTO extends ErrorDataDTO {
    private String name;
    private PetType petType;
    private String status;
    private String value;

    /**
     * Default constructor.
     */
    public StatusDTO() {
    }

    /**
     * Parameterized constructor to initialize a StatusDTO instance with specific values.
     * 
     * @param name The name of the pet.
     * @param petType The type of the pet (e.g., Dog, Cat, RoboticDog).
     * @param status The current status message of the pet.
     * @param value The value associated with the pet's status.
     */
    public StatusDTO(String name, PetType petType, String status, String value) {
        this.name = name;
        this.petType = petType;
        this.status = status;
        this.value = value;
    }

    /**
     * Gets the name of the pet.
     * 
     * @return The pet's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the pet.
     * 
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name; // Sets the pet's name
    }

    /**
     * Gets the pet type (e.g., Dog, Cat, RoboticDog, RoboticCat).
     * 
     * @return The type of the pet.
     */
    public PetType getPetType() {
        return petType;
    }

    /**
     * Sets the pet type.
     * 
     * @param petType The type of the pet to set.
     */
    public void setPetType(PetType petType) {
        this.petType = petType; // Sets the pet's type
    }

    /**
     * Gets the status message of the pet.
     * 
     * @return The current status of the pet.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status message of the pet.
     * 
     * @param status The status message to set.
     */
    public void setStatus(String status) {
        this.status = status; // Assigns the provided status to the pet
    }

    /**
     * Gets the value associated with the pet's status.
     * 
     * @return The value related to the pet's status.
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value associated with the pet's status.
     * 
     * @param value The value to set.
     */
    public void setValue(String value) {
        this.value = value; // Sets the associated value for the status
    }
}
