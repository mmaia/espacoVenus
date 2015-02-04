package br.com.espacovenus.seguranca;

import org.apache.shiro.authc.HostAuthenticationToken;
import org.apache.shiro.authc.RememberMeAuthenticationToken;

public class UserNameOnlyToken implements HostAuthenticationToken, RememberMeAuthenticationToken {

	private static final long serialVersionUID = -6389983542148398841L;
	
	/**
     * The username
     */
    private String username;
    
    /**
     * Whether or not 'rememberMe' should be enabled for the corresponding login attempt;
     * default is <code>false</code>
     */
    private boolean rememberMe = false;

    /**
     * The location from where the login attempt occurs, or <code>null</code> if not known or explicitly
     * omitted.
     */
    private String host;
    
    //constructors
    public UserNameOnlyToken(){}
    
    public UserNameOnlyToken(final String username, final boolean rememberMe, final String host)
    {
    	  this.username = username;
          this.rememberMe = rememberMe;
          this.host = host;
    }

	@Override
	public Object getPrincipal() {
		return getUsername();
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public boolean isRememberMe() {
		return rememberMe;
	}

	@Override
	public String getHost() {
		return host;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	public void setHost(String host) {
		this.host = host;
	}
	
	
	public void clear() {
        this.username = null;
        this.host = null;
        this.rememberMe = false;
    }

	@Override
	public String toString() {
		return "UserNameOnlyToken [username=" + username + ", rememberMe="
				+ rememberMe + ", host=" + host + "]";
	}
	
	
}
