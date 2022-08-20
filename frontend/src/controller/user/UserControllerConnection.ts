import {ControllerConnection} from 'src/controller/ControllerConnection';
import UserCreationDto from 'src/controller/user/dto/out/UserCreationDto';
import UserDto from 'src/controller/user/dto/in/UserDto';
import AuthenticationCreationDto from 'src/controller/user/dto/out/AuthenticationCreationDto';
import AuthenticationRequestDto from 'src/controller/user/dto/out/AuthenticationRequestDto';
import UserAuthenticationResponseDto from 'src/controller/user/dto/in/UserAuthenticationResponseDto';
import {LocalStorage} from 'quasar';

export class UserControllerConnection extends ControllerConnection {

  public async create(data: UserCreationDto): Promise<UserDto> {
    return <UserDto>await this.request({
      method: 'POST',
      url: '/user/',
      data
    });
  }

  public async createAuthentication(data: AuthenticationCreationDto) {
    await this.request({
      method: 'POST',
      url: '/user/authentication/',
      data
    });
  }

  public async createSession(data: AuthenticationRequestDto): Promise<UserAuthenticationResponseDto> {
    return await this.request({
      method: 'POST',
      url: '/user/authentication/session/',
      data
    });
  }

  public async getSession(): Promise<UserDto> {
    return await this.request({
      method: 'GET',
      url: '/user/authentication/session/'
    });
  }
}
