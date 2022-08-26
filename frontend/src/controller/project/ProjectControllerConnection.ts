import {ControllerConnection} from 'src/controller/ControllerConnection';
import {Page, Pageable} from 'src/controller/Page';
import ProjectDto from 'src/controller/project/dto/ProjectDto';

export class ProjectControllerConnection extends ControllerConnection {

  public async list(organizationId: string | null, pageable: Pageable): Promise<Page<ProjectDto>> {
    return <Page<ProjectDto>>await this.request({
      method: 'GET',
      url: '/project/',
      params: {
        organizationId: organizationId || '*',
        size: pageable.rowsPerPage,
        page: pageable.page - 1,
        sort: pageable.sortBy + ',' + (pageable.descending ? 'DESC' : 'ASC'),
        filter: pageable.filter,
      }
    });
  }

}
