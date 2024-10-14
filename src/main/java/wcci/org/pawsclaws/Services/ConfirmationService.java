package wcci.org.pawsclaws.Services;
import org.springframework.stereotype.Service;
@Service
public class ConfirmationService {
        public final String server = "http://localhost:8080";
        public boolean confirmAction(String action) {
            // Add logic for confirmation here if needed
            // For example, always return true for simplicity
            return true; // You can customize this based on your needs
        }
}

