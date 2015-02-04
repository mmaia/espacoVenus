package br.com.espacovenus.seguranca;

import java.util.logging.Logger;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * This class creates a custom shiro realm to allow the user who has already authenticated using the social login 
 * to have it's authorization still controlled by shiro 
 * @author mmaia
 */
public class CustomShiroRealm extends JdbcRealm {

	Logger logger = Logger.getLogger(CustomShiroRealm.class.getName());
	
	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		
		if(token instanceof UserNameOnlyToken)
		{
			logger.info("Processando UserNameOnlyToken");
			AuthenticationInfo ai = new SimpleAuthenticationInfo(token.getPrincipal(), "", "customShiroRealm");
		return ai;
		}
		else{
			logger.info("retornando null para autenticacao já que este realm não deve ser usado neste caso");
			return null;
		}
	}
}
