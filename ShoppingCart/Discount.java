import java.util.Arrays;
import java.util.stream.Stream;

enum DiscountType {
    CART , ITEM, GROUP
}

interface ItemLevelDiscount {
    public double apply(String itemName);
}

interface GroupLevelDiscount {
    public double apply(  Item[] items, String itemName);
}

interface CartLevelDiscount {
    public double apply(double price);
}

class ItemLevelPercentDiscount implements ItemLevelDiscount {
    DiscountType type;
    Item item;
    double percent;

    public ItemLevelPercentDiscount(DiscountType type, Item item, double percent) {
        this.type = type;
        this.item = item;
        this.percent = percent;
    }

    @Override
    public double apply(String itemName) {
        System.out.println(" this.item.name  - " + this.item.name);

        if(this.item.name == itemName)
            return item.cost * (percent / 100 );
        return 0;
    }
}

class ItemLevelPriceOffDiscount implements ItemLevelDiscount {
    DiscountType type;
    Item item;
    double discountPrice;

    public ItemLevelPriceOffDiscount(DiscountType type, Item item, double discountPrice) {
        this.type = type;
        this.item = item;
        this.discountPrice = discountPrice;
    }

    @Override
    public double apply(String itemName) {
        if(this.item.name == itemName)
            return discountPrice;
        return 0;
    }
}

class CartLevelPercentDiscount implements CartLevelDiscount {
    double percent;

    public CartLevelPercentDiscount(double percent) {
        this.percent = percent;
    }

    @Override
    public double apply(double price) {
        return price * (percent / 100 );
    }
}

class CartLevelCountDiscount implements CartLevelDiscount {
    double discountPrice;

    public CartLevelCountDiscount(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    @Override
    public double apply(double price) {
        return discountPrice;
    }
}

class GroupLevelFreeDiscount implements GroupLevelDiscount {
    int itemTotalQuantity;
    Item item;

    public GroupLevelFreeDiscount(Item item, int itemTotalQuantity) {
        this.itemTotalQuantity = itemTotalQuantity;
        this.item = item;
    }

    @Override
    public double apply(  Item[] items, String itemName) {
        int totalCount = ((int) Arrays.stream(items).filter(x -> x.name.equals(itemName)).count());

        if(totalCount >= this.itemTotalQuantity) {
            int totalFreeQuantities = totalCount % this.itemTotalQuantity;
            return item.cost * totalFreeQuantities;
        }
        return 0;
    }
}
