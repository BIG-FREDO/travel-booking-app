package com.fredo.book_travel.dto.request.UserRequest;

public record UpdateUserRequestDto(
                                   String name,
                                   String phone,
                                   String username,
                                   String password
){}
