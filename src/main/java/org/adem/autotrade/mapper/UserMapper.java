package org.adem.autotrade.mapper;

import org.adem.autotrade.dto.request.UserRequestDto;
import org.adem.autotrade.dto.response.UserResponseDto;
import org.adem.autotrade.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User toUserEntity(UserRequestDto userRequestDto);

    UserResponseDto toUserResponse(User user);
}
