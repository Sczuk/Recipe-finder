package br.com.recipe_finder.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Permissions {

    CHEF_GET("chef:get"),
    CHEF_POST("chef:post"),

    BARMAN_GET("barman:get"),
    BARMAN_POST("barman:post"),

    USER_POST("user:post"),
    USER_DELETE("user:delete"),
    USER_PUT("user:put"),
    USER_GET("user:get"),

    ADMIN_GET("admin:get"),
    ADMIN_POST("admin:post"),
    ADMIN_DELETE("admin:delete");

    @Getter
    private final String permission;
}
