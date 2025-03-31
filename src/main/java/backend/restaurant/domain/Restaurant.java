package backend.restaurant.domain;

import jakarta.persistence.*;


@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String address;
    private String priceRange;
    private Integer rating;
    private String text;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private FoodCategory category;

    private String imageUrl;

    public Restaurant() {
    }

    public Restaurant(String name, String address, String priceRange, Integer rating, String text, FoodCategory category, String imageUrl) {
        this.name = name;
        this.address = address;
        this.priceRange = priceRange;
        this.rating = rating;
        this.text = text;
        this.category = category;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public FoodCategory getCategory() {
        return category;
    }

    public void setCategory(FoodCategory category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Restaurant [id=" + id + ", name=" + name + ", address=" + address + ", priceRange=" + priceRange
                + ", rating=" + rating + ", text=" + text + ", category=" + category + "]";
    }
}
