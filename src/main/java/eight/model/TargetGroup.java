package eight.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TARGET_GROUP")
public class TargetGroup {
	@Column(name = "IN_BOUND_PORT", length = 10, nullable = false)
	Integer inBoundPort;

	@Column(name = "OUT_BOUND_PORT", length = 10, nullable = false)
	int outboundPort;

	@Id
	@Column(name = "HOST", length = 20, nullable = false)
	String host;

	@Column(name = "CONNECTION", length = 10, nullable = false)
	Integer connection = 0;

	public int getInboundPort() {
		return inBoundPort;
	}

	public void setInboundPort(int inboundPort) {
		this.inBoundPort = inboundPort;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getConnection() {
		return connection;
	}

	public void setConnection(Integer connection) {
		this.connection = connection;
	}
}
