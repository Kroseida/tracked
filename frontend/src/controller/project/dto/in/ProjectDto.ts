export default interface ProjectDto {
  id: string;
  name: string;
  description: string;
  active: boolean;
  startDate: string,
  endDate: string,
  organizationId: string;
}
