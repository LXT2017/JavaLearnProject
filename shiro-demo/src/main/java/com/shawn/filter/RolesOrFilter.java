package com.shawn.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.springframework.stereotype.Component;
@Component
public class RolesOrFilter extends AuthorizationFilter {

	@Override
	protected boolean isAccessAllowed(ServletRequest req,
			ServletResponse resp, Object object) throws Exception {
		
		Subject subject = getSubject(req, resp);
		
		String[] roles = (String[]) object;
		
		if (roles == null || roles.length == 0) {
			return true;
		}
		
		for (String role : roles) {
			if (subject.hasRole(role)) {
				return true;
			}
		}
		
		return false;
	}

}