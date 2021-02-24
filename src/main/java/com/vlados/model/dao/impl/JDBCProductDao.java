package com.vlados.model.dao.impl;

import com.vlados.model.dao.ProductDao;
import com.vlados.model.dao.impl.query.ProductQueries;
import com.vlados.model.dao.mapper.ProductMapper;
import com.vlados.model.entity.Product;
import com.vlados.model.entity.SortCriteria;
import com.vlados.model.util.Page;
import com.vlados.model.util.Pageable;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCProductDao implements ProductDao {
    private final Connection connection;
    private final ProductMapper productMapper = new ProductMapper();

    public JDBCProductDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean reduceAmountById(long id, int quantity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(ProductQueries.REDUCE_AMOUNT)) {
            preparedStatement.setInt(1, quantity);
            preparedStatement.setLong(2, id);
            return preparedStatement.execute();
        } catch (SQLException e) {
            System.err.println("cant reduce amount");
        }
        return false;
    }

    @Override
    public boolean increaseAmountById(long id, int quantity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(ProductQueries.INCREASE_AMOUNT)) {
            preparedStatement.setInt(1, quantity);
            preparedStatement.setLong(2, id);
            return preparedStatement.execute();
        } catch (SQLException e) {
            System.err.println("cant increase amount");
        }
        return false;
    }


    @Override
    public boolean create(Product product) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(ProductQueries.CREATE)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setBigDecimal(2, product.getPrice());
            preparedStatement.setString(3, product.getCategory().name());
            preparedStatement.setString(4, product.getMaterial().name());
            preparedStatement.setString(5, product.getPicPath());
            preparedStatement.setTimestamp(6, Timestamp.valueOf(product.getDate()));
            preparedStatement.setString(7, product.getDescription());
            preparedStatement.setInt(8, product.getAmount());
            System.out.println("dao statement: " + preparedStatement);
            return preparedStatement.execute();
        } catch (SQLException e) {
            //TODO log
            System.err.println("cant add new product");
            throw  new RuntimeException();
        }
    }

    @Override
    public Optional<Product> findById(long id) {
        Product product = null;
        try (PreparedStatement statement = connection.prepareStatement(ProductQueries.FIND_BY_ID)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                product = productMapper.extractFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.err.println("cant find product");
            throw new RuntimeException();
        }
        return Optional.ofNullable(product);
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ProductQueries.FIND_ALL)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                products.add(productMapper.extractFromResultSet(rs));
            }
        } catch (SQLException ex) {
            //TODO log
            //TODO handle exception
            System.err.println(ex.getMessage());
        }
        return products;
    }

    @Override
    public Page<Product> findFiltered(Pageable pageable, String sortCriteria, String category,
                                      String material, BigDecimal priceFrom, BigDecimal priceTo) {
        List<Product> products = new ArrayList<>();
        String query = generateQuery(SortCriteria.valueOf(sortCriteria), category, material, priceFrom, priceTo);
        System.out.println(query);
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                products.add(productMapper.extractFromResultSet(rs));
            }
        } catch (SQLException ex) {
            //TODO log
            //TODO handle exception
            System.err.println(ex.getMessage());
        }

        int firstProductInPageIndex = pageable.getSizeOfPage() * pageable.getCurrentPage();
        int lastProductInPageIndex = Math.min(products.size(), firstProductInPageIndex + pageable.getSizeOfPage());

        List<Product> productsForPage = products.subList(firstProductInPageIndex, lastProductInPageIndex);
        return new Page<>((int) Math.ceil((double) products.size() / pageable.getSizeOfPage()), productsForPage);

    }

    @Override
    public boolean update(Product product) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(ProductQueries.UPDATE)) {
            System.out.println(product);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setBigDecimal(2, product.getPrice());
            preparedStatement.setString(3, product.getCategory().name());
            preparedStatement.setString(4, product.getMaterial().name());
            preparedStatement.setString(5, product.getPicPath());
            preparedStatement.setString(6, product.getDescription());
            preparedStatement.setInt(7, product.getAmount());
            preparedStatement.setLong(8, product.getId());
            System.out.println(preparedStatement);
            return preparedStatement.execute();
        } catch (SQLException e) {
            //TODO log
            throw new RuntimeException();
        }
    }

    @Override
    public boolean delete(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(ProductQueries.DELETE_BY_ID)) {
            preparedStatement.setLong(1, id);
            System.out.println(preparedStatement);
            return preparedStatement.execute();
        } catch (SQLException ex) {
            //TODO log
            throw new RuntimeException();
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateQuery(SortCriteria sortCriteria, String category,
                                 String material, BigDecimal priceFrom, BigDecimal priceTo) {
        StringBuilder query = new StringBuilder(ProductQueries.FIND_ALL)
                .append(ProductQueries.WHERE)
                .append(ProductQueries.PRICE_BETWEEN)
                .append(priceFrom)
                .append(ProductQueries.AND)
                .append(priceTo);

        if (!"ALL".equals(category) && !"ALL".equals(material)) {
            query.append(ProductQueries.AND)
                    .append(ProductQueries.CATEGORY_EQUALS)
                    .append(ProductQueries.QUOTE)
                    .append(category)
                    .append(ProductQueries.QUOTE)
                    .append(ProductQueries.AND)
                    .append(ProductQueries.MATERIAL_EQUALS)
                    .append(ProductQueries.QUOTE)
                    .append(material)
                    .append(ProductQueries.QUOTE);
        } else if ("ALL".equals(material) && !"ALL".equals(category)) {
            query.append(ProductQueries.AND)
                    .append(ProductQueries.CATEGORY_EQUALS)
                    .append(ProductQueries.QUOTE)
                    .append(category)
                    .append(ProductQueries.QUOTE);
        } else if (!"ALL".equals(material)) {
            query.append(ProductQueries.AND)
                    .append(ProductQueries.MATERIAL_EQUALS)
                    .append(ProductQueries.QUOTE)
                    .append(material)
                    .append(ProductQueries.QUOTE);
        }

        switch (sortCriteria) {
            case PRICE_LOW_TO_HIGH:
                query.append(ProductQueries.ORDER_BY_PRICE_ASC);
                break;
            case PRICE_HIGH_TO_LOW:
                query.append(ProductQueries.ORDER_BY_PRICE_DESC);
                break;
            case NEW_IN:
                query.append(ProductQueries.ORDER_BY_NEW_IN);
                break;
            case BY_NAME_DESC:
                query.append(ProductQueries.ORDER_BY_NAME_DESC);
                break;
            default:
                query.append(ProductQueries.ORDER_BY_NAME_ASC);
        }
        return query.toString();
    }
}
