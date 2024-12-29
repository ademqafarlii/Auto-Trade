package org.adem.autotrade.mapper;

import org.adem.autotrade.dto.response.UserResponseDto;
import org.adem.autotrade.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserResponseDto toUserResponse(User user);
}
