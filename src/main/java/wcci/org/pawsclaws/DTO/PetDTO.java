package wcci.org.pawsclaws.DTO;

import wcci.org.pawsclaws.Enums.PetType;

/**
 * Data Transfer Object (DTO) for managing pet information.
 * This class stores various attributes related to a pet, including its health, status,
 * and other specific characteristics based on whether it's a regular or robotic pet.
 */
public class PetDTO extends ErrorDataDTO {
    private long id;
    private PetType petType;
    private String name;
    private Integer age;
    private int health;
    private int happiness;
    private String deathBy;
    private int hunger;
    private int thirst;
    private int battery;
    private int oil;
    private boolean dead;
    private String status;
    private String image;

    /**
     * Gets the ID of the pet.
     *
     * @return The pet ID.
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the ID of the pet.
     *
     * @param id The pet ID to set.
     */
    public void setId(long id) {
        this.id = id; // Assigns the provided ID to the pet
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
     * @param name The name of the pet.
     */
    public void setName(String name) {
        this.name = name; // Assigns the provided name to the pet
    }

    /**
     * Gets the age of the pet.
     *
     * @return The pet's age.
     */
    public Integer getAge() {
        return age;
    }

    /**
     * Sets the age of the pet.
     *
     * @param age The age of the pet.
     */
    public void setAge(Integer age) {
        this.age = age; // Sets the pet's age
    }

    /**
     * Gets the health status of the pet.
     *
     * @return The pet's health as an integer.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Sets the health status of the pet.
     *
     * @param health The health value to set.
     */
    public void setHealth(int health) {
        this.health = health; // Sets the pet's health
    }

    /**
     * Gets the happiness level of the pet.
     *
     * @return The pet's happiness as an integer.
     */
    public int getHappiness() {
        return happiness;
    }

    /**
     * Sets the happiness level of the pet.
     *
     * @param happiness The happiness value to set.
     */
    public void setHappiness(int happiness) {
        this.happiness = happiness; // Sets the pet's happiness
    }

    /**
     * Gets the reason or cause of the pet's death.
     *
     * @return The cause of death as a string.
     */
    public String getDeathBy() {
        return deathBy;
    }

    /**
     * Sets the reason or cause of the pet's death.
     *
     * @param deathBy The cause of death.
     */
    public void setDeathBy(String deathBy) {
        this.deathBy = deathBy; // Sets the cause of death
    }

    /**
     * Gets the hunger level of the pet.
     *
     * @return The pet's hunger as an integer.
     */
    public int getHunger() {
        return hunger;
    }

    /**
     * Sets the hunger level of the pet.
     *
     * @param hunger The hunger value to set.
     */
    public void setHunger(int hunger) {
        this.hunger = hunger; // Sets the pet's hunger level
    }

    /**
     * Gets the thirst level of the pet.
     *
     * @return The pet's thirst as an integer.
     */
    public int getThirst() {
        return thirst;
    }

    /**
     * Sets the thirst level of the pet.
     *
     * @param thirst The thirst value to set.
     */
    public void setThirst(int thirst) {
        this.thirst = thirst; // Sets the pet's thirst level
    }

    /**
     * Gets the battery level for robotic pets.
     *
     * @return The battery level as an integer.
     */
    public int getBattery() {
        return battery;
    }

    /**
     * Sets the battery level for robotic pets.
     *
     * @param battery The battery level to set.
     */
    public void setBattery(int battery) {
        this.battery = battery; // Sets the battery level
    }

    /**
     * Gets the oil level for robotic pets.
     *
     * @return The oil level as an integer.
     */
    public int getOil() {
        return oil;
    }

    /**
     * Sets the oil level for robotic pets.
     *
     * @param oil The oil level to set.
     */
    public void setOil(int oil) {
        this.oil = oil; // Sets the oil level
    }

    /**
     * Checks if the pet is marked as dead.
     *
     * @return True if the pet is dead, otherwise false.
     */
    public boolean isDead() {
        return dead;
    }

    /**
     * Sets the dead status of the pet.
     *
     * @param dead The status to set (true if dead, false if alive).
     */
    public void setDead(boolean dead) {
        this.dead = dead; // Sets the dead status
    }

    /**
     * Gets the current status of the pet.
     *
     * @return The pet's status as a string.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the current status of the pet.
     *
     * @param status The status to set.
     */
    public void setStatus(String status) {
        this.status = status; // Sets the pet's status
    }

    /**
     * Checks if the pet is a robotic pet.
     *
     * @return True if the pet is robotic, otherwise false.
     */
    public boolean isRobot(){
        return (this.getPetType() == PetType.RoboticCat || this.getPetType() == PetType.RoboticDog);
    }

    /**
     * Gets the image URL or path associated with the pet.
     *
     * @return The image as a string.
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets the image URL or path associated with the pet.
     *
     * @param image The image to set.
     */
    public void setImage(String image) {
        this.image = image; // Sets the image associated with the pet
    }
}
