package fsm

/**
 *
 */
class State(val name: BaseState) {
    private val edges = mutableListOf<Edge>()
    private val onEnterStateActions = mutableListOf<(State) -> Unit>()

    /**
     * Creates an edge from a [State] to another when a [BaseEvent] occurs
     * @param event: Transition event
     * @param targetState: Next state
     * @param init: I find it as weird as you do, here you go https://kotlinlang.org/docs/reference/lambdas.html
     */
    fun edge(event: BaseEvent, targetState: BaseState, init: Edge.() -> Unit) {
        val edge = Edge(event, targetState)
        edge.init()

        edges.add(edge)
    }

    /**
     * Action performed by state
     */
    fun action(action: (State) -> Unit) {
        onEnterStateActions.add(action)
    }

    /**
     * Enter the state and run all actions
     */
    fun enter() {
        // Every action takes the current state
        onEnterStateActions.forEach { it(this) }
    }

    /**
     * Get the appropriate fsm.Edge for the Event
     */
    fun getEdgeForEvent(event: BaseEvent): Edge {
        return edges.first { it.hasTransitionOn(event) }
    }

}
