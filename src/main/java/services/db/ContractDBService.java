package services.db;

import java.sql.ResultSet;
import structure.Contract;
import structure.Worker;

public class ContractDBService {
    public ResultSet allData(){
        DataBaseService dataBaseService = new DataBaseService();
        String request = "SELECT * FROM contracts";
        return dataBaseService.select(request);
    }

    public ResultSet select(int id){
        DataBaseService dataBaseService = new DataBaseService();
        String request = "SELECT * FROM contracts WHERE id='"+id+"'";
        return dataBaseService.select(request);
    }
    public Boolean create(Contract contract) {
        DataBaseService dataBaseService = new DataBaseService();
        String request = "INSERT INTO contracts (id, name, disc, deadline, execLogin)\n" +
                "VALUES ("+contract.getId()+",'"+contract.getName()+"','"+contract.getDisc()+"','"+contract.getDeadline()+"','"+contract.getExecLog()+"')";
        return dataBaseService.insert(request);
    }

    public Boolean update(Contract contract){
        DataBaseService dataBaseService = new DataBaseService();
        String request = "UPDATE contracts SET name='" + contract.getName()+"',disc='"+contract.getDisc()+"',deadline='"+contract.getDeadline()+"',execLogin='"+contract.getExecLog()+"' WHERE id="+contract.getId();
        return dataBaseService.update(request);
    }
    public Boolean delete(Contract contract){
        DataBaseService dataBaseService = new DataBaseService();
        String request = "DELETE FROM contracts WHERE id=" + contract.getId();
        return dataBaseService.delete(request);
    }
}
