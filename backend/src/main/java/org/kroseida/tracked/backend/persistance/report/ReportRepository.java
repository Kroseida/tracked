package org.kroseida.tracked.backend.persistance.report;

import org.kroseida.tracked.backend.persistance.report.model.Report;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ReportRepository extends CrudRepository<Report, UUID> {

}
