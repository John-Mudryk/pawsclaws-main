package wcci.org.pawsclaws.DTO;

import wcci.org.pawsclaws.Enums.PetType;

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

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public PetType getPetType() {
        return petType;
    }
    public void setPetType(PetType petType) {
        this.petType = petType;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public int getHappiness() {
        return happiness;
    }
    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }
    public String getDeathBy() {
        return deathBy;
    }
    public void setDeathBy(String deathBy) {
        this.deathBy = deathBy;
    }
    public int getHunger() {
        return hunger;
    }
    public void setHunger(int hunger) {
        this.hunger = hunger;
    }
    public int getThirst() {
        return thirst;
    }
    public void setThirst(int thirst) {
        this.thirst = thirst;
    }
    public int getBattery() {
        return battery;
    }
    public void setBattery(int battery) {
        this.battery = battery;
    }
    public int getOil() {
        return oil;
    }
    public void setOil(int oil) {
        this.oil = oil;
    }
    public boolean isDead() {
        return dead;
    }
    public void setDead(boolean dead) {
        this.dead = dead;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isRobot(){
        return (this.getPetType() == PetType.RoboticCat || this.getPetType() == PetType.RoboticDog);
    }
}
