package shakram02.fsm

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

    override fun toString(): String {
        return "Edge to ${targetState.javaClass.simpleName} on ${event.javaClass.simpleName}"
    }
}
