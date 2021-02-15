package com.vlados.controller.command.guest;

import com.vlados.controller.command.Command;
import com.vlados.model.dto.UserDTO;
import com.vlados.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class RegistrationCommand implements Command {
    private UserService userService;

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
        //TODO validate
        //TODO log
        userService.saveUser(userDTO);
        return "redirect:/login";
    }
}
