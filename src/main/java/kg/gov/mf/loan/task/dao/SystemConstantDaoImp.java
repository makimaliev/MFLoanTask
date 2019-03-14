package kg.gov.mf.loan.task.dao;

import kg.gov.mf.loan.task.model.SystemConstant;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class SystemConstantDaoImp extends GenericDaoImpl<SystemConstant> implements SystemConstantDao {
}
