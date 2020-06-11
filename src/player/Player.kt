package player

import inventory.*
import zombies.Zombie
import java.util.*

object Tutorial {
    var isTutorial = true
}

sealed class Unit(val name: String, var hitPoints: Int = 30, var unitStrength: Int) {
    //    open fun makeDecision(
//        player: Player,
//        petZombie: Zombie?
//    ) {
//        when (readLine()!!) {
//
//        }
//    }


    open fun makeDecision() {}

    open fun makeBattleDecision(
        player: Player,
        petZombie: Zombie?,
        zombie: Zombie,
        playerStrength: Int
    ) {
//        when (readLine()!!) {
//            "punch" -> {
//
//                punch(playerStrength, zombie)
//                println("you punched ${zombie.name}")
//                println("inflicted $playerStrength points of damage")
//
//                if (zombie.hitPoints > 0) {
//                    println("${zombie.hitPoints} hit points remaining")
//
//                    zombie.bite(zombie.zombieStrength, player)
//                    println("${zombie.name} bit you! \nouch")
//                    println("Remaining hit points ${player.hitPoints}")
//
//                    println("what would you do?\npunch, run or pet?")
//                    makeBattleDecision(player, petZombie, zombie, playerStrength)
//
//                } else println("${zombie.name} lost all his hit points.\n${zombie.name} is dead")
//
//
//            }
//            "run" -> println("you ran away!\nPhew")
//            "pet" -> {
//
//                tameZombie(zombie)
//                if (!zombie.tamed) {
//                    println("${zombie.name} refused to be pet")
//
//                    zombie.bite(zombie.zombieStrength, player)
//                    println("${zombie.name} bit you! \nouch")
//                    println("Remaining hit points ${player.hitPoints}")
//
//                    println("what would you do?\npunch, run or pet?")
//                    makeBattleDecision(player, petZombie, zombie, playerStrength)
//                } else {
//                    println("Success! ${zombie.name} is now your friend")
//                    addZombie(zombie)
//                    println("${zombie.name} is now in your Zombie Pocket")
//                }
//            }
//            else -> {
//                println("please make a good decision")
//            }
//        }
    }

}

class Player(name: String, hitPoints: Int = 30, playerStrength: Int = 3) : Unit(name, hitPoints, playerStrength),
    UseItem, ChooseItem {


    override fun makeDecision() {
        when (readLine()!!) {
            "zombies" -> openZombieInventory()
            "inventory" -> {
                openInventory()
                chooseItem()
            }
            "open inventory" -> {
                openInventory()
                chooseItem()
            }
        }
    }

    override fun chooseItem() {
        println("what item will you use?")
        when (val decision = readLine()!!) {
            "meat" -> if (zombieArrayListPairs.isEmpty()) {

                println("You don't have any zombies left")
                chooseItem()

            } else {
                val item = retrieveItem(decision)
                if (item in itemList) {
                    println("who should use ${item.itemName}?")
                    for (element in zombieArrayListPairs) println(
                        "${element.first.petZombieName}, strength ${element.first.petZombieDamage}, hit points ${element
                            .first.petZombieHitPoints}/${element.second.baseHitPoints}"
                    )
                    println("choose a zombie")
                    val selectedPetZombiePair = retrieveZombie(readLine()!!)

                    if (selectedPetZombiePair in zombieArrayListPairs) {
                        println("${selectedPetZombiePair.second.name} has been selected")
                        Meat().useItem(item, selectedPetZombiePair.second)
                        itemList.remove(item)
                    } else {
                        println("you don't have that zombie. Select a valid zombie")
                        chooseItem()
                    }
                } else {
                    println("you don't have any ${item.itemName}")
                    chooseItem()
                }

            }
            else -> {
                println("that item doesn't exist, try again")
                chooseItem()
            }
        }

    }

    override fun makeBattleDecision(
        player: Player,
        petZombie: Zombie?,
        zombie: Zombie,
        playerStrength: Int
    ) {
        when (readLine()!!) {
            "punch" -> {
                val startingHitPoints = zombie.hitPoints

                punch(playerStrength, zombie)
                zombieMoreTameable(startingHitPoints, zombie)
                println("you punched ${zombie.name}")
                println("inflicted $playerStrength points of damage")

                if (zombie.hitPoints > 0) {
                    println("${zombie.hitPoints} hit points remaining")

                    zombie.bite(zombie.zombieStrength, player)
                    println("${zombie.name} bit you! \nouch")
                    println("Remaining hit points ${player.hitPoints}")

                    when {
                        (Tutorial.isTutorial && zombie.hitPoints > 2) -> {
                            println("let's punch it again")
                            makeBattleDecision(player, petZombie, zombie, playerStrength)
                        }
                        (Tutorial.isTutorial && zombie.hitPoints in 1..2) -> {
                            println("Now! he is weak and less angry anymore!\nLet's \"pet\" it!")
                            makeBattleDecision(player, petZombie, zombie, playerStrength)
                        }
                        else -> {
                            println("what would you do?\npunch, run or pet?")
                            makeBattleDecision(player, petZombie, zombie, playerStrength)
                        }
                    }


                } else println("${zombie.name} lost all his hit points.\n${zombie.name} is dead")


            }
            "run" -> println("you ran away!\nPhew")
            "pet" -> {

                tameZombie(zombie)
                if (!zombie.tamed) {
                    println("${zombie.name} refused to be pet")

                    zombie.bite(zombie.zombieStrength, player)
                    println("${zombie.name} bit you! \nouch")
                    println("Remaining hit points ${player.hitPoints}")

                    println("what would you do?\npunch, run or pet?")
                    makeBattleDecision(player, petZombie, zombie, playerStrength)
                } else {
                    zombie.tamed = true
                    println("Success! ${zombie.name} is now your friend")
                    addZombie(zombie)
                    println("${zombie.name} is now in your Zombie Pocket")
                }
            }
            "zombie bite" -> {
                if (zombieArrayListPairs.isEmpty()) {

                    println("You don't have any zombies left")
                    println("what would you do? \npunch, run, pet?")
                    makeBattleDecision(player, petZombie, zombie, playerStrength)
                } else {
                    println("who should attack?")
                    for (element in zombieArrayListPairs) println(
                        "${element.first.petZombieName}, strength ${element.first.petZombieDamage}, hit points ${element
                            .first.petZombieHitPoints}"
                    )
                    println("choose a zombie")
                    var selectedPetZombiePair = retrieveZombie(readLine()!!)
                    println("${selectedPetZombiePair.second.name} has been selected")
                    if (zombieArrayListPairs.contains(selectedPetZombiePair)) {
                        petZombieBite(
                            selectedPetZombiePair.second, zombie
                        )
                        if (zombie.hitPoints > 0 && petZombie != null) {
                            petZombie.hitPoints = (petZombie.hitPoints - zombie.zombieStrength).toInt()

                            if (petZombie.hitPoints > 0) {
                                println("${petZombie.name} was bit \nRemaining hit points ${petZombie.hitPoints}")

                                println("what would you do? \npunch, run, pet or zombie bite?")
                                makeBattleDecision(player, petZombie, zombie, playerStrength)
                            } else {
                                println("${petZombie.name}is dead")

                                println("what would you do? \npunch, run, pet or zombie bite?")
                                makeBattleDecision(player, petZombie, zombie, playerStrength)
                            }
                        } else println("${zombie.name} is dead")
                    } else {
                        println("you don't have that zombie. Who should attack?")
                        selectedPetZombiePair = retrieveZombie(readLine()!!)
                        petZombieBite(selectedPetZombiePair.second, zombie)
                    }
                }
            }
            else -> {
                println("please make a correct decision")
                makeBattleDecision(player, petZombie, zombie, playerStrength)
            }
        }
    }


}

fun zombieMoreTameable(startingHitPoints: Int, zombie: Zombie) {


    when {
        zombie.hitPoints > startingHitPoints * 0.5 && zombie.hitPoints != startingHitPoints -> {

            zombie.changingTamable =
                zombie.changingTamable * 0.75
            println(zombie.hitPoints)
            println("${zombie.changingTamable}")
        }
        zombie.hitPoints <= startingHitPoints * 0.5 && zombie.hitPoints >= startingHitPoints * 0.2 -> {
            zombie.changingTamable = zombie.changingTamable * 0.5
            println(zombie.hitPoints)
            println("${zombie.changingTamable}")
        }

        zombie.hitPoints < startingHitPoints * 0.2 && zombie.hitPoints > startingHitPoints * 0.1 -> {
            zombie.changingTamable = zombie.changingTamable * 0.2
            println(zombie.hitPoints)
            println("${zombie.changingTamable}")
        }
        else -> println("this shouldn't happen")

    }

}

fun punch(playerStrength: Int, zombie: Zombie): Int {
    zombie.hitPoints = zombie.hitPoints - playerStrength
//    zombieMoreTameable(startingHitPoints, zombie)
//    println("you punched ${zombie.name}")
//    println("inflicted $playerStrength points of damage")

    return zombie.hitPoints
}

fun petZombieBite(selectedPetZombie: Zombie?, zombie: Zombie) {


    if (selectedPetZombie != null) {
        println("${selectedPetZombie.name} attacks")
        zombie.hitPoints = (zombie.hitPoints - selectedPetZombie.zombieStrength).toInt()
        println("${zombie.name} has ${zombie.hitPoints} hit points remaining")
    }
}

private fun tameZombie(zombie: Zombie) {
    var dice = 6
    val tameNumber = Random().nextInt(dice)
    println("$tameNumber dice, ${zombie.changingTamable} tamable number")
    if (tameNumber >= zombie.changingTamable) zombie.tamed = true
}


fun openZombieInventory() {
    for (element in zombieArrayListPairs) println(
        "${element.first.petZombieName}, strength ${element.first.petZombieDamage}, hit points ${element.first.petZombieHitPoints}"
    )
}
interface ChooseItem {
    fun chooseItem() {}
}