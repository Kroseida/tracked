package org.kroseida.tracked.backend.controller.user;

import org.kroseida.tracked.backend.controller.user.dto.in.AuthenticationCreationDto;
import org.kroseida.tracked.backend.controller.user.dto.in.AuthenticationRequestDto;
import org.kroseida.tracked.backend.controller.user.dto.out.UserAuthenticationResponseDto;
import org.kroseida.tracked.backend.util.response.ResponseData;
import org.kroseida.tracked.backend.controller.user.dto.in.UserCreationDto;
import org.kroseida.tracked.backend.controller.user.dto.out.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user/")
public interface UserController {

  @RequestMapping(value = "/", method = RequestMethod.POST)
  @ResponseBody
  ResponseEntity<ResponseData<UserDto>> create(@RequestBody UserCreationDto creation);

  @RequestMapping(value = "/authentication/", method = RequestMethod.POST)
  @ResponseBody
  ResponseEntity<ResponseData<Boolean>> createAuthentication(@RequestBody AuthenticationCreationDto creation);

  @RequestMapping(value = "/authentication/session/", method = RequestMethod.POST)
  @ResponseBody
  ResponseEntity<ResponseData<UserAuthenticationResponseDto>> createSession(@RequestBody AuthenticationRequestDto request);

  @RequestMapping(value = "/authentication/session/", method = RequestMethod.GET)
  @ResponseBody
  ResponseEntity<ResponseData<UserDto>> getSession(@RequestHeader("Authorization") String session);

}
