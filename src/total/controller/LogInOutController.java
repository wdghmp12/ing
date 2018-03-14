package total.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import total.service.FindService;

@Controller
public class LogInOutController {
	@Autowired
	FindService FindService;
	
	
	@RequestMapping(path="/login", method=RequestMethod.GET)
	public String loginGetHandle(HttpSession session) {
		if(session.getAttribute("logon") == null) {
			return "login";
		}else {
			return "redirect:/";
		}
	}
	
	@RequestMapping(path="/session", method=RequestMethod.POST)
	public String loginPostHandle(@RequestParam Map<String, String> param, HttpSession session, Model model) {
		
		Map rst = FindService.findByIdMailAndPass(param);
		if(rst != null) {
			session.setAttribute("logon", rst.get("ID"));
			return "redirect:/";
		} else {
			model.addAttribute("err", "logon failed");
			return "login";
		}
	}
	
	@RequestMapping(path="/logout", method=RequestMethod.GET)
	public String logoutPostHandle(HttpSession session) {
		session.removeAttribute("logon");
		return "redirect:/";
	}
	
}
