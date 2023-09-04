package com.rhein.authenticator.domain.mapper;

import com.rhein.authenticator.domain.model.User;
import com.rhein.authenticator.domain.request.UserRequestPostBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    public abstract User toUser(UserRequestPostBody userRequestPostBody);
}
