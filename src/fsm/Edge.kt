package fsm

/**
 * A transition between states when an [BaseEvent] occurs that goes
 * to a next [State]
 */
class Edge(private val event: BaseEvent, private val targetState: BaseState) {
    private val actionList = mutableListOf<(Edge) -> Unit>()

    /**
     * Add an action to be performed upon transition
     */
    fun action(action: (Edge) -> Unit) {
        actionList.add(action)
    }

    /**
     * Apply the transition actions
     */
    fun applyTransition(getNextState: (BaseState) -> State): State {
        actionList.forEach { it(this) }

        return getNextState(targetState)
    }

    /**
     * Check if this edge transitions on the given [BaseEvent]
     */
    fun hasTransitionOn(e: BaseEvent): Boolean {
        return event.javaClass == e.javaClass
    }
}



