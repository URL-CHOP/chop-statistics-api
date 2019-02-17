package me.nexters.chopstatsapi.grpc.error;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author manki.kim
 */
@AllArgsConstructor
@Getter
public enum UrlErrorHandler {
	NOT_FOUND(HttpStatus.NOT_FOUND, "해당 url가 존재 하지 않습니다 : ");

	private HttpStatus httpStatus;
	private String message;
}
