import java.util.*;
public class CostManager {
    private static CostManager instance = null;
    private CostManager() {}
    public static CostManager getInstance() {
        if(instance == null) {
            synchronized (CostManager.class) {
                if(instance == null) {
                    instance =  new CostManager();
                }
            }
        }
        return instance;
    }

    public double getCostOfItem(Item item) {
        if(item.itemType == Item.ItemType.SIMPLE)
            return item.cost;
        else if(item.itemType == Item.ItemType.WEIGHT)
            return item.cost * item.weight;
        return 0;
    }

    public double getTotalCost(Item... items) {
        double totalCost = 0;
        for(Item item : items)
        {
            getCostOfItem(item);
        }
        return totalCost;
    }

}
