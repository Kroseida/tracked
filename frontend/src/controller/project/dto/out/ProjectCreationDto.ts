export default interface ProjectCreationDto {
  name: string;
  description: string;
  active: boolean,
  startedAt: number,
  endAt: number,
  organizationId: string
}
