package ua.kharkiv.rsyrtsov.db.dao;

import ua.kharkiv.rsyrtsov.db.dao.exception.DAOException;
import ua.kharkiv.rsyrtsov.db.model.Master;
import ua.kharkiv.rsyrtsov.db.model.Record;

import java.util.List;

public interface MasterDao {

    List<Master> getAllMasters(String locale) throws DAOException;
    List<Master> getMastersByServicesId(int id, String locale) throws DAOException;
    List<Record> getMasterScheduleByMasterId(int id) throws DAOException;
    void updateStatusId(String recordId) throws DAOException;

}
