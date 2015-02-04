package br.com.espacovenus.seguranca;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.brickred.socialauth.SocialAuthConfig;
import org.brickred.socialauth.SocialAuthManager;
import org.brickred.socialauth.cdi.SocialAuth;
import org.brickred.socialauth.util.SocialAuthUtil;

/**
 * Classe que faz a autenticação Social do usuário Facebook, google, etc.
 * 
 */
@SessionScoped
@Named("socialauthenticator")
public class Authenticator implements Serializable {

	private static final long serialVersionUID = -8505934290529716503L;

	private static final Logger log = Logger.getLogger(Authenticator.class);

	/**
	 * Variable for storing open id from main form
	 */
	private String openID;

	private HttpSession session;

	/**
	 * Track the user interaction with main page and set the state of components
	 * accordingly.
	 * 
	 * @param ActionEvent
	 */
	public void login() {

		log.info("*************login method called ************");

		openID = "facebook";

		ExternalContext context = FacesContext.getCurrentInstance()
				.getExternalContext();

		// URL of YOUR application which will be called after authentication
		String successUrl = context.getInitParameter("successUrl");

		// Create an instance of SocialAuthManager and set config
		SocialAuthManager manager = new SocialAuthManager();

		// Create an instance of SocialAuthConfgi object
		SocialAuthConfig config = SocialAuthConfig.getDefault();

		// load configuration. By default load the configuration from
		// oauth_consumer.properties.
		// You can also pass input stream, properties object or properties file
		// name.
		try {
			config.load();
			manager.setSocialAuthConfig(config);

			// get Provider URL to which you should redirect for authentication.
			// id can have values "facebook", "twitter", "yahoo" etc. or the
			// OpenID URL
			String sUrl = manager.getAuthenticationUrl(openID, successUrl);

			session = (HttpSession) context.getSession(true);

			// Store in session
			session.setAttribute("authManager", manager);
			
			context.redirect(sUrl);

		} catch (Exception e) {
			log.error(e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static Map<String, String> getQueryParams(String url) {
		log.info("recuperando parametros da URL em um Map");
		try {
			Map<String, String> params = new HashMap<String, String>();
			String[] urlParts = url.split("\\?");
			if (urlParts.length > 1) {
				String query = urlParts[1];
				for (String param : query.split("&")) {
					String[] pair = param.split("=");
					String key = URLDecoder.decode(pair[0], "UTF-8");
					String value = "";
					if (pair.length > 1) {
						value = URLDecoder.decode(pair[1], "UTF-8");
					}

					String values = params.get(key);
					if (values == null) {
						values = new String();
						params.put(key, values);
					}
				}
			}

			return params;
		} catch (UnsupportedEncodingException ex) {
			throw new AssertionError(ex);
		}
	}

	/**
	 * Redirect the user back to the main page from success view.
	 * 
	 * @param ActionEvent
	 */
	public String mainPage() {
		return "/home.xhtml";
	}

	public String getOpenID() {
		return openID;
	}

	public void setOpenID(String openID) {
		this.openID = openID;
	}
}