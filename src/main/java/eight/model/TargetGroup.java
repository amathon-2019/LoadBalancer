package eight.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TARGET_GROUP")
public class TargetGroup {
	@Id
	@Column(name = "SERVER_NAME", length = 20, nullable = false)
	String serverName;

	@Column(name = "IN_BOUND_PORT", length = 10, nullable = false)
	int inboundPort;

	@Column(name = "OUT_BOUND_PORT", length = 10, nullable = false)
	int outboundPort;

	@Column(name = "HOST", length = 20, nullable = false)
	String host;

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public int getInboundPort() {
		return inboundPort;
	}

	public void setInboundPort(int inboundPort) {
		this.inboundPort = inboundPort;
	}

	public int getOutboundPort() {
		return outboundPort;
	}

	public void setOutboundPort(int outboundPort) {
		this.outboundPort = outboundPort;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}
}
