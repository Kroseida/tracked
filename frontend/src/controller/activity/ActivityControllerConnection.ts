import {ControllerConnection} from 'src/controller/ControllerConnection';
import {Page, Pageable} from 'src/controller/Page';
import ActivityCreationDto from 'src/controller/activity/dto/out/ActivityCreationDto';
import ActivityDto from 'src/controller/activity/dto/in/ActivityDto';
import ActivityUpdateDto from 'src/controller/activity/dto/out/ActivityUpdateDto';

export class ActivityControllerConnection extends ControllerConnection {

  public async create(data: ActivityCreationDto): Promise<ActivityDto> {
    return <ActivityDto>await this.request({
      method: 'POST',
      url: '/activity/',
      data
    });
  }

  public async delete(id: string): Promise<Page<ActivityDto>> {
    return <Page<ActivityDto>>await this.request({
      method: 'DELETE',
      url: '/activity/' + id + '/',
    });
  }

  public async list(organizationId: string | null, pageable: Pageable): Promise<Page<ActivityDto>> {
    return <Page<ActivityDto>>await this.request({
      method: 'GET',
      url: '/activity/',
      params: {
        organizationId: organizationId || '*',
        size: pageable.rowsPerPage,
        page: pageable.page - 1,
        sort: pageable.sortBy + ',' + (pageable.descending ? 'DESC' : 'ASC'),
        filter: pageable.filter,
      }
    });
  }

  async patch(id: string, data: ActivityUpdateDto) {
    return <ActivityDto>await this.request({
      method: 'PATCH',
      url: '/activity/' + id + '/',
      data
    });
  }

}
