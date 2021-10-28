package Vending.Service;

import Vending.DTO.Item;
import Vending.Dao.VendingAuditDAO;
import Vending.Dao.VendingDao;
import Vending.Dao.VendingPersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendingServiceLayerImpl implements VendingServiceLayer{
    VendingDao dao;
    VendingAuditDAO auditDao;

    @Autowired
    public VendingServiceLayerImpl(VendingDao dao, VendingAuditDAO auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public List<Item> getAllItems() throws
        VendingPersistenceException {
            return dao.getAllItems();
            //Pass-through
    };

    @Override
    public Item getItem(String name) throws
        VendingPersistenceException, VendingDataValidationException, OutOfStockException {
        validateData(dao.getItem(name));
        if(dao.getItem(name).getNumberOf()!=0){
            return dao.getItem(name);
        }else{
            throw new OutOfStockException("Item is out of stock!");
        }
            //Pass through
    };

    @Override
    public void updateStock(String name, Item item) throws VendingPersistenceException {
            dao.updateStock(name, item);
            //Pass through
    };
    private void validateData(Item item) throws
            VendingDataValidationException{
            if(item == null || item.getName() == null
                || item.getName().trim().length() == 0
                || item.getCost() == null
                    ) {

                throw new VendingDataValidationException(
                        "ERROR: Enter correct choice are required.");
            }
        }

}

