package com.vlados.controller.command;

import com.vlados.model.dto.ProductDTO;
import com.vlados.model.entity.Product;
import com.vlados.model.entity.User;
import com.vlados.model.exception.store_exc.login_exc.UserIsLockedException;
import com.vlados.model.util.ExceptionKeys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;

public class CommandUtility {

    public static void setUserRole(HttpServletRequest request, String username, User.Role role) {
        HttpSession session = request.getSession();
        session.setAttribute("username", username);
        session.setAttribute("role", role);
    }

    public static boolean checkUserIsLogged(HttpServletRequest request, String username) {
        @SuppressWarnings("unchecked")
        HashSet<String> loggedUsers = (HashSet<String>) request.getSession()
                .getServletContext()
                .getAttribute("loggedUsers");

        if (loggedUsers.stream().anyMatch(username::equals)) {
            return true;
        }
        loggedUsers.add(username);
        request.getSession().getServletContext().setAttribute("loggedUsers", loggedUsers);
        return false;
    }

    public static void setAttributesIfProblemsWithProductDTO(HttpServletRequest request, ProductDTO productDTO) {
        request.setAttribute("materials", Product.Material.values());
        request.setAttribute("categories", Product.Category.values());
        request.setAttribute("product", productDTO);
    }

    public static ProductDTO buildProductDtoFromRequest(HttpServletRequest request) {
        return ProductDTO.builder()
                .name(request.getParameter("name"))
                .amount(Integer.parseInt(request.getParameter("amount")))
                .category(Product.Category.valueOf(request.getParameter("category")))
                .material(Product.Material.valueOf(request.getParameter("material")))
                .date(LocalDateTime.now())
                .description(request.getParameter("description"))
                .price(new BigDecimal(request.getParameter("price")))
                .build();
    }
}
