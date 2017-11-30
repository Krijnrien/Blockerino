package Server.game.items;

public abstract class Item {
    private int id;
    private String name;
    private ItemTypeEnum type;
    private ItemRarityEnum rarity;
    private int stackSize;
    private String description;

    //region Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemTypeEnum getType() {
        return type;
    }

    public void setType(ItemTypeEnum type) {
        this.type = type;
    }

    public ItemRarityEnum getRarity() {
        return rarity;
    }

    public void setRarity(ItemRarityEnum rarity) {
        this.rarity = rarity;
    }

    public int getStackSize() {
        return stackSize;
    }

    public void setStackSize(int stackSize) {
        this.stackSize = stackSize;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //endregion

    //region Enums
    public enum ItemRarityEnum {
        BASIC,
        COMMON,
        MASTERWORK,
        RARE,
        EXOTIC,
        LEGENDARY,
    }

    public enum ItemTypeEnum {
        CONTAINER,
        WEAPON,
        BLOCK
    }

//endregion
}
