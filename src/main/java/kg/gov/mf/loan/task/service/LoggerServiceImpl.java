package kg.gov.mf.loan.task.service;

import kg.gov.mf.loan.task.dao.LoggerDao;
import kg.gov.mf.loan.task.model.MFLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoggerServiceImpl extends GenericServiceImpl<MFLog> implements LoggerService {

    @Override
    public void addLog(String user, String entity, String action, String ip)
    {
        MFLog log = new MFLog();
        log.setCreatedBy(user);
        log.setEntity(entity);
        log.setAction(action);
        log.setIp(ip);
        dao.add(log);
    }

}
