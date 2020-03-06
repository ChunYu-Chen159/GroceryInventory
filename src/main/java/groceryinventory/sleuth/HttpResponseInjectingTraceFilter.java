package groceryinventory.sleuth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.cloud.sleuth.instrument.web.HttpSpanInjector;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class HttpResponseInjectingTraceFilter extends GenericFilterBean {

    private final Tracer tracer;

    @Value("${info.version}")
    private String version;
    @Value("${spring.application.name}")
    private String appName;

    public HttpResponseInjectingTraceFilter(Tracer tracer, HttpSpanInjector spanInjector) {
        this.tracer = tracer;
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Span currentSpan = this.tracer.getCurrentSpan();
        if (currentSpan == null) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        ((HttpServletResponse) servletResponse).addHeader("ZIPKIN-TRACE-ID", Long.toString(currentSpan.getTraceId()));
        currentSpan.tag("http.version", version);
        currentSpan.tag("http.appName", appName);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
