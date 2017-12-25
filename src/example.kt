import fsm.BaseEvent
import fsm.BaseState
import fsm.StateMachine

fun main(args: Array<String>) {
    // Inherit your custom events and states from provided base interfaces
    class GoUp : BaseEvent

    class Initial : BaseState
    class Input : BaseState
    class Repeat : BaseState

    val m = StateMachine.buildStateMachine(Initial()) {
        state(Initial()) {
            action {
                println("Entered state ${it.name.javaClass.simpleName}")
            }
            edge(GoUp(), Input()) {
                println("Going to Input")
            }
        }
        state(Input()) {
            action {
                println("Entered state ${it.name.javaClass.simpleName}")
            }

            action {
                println("another action is being executed")
            }

            edge(GoUp(), Repeat()) {
                action {
                    println("Going UP")
                }
            }
        }

        state(Repeat()) {
            action {
                println("Entered state ${it.name.javaClass.simpleName}")
            }
            action {
                println("Will get bored!")
            }
            edge(GoUp(), Repeat()) {
                println("Repeating")
            }
        }
    }

    m.initialize()
    m.acceptEvent(GoUp())
    m.acceptEvent(GoUp())
    m.acceptEvent(GoUp())
    m.acceptEvent(GoUp())
    m.acceptEvent(GoUp())
}