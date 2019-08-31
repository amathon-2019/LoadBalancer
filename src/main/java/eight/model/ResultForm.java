package eight.model;

import org.springframework.http.HttpStatus;

public class ResultForm {
	private HttpStatus code;

	private Object contents;

	public HttpStatus getCode() {
		return code;
	}

	public void setCode(HttpStatus code) {
		this.code = code;
	}

	public Object getContents() {
		return contents;
	}

	public void setContents(Object contents) {
		this.contents = contents;
	}
}
