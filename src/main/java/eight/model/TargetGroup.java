package eight.model;

import com.sun.javafx.beans.IDProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TARGET_GROUP")
public class TargetGroup {
	@Column(name = "IN_BOUND_PORT", length = 10, nullable = false)
	int inboundPort;

	@Column(name = "OUT_BOUND_PORT", length = 10, nullable = false)
	int outboundPort;

	@Id
	@Column(name = "HOST", length = 20, nullable = false)
	String host;

	@Column(name = "CONNECTION", length = 10, nullable = false)
	Integer connection = 0;

	public Integer getConnection() { return connection; }

	public void setConnection(Integer connection) { this.connection = connection; }

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
