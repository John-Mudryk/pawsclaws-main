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

@Controller
public class PetController {

    private final ConfirmationService confirmationService;

    public PetController(ConfirmationService confirmationService, PetService service) {
        this.confirmationService = confirmationService;
        this.service = service;
    }

    @Autowired
    private PetService service;

    @GetMapping({ "/", "home" })
    public String getAllPets(Model model) {
        try {
            List<PetDTO> pets = service.getAllPets();
            model.addAttribute("pets", pets);
            model.addAttribute("title", "Shelter Pets");
            return "Shelter/ViewPets";
        } catch (Exception ex) {
            ErrorDataDTO errorData = new ErrorDataDTO();
            errorData.setErrorMessage(ex.getMessage());
            errorData.setErrorCode(500);
            model.addAttribute("errorData", errorData);
            model.addAttribute("title", "Shelter Pets - Error");
            return "Shelter/ErrorMessage";
        }
    }

    @GetMapping("details/{id}")
    public String getDetails(@PathVariable long id, Model model) {
        StatusDTO status = new StatusDTO();
        PetDTO pet = service.getPetById(id);
        if (pet == null) {
            model.addAttribute("errorMessage",
                    "Pet not found. It might have been adopted, died, or is no longer in the shelter.");
            return "Shelter/ErrorMessage"; // Redirect to an error page
        }
        try {
            // pet.setStatus(pet.getStatus().replace("/n", "<br/>"));
            model.addAttribute("pet", pet);
            model.addAttribute("title", "Details for " + pet.getName());
            model.addAttribute("status", status);
            String image = service.getPetImage(pet.getName(), pet.getPetType().toString());
            model.addAttribute("image", image);
            return "Shelter/ViewDetails";
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.BAD_REQUEST) {
                model.addAttribute("errorMessage", "Pet not found or has been adopted.");
                return "Shelter/ErrorMessage";
            }
            throw ex;
        }
    }

    @GetMapping("create")
    public String createPet(Model model) {
        AdmissionDTO addedPet = new AdmissionDTO();
        model.addAttribute("pet", addedPet);
        model.addAttribute("title", "Create pet");
        model.addAttribute("petTypes", PetType.values());
        return "Shelter/CreatePet";
    }

    @PostMapping("saveadd")
    public String saveadd(@ModelAttribute AdmissionDTO pet, Model model) {
        try {
            if (pet.getAge() == null) {
                pet.setAge(0);
            }
            service.saveAdd(pet);
        } catch (Exception ex) {
            pet.setErrorMessage(ex.getMessage());
            pet.setErrorCode(400);
            model.addAttribute("pet", pet);
            model.addAttribute("title", "Create pet");
            model.addAttribute("petTypes", PetType.values());
            return "Shelter/CreatePet";
        }
        return "redirect:/home";
    }

    @GetMapping("edit/{id}")
    public String editPet(@PathVariable long id, Model model) {
        EditPetDTO pet = new EditPetDTO(service.getPetById(id));
        model.addAttribute("pet", pet);
        model.addAttribute("title", "Edit pet");
        model.addAttribute("petTypes", PetType.values());
        return "Shelter/EditPet";
    }

    @PostMapping("saveedit")
    public String saveEdit(@ModelAttribute EditPetDTO pet, Model model) {
        try {
            if (pet.getAge() == null) {
                pet.setAge(0);
            }
            service.saveEdit(pet);
        } catch (Exception ex) {
            pet.setErrorMessage(ex.getMessage());
            pet.setErrorCode(400);
            model.addAttribute("pet", pet);
            model.addAttribute("title", "Edit pet");
            model.addAttribute("petTypes", PetType.values());
            return "Shelter/EditPet";
        }
        return "redirect:/home";
    }

    @GetMapping("feed/{id}")
    public String feedAPet(@PathVariable long id, Model model) {
        StatusDTO status = service.carePet(id, "feed");
        PetDTO pet = service.getPetById(id);
        if (pet == null) {
            model.addAttribute("errorMessage",
                    "Pet not found. It might have been adopted, died, or is no longer in the shelter.");
            return "Shelter/ErrorMessage"; // Redirect to error page
        }
        model.addAttribute("pet", pet);
        model.addAttribute("title", "Details for " + pet.getName());
        model.addAttribute("petTypes", PetType.values());
        model.addAttribute("status", status);
        return "Shelter/ViewDetails";
    }

    @GetMapping("water/{id}")
    public String waterAPet(@PathVariable long id, Model model) {
        StatusDTO status = service.carePet(id, "water");
        PetDTO pet = service.getPetById(id);
        if (pet == null) {
            model.addAttribute("errorMessage",
                    "Pet not found. It might have been adopted, died, or is no longer in the shelter.");
            return "Shelter/ErrorMessage"; // Redirect to error page
        }
        model.addAttribute("pet", pet);
        model.addAttribute("title", "Details for " + pet.getName());
        model.addAttribute("petTypes", PetType.values());
        model.addAttribute("status", status);
        return "Shelter/ViewDetails";
    }

    @GetMapping("play/{id}")
    public String playAPet(@PathVariable long id, Model model) {
        StatusDTO status = service.carePet(id, "play");
        PetDTO pet = service.getPetById(id);
        if (pet == null) {
            model.addAttribute("errorMessage",
                    "Pet not found. It might have been adopted, died, or is no longer in the shelter.");
            return "Shelter/ErrorMessage"; // Redirect to error page
        }
        model.addAttribute("pet", pet);
        model.addAttribute("title", "Details for " + pet.getName());
        model.addAttribute("petTypes", PetType.values());
        model.addAttribute("status", status);
        return "Shelter/ViewDetails";
    }

    @GetMapping("heal/{id}")
    public String healAPet(@PathVariable long id, Model model) {
        StatusDTO status = service.carePet(id, "heal");
        PetDTO pet = service.getPetById(id);
        if (pet == null) {
            model.addAttribute("errorMessage",
                    "Pet not found. It might have been adopted, died, or is no longer in the shelter.");
            return "Shelter/ErrorMessage"; // Redirect to error page
        }
            model.addAttribute("pet", pet);
            model.addAttribute("title", "Details for " + pet.getName());
            model.addAttribute("petTypes", PetType.values());
            model.addAttribute("status", status);
        return "Shelter/ViewDetails";
    }

    @GetMapping("adoptPet/{id}")
    public String deletePet(@PathVariable long id, Model model) {
        try {
            service.deletePetById(id); // Call the service method to delete the pet
        } catch (Exception ex) {
            ErrorDataDTO errorData = new ErrorDataDTO();
            errorData.setErrorMessage(ex.getMessage());
            errorData.setErrorCode(500);
            model.addAttribute("errorData", errorData);
            model.addAttribute("title", "Error Adopting Pet");
            return "Shelter/ErrorMessage";
        }
        return "redirect:/home"; // Redirect back to home after successful deletion
    }

    @GetMapping("/adoptPetConfirmation/{id}")
    public String confirmAction(@PathVariable EditPetDTO pet, Long id, Model model) {
        // Here you could either confirm the action directly
        // or use the confirmationService if you have specific logic
        // Assuming you want to confirm the action and then adopt the pet
        boolean confirmed = confirmationService.confirmAction("adopt");
        if (confirmed) {
            service.deletePetById(id); // This would be your adoption logic
            return "redirect:/adoptionSuccess"; // Redirect to a success page
        } else {
            return "redirect:/adoptionCancelled"; // Redirect to a cancellation page or message
        }
    }
}
