package eight.domain.balancer;

import javax.servlet.http.HttpServletResponse;

public interface NodeBalancer {
	HttpServletResponse execute();
}
