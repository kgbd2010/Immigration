package co.laomag.immigration.common;

import co.laomag.immigration.entity.Programs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 项目部分代码
 */
@Repository
@Transactional
public interface ProgramsRepository extends JpaRepository<Programs, String>, JpaSpecificationExecutor<Programs>{

}
