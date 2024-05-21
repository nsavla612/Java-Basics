public class ItemManager {

    private static ItemManager instance = null;
    private ItemManager() {}
    public static ItemManager getInstance() {
        if(instance == null) {
            synchronized (ItemManager.class) {
                if(instance == null) {
                    instance =  new ItemManager();
                }
            }
        }
        return instance;
    }

    public Item getMockProduct(String productId)
    {
        return new Item.Builder().
                name("MILK").
                type(Item.ItemType.SIMPLE).
                cost(5).
                weight(100).
                build();
    }

    public Item[] getMockProductList()
    {
        Item[] items =  new Item[6];
        items[0] = new Item.Builder().name("MILK").type(Item.ItemType.SIMPLE).cost(5).weight(100).build();
        items[1] = new Item.Builder().name("TOMATO").type(Item.ItemType.WEIGHT).cost(2).weight(40).build();
        items[2] = new Item.Builder().name("TOMATO").type(Item.ItemType.WEIGHT).cost(2).weight(60).build();
        items[3] = new Item.Builder().name("MILK").type(Item.ItemType.SIMPLE).cost(5).weight(300).build();
        items[4] = new Item.Builder().name("ONION").type(Item.ItemType.WEIGHT).cost(2.3).weight(10).build();
        items[5] = new Item.Builder().name("MILK").type(Item.ItemType.SIMPLE).cost(5).weight(100).build();
        return items;
    }
}

