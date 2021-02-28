package com.vlados.model.tag;

import lombok.extern.log4j.Log4j2;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Log4j2
public class CustomTagHandler extends BodyTagSupport {

    @Override
    public int doEndTag() {
        try {
            JspWriter writer = pageContext.getOut();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime date = LocalDateTime.parse(getBodyContent().getString().trim());

            writer.print(date.format(formatter));
        } catch (Exception exception) {
            log.error("{} while trying to parse date in custom tag", exception.getMessage());
            throw new RuntimeException();
        }
        return BodyTagSupport.SKIP_BODY;
    }
}
