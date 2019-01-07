package kg.gov.mf.loan.task.service;

import kg.gov.mf.loan.task.model.SystemConstant;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SystemConstantServiceImpl extends GenericServiceImpl<SystemConstant> implements SystemConstantService {
}
