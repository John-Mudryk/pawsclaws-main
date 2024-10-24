package wcci.org.pawsclaws.DTO;

/**
 * Data Transfer Object (DTO) for representing information about a cat image.
 * This class contains details such as the image's ID, URL, width, and height.
 */
public class CatImageDTO {
    private String id;
    private String url;
    private int width;
    private int height;

    /**
     * Constructor to initialize a CatImageDTO with specific attributes.
     *
     * @param id     The unique identifier of the cat image.
     * @param url    The URL where the cat image is located.
     * @param width  The width of the cat image.
     * @param height The height of the cat image.
     */
    public CatImageDTO(String id, String url, int width, int height) {
        this.id = id;
        this.url = url;
        this.width = width;
        this.height = height;
    }

    /**
     * Default constructor for CatImageDTO.
     */
    public CatImageDTO() {
        super();
    }

    /**
     * Gets the unique ID of the cat image.
     *
     * @return The ID of the cat image.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the unique ID of the cat image.
     *
     * @param id The ID to set for the cat image.
     */
    public void setId(String id) {
        this.id = id; // Assigns the provided ID to the cat image
    }

    /**
     * Gets the URL of the cat image.
     *
     * @return The URL of the cat image.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the URL of the cat image.
     *
     * @param url The URL to set for the cat image.
     */
    public void setUrl(String url) {
        this.url = url; // Assigns the provided URL to the cat image
    }

    /**
     * Gets the width of the cat image.
     *
     * @return The width of the cat image in pixels.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets the width of the cat image.
     *
     * @param width The width to set for the cat image in pixels.
     */
    public void setWidth(int width) {
        this.width = width; // Assigns the provided width to the cat image
    }

    /**
     * Gets the height of the cat image.
     *
     * @return The height of the cat image in pixels.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the height of the cat image.
     *
     * @param height The height to set for the cat image in pixels.
     */
    public void setHeight(int height) {
        this.height = height; // Assigns the provided height to the cat image
    }
}
