package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import entity.AdminDetails;

@Controller
public class controller {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	HttpServletRequest request;
	
	@PostMapping("/loginValidation")
	@Transactional
	public String adminLogin(@ModelAttribute("theadmin") AdminDetails theadmin, Model theModel) throws Exception {
		
		System.out.println("Reached login validation");
		
		System.out.println(theadmin.getUsername());
		System.out.println(theadmin.getPassword());
		
		Session currentSession=sessionFactory.getCurrentSession();
		AdminDetails obj=currentSession.get(AdminDetails.class, theadmin.getUsername());
		
		System.out.println("obj= "+obj);
		
		if(obj!=null)
			System.out.println("obj username= "+obj.getUsername());
		
		
		if(obj!=null)
		{
			EncryptPassword ep=new EncryptPassword();
			if((ep.encrypt(theadmin.getPassword())).compareTo(obj.getPassword())==0)
			{
				HttpSession session=request.getSession();
				session.setAttribute("adminusername",obj.getUsername());
				return "adminPage";
			}
		}
		
		theModel.addAttribute("loginerror", "Invalid Credentials!");
		return "main";
	}
	
	@PostMapping("/addAdmin")
	@Transactional
	public String addAdmin(@ModelAttribute("newadmin") AdminDetails theadmin, Model theModel) throws Exception {
		
		System.out.println("Reached addAdmin");
		
		System.out.println(theadmin.getFirstname());
		System.out.println(theadmin.getLastname());
		System.out.println(theadmin.getUsername());
		System.out.println(theadmin.getPassword());
		String password=theadmin.getPassword();
		
		EncryptPassword ep=new EncryptPassword();
		theadmin.setPassword(ep.encrypt(theadmin.getPassword()));
		
		Session currentSession=sessionFactory.getCurrentSession();
		AdminDetails obj=currentSession.get(AdminDetails.class, theadmin.getUsername());
		if(obj==null)
			currentSession.save(theadmin);       //if no same username found then create new user
		else
		{
			theModel.addAttribute("firstname", theadmin.getFirstname());
			theModel.addAttribute("lastname", theadmin.getLastname());
			theModel.addAttribute("username", theadmin.getUsername());
			theModel.addAttribute("password", password);
			theModel.addAttribute("error", "Username exists");
			return "adminPage";
		}
		
		theModel.addAttribute("success", "New user created!");
		return "adminPage";
		
	}
	
	@RequestMapping("/logout")
	public String logout()
	{
		HttpSession session=request.getSession();
		session.removeAttribute("adminusername");
		session.invalidate();
		
		return "main";
	}

}
