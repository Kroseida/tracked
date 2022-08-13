package org.kroseida.tracked.backend.controller.user;

import org.kroseida.tracked.backend.controller.user.dto.in.AuthenticationCreationDto;
import org.kroseida.tracked.backend.controller.user.dto.in.AuthenticationRequestDto;
import org.kroseida.tracked.backend.controller.user.dto.out.UserAuthenticationResponseDto;
import org.kroseida.tracked.backend.logic.exception.UnauthorizedException;
import org.kroseida.tracked.backend.persistance.authentication.model.Authentication;
import org.kroseida.tracked.backend.persistance.authentication.model.AuthenticationType;
import org.kroseida.tracked.backend.util.dto.DtoUtils;
import org.kroseida.tracked.backend.util.response.ResponseData;
import org.kroseida.tracked.backend.controller.user.dto.in.UserCreationDto;
import org.kroseida.tracked.backend.controller.user.dto.out.UserDto;
import org.kroseida.tracked.backend.logic.user.UserLogicLayer;
import org.kroseida.tracked.backend.persistance.user.model.User;
import org.kroseida.tracked.backend.util.response.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class UserControllerImpl implements UserController {

  private final UserLogicLayer userLogicLayer;

  @Autowired
  public UserControllerImpl(UserLogicLayer userLogicLayer) {
    this.userLogicLayer = userLogicLayer;
  }

  @Override
  public ResponseEntity<ResponseData<UserDto>> create(UserCreationDto creation) {
    return ResponseUtils.handle(() -> {
      User user = userLogicLayer.createUser(
          creation.getUsername(),
          creation.getEmail(),
          creation.getFirstName(),
          creation.getLastName()
      );
      return DtoUtils.dto(user, UserDto.class);
    });
  }

  @Override
  public ResponseEntity<ResponseData<Boolean>> createAuthentication(AuthenticationCreationDto creation) {
    return ResponseUtils.handle(() -> {
      User user = userLogicLayer.getUser(creation.getUserId());
      user.addAuthentication(Authentication.builder()
          .id(UUID.randomUUID())
          .user(user)
          .type(creation.getType())
          .data(new HashMap<>(creation.getData()))
          .build());
      userLogicLayer.save(user);
      return true;
    });
  }

  @Override
  public ResponseEntity<ResponseData<UserAuthenticationResponseDto>> createSession(AuthenticationRequestDto request) {
    return ResponseUtils.handle(() -> {
      User user = userLogicLayer.getUser(request.getUsername());
      if (user == null) {
        throw new UnauthorizedException();
      }
      Authentication authentication = user.authenticate(
          AuthenticationType.USERNAME_PASSWORD,
          Map.of("password", request.getPassword())
      );
      if (authentication == null) {
        throw new UnauthorizedException();
      }
      userLogicLayer.save(user);

      return new UserAuthenticationResponseDto(
          user.getId(),
          authentication.getData().get("token")
      );
    });
  }

  @Override
  public ResponseEntity<ResponseData<UserDto>> getSession(String token) {
    return ResponseUtils.handle(() ->
        DtoUtils.dto(userLogicLayer.getUserBySession(token), UserDto.class)
    );
  }

}
