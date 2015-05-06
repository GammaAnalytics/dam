package com.gamma.dam.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginController.
 *
 * @author abhijit
 */

@Controller
@RequestMapping(value = "/login")
public class LoginController {

	/** The logger. */
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	/** The login handler. */
	private LoginHandler loginHandler = new LoginHandler();
	
	/** The login model. */
	private LoginModel loginModel;

	/**
	 * Inits the.
	 *
	 * @return the model and view
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView init() {
		loginModel = new LoginModel();
		return new ModelAndView("login", "loginDetails", loginModel);
	}

	/**
	 * Login.
	 *
	 * @param loginModel the login model
	 * @param bindingResult the binding result
	 * @param request the request
	 * @param response the response
	 * @return the model and view
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("loginDetails") LoginModel loginModel, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {
		try {
			// Using Spring ValidationUtils class to check for empty fields.
			// This will add the error if any in the bindingResult object.
			ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult, "userId", "userId", "Username can not be empty.");
			ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult, "passwd", "passwd", "Password not be empty");

			if (bindingResult.hasErrors()) {
				// returning the errors on same page if any errors..
				return new ModelAndView("login", "loginDetails", loginModel);
			} else {
				// If the user details is validated then redirecting the user to
				// success page,
				// else returning the error message on login page.

				if (loginHandler.validate(loginModel)) {
					request.getSession().setAttribute("user", loginModel);
					// Creating a redirection view to success page. This will
					// redirect to UsersController

					RedirectView redirectView = new RedirectView("home", true);

					logger.info("Successfully Logged In !!!");
					return new ModelAndView(redirectView);
				} else {
					bindingResult.addError(new ObjectError("Invalid", "Invalid credentials. " + "Username or Password is incorrect."));
					return new ModelAndView("login", "loginDetails", loginModel);
				}
			}
		}
		catch (Exception e) {
			logger.info("Exception in LoginController " + e.getMessage());
			e.printStackTrace();
			return new ModelAndView("login", "loginDetails", loginModel);
		}
	}

}
