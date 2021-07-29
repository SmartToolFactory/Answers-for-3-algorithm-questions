package com.smarttoolfactory.answers

fun main() {

    val str = "?.??"

    val chars: List<Char> = getMorseChars(str)
    chars.forEach {
        println("🎃CHAR: $it")
    }

  val result: List<Char> =  getMorseCharsRecursive(str)

    result.forEach {
       println( "Possible CHAR: $it")
    }

}

/**
 * Recursively traverses binary tree to get valid character for [signals] input.
 *
 * If there is a transmission error due to one or multiple chars being [?] Traverses
 * both left and right of the current note
 *
 * @param signals Sequence of [*], [-], or [?]
 * @param nodes Contain the character in a specific node in tree. There can be variants of
 * result due to missing signals
 * @param index of the char in [signals]
 * @param rootNode is either root node or the current node depending [index]
 *
 * @return [List<Char>] that contains possible outcomes depending on the input.
 */
fun getMorseCharsRecursive(
    signals: String,
    nodes: MutableList<Char> = mutableListOf(),
    index: Int = 0,
    rootNode: Node? = null,
): List<Char> {

    val node = rootNode ?: getParentNode()

    if (index < signals.length) {

        when (signals[index]) {
            '.' -> {
                getMorseCharsRecursive(signals, nodes, index + 1, node.left)
            }
            '-' -> {
                getMorseCharsRecursive(signals, nodes, index + 1, node.right)
            }
            '?' -> {
                getMorseCharsRecursive(signals, nodes, index + 1, node.left)
                getMorseCharsRecursive(signals, nodes, index + 1, node.right)
            }
        }

    } else {
        nodes.add(node.char?:'?')
    }

    return nodes
}


 fun getMorseChars(str: String): List<Char> {
    val rootNode = getParentNode()
    val chars = mutableListOf<Char>()
    getValidChars(str, chars, rootNode)
    return chars
}

/**
 * Returns [List<Char>] by looping each char in [signals], if it hits **?** then rescursively
 * moves for ***left*** and ***right*** branches.
 *
 * ## Note: This function uses mix of loop and recursion.
 */
private fun getValidChars(
    signals: String,
    list: MutableList<Char> = mutableListOf(),
    rootNode: Node? = null
) {

    var parentNode = rootNode ?: getParentNode()
    var currentNode = parentNode

    signals.forEachIndexed { index, signal ->
        when (signal) {
            '.' -> {
                parentNode.left?.let {
                    currentNode = it
                }
            }
            '-' -> {
                parentNode.right?.let {
                    currentNode = it
                }
            }
            '?' -> {
                // Create new string after index of ?
                val str = signals.substring(index + 1)
                getValidChars(".$str", list, parentNode)
                getValidChars("-$str", list, parentNode)

                return
            }
        }

        parentNode = currentNode
    }

    currentNode.char?.let { char ->
        list.add(char)
    }
}


class Node(val char: Char?) {
    var left: Node? = null
    var right: Node? = null

}

fun getParentNode(): Node {
    val parentNode = Node(null)

    // First level
    val e = Node('E')
    val t = Node('T')

    parentNode.left = e
    parentNode.right = t

    // Second level
    val i = Node('I')
    val a = Node('A')
    e.left = i
    e.right = a

    val n = Node('N')
    val m = Node('M')
    t.left = n
    t.right = m

    // Third level
    val s = Node('S')
    val u = Node('U')
    i.left = s
    i.right = u

    val r = Node('R')
    val w = Node('W')
    a.left = r
    a.right = w

    val d = Node('D')
    val k = Node('K')
    n.left = d
    n.right = k

    val g = Node('G')
    val o = Node('O')
    m.left = g
    m.right = o

    // Forth Level
    val h = Node('H')
    val v = Node('V')
    s.left = h
    s.right = v

    val f = Node('F')
    val u1 = Node('Ü')
    u.left = f
    u.right = u1

    val l = Node('L')
    val a1 = Node('Ä')
    r.left = l
    r.right = a1

    val p = Node('P')
    val j = Node('J')
    w.left = p
    w.right = j

    val b = Node('B')
    val x = Node('X')
    d.left = b
    d.right = x

    val c = Node('C')
    val y = Node('Y')
    k.left = c
    k.right = y

    val z = Node('Z')
    val q = Node('Q')
    g.left = z
    g.right = q

    val o1 = Node('Ö')
    val ch = Node('¢')
    o.left  = o1
    o.right = ch
    return parentNode
}
