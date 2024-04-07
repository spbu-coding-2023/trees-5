package nodes

class AVLNode<K : Comparable<K>, V>(key: K, value: V): AbstractNode<K, V, AVLNode<K, V>>(key, value) {
    var height = 1
}