import java.util.*;
public class ShoppingCart {
    ItemManager itemmanager;
    CostManager costManager;
    Item[] items;
    double totalCost;
    List<ItemLevelDiscount> itemLevelDiscounts = new ArrayList<ItemLevelDiscount>();
    List<CartLevelDiscount> cartLevelDiscounts = new ArrayList<CartLevelDiscount>();
    List<GroupLevelDiscount> groupLevelDiscounts = new ArrayList<GroupLevelDiscount>();


    public ShoppingCart() {
        this.itemmanager = ItemManager.getInstance();
        this.costManager = costManager.getInstance();
        this.items = itemmanager.getMockProductList();
        this.totalCost = 0;
        itemLevelDiscounts.add(
                new ItemLevelPercentDiscount(DiscountType.ITEM, this.items[0], 10)
        );
        itemLevelDiscounts.add(
                new ItemLevelPriceOffDiscount(DiscountType.ITEM, this.items[0], 1)
        );

       cartLevelDiscounts.add(
                new CartLevelPercentDiscount(15)
       );

        cartLevelDiscounts.add(
                new CartLevelCountDiscount(1)
        );

        // if you have 3 MILK , then you get 1 free.
        groupLevelDiscounts.add(
                new GroupLevelFreeDiscount(this.items[0], 2)
        );
    }


    public double getTotalCost()
    {

        for(Item item : this.items)
        {
            this.totalCost += item.cost;
        }
        System.out.println(" total price before discount is - " +  this.totalCost);

        // applyItemLevelDiscounts();
        // applyCartLevelDiscounts();
        applyGroupLevelDiscounts();
        return  this.totalCost;
    }

    public void applyItemLevelDiscounts()
    {
        for(Item item : this.items)
        {
           for(ItemLevelDiscount disc : this.itemLevelDiscounts)
           {
               this.totalCost -= disc.apply(item.name);
               if( this.totalCost < 0 )  this.totalCost = 0;
           }
        }
    }

    public void applyCartLevelDiscounts()
    {
        for(CartLevelDiscount disc : this.cartLevelDiscounts)
        {
            this.totalCost -= disc.apply(this.totalCost );
            if( this.totalCost < 0 )  this.totalCost = 0;
        }
    }


    public void applyGroupLevelDiscounts()
    {
        for(Object item : Arrays.stream(this.items).distinct().toArray()) {
            for(GroupLevelDiscount disc : this.groupLevelDiscounts)
            {
                Item castItem = (Item)(item);
                this.totalCost -= disc.apply(items, castItem.name);
                if( this.totalCost < 0 )  this.totalCost = 0;
            }
        }
    }


}

