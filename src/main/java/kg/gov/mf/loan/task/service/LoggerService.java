package kg.gov.mf.loan.task.service;

import kg.gov.mf.loan.task.model.MFLog;

public interface LoggerService extends GenericService<MFLog>
{
    void addLog(String user, String entity, String action, String ip);
}
