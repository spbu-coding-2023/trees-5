package nodes

abstract class abstractNode<K: Comparable<K>, V, someNode: abstractNode<K, V, someNode>>(var key:K, var value: V) {
    var leftChild: someNode? = null
    var rightChild: someNode? = null
}