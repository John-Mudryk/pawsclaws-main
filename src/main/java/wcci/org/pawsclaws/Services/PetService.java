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

/**
 * Service class for managing pets in the shelter.
 * <p>
 * This class provides methods to interact with external APIs and handle CRUD
 * operations for pets, including adding, editing, fetching, and deleting pets.
 * It also includes methods to care for pets and fetch pet images based on their
 * type.
 * </p>
 */
@Service
public class PetService {

    /**
     * RestTemplate for making HTTP requests.
     */
    private final RestTemplate restTemplate;

    /**
     * Base URL of the server for shelter-related API requests.
     */
    public final String server = "http://localhost:8080";

    /**
     * Constructor to initialize RestTemplate.
     *
     * @param restTemplate The RestTemplate object to be used for HTTP requests.
     */
    public PetService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Fetches a list of all pets from the shelter.
     *
     * @return A list of PetDTO objects representing all pets in the shelter.
     */
    public List<PetDTO> getAllPets() {
        String url = server + "/api/v1/shelter";
        PetDTO[] pets = restTemplate.getForObject(url, PetDTO[].class);
        return Arrays.asList(pets);
    }

    /**
     * Fetches a pet by its ID.
     *
     * @param id The ID of the pet.
     * @return The PetDTO object representing the pet or null if not found.
     */
    public PetDTO getPetById(long id) {
        String url = server + "/api/v1/shelter/" + id;
        try {
            return restTemplate.getForObject(url, PetDTO.class);
        } catch (HttpClientErrorException ex) {
            // Handle specific case when pet is not found
            if (ex.getStatusCode() == HttpStatus.BAD_REQUEST) {
                return null; // Pet not found, return null
            }
            throw ex; // Re-throw other errors
        }
    }

    /**
     * Adds a new pet to the shelter.
     *
     * @param admit The AdmissionDTO object containing pet details.
     * @return The PetDTO object representing the newly added pet.
     */
    public PetDTO saveAdd(AdmissionDTO admit) {
        String url = server + "/api/v1/shelter";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<AdmissionDTO> requestEntity = new HttpEntity<>(admit, headers);
        PetDTO pet = restTemplate.postForObject(url, requestEntity, PetDTO.class);
        return pet;
    }

    /**
     * Updates an existing pet's details.
     *
     * @param edit The EditPetDTO object containing updated pet details.
     * @return The PetDTO object representing the updated pet.
     */
    public PetDTO saveEdit(EditPetDTO edit) {
        String url = server + "/api/v1/shelter";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<EditPetDTO> requestEntity = new HttpEntity<>(edit, headers);
        PetDTO pet = restTemplate.postForObject(url, requestEntity, PetDTO.class);
        return pet;
    }

    /**
     * Performs care actions on a pet (e.g., feed, water, play, heal).
     *
     * @param id     The ID of the pet.
     * @param action The action to perform (e.g., "feed", "water").
     * @return The StatusDTO object containing the result of the action.
     */
    public StatusDTO carePet(long id, String action) {
        String url = server + "/api/v1/shelter/" + action + "/" + id;
        StatusDTO status = restTemplate.getForObject(url, StatusDTO.class);
        return status;
    }

    /**
     * Deletes a pet from the shelter by its ID.
     *
     * @param id The ID of the pet to be deleted.
     */
    public void deletePetById(long id) {
        String url = server + "/api/v1/shelter/" + id;
        restTemplate.delete(url);
    }

    /**
     * Fetches the image URL of a pet based on its name and type.
     *
     * @param name           The name of the pet.
     * @param petTypeString  The type of the pet as a string.
     * @return The image URL of the pet.
     */
    public String getPetImage(String name, String petTypeString) {
        name = name.replace(" ", "_");
        String result = "";
        PetType petType = PetType.valueOf(petTypeString);
        switch (petType) {
            case Cat: {
                result = getCatImage(name);
                break;
            }
            case Dog: {
                result = getDogImage(name);
                break;
            }
            case RoboticCat:
            case RoboticDog: {
                result = getRoboImage(name);
                break;
            }
            default: {
                break;
            }
        }
        return result;
    }

    /**
     * Fetches a random cat image URL using an external API.
     *
     * @param name The name of the cat.
     * @return The URL of the cat image.
     */
    public String getCatImage(String name) {
        String url = "https://api.thecatapi.com/v1/images/search";
        CatImageDTO[] pets = restTemplate.getForObject(url, CatImageDTO[].class);
        if (pets == null) {
            return "";
        }
        return pets[0].getUrl();
    }

    /**
     * Provides a static dog image URL.
     *
     * @param name The name of the dog.
     * @return The URL of the dog image.
     */
    public String getDogImage(String name) {
        String url = "https://place.dog/300/300";
        return url;
    }

    /**
     * Generates a robotic pet image URL based on the pet's name.
     *
     * @param name The name of the robotic pet.
     * @return The URL of the robotic pet image.
     */
    public String getRoboImage(String name) {
        String url = "https://robohash.org/" + name;
        return url;
    }
}
