package inventory

import player.ZombiePocket
import player.zombieArrayListPairs
import zombies.Zombie


val itemList = mutableListOf<BaseItem>()

fun itemToInventory(item: BaseItem) {
    itemList.add(item)
}


fun openInventory() {
    if (itemList.isEmpty()) {
        println("your inventory is empty")
    } else {
        for (element in itemList) println(
            "you have:\n${element.itemName}"
        )
    }
}

fun retrieveItem(itemName: String): BaseItem {


    var selectedItemIndex = 0

    for (element in itemList){
        if(element.itemName == itemName){
            selectedItemIndex = itemList.indexOf(element)
        }
    }
    return itemList[selectedItemIndex]
}
