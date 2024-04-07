package nodes

abstract class AbstractNode<K : Comparable<K>, V, someNode : AbstractNode<K, V, someNode>>(val key:K, var value: V) {
    var leftChild: someNode? = null
    var rightChild: someNode? = null
}