import inventory.Meat
import inventory.itemToInventory
import player.*
import zombies.BasicZombie
import zombies.FriendlyZombie
import zombies.Zombie



fun main() {
    var selectedPetZombie: Pair<ZombiePocket, Zombie?>


    println("what's your name?")
    var pInput = readLine()!!
    val player = Player(pInput)

    println("No time for explanations! here comes the first one!")

    val zombie1 = FriendlyZombie()
    println("\n${zombie1.name} has appeared! \n${zombie1.hitPoints} hit points, tamable ${zombie1.changingTamable}")

    println("let's \"punch\" it!")
    player.makeBattleDecision(player, null, zombie1, player.unitStrength)

    println("Let's see your new friend")
    openZombieInventory()
    println("Phew, that was close. \nhello ${player.name}")
    println("Let's heal your new friend, take this")
    itemToInventory(Meat())
    println("you got some \"meat\"")
    println("Let's open your \"inventory\"")
    player.makeDecision()
    println("\nhere comes another one, get ready!")

    val zombie2 = BasicZombie()
    println("\n${zombie2.name} has appeared! \n${zombie2.hitPoints} hit points")
    println("let's ask our new friend for help. \nChoose \"zombie bite\"")

    selectedPetZombie = retrieveZombie(pInput)
    player.makeBattleDecision(player, selectedPetZombie.second, zombie2, player.unitStrength)

}



