package com.example.lab08_1;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static jakarta.servlet.RequestDispatcher.ERROR_STATUS_CODE;

@Controller
public class ErrorControllerCustom implements ErrorController {
    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String errorPage(HttpServletRequest httpServletRequest, Model model) {
        String errorMessage = "";
        int httpErrorCode = getErrorCode(httpServletRequest);

        switch (httpErrorCode) {
            case 400: {
                errorMessage = "Http Error code: 400. Bad Request";
                break;
            }
            case 401: {
                errorMessage = "Http Error code: 401. Unauthorized";
                break;
            }
            case 404: {
                errorMessage = "Http Error code: 404. Resource not found";
                break;
            }
            case 500: {
                errorMessage = "Http Error code: 500. Internet Server Error";
                break;
            }
            default:
                errorMessage = "Error";
                break;
        }
        model.addAttribute("errorMessage", errorMessage);
        return "errorPage";
    }

    private int getErrorCode(HttpServletRequest httpServletRequest){
        Object errorCode = httpServletRequest.getAttribute(ERROR_STATUS_CODE);
        return errorCode == null ? 0 : (Integer) errorCode;
    }
}
