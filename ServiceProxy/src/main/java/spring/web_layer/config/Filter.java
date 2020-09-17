package spring.web_layer.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import spring.service_layer.services.jwt.JwtComponent;

import javax.servlet.http.HttpServletRequest;

@Component
public class Filter extends ZuulFilter {

    @Autowired
    private JwtComponent component;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();

        final String authHeader = request.getHeader("Authorization");
        String jwt;
        if (authHeader != null && authHeader.startsWith("Bearer "))
            jwt = authHeader.substring(7);
        else return null;

        context.addZuulRequestHeader("user-id", component.extractId(jwt).toString());

        return null;
    }
}