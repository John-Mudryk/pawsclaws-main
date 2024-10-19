package wcci.org.pawsclaws.Services;

import java.util.*;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import wcci.org.pawsclaws.DTO.*;
import wcci.org.pawsclaws.Enums.PetType;

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
        try {
            return restTemplate.getForObject(url, PetDTO.class);
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.BAD_REQUEST) {
                return null; // Pet not found, return null
            }
            throw ex; // Re-throw other errors
        }
    }

    public PetDTO saveAdd(AdmissionDTO admit) {
        String url = server + "/api/v1/shelter";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<AdmissionDTO> requestEntity = new HttpEntity<>(admit, headers);
        PetDTO pet = restTemplate.postForObject(url, requestEntity, PetDTO.class);
        return pet;
    }

    public PetDTO saveEdit(EditPetDTO edit) {
        String url = server + "/api/v1/shelter";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<EditPetDTO> requestEntity = new HttpEntity<>(edit, headers);
        PetDTO pet = restTemplate.postForObject(url, requestEntity, PetDTO.class);
        return pet;
    }

    public StatusDTO carePet(long id, String action) {
        String url = server + "/api/v1/shelter/" + action + "/" + id;
        StatusDTO status = restTemplate.getForObject(url, StatusDTO.class);
        return status;
    }

    public void deletePetById(long id) {
        String url = server + "/api/v1/shelter/" + id;
        restTemplate.delete(url);
    }

    public String getPetImage(String name, String petTypeString) {
        name=name.replace(" ", "_");
        String result = "";
        PetType petType = PetType.valueOf(petTypeString);
        switch(petType){
            case Cat: {
                result = getCatImage(name);
                break;
            }
            case Dog:{
                result = getDogImage(name);
                break;
            }
            case RoboticCat:
            case RoboticDog:
            {
                result = getRoboImage(name);
                break;
            }
            default:{
                break;
            }
        }
        return result;
    }

    public String getCatImage(String name){
        String url = "https://api.thecatapi.com/v1/images/search";
        CatImageDTO[] pets = restTemplate.getForObject(url, CatImageDTO[].class);
        if (pets==null){
            return "";
        }
        return pets[0].getUrl();
    }

    public String getDogImage(String name) {
        String url = "https://place.dog/300/300";
        return url;
    }

    public String getRoboImage(String name) {
        String url = "https://robohash.org/" + name;
        return url;
    }
 }
