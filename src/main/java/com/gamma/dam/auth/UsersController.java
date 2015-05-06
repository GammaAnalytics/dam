package com.gamma.dam.auth;

import com.gamma.dam.exceptions.DAMAccountException;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UsersController {

	/** The login model. */
    private LoginModel loginModel;

	/**
	 * Inits the.
	 *
	 * @param request the request
	 * @param response the response
	 * @return the model and view
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView init(HttpServletRequest request, HttpServletResponse response) {
		ApplicationModel applicationModel = new ApplicationModel();
		try{
			loginModel = (LoginModel) request.getSession().getAttribute("user");
			applicationModel.setLoginModel(loginModel);
			Map<String, String> appVar = new HashMap<String, String>();
			appVar.put("username", loginModel.getPasswd());
			return new ModelAndView("home", appVar);
		}catch(Exception e){
			return new ModelAndView("login");
		}
	}
	
	@RequestMapping(value = "/userPasswordReset", method = RequestMethod.POST)
	public ModelAndView resetPassword(@RequestParam String password_reset) {
		ModelAndView mav = null;
		boolean rt = new LoginHandler().reset(password_reset);
		mav = null;
		if (rt) {
			RedirectView redirectView = new RedirectView("login", true);
			mav = new ModelAndView(redirectView);
			Subject currentSubject = SecurityUtils.getSubject();
			currentSubject.logout();
		} else {
			mav = new ModelAndView("reset");
			mav.addObject("errorMessage", "Invalid Credentials");
		}
		return mav;
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public @ResponseBody
    String changePassword(@RequestParam String password,
			@RequestParam String password_reset) {
		JSONObject json = new JSONObject();
		String message, code;
		try{
			new LoginHandler().changePassword(password, password_reset);
			code = "200";
			message = "Password Changed Successfully";
		}catch(DAMAccountException e){
			code = "400";
			message =  e.getMessage();
		}
		json.put("code", code);
		json.put("message", message);
		return json.toString();
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView admin(HttpServletRequest request,
			HttpServletResponse response) {
		Subject subject = SecurityUtils.getSubject();
		if (subject.hasRole("ROLE_ADMIN")) {
			return new ModelAndView("admin");
		}
		return null;
	}
	
	@RequestMapping(value = "/forgotPassword", method = RequestMethod.GET)
	public String forgotPassword(HttpServletRequest request,
			HttpServletResponse response) {
		return "forgotpassword";
	}
	
	@RequestMapping(value = "/emailPassword", method = RequestMethod.POST)
	public @ResponseBody String  emailMePassword(@RequestParam String username){
		//ModelAndView mav = new ModelAndView("forgotpassword");

		JSONObject data = new JSONObject();
		try{
			new LoginHandler().emailPassword(username);
			data.put("successMessage", "Password Sent to the Registered Email Account");
		}catch(DAMAccountException e){
			data.put("errorMessage", e.getMessage());
		}
		return data.toString();
	}
}
