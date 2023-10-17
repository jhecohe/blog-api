package com.jhecohe.solvedex.blog.api.dto;

import java.io.Serializable;
import java.time.LocalTime;

import lombok.Builder;

@Builder
public record ApiException(String messageServer, String message, String url, String method, LocalTime timestamp)
		implements Serializable {
}
