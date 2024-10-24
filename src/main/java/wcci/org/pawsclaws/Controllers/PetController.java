package wcci.org.pawsclaws.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import wcci.org.pawsclaws.DTO.*;
import wcci.org.pawsclaws.Enums.PetType;
import wcci.org.pawsclaws.Services.ConfirmationService;
import wcci.org.pawsclaws.Services.PetService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

/**
 * Controller class responsible for handling requests related to pets.
 * Provides endpoints for viewing, creating, editing, and managing pets in the shelter.
 */
@Controller
public class PetController {

    private final ConfirmationService confirmationService;

    /**
     * Constructor for the PetController class.
     *
     * @param confirmationService Service to handle confirmation actions.
     * @param service             Service to manage pet-related operations.
     */
    public PetController(ConfirmationService confirmationService, PetService service) {
        this.confirmationService = confirmationService;
        this.service = service;
    }

    @Autowired
    private PetService service;

    /**
     * Retrieves all pets in the shelter and displays them on the home page.
     *
     * @param model Model to pass data to the view.
     * @return The view name for displaying all shelter pets.
     */
    @GetMapping({ "/", "home" })
    public String getAllPets(Model model) {
        try {
            List<PetDTO> pets = service.getAllPets(); // Fetches all pets from the service
            model.addAttribute("pets", pets); // Adds the list of pets to the model
            model.addAttribute("title", "Shelter Pets"); // Sets the page title
            return "Shelter/ViewPets"; // Returns the view for displaying pets
        } catch (Exception ex) {
            // Handle any errors by setting error information in the model
            ErrorDataDTO errorData = new ErrorDataDTO();
            errorData.setErrorMessage(ex.getMessage());
            errorData.setErrorCode(500);
            model.addAttribute("errorData", errorData);
            model.addAttribute("title", "Shelter Pets - Error");
            return "Shelter/ErrorMessage"; // Redirects to an error message view
        }
    }

    /**
     * Retrieves the details of a specific pet based on its ID.
     *
     * @param id    The ID of the pet to be viewed.
     * @param model Model to pass data to the view.
     * @return The view name for displaying pet details or an error view if not found.
     */
    @GetMapping("details/{id}")
    public String getDetails(@PathVariable long id, Model model) {
        StatusDTO status = new StatusDTO();
        PetDTO pet = service.getPetById(id); // Fetches pet details by ID
        if (pet == null) {
            // If the pet is not found, show an error message
            model.addAttribute("errorMessage",
                    "Pet not found. It might have been adopted, died, or is no longer in the shelter.");
            return "Shelter/ErrorMessage"; // Redirect to an error page
        }
        try {
            model.addAttribute("pet", pet); // Adds pet details to the model
            model.addAttribute("title", "Details for " + pet.getName()); // Sets the page title
            model.addAttribute("status", status);
            String image = service.getPetImage(pet.getName(), pet.getPetType().toString()); // Fetches the pet image
            model.addAttribute("image", image); // Adds the pet image to the model
            return "Shelter/ViewDetails"; // Returns the view for displaying pet details
        } catch (HttpClientErrorException ex) {
            // Handle HTTP errors
            if (ex.getStatusCode() == HttpStatus.BAD_REQUEST) {
                model.addAttribute("errorMessage", "Pet not found or has been adopted.");
                return "Shelter/ErrorMessage"; // Redirects to an error view
            }
            throw ex;
        }
    }

    /**
     * Displays a form for creating a new pet.
     *
     * @param model Model to pass data to the view.
     * @return The view name for the pet creation form.
     */
    @GetMapping("create")
    public String createPet(Model model) {
        AdmissionDTO addedPet = new AdmissionDTO(); // Creates a new DTO for the form
        model.addAttribute("pet", addedPet); // Adds the new pet DTO to the model
        model.addAttribute("title", "Create pet"); // Sets the page title
        model.addAttribute("petTypes", PetType.values()); // Adds available pet types to the model
        return "Shelter/CreatePet"; // Returns the view for creating a pet
    }

    /**
     * Saves the new pet information submitted from the creation form.
     *
     * @param pet   The DTO containing new pet information.
     * @param model Model to pass data to the view.
     * @return A redirect to the home page if successful, or back to the form if there are errors.
     */
    @PostMapping("saveadd")
    public String saveadd(@ModelAttribute AdmissionDTO pet, Model model) {
        try {
            if (pet.getAge() == null) {
                pet.setAge(0); // Default age to 0 if not provided
            }
            service.saveAdd(pet); // Saves the new pet
        } catch (Exception ex) {
            // Handles validation errors and shows the form again with error details
            pet.setErrorMessage(ex.getMessage());
            pet.setErrorCode(400);
            model.addAttribute("pet", pet);
            model.addAttribute("title", "Create pet");
            model.addAttribute("petTypes", PetType.values());
            return "Shelter/CreatePet";
        }
        return "redirect:/home"; // Redirect to home page on successful save
    }

    /**
     * Displays a form for editing an existing pet.
     *
     * @param id    The ID of the pet to be edited.
     * @param model Model to pass data to the view.
     * @return The view name for the pet edit form.
     */
    @GetMapping("edit/{id}")
    public String editPet(@PathVariable long id, Model model) {
        EditPetDTO pet = new EditPetDTO(service.getPetById(id)); // Retrieves pet data and prepares it for editing
        model.addAttribute("pet", pet); // Adds the pet data to the model
        model.addAttribute("title", "Edit pet"); // Sets the page title
        model.addAttribute("petTypes", PetType.values()); // Adds available pet types to the model
        return "Shelter/EditPet"; // Returns the view for editing a pet
    }

    /**
     * Saves the edited pet information submitted from the edit form.
     *
     * @param pet   The DTO containing edited pet information.
     * @param model Model to pass data to the view.
     * @return A redirect to the home page if successful, or back to the form if there are errors.
     */
    @PostMapping("saveedit")
    public String saveEdit(@ModelAttribute EditPetDTO pet, Model model) {
        try {
            if (pet.getAge() == null) {
                pet.setAge(0); // Default age to 0 if not provided
            }
            service.saveEdit(pet); // Saves the edited pet details
        } catch (Exception ex) {
            // Handles validation errors and shows the form again with error details
            pet.setErrorMessage(ex.getMessage());
            pet.setErrorCode(400);
            model.addAttribute("pet", pet);
            model.addAttribute("title", "Edit pet");
            model.addAttribute("petTypes", PetType.values());
            return "Shelter/EditPet";
        }
        return "redirect:/home"; // Redirect to home page on successful save
    }

    /**
     * Feeds a pet and updates its status.
     *
     * @param id    The ID of the pet to be fed.
     * @param model Model to pass data to the view.
     * @return The view name for displaying the updated details of the pet.
     */
    @GetMapping("feed/{id}")
    public String feedAPet(@PathVariable long id, Model model) {
        StatusDTO status = service.carePet(id, "feed"); // Feeds the pet
        PetDTO pet = service.getPetById(id); // Retrieves pet details by ID
        if (pet == null) {
            // If the pet is not found, show an error message
            model.addAttribute("errorMessage",
                    "Pet not found. It might have been adopted, died, or is no longer in the shelter.");
            return "Shelter/ErrorMessage"; // Redirect to error page
        }
        // Add pet details and status to the model
        model.addAttribute("pet", pet);
        model.addAttribute("title", "Details for " + pet.getName());
        model.addAttribute("petTypes", PetType.values());
        model.addAttribute("status", status);
        return "Shelter/ViewDetails"; // Return the view for displaying pet details
    }

    /**
     * Waters a pet and updates its status.
     *
     * @param id    The ID of the pet to be watered.
     * @param model Model to pass data to the view.
     * @return The view name for displaying the updated details of the pet.
     */
    @GetMapping("water/{id}")
    public String waterAPet(@PathVariable long id, Model model) {
        StatusDTO status = service.carePet(id, "water"); // Waters the pet
        PetDTO pet = service.getPetById(id); // Retrieves pet details by ID
        if (pet == null) {
            // If the pet is not found, show an error message
            model.addAttribute("errorMessage",
                    "Pet not found. It might have been adopted, died, or is no longer in the shelter.");
            return "Shelter/ErrorMessage"; // Redirect to error page
        }
        // Add pet details and status to the model
        model.addAttribute("pet", pet);
        model.addAttribute("title", "Details for " + pet.getName());
        model.addAttribute("petTypes", PetType.values());
        model.addAttribute("status", status);
        return "Shelter/ViewDetails"; // Return the view for displaying pet details
    }

    /**
     * Plays with a pet and updates its status.
     *
     * @param id    The ID of the pet to be played with.
     * @param model Model to pass data to the view.
     * @return The view name for displaying the updated details of the pet.
     */
    @GetMapping("play/{id}")
    public String playAPet(@PathVariable long id, Model model) {
        StatusDTO status = service.carePet(id, "play"); // Plays with the pet
        PetDTO pet = service.getPetById(id); // Retrieves pet details by ID
        if (pet == null) {
            // If the pet is not found, show an error message
            model.addAttribute("errorMessage",
                    "Pet not found. It might have been adopted, died, or is no longer in the shelter.");
            return "Shelter/ErrorMessage"; // Redirect to error page
        }
        // Add pet details and status to the model
        model.addAttribute("pet", pet);
        model.addAttribute("title", "Details for " + pet.getName());
        model.addAttribute("petTypes", PetType.values());
        model.addAttribute("status", status);
        return "Shelter/ViewDetails"; // Return the view for displaying pet details
    }

    /**
     * Heals a pet and updates its status.
     *
     * @param id    The ID of the pet to be healed.
     * @param model Model to pass data to the view.
     * @return The view name for displaying the updated details of the pet.
     */
    @GetMapping("heal/{id}")
    public String healAPet(@PathVariable long id, Model model) {
        StatusDTO status = service.carePet(id, "heal"); // Heals the pet
        PetDTO pet = service.getPetById(id); // Retrieves pet details by ID
        if (pet == null) {
            // If the pet is not found, show an error message
            model.addAttribute("errorMessage",
                    "Pet not found. It might have been adopted, died, or is no longer in the shelter.");
            return "Shelter/ErrorMessage"; // Redirect to error page
        }
        // Add pet details and status to the model
        model.addAttribute("pet", pet);
        model.addAttribute("title", "Details for " + pet.getName());
        model.addAttribute("petTypes", PetType.values());
        model.addAttribute("status", status);
        return "Shelter/ViewDetails"; // Return the view for displaying pet details
    }

    /**
     * Deletes (adopts) a pet based on its ID.
     *
     * @param id    The ID of the pet to be adopted.
     * @param model Model to pass data to the view in case of errors.
     * @return Redirects to the home page if successful, or an error view if an exception occurs.
     */
    @GetMapping("adoptPet/{id}")
    public String deletePet(@PathVariable long id, Model model) {
        try {
            service.deletePetById(id); // Call the service method to delete the pet
        } catch (Exception ex) {
            // Handle errors and display an error message
            ErrorDataDTO errorData = new ErrorDataDTO();
            errorData.setErrorMessage(ex.getMessage());
            errorData.setErrorCode(500);
            model.addAttribute("errorData", errorData);
            model.addAttribute("title", "Error Adopting Pet");
            return "Shelter/ErrorMessage";
        }
        return "redirect:/home"; // Redirect back to home after successful deletion
    }

    /**
     * Confirms the adoption action for a pet.
     *
     * @param pet   The DTO of the pet being adopted.
     * @param id    The ID of the pet to be adopted.
     * @param model Model to pass data to the view.
     * @return Redirects to a success or cancellation page based on confirmation.
     */
    @GetMapping("/adoptPetConfirmation/{id}")
    public String confirmAction(@PathVariable EditPetDTO pet, Long id, Model model) {
        // Confirm the action using the confirmation service
        boolean confirmed = confirmationService.confirmAction("adopt");
        if (confirmed) {
            service.deletePetById(id); // Adopt the pet by deleting it from the shelter
            return "redirect:/adoptionSuccess"; // Redirect to a success page
        } else {
            return "redirect:/adoptionCancelled"; // Redirect to a cancellation page or message
        }
    }
}
