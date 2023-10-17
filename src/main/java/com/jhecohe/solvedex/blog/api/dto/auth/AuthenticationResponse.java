package com.jhecohe.solvedex.blog.api.dto.auth;

import java.io.Serializable;

public record AuthenticationResponse(String jwt) implements Serializable{

}
