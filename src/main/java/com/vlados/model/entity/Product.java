package com.vlados.model.entity;

import com.vlados.model.dto.ProductDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Product {
    private Long id;
    private String name;
    private Category category;
    private Material material;
    private String picPath;
    private LocalDateTime date;
    private String description;
    private BigDecimal price;
    private int amount;

    public Product(ProductDTO productDTO) {
        id = productDTO.getId();
        name = productDTO.getName();
        category = Category.valueOf(productDTO.getCategory());
        price = productDTO.getPrice();
        amount = productDTO.getAmount();
        picPath = productDTO.getPicPath();
        material = Material.valueOf(productDTO.getMaterial());
        description = productDTO.getDescription();
        date = productDTO.getDate();
    }

    public Product(Long id, String name, Category category, Material material,
                   String picPath, LocalDateTime date, String description,
                   BigDecimal price, int amount) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.material = material;
        this.picPath = picPath;
        this.date = date;
        this.description = description;
        this.price = price;
        this.amount = amount;
    }

    public enum Category {
        RING,
        NECKLACE,
        BRACELET,
        EARRINGS
    }

    public enum Material {
        PLATINUM,
        YELLOW_GOLD,
        PINK_GOLD,
        WHITE_GOLD,
        DIAMONDS
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return amount == product.amount &&
                id.equals(product.id) &&
                name.equals(product.name) &&
                category == product.category &&
                material == product.material &&
                Objects.equals(picPath, product.picPath) &&
                Objects.equals(date, product.date) &&
                Objects.equals(description, product.description) &&
                price.equals(product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, category, material, picPath, date, description, price, amount);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", material=" + material +
                ", picPath='" + picPath + '\'' +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                '}';
    }

    public static Product.ProductBuilder builder() {
        return new Product.ProductBuilder();
    }

    public static class ProductBuilder {
        private long id;
        private String name;
        private Category category;
        private Material material;
        private String picPath;
        private LocalDateTime date;
        private String description;
        private BigDecimal price;
        private int amount;

        public ProductBuilder() {
        }


        public Product.ProductBuilder id(final long id) {
            this.id = id;
            return this;
        }

        public Product.ProductBuilder name(final String name) {
            this.name = name;
            return this;
        }

        public Product.ProductBuilder category(final Category category) {
            this.category = category;
            return this;
        }

        public Product.ProductBuilder material(final Material material) {
            this.material = material;
            return this;
        }

        public Product.ProductBuilder picPath(final String picPath) {
            this.picPath = picPath;
            return this;
        }

        public Product.ProductBuilder date(final LocalDateTime date) {
            this.date = date;
            return this;
        }

        public Product.ProductBuilder description(final String description) {
            this.description = description;
            return this;
        }

        public Product.ProductBuilder price(final BigDecimal price) {
            this.price = price;
            return this;
        }

        public Product.ProductBuilder amount(final int amount) {
            this.amount = amount;
            return this;
        }

        public Product build() {
            return new Product(id, name, category, material, picPath, date, description, price, amount);
        }
    }
}
