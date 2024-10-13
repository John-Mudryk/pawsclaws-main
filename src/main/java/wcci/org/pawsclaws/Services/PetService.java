package wcci.org.pawsclaws.Services;
import java.util.*;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import wcci.org.pawsclaws.DTO.*;

@Service
public class PetService {
    private final RestTemplate restTemplate;
    public final String server = "http://localhost:8080";

    public PetService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<PetDTO> getAllPets() {
        String url = server + "/api/v1/shelter";
        PetDTO[] pets = restTemplate.getForObject(url, PetDTO[].class);
        return Arrays.asList(pets);
    }

    public PetDTO getPetById(long id) {
        String url = server + "/api/v1/shelter/" + id;
        PetDTO pet = restTemplate.getForObject(url, PetDTO.class);
        return pet;
    }

    public PetDTO saveAdd(AdmissionDTO admit){
        String url = server + "/api/v1/shelter";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<AdmissionDTO> requestEntity = new HttpEntity<>(admit, headers);
        PetDTO pet = restTemplate.postForObject(url, requestEntity, PetDTO.class);
        return pet;
    }

    public PetDTO saveEdit(EditPetDTO edit){
        String url = server + "/api/v1/shelter";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<EditPetDTO> requestEntity = new HttpEntity<>(edit, headers);
        PetDTO pet = restTemplate.postForObject(url, requestEntity, PetDTO.class);
        return pet;
    }

    public StatusDTO carePet(long id, String action){
        String url = server + "/api/v1/shelter/" + action + "/" + id;
        StatusDTO status = restTemplate.getForObject(url, StatusDTO.class);
        return status;
    }

    public void deletePetById(long id) {
        String url = server + "/api/v1/shelter/" + id;
        restTemplate.delete(url);
    }
}
