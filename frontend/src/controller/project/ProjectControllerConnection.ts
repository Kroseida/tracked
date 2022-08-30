import {ControllerConnection} from 'src/controller/ControllerConnection';
import {Page, Pageable} from 'src/controller/Page';
import ProjectDto from 'src/controller/project/dto/in/ProjectDto';
import ProjectUpdateDto from 'src/controller/project/dto/out/ProjectUpdateDto';
import ProjectCreationDto from 'src/controller/project/dto/out/ProjectCreationDto';

export class ProjectControllerConnection extends ControllerConnection {

  public async create(data: ProjectCreationDto): Promise<ProjectDto> {
    return <ProjectDto>await this.request({
      method: 'POST',
      url: '/project/',
      data
    });
  }

  public async delete(id: string): Promise<Page<ProjectDto>> {
    return <Page<ProjectDto>>await this.request({
      method: 'DELETE',
      url: '/project/' + id + '/',
    });
  }

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

  async patch(id: string, data: ProjectUpdateDto) {
    return <ProjectDto>await this.request({
      method: 'PATCH',
      url: '/project/' + id + '/',
      data
    });
  }


}
