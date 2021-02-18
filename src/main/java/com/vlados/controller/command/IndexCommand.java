package com.vlados.controller.command;

import javax.servlet.http.HttpServletRequest;

public class IndexCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/guest/index.jsp";
    }
}
