package com.vlados.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductDTO {
    private String name;
    private String category;
    private String material;
    private String picPath;
    private LocalDateTime date;
    private String description;
    private BigDecimal price;
    private int amount;


    public static ProductDTO.ProductDTOBuilder builder() {
        return new ProductDTO.ProductDTOBuilder();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "name='" + name + '\'' +
                ", category=" + category +
                ", material=" + material +
                ", picPath='" + picPath + '\'' +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                '}';
    }

    public ProductDTO(final String name, final String category, final String material,
                      final String picPath, final LocalDateTime date, final String description,
                      final BigDecimal price, final Integer amount) {
        this.name = name;
        this.category = category;
        this.material = material;
        this.picPath = picPath;
        this.date = date;
        this.description = description;
        this.price = price;
        this.amount = amount;
    }

    public ProductDTO() {}

    public static class ProductDTOBuilder {
        private String name;
        private String category;
        private String material;
        private String picPath;
        private LocalDateTime date;
        private String description;
        private BigDecimal price;
        private int amount;

        public ProductDTOBuilder() {
        }


        public ProductDTO.ProductDTOBuilder name(final String name) {
            this.name = name;
            return this;
        }

        public ProductDTO.ProductDTOBuilder category(final String category) {
            this.category = category;
            return this;
        }

        public ProductDTO.ProductDTOBuilder material(final String material) {
            this.material = material;
            return this;
        }

        public ProductDTO.ProductDTOBuilder picPath(final String picPath) {
            this.picPath = picPath;
            return this;
        }

        public ProductDTO.ProductDTOBuilder date(final LocalDateTime date) {
            this.date = date;
            return this;
        }

        public ProductDTO.ProductDTOBuilder description(final String description) {
            this.description = description;
            return this;
        }

        public ProductDTO.ProductDTOBuilder price(final BigDecimal price) {
            this.price = price;
            return this;
        }

        public ProductDTO.ProductDTOBuilder amount(final int amount) {
            this.amount = amount;
            return this;
        }

        public ProductDTO build() {
            return new ProductDTO(name, category, material, picPath, date, description, price, amount);
        }
    }
}
