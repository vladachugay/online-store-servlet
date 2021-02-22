package com.vlados.controller.command;

import com.vlados.model.entity.Product;
import com.vlados.model.entity.SortCriteria;
import com.vlados.model.service.ProductService;
import com.vlados.model.util.Page;
import com.vlados.model.util.Pageable;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GetProductsCommand implements Command {
    private final ProductService productService;

    public GetProductsCommand(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String chosenSortCriteria = Optional.ofNullable(request.getParameter("sortcriteria")).orElse(SortCriteria.BY_NAME_ASC.name());
        String chosenCategory = Optional.ofNullable(request.getParameter("category")).orElse("ALL");
        String chosenMaterial = Optional.ofNullable(request.getParameter("material")).orElse("ALL");
        BigDecimal chosenPriceFrom = new BigDecimal(Optional.ofNullable(request.getParameter("price_from")).orElse("0"));
        BigDecimal chosenPriceTo = new BigDecimal(Optional.ofNullable(request.getParameter("price_to")).orElse("100000"));

        int currentPage = Integer.parseInt(Optional.ofNullable(request.getParameter("page")).orElse("1"));
        int pageSize = Integer.parseInt(Optional.ofNullable(request.getParameter("size")).orElse("3"));

        Page<Product> productPage = productService.getFilteredProducts(new Pageable(currentPage - 1, pageSize),
                chosenSortCriteria, chosenCategory, chosenMaterial, chosenPriceFrom, chosenPriceTo);

        request.setAttribute("products", productPage.getContent());

        int totalPages = productPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            request.setAttribute("pageNumbers", pageNumbers);
        }

        request.setAttribute("sorting", SortCriteria.values());
        request.setAttribute("materials", Product.Material.values());
        request.setAttribute("categories", Product.Category.values());
        request.setAttribute("chosen_sortcriteria", chosenSortCriteria);
        request.setAttribute("chosen_category", chosenCategory);
        request.setAttribute("chosen_material", chosenMaterial);
        request.setAttribute("chosen_price_from", chosenPriceFrom);
        request.setAttribute("chosen_price_to", chosenPriceTo);
        request.setAttribute("size", pageSize);
        request.setAttribute("currentPage", currentPage);

        return "/WEB-INF/" + request.getSession().getAttribute("role").toString().toLowerCase() + "/products.jsp";
    }
}
