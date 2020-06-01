import player.*
import zombies.BasicZombie
import zombies.FriendlyZombie
import zombies.Zombie

fun main() {
    var selectedPetZombie: Zombie? = null


    println("what's your name?")
    var pInput = readLine()!!
    val player = Player(pInput)
    println("hello ${player.name}")
    println("No time for explanations! here comes the first one!")

    val zombie1 = FriendlyZombie()
    println("\n${zombie1.name} has appeared! \n${zombie1.hitPoints} hit points")

    println("what would you do? \npunch, run or pet?")
    player.makeDecision(player, null, zombie1, player.playerStrength)

    println("Let's see your new friend")
    openInventory()

    val zombie2 = BasicZombie()
    println("\n${zombie2.name} has appeared! \n${zombie2.hitPoints} hit points")
    println("let's ask our new friend for help. \nChoose \"zombie bite\"")

    selectedPetZombie = retrieveZombie(pInput)
    player.makeDecision(player, selectedPetZombie, zombie2, player.playerStrength)

}



