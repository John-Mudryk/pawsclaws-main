package wcci.org.pawsclaws.DTO;

public class EditPetDTO extends ErrorDataDTO {
    private long id;
    private String name;
    private Integer age;
    
    public EditPetDTO(PetDTO pet) {
        this.id = pet.getId();
        this.name = pet.getName();
        this.age = pet.getAge();
    }

    public EditPetDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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



    
}
