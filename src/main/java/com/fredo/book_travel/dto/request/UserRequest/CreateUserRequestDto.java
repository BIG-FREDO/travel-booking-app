package com.fredo.book_travel.dto.request.UserRequest;

import com.fredo.book_travel.security.rolseAndPermissions.Role;

public record CreateUserRequestDto(
                                    String name,
                                    String phone,
                                    String username,
                                    String password,
                                    String role
) {}
