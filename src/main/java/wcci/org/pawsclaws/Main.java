package wcci.org.pawsclaws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main entry point of the Paws & Claws application.
 * <p>
 * This class initializes the Spring Boot application and starts the embedded
 * web server.
 * </p>
 */
@SpringBootApplication
public class Main {

    /**
     * The main method that launches the Paws & Claws application.
     * <p>
     * It uses {@link SpringApplication#run(Class, String...)} to bootstrap the
     * application and start the embedded server.
     * </p>
     *
     * @param args Command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args); // Start the Spring Boot application
    }

}
