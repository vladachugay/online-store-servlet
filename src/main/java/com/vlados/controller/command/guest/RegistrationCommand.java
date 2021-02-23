package com.vlados.controller.command.guest;

import com.vlados.controller.command.Command;
import com.vlados.controller.util.Validator;
import com.vlados.model.dto.UserDTO;
import com.vlados.model.exception.StoreException;
import com.vlados.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class RegistrationCommand implements Command {

    private static final String ERROR = "/WEB-INF/guest/registration.jsp";
    private static final String REDIRECT_LOGIN = "redirect:/login";

    private final UserService userService;

    public RegistrationCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        UserDTO userDTO = UserDTO.builder()
                .fullName(request.getParameter("full_name"))
                .username(request.getParameter("username"))
                .email(request.getParameter("email"))
                .phoneNumber(request.getParameter("phone_number"))
                .password(request.getParameter("password"))
                .build();

        Map<String, String> errors = Validator.validateUser(userDTO);

        //TODO add constants for redirect/error
        if (errors.size() > 0) {
            request.setAttribute("errors", errors);
            return ERROR;
        }
        //TODO log
        try {
            userService.saveUser(userDTO);
        } catch (StoreException e) {
            request.setAttribute("duplicate_error", e.getMessage());
            return ERROR;
        }
        return REDIRECT_LOGIN;
    }
}
