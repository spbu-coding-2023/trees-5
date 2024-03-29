package nodes

abstract class abstractNode<K: Comparable<K>, V, someNode: abstractNode<K, V, someNode>>(val key:K, var value: V) {
    var leftChild: someNode? = null
    var rightChild: someNode? = null
}