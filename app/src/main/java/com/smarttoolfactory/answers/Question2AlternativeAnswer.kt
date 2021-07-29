package com.smarttoolfactory.answers

fun main() {

}

/**
 * Alternative version of recursive function that only recursively collects nodes.
 */
fun getMorseCharsRecursiveAlt(signals: String): List<Char> {

    val chars = ArrayList<Char>()

    val rootNode = getParentNode()

    val nodes = mutableListOf<Node?>()
    getValidNodesRecursively(signals, nodes, 0, rootNode)

    nodes.forEach { node ->
        node?.char?.let { char ->
            chars.add(char)
        }
    }

    return chars
}

fun getValidNodesRecursively(
    signals: String,
    nodes: MutableList<Node?>,
    index: Int,
    rootNode: Node? = null,
) {

    if (index < signals.length) {

        when (signals[index]) {
            '.' -> {
                getValidNodesRecursively(signals, nodes, index + 1, rootNode?.left)
            }
            '-' -> {
                getValidNodesRecursively(signals, nodes, index + 1, rootNode?.right)
            }
            '?' -> {
                getValidNodesRecursively(signals, nodes, index + 1, rootNode?.left)
                getValidNodesRecursively(signals, nodes, index + 1, rootNode?.right)
            }
        }

    } else {
        nodes.add(rootNode)
    }
}
