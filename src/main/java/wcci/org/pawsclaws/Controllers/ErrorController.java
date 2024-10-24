package wcci.org.pawsclaws.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller class for handling general errors within the application.
 * Implements the {@link org.springframework.boot.web.servlet.error.ErrorController} interface from Spring Boot to define a custom error page.
 */
@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    /**
     * Handles requests directed to the default error page.
     * This method returns the view name for a custom error page that users will see when an error occurs.
     *
     * @return The view name of the error page (e.g., "Shelter/ErrorMessage").
     */
    @RequestMapping("/error")
    public String handleError() {
        return "Shelter/ErrorMessage"; // Returns the name of the error view page
    }
}
