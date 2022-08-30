export default interface ProjectCreationDto {
  name: string;
  description: string;
  active: boolean,
  startDate: string,
  endDate: string,
  organizationId: string
}
