using CSharpHospitalREST.config.db;
using CSharpHospitalREST.Models;

namespace CSharpHospitalREST.Service;

public class EOrderService(HospitalDatabaseContext db)
{
    public EOrder CreateOrder(Medicament medicament, int count)
    {
        EOrder order = new EOrder();
        order.Medicament = medicament;
        order.Count = count;
        db.EOrders.Add(order);
        db.SaveChanges();
        return order;
        
    }

    public void DeleteOrder(long requestOrderId)
    {
        var eOrder = db.EOrders.Find(requestOrderId);
        db.EOrders.Remove(eOrder);
        db.SaveChanges();
    }
}