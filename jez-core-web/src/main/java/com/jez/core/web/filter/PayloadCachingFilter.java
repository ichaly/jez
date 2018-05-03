package com.jez.core.web.filter;

import com.jez.core.persistence.enums.RequestMethod;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.context.request.async.WebAsyncUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;

/**
 * Created by JEZ on 2017/7/15.
 */
public class PayloadCachingFilter extends OncePerRequestFilter {

  private static final int DEFAULT_MAX_PAYLOAD_LENGTH = 50;

  private int maxPayloadLength = DEFAULT_MAX_PAYLOAD_LENGTH;

  private static final Set<String> ACCEPT_METHODS = Stream
      .of(RequestMethod.POST.name(), RequestMethod.PUT.name()).collect(Collectors.toSet());

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    boolean isAccept = isAccept(request);
    HttpServletRequest requestToUse = request;
    if (isAccept && !(request instanceof ContentCachingRequestWrapper)) {
      requestToUse = new ContentCachingRequestWrapper(request, getMaxPayloadLength());
    }
    filterChain.doFilter(requestToUse, response);
  }

  protected boolean isAccept(HttpServletRequest request) {
    return isUriAccept(request.getRequestURI()) && isMethodAccept(request.getMethod().toUpperCase())
        && !isAsyncDispatch(request);
  }

  protected boolean isUriAccept(String uri) {
    return true;
  }

  protected boolean isMethodAccept(String method) {
    return ACCEPT_METHODS.contains(method);
  }

  protected boolean isAsyncDispatch(HttpServletRequest request) {
    return WebAsyncUtils.getAsyncManager(request).hasConcurrentResult();
  }

  public int getMaxPayloadLength() {
    return maxPayloadLength;
  }

  public void setMaxPayloadLength(int maxPayloadLength) {
    this.maxPayloadLength = maxPayloadLength;
  }

}
