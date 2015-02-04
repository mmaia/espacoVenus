package br.com.espacovenus.seguranca.servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.brickred.socialauth.AuthProvider;
import org.brickred.socialauth.Profile;
import org.brickred.socialauth.SocialAuthManager;
import org.brickred.socialauth.cdi.SocialAuth;
import org.brickred.socialauth.util.SocialAuthUtil;

import br.com.espacovenus.model.Role;
import br.com.espacovenus.model.User;
import br.com.espacovenus.seguranca.UserNameOnlyToken;
import br.com.espacovenus.service.UserService;

/**
 * Servlet implementation class ServletSeguranca
 */
@WebServlet(name = "Seguranca", urlPatterns = { "/Seguranca" })
public class ServletSeguranca extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final static Logger log = Logger.getLogger(ServletSeguranca.class
			.getName());

	@Inject
	SocialAuth socialauth;
	
	@Inject
	User user;
	
	@EJB
    private UserService userService;
	
	@PersistenceUnit(unitName="espacoVenus")
	private EntityManagerFactory emf;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletSeguranca() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.info("Executando ServletSeguranca.doPost()");
		try {
			SocialAuthManager manager = (SocialAuthManager) request
					.getSession().getAttribute("authManager");
			if (manager != null) {
				log.info("SocialAuthManager recuperado com sucesso");

				// call connect method of manager which returns the provider
				// object.
				// Pass request parameter map while calling connect method.
				AuthProvider provider = manager.connect(SocialAuthUtil
						.getRequestParametersMap(request));
				
				// get profile
				Profile p = provider.getUserProfile();

				// you can obtain profile information
				log.info(p.getFirstName());
				log.info(p.getFullName());
				log.info(p.getEmail());
				
				//Verifica se usuario já fez algum login no sistema anteriormente, se já está cadastrado
				EntityManager em = emf.createEntityManager();
				Query query= em.createNamedQuery("user.find.social");
				query.setParameter("username", p.getEmail());
				
				@SuppressWarnings("unchecked")
				List<User> userList = query.getResultList();
				if(userList.size() < 1)
				{
					log.info("Usuario ainda não cadastrado no sistema, cadastrar e autenticar no Shiro");
					user.setUsername(p.getEmail());
					user.setRole(Role.PACIENTE);
					userService.create(user);
					
					
					Subject subject = SecurityUtils.getSubject();
					UserNameOnlyToken upt = new UserNameOnlyToken();
					upt.setUsername(p.getEmail());
					upt.setRememberMe(true);
					subject.login(upt);
					
					boolean authenticated = subject.isAuthenticated();
					if(!authenticated)
					{
						log.info("Shiro não tem um principal associado ainda...");

					}
					else
					{
						log.info("Shiro tem um principal...");
						subject = SecurityUtils.getSubject();
						if(subject.hasRole("PACIENTE"))
						{
							log.info("Role PACIENTE");
						}
						if(subject.hasRole("ADMINISTRADOR"))
						{
							log.info("Role ADMINISTRADOR");
						}
						if(subject.hasRole("NUTRICIONISTA"))
						{
							log.info("Role NUTRICIONISTA");
						}
					}
				}
				else{
					log.info("Usuario ja cadastrado no sistema, autenticar no Shiro agora :) ");
					
					Subject subject = SecurityUtils.getSubject();
					boolean authenticated = subject.isAuthenticated();
					
					if(! authenticated)
					{
						log.info("Shiro não tem um principal associado ainda...");
						UserNameOnlyToken upt = new UserNameOnlyToken();
						upt.setUsername(p.getEmail());
						upt.setRememberMe(true);
						subject.login(upt);
					}
					else
					{
						log.info("Shiro tem um principal...");
						subject = SecurityUtils.getSubject();
						if(subject.hasRole("PACIENTE"))
						{
							log.info("Role PACIENTE");
						}
						if(subject.hasRole("ADMINISTRADOR"))
						{
							log.info("Role ADMINISTRADOR");
						}
						if(subject.hasRole("NUTRICIONISTA"))
						{
							log.info("Role NUTRICIONISTA");
						}
					}
				}
				
			} else {
				log.error("SocialAuthManager não recuperado da sessão, erro!");
			}
		} catch (Exception e) {
			log.fatal(e.getMessage());
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/web/index.xhtml").forward(request, response);

	}

}
